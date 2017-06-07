package Query.Plan;

import Query.Engine.QueryIndexer;
import Query.Entities.PlanTable;

/**
 * Created by liuche on 5/29/17.
 */
public class Plan {
    protected QueryIndexer indexer;
    protected double estimatedSize = 0;
    protected String variable = "";

    public QueryIndexer getIndexer(){
        return indexer;
    }

    public Plan(QueryIndexer queryIndexer) {
        this.indexer = queryIndexer;
    }

    public double estimateSize(){
        return estimatedSize;
    }

    public void applyTo(PlanTable table){
    }

    public String getParams(){
        return "";
    }

    public String getVariable(){
        return variable;
    }

    public String getName(){
        return "BasePlan";
    }
}
