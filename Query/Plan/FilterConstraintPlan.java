package Query.Plan;

import Query.QueryIndexer;
import Utility.Constraint;
import Utility.Value;

import java.util.List;

/**
 * Created by liuche on 5/29/17.
 */
public class    FilterConstraintPlan extends Plan {
    Constraint varConstraint;

    public FilterConstraintPlan(QueryIndexer queryIndexer, String var, Constraint constraint,PlanTable table) {
        super(queryIndexer);
        this.variable = var;
        this.varConstraint = constraint;
        estimatedSize = table.estimatedSize;
        switch (constraint.name){
            case "nodeLabels":
                List<String> labels = ((List<String>)constraint.value);
                for(String label : labels){
                    estimatedSize /= queryIndexer.getNodesWithLabel(label);
                }
                break;
            case "id":
                estimatedSize = 1;
                break;
            default:
                Value val = constraint.value;
                //TODO: Add supply for other equality types.
                assert val.type.equals("String");
                assert constraint.equality.equals("==");
                String property = constraint.name;
                this.estimatedSize /= indexer.getNodesWithProperty(property);
                break;
        }

    }

    @Override
    public void applyTo(PlanTable table) {
        table.estimatedSize = estimatedSize;
        table.plans.add(this);
    }

    @Override
    public String getParams() {
        return varConstraint.toString();
    }

    @Override
    public String getName() {
        return "FilterConstraintPlan";
    }
}
