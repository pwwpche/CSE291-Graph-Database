package Query.Execution;

import Entity.Equality;
import Query.Plan.FilterRelationEqualityPlan;
import Query.Plan.Plan;
import Utility.DBUtil;

import java.util.List;

/**
 * Created by liuche on 6/12/17.
 */
public class FilterRelationEqualityExec extends Execution {
    public FilterRelationEqualityExec(DBUtil util, Plan plan) {
        super(util, plan);
        this.operandCount = 1;
    }

    @Override
    public ResultTable execute(ResultTable table1) {
        exeUtil.startRecording();
        List<Equality> equalities = ((FilterRelationEqualityPlan) plan).getEquality();
        for(Equality equality : equalities){
            String relation1 = equality.var1;
            String relation2 = equality.var2;
            table1.shrinkByEquality(relation1, relation2, equality.equality);
        }
        this.querySQL = exeUtil.getHistory();
        return table1;
    }
}
