package Query.Execution;

import Query.Plan.Plan;
import Query.Plan.ScanByLabelPlan;
import Utility.DBUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by liuche on 6/5/17.
 */
public class ScanByLabelExec extends Execution {
    public ScanByLabelExec(DBUtil util, Plan plan) {
        super(util, plan);
        this.operandCount = 0;
    }

    @Override
    public ResultTable execute() {
        this.exeUtil.startRecording();
        ResultTable table = new ResultTable();
        List<String> labels = ((ScanByLabelPlan)plan).getLabels();
        String var = plan.getVariable();
        Set<String> gids = new HashSet<>();
        boolean first = true;
        for(String label : labels){
            if(first){
                gids.addAll(exeUtil.getNodeByLabel(label));
                first = false;
                continue;
            }
            gids.retainAll(exeUtil.getNodeByLabel(label));
        }
        table.putAll(var, ResultTable.ObjectType.Node_ID, new ArrayList<>(gids));
        querySQL = exeUtil.getHistory();
        return table;
    }
}
