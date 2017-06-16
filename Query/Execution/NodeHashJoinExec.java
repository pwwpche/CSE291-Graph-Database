package Query.Execution;

import Entity.Equality;
import Query.Plan.NodeHashJoinPlan;
import Query.Plan.Plan;
import Utility.DBUtil;

import java.util.*;

/**
 * Created by liuche on 6/12/17.
 */
public class NodeHashJoinExec extends Execution {
    public NodeHashJoinExec(DBUtil util, Plan plan) {
        super(util, plan);
        this.operandCount = 2;
    }


    private void updateHash(List<Integer> hashList, ResultTable table, String key) {
        assert hashList.size() == table.rows();
        List<List<String>> tableContent = table.getTable();
        Integer index = table.getKeyIdxMap().get(key);
        for (int i = 0, size = hashList.size(); i < size; i++) {
            hashList.set(i, hashList.get(i) * tableContent.get(i).get(index).hashCode());
        }
    }

    private void updateHash(List<Integer> hashList, List<String> table){
        assert hashList.size() == table.size();
        for(int i = 0 , size = table.size() ; i < size ; i++){
            hashList.set(i, hashList.get(i) * table.get(i).hashCode());
        }
    }

    @Override
    public ResultTable execute(ResultTable table1, ResultTable table2) {
        exeUtil.startRecording();
        List<Equality> eqList = ((NodeHashJoinPlan) plan).getEquality();


        // First get intersection among tables.
        Set<String> joinKeys = new HashSet<>(table1.getKeys());
        joinKeys.retainAll(new HashSet<>(table2.getKeys()));
        List<String> keyList = new ArrayList<>(joinKeys);

        // Calculate hash.
        int size1 = table1.rows(), size2 = table2.rows();
        List<Integer> tableHash1 = new ArrayList<>(size1);
        for (int i = 0; i < size1; i++) {
            tableHash1.add(1);
        }

        List<Integer> tableHash2 = new ArrayList<>(size2);
        for (int i = 0; i < size2; i++) {
            tableHash2.add(1);
        }

        for (String key : keyList) {
            updateHash(tableHash1, table1, key);
            updateHash(tableHash2, table2, key);
        }

        // Add additional intersection by explicit equality.
        Set<String> keySet1 = table1.getKeyIdxMap().keySet();
        for (Equality equality : eqList) {
            assert (table1.getKeys().contains(equality.var1) && table2.getKeys().contains(equality.var2)) ||
                    (table1.getKeys().contains(equality.var2) && table2.getKeys().contains(equality.var1));
            // var1 property1 equality var2 property2
            assert equality.equality.equals("==");
            // t1.var1 = t2.var2
            Equality eq = new Equality(equality);
            if (!keySet1.contains(eq.var1)) {
                eq.swap();
            }

            String key1 = eq.var1, key2 = eq.var2;
            List<String> t1 = table1.getAll(key1);
            List<String> t2 = table2.getAll(key2);
            if (!"".equals(eq.property1)) {
                t1 = exeUtil.getPropertyByGids(eq.property1, t1);
                t2 = exeUtil.getPropertyByGids(eq.property2, t2);
            }
            updateHash(tableHash1, t1);
            updateHash(tableHash2, t2);


        }


        //TODO: Hash Conflict
//        List<String> additionalFields = table2.getAdditionalFields(keyList);
//
//        List<ResultTable.ObjectType> additionalTypes = table2.getAdditionalTypes(keyList);
//        Map<String, List<List<String>>> joinValues = table2.generateJoinMap(keyList);


//        table1.hashJoin(keyList, additionalFields, additionalTypes, joinValues);

        List<String> additionalFields = table2.getAdditionalFields(keyList);
        List<ResultTable.ObjectType> additionalTypes = table2.getAdditionalTypes(keyList);

        //List<List<String>> joinValues = table2.getTableByKeys(additionalFields);

        table1.hashJoin(tableHash1, tableHash2, additionalFields, additionalTypes, table2);
        this.querySQL = exeUtil.getHistory();
        return table1;
    }
}
