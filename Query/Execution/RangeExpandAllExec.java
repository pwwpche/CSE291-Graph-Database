package Query.Execution;

import Entity.Pair;
import Entity.QueryConstraints;
import Query.Entities.RelationEdge;
import Query.Plan.Plan;
import Query.Plan.RangeExpandAllPlan;
import Utility.DBUtil;

import java.util.*;

/**
 * Created by liuche on 6/12/17.
 */
public class RangeExpandAllExec extends Execution {
    private List<RangePath> pathMem;

    public RangeExpandAllExec(DBUtil util, Plan plan, List<RangePath> pathMem) {
        super(util, plan);
        this.operandCount = 1;
        this.pathMem = pathMem;
    }

    @Override
    public ResultTable execute(ResultTable table) {
        exeUtil.startRecording();

        // Filter out valid relations
        QueryConstraints relationConstraints = ((RangeExpandAllPlan)plan).getRelationConstraint();
        QueryConstraints nodeConstraints = ((RangeExpandAllPlan)plan).getNodeConstraints();
        RelationEdge edge = ((RangeExpandAllPlan)plan).getRelationEdge();

        List<String> relationTypes = relationConstraints.getEdgeLabels();
        List<String> nodeLabels = nodeConstraints.getNodeLabels();
        Map<String, String> nodeProperties = nodeConstraints.getNodeProperties();
        String rangeEdgeName = edge.name;
        String edgeDirection = edge.direction.equals("--") ? "<-->" : edge.direction;
        String expandedNode = ((RangeExpandAllPlan)plan).getExpandedNode();
        String insideNode = edge.start.equals(expandedNode) ? edge.end : edge.start;


        Set<String> startNodes = new HashSet<>(table.getAll(insideNode));
        Integer rangeStart = ((RangeExpandAllPlan)plan).getRange().getV0();
        Integer rangeEnd = ((RangeExpandAllPlan)plan).getRange().getV1();


        Map<String, List<Pair<String, String>>> resultPaths = new HashMap<>();
        Map<String, Queue<RangePath>> nodePaths = new HashMap<>();


        // |a|-[r]->b   a<-[r]-|b|  :: getOutingEdge
        if(insideNode.equals(edge.start) && edgeDirection.contains("-->") ||
                insideNode.equals(edge.end) && edgeDirection.contains("<--")
                ){
            for (String startNode : startNodes) {
                Queue<RangePath> initPath = new LinkedList<>();
                initPath.add(new RangePath(RangePath.Direction.FROM, startNode));
                nodePaths.put(startNode, initPath);
                resultPaths.put(startNode, new ArrayList<>());
            }
            for(int i = 0 ; i <= rangeEnd ; i++){
                if(i >= rangeStart && i <= rangeEnd){
                    for(String startNode : nodePaths.keySet()){
                        for(RangePath path : nodePaths.get(startNode)){
                            String endNode = path.backNode();
                            if(exeUtil.checkNode(endNode, nodeProperties, nodeLabels)){
                                String pathIdx = String.valueOf(pathMem.size());
                                pathMem.add(path);
                                resultPaths.get(startNode).add(Pair.mkPair(endNode, pathIdx));
                            }
                        }
                    }

                }
                boolean done = true;
                for(String key : nodePaths.keySet()){
                    Queue<RangePath> q = nodePaths.get(key);
                    int index = q.size();
                    done = done && (index == 0);
                    while(index > 0){
                        index--;
                        RangePath path = q.remove();
                        String lastNode = path.backNode();
                        //TODO: Add a cache here.
                        List<List<String>> rows = exeUtil.getEdgeEndByStart(lastNode, relationTypes);
                        for(List<String> row : rows){
                            String nextEdge = row.get(0);
                            String nextNode = row.get(1);
                            RangePath newPath = new RangePath(path);
                            newPath.put(nextNode, nextEdge);
                            q.add(newPath);
                        }
                    }
                }
                if(done){
                    break;
                }
            }
        }

        // |a|<-[r]-b   a->r->|b|   :: getIncomingEdge
        if(insideNode.equals(edge.start) && edgeDirection.contains("<--") ||
                insideNode.equals(edge.end) && edgeDirection.contains("-->")){

            nodePaths = new HashMap<>();
            for (String startNode : startNodes) {
                Queue<RangePath> initPath = new LinkedList<>();
                initPath.add(new RangePath(RangePath.Direction.TO, startNode));
                nodePaths.put(startNode, initPath);
            }

            for(int i = 0 ; i <= rangeEnd ; i++){
                if(i >= rangeStart && i <= rangeEnd){
                    for(String startNode : nodePaths.keySet()){
                        for(RangePath path : nodePaths.get(startNode)){
                            String endNode = path.backNode();
                            if(exeUtil.checkNode(endNode, nodeProperties, nodeLabels)){
                                String pathIdx = String.valueOf(pathMem.size());
                                pathMem.add(path);
                                resultPaths.get(startNode).add(Pair.mkPair(endNode, pathIdx));
                            }
                        }
                    }
                }
                boolean done = true;
                for(String key : nodePaths.keySet()){
                    Queue<RangePath> q = nodePaths.get(key);
                    int index = q.size();
                    done = done && (index == 0);
                    while(index > 0){
                        index--;
                        RangePath path = q.remove();
                        String lastNode = path.backNode();
                        //TODO: Add a cache here.
                        List<List<String>> rows = exeUtil.getEdgeStartByEnd(lastNode, relationTypes);
                        for(List<String> row : rows){
                            RangePath newPath = new RangePath(path);
                            newPath.put(row.get(0), row.get(1));
                            q.add(newPath);
                        }
                    }
                }
                if(done){
                    break;
                }
            }

        }

        table.expandPath(insideNode, expandedNode, rangeEdgeName, resultPaths);

        this.querySQL = exeUtil.getHistory();
        return table;
    }
}
