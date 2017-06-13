package Query.Plan;

import Entity.Pair;
import Query.Engine.QueryIndexer;
import Query.Entities.PlanTable;

import java.util.*;

/**
 * Created by liuche on 6/5/17.
 */
public class ProduceResultPlan extends Plan {
    private Map<String, String> projection = new HashMap<>();
    private Set<String> varSet = new HashSet<>();
    private List<Pair<String, String>> retList = new ArrayList<>();

    public ProduceResultPlan(QueryIndexer queryIndexer, PlanTable table, List<Pair<String, String>> retList) {
        super(queryIndexer);
        this.varSet = table.nodes;
        this.retList = retList;
        retList.forEach(kvPair -> {
            String var = kvPair.getV0();
            String prop = kvPair.getV1();
            String nodeStr = var + (prop.equals("") ? "" : "." + prop);
            if(var.equals("Constant")){
                this.projection.put(var, var);
            }else{
                if(varSet.contains(var)){
                    this.projection.put(var, nodeStr);
                }
            }
        });
    }

    public List<Pair<String, String>> getRetList(){
        return retList;
    }

    @Override
    public void applyTo(PlanTable table) {
        super.applyTo(table);
        table.nodes = this.projection.keySet();
        table.plans.add(this);
    }


    @Override
    public String getParams() {
        List<String> res = new ArrayList<>();
        for(String key : projection.keySet()){
            res.add(key + ":" + projection.get(key));
        }
        return String.join(" ", res);
    }

    @Override
    public String getVariable() {
        return String.join(",", projection.keySet());
    }

    @Override
    public String getName() {
        return "ProduceResult";
    }
}
