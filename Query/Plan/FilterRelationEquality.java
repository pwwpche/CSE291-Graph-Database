package Query.Plan;

import Query.QueryIndexer;
import Utility.Equality;

import java.util.List;

/**
 * Created by liuche on 5/29/17.
 */
public class FilterRelationEquality extends Plan {
    public FilterRelationEquality(QueryIndexer queryIndexer, List<Equality> equalityList) {
        super(queryIndexer);
    }

    @Override
    public void applyTo(PlanTable table) {
        super.applyTo(table);
    }

    @Override
    public String getParams() {
        return super.getParams();
    }

    @Override
    public String getVariable() {
        return super.getVariable();
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
