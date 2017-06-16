package Query.Execution;

import Entity.QueryConstraints;
import Query.Entities.RelationEdge;
import Query.Plan.Plan;
import Query.Plan.RangeExpandIntoPlan;
import Utility.DBUtil;

import java.util.*;

/**
 * Created by liuche on 6/12/17.
 *
 */
public class RangeExpandIntoExec extends Execution {
    private List<RangePath> pathMem;
    public RangeExpandIntoExec(DBUtil util, Plan plan, List<RangePath> pathMem) {
        super(util, plan);
        this.operandCount = 1;
        this.pathMem = pathMem;
    }

    @Override
    public ResultTable execute(ResultTable table) {
        QueryConstraints relationConstraints = ((RangeExpandIntoPlan)plan).getRelationConstraint();
        RelationEdge edge = ((RangeExpandIntoPlan)plan).getRelationEdge();
        List<String> relationTypes = relationConstraints.getEdgeLabels();

        Integer rangeStart = ((RangeExpandIntoPlan)plan).getRange().getV0();
        Integer rangeEnd = ((RangeExpandIntoPlan)plan).getRange().getV1();
        String edgeDirection = edge.direction.equals("--") ? "<-->" : edge.direction;

        Set<String> startNodes = new HashSet<>(table.getAll(edge.start));
        Set<String> endNodes = new HashSet<>(table.getAll(edge.end));
        Map<String, Map<String, List<String>>> resultPaths = new HashMap<>();
        Map<String, Queue<RangePath>> nodePaths = new HashMap<>();



        if(edgeDirection.contains("-->")){
            for (String startNode : startNodes) {
                Queue<RangePath> initPath = new LinkedList<>();
                initPath.add(new RangePath(RangePath.Direction.FROM, startNode));
                nodePaths.put(startNode, initPath);
            }
            for(int i = 0 ; i <= rangeEnd ; i++){
                if(i >= rangeStart && i <= rangeEnd){
                    for(String startNode : nodePaths.keySet()){
                        for(RangePath path : nodePaths.get(startNode)){
                            String endNode = path.backNode();
                            if(endNodes.contains(endNode)){
                                if(!resultPaths.containsKey(startNode)){
                                    resultPaths.put(startNode, new HashMap<>());
                                }
                                if(!resultPaths.get(startNode).containsKey(endNode)){
                                    resultPaths.get(startNode).put(endNode, new ArrayList<>());
                                }
                                String pathIdx = String.valueOf(pathMem.size());
                                pathMem.add(path);
                                resultPaths.get(startNode).get(endNode).add(pathIdx);
                            }
                        }
                    }
                }
                if(nodePaths.size() == 0){
                    break;
                }
                List<String> removed = new ArrayList<>();
                for(String key : nodePaths.keySet()){
                    Queue<RangePath> q = nodePaths.get(key);
                    int index = q.size();
                    if(index == 0){
                        removed.add(key);
                    }
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
                for(String key : removed){
                    nodePaths.remove(key);
                }

            }
        }

        if(edgeDirection.contains("<--")){
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
                            if(endNodes.contains(endNode)){
                                if(!resultPaths.containsKey(startNode)){
                                    resultPaths.put(startNode, new HashMap<>());
                                }
                                if(!resultPaths.get(startNode).containsKey(endNode)){
                                    resultPaths.get(startNode).put(endNode, new ArrayList<>());
                                }
                                String pathIdx = String.valueOf(pathMem.size());
                                pathMem.add(path);
                                resultPaths.get(startNode).get(endNode).add(pathIdx);
                            }
                        }
                    }
                }
                if(nodePaths.size() == 0){
                    break;
                }
                List<String> removed = new ArrayList<>();
                for(String key : nodePaths.keySet()){
                    Queue<RangePath> q = nodePaths.get(key);
                    int index = q.size();
                    if(index == 0){
                        removed.add(key);
                    }
                    while(index > 0){
                        index--;
                        RangePath path = q.remove();
                        String lastNode = path.backNode();
                        //TODO: Add a cache here.
                        List<List<String>> rows = exeUtil.getEdgeStartByEnd(lastNode, relationTypes);
                        for(List<String> row : rows){
                            String nextEdge = row.get(0);
                            String nextNode = row.get(1);
                            RangePath newPath = new RangePath(path);
                            newPath.put(nextNode, nextEdge);
                            q.add(newPath);
                        }
                    }
                }
                for(String key : removed){
                    nodePaths.remove(key);
                }
            }
        }

        table.expandIntoPaths(edge.start, edge.end, edge.name, ResultTable.ObjectType.RangePath, resultPaths);
        this.querySQL = exeUtil.getHistory();
        return table;
    }
}
