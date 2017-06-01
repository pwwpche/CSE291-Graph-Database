package Query.Plan;

import Query.Engine.QueryIndexer;
import Query.Entities.PlanTable;
import Utility.Constraint;
import Utility.Value;

import java.util.List;

/**
 * Created by liuche on 5/29/17.
 *
 */
public class    FilterConstraintPlan extends Plan {
    private Constraint varConstraint;

    public FilterConstraintPlan(QueryIndexer queryIndexer, String var, Constraint constraint,PlanTable table) {
        super(queryIndexer);
        this.variable = var;
        this.varConstraint = constraint;
        estimatedSize = table.estimatedSize;
        switch (constraint.name){
            case "nodeLabels":
                assert constraint.value.type.contains("List");
                List<String> labels = (List<String>)(constraint.value.val);
                for(String label : labels){
                    estimatedSize = estimatedSize * (queryIndexer.getNodesWithLabel(label) * 1.0 / queryIndexer.getNumberOfNode());
                }
                break;
            case "id":
                estimatedSize = 1;
                break;
            default:
                Value val = constraint.value;
                //TODO: Add supply for other equality types.
                assert val.type.equals("String");

                if(constraint.equality.equals("==")){
                    String property = constraint.name;
                    this.estimatedSize /= indexer.getNodesWithProperty(property) ;
                }

                break;
        }

    }

    @Override
    public void applyTo(PlanTable table) {
        table.cost += table.estimatedSize;
        table.estimatedSize = estimatedSize;
        table.plans.add(this);
    }

    @Override
    public String getParams() {
        return varConstraint.toString();
    }

    @Override
    public String getName() {
        return "FilterConstraint";
    }
}
