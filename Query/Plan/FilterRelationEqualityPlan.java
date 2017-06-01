package Query.Plan;

import Query.Engine.QueryIndexer;
import Query.Entities.PlanTable;
import Utility.Equality;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuche on 5/29/17.
 *
 */
public class FilterRelationEqualityPlan extends Plan {
    //TODO: Filter relations with equality "!="
    private List<Equality> eqList;

    public FilterRelationEqualityPlan(QueryIndexer queryIndexer, List<Equality> equalityList, PlanTable table) {
        super(queryIndexer);
        this.eqList = equalityList;
        //Calculate the cost for hash equality test for properties.
        this.estimatedSize = table.estimatedSize;
        for(Equality equality : eqList){
            if(equality.equality.equals("==")){
                this.estimatedSize /= indexer.getNumberOfRelations();
            }
        }

    }

    @Override
    public void applyTo(PlanTable table) {
        table.cost += table.estimatedSize * eqList.size();
        table.estimatedSize = this.estimatedSize;
        table.plans.add(this);
        super.applyTo(table);
    }

    @Override
    public String getParams() {
        List<String> strings = new ArrayList<>();
        eqList.forEach(equality -> strings.add(equality.toString()));
        return String.join(" AND ", strings);
    }

    @Override
    public String getVariable() {
        return super.getVariable();
    }

    @Override
    public String getName() {
        return "FilterRelationEquality";
    }

    public List<Equality> getEquality(){
        return this.eqList;
    }
}
