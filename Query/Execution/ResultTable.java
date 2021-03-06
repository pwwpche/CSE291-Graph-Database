package Query.Execution;

import Entity.Pair;
import Entity.Path;

import java.util.*;

/**
 * Created by liuche on 6/5/17.
 *
 */
public class ResultTable {
    public enum  ObjectType {OBJECT, Constant, PropertyLookup, List, ID, Node_ID, Edge_ID, EMPTY, RangePath}

    private Map<String, Integer> keyToIdx = new HashMap<>();
    private Map<String, ObjectType> keyToType = new HashMap<>();
    private List<List<String>> table = new ArrayList<>();

    // Note that this is only used in the final step.
    // TODO: Generate them in the last step
    private List<List<Object>> finalResult = new ArrayList<>();
    private List<ObjectType> finalType = new ArrayList<>();
    private List<String> finalVars = new ArrayList<>();



    public List<List<Object>> getFinalResult() {
        return finalResult;
    }

    public List<ObjectType> getFinalType() {
        return finalType;
    }

    public void setFinals(List<String> vars, List<ObjectType> finalType, List<List<Object>> resVal){
        this.finalVars = finalVars;
        this.finalType = finalType;
        this.finalResult = resVal;
    }


    public void putAll(String key, ObjectType type, List<String> value){
        keyToIdx.put(key, keyToIdx.size());
        keyToType.put(key, type);
        ((ArrayList)table).ensureCapacity(value.size());
        for(int i = table.size(), valueSize = value.size(); i < valueSize ; i++){
            assert keyToIdx.size() == 1;
            table.add(new ArrayList<>());
        }
        for(int i = 0 , valueSize = value.size(); i < valueSize ; i++){
            table.get(i).add(value.get(i));
        }
    }


    public void shrinkBySet(String key, Set<String> values){
        int index = keyToIdx.get(key);
        List<List<String>> newTable = new ArrayList<>();
        for(List<String> row : table){
            if(values.contains(row.get(index))){
               newTable.add(row);
            }
        }
        table = newTable;
    }

    public void shrinkByEquality(String key1, String key2, String equality){
        int index1 = keyToIdx.get(key1), index2 = keyToIdx.get(key2);
        List<List<String>> newTable = new ArrayList<>();
        for(List<String> row : table){
            switch (equality){
                case "==" :
                    if(row.get(index1).equals(row.get(index2))){
                        newTable.add(row);
                    }
                    break;
                case "!=":
                    if(!row.get(index1).equals(row.get(index2))){
                        newTable.add(row);
                    }
                    break;
                default:
                    break;
            }
        }
        table = newTable;
    }


    public void expand(String startNode, List<String> endKeys,
                       List<ObjectType> keyTypes,
                       Map<String, List<List<String>>> values){

        List<List<String> > newTable = new ArrayList<>();
        for(int i = 0, size = keyTypes.size() ; i < size ; i++){
            String key = endKeys.get(i);
            ObjectType type = keyTypes.get(i);
            keyToIdx.put(key, keyToIdx.size());
            keyToType.put(key, type);
        }
        Integer fromIndex = keyToIdx.get(startNode);

        for(List<String> row : table){
            String targetValue = row.get(fromIndex);
            if(values.containsKey(targetValue)){
                List<List<String>> newRows = new ArrayList<>();

                for(List<String> newVals : values.get(targetValue)){
                    List<String> newRow = new ArrayList<>(row);
                    newRow.addAll(newVals);
                    newRows.add(newRow);
                }
                newTable.addAll(newRows);
            }
        }
        table = newTable;
    }


    public void expandPath(String startNode, String endNode, String relation,
                           Map<String, List<Pair<String, String>>> newPaths){
        List<List<String>> newTable = new ArrayList<>();
        keyToIdx.put(relation, keyToIdx.size());
        keyToIdx.put(endNode, keyToIdx.size());
        keyToType.put(relation, ObjectType.RangePath);
        keyToType.put(endNode, ObjectType.Node_ID);
        Integer fromIndex = keyToIdx.get(startNode);
        for(List<String> row : table){
            String targetValue = row.get(fromIndex);
            if(newPaths.containsKey(targetValue)){
                List<List<String>> newRows = new ArrayList<>();

                for(Pair<String, String> path: newPaths.get(targetValue)){
                    String end = path.getV0();
                    String pathIdx = path.getV1();
                    List<String> newRow = new ArrayList<>(row);
                    newRow.add(pathIdx);
                    newRow.add(end);
                    newRows.add(newRow);
                }
                newTable.addAll(newRows);
            }
        }
        this.table = newTable;
    }


    public void expandIntoPaths(String fromKey1, String fromKey2,
                               String toKey, ObjectType keyType,
                               Map<String, Map<String, List<String>>> values){
        List<List<String>> newTable = new ArrayList<>();
        keyToIdx.put(toKey, keyToIdx.size());
        keyToType.put(toKey, keyType);
        Integer fromIdx1 = keyToIdx.get(fromKey1), fromIdx2 = keyToIdx.get(fromKey2);
        for(List<String> row : table){
            String val1 = row.get(fromIdx1), val2 = row.get(fromIdx2);
            if(values.containsKey(val1) && values.get(val1).containsKey(val2)){
                List<String> val = values.get(val1).get(val2);
                for(String v: val){
                    List<String> newRow = new ArrayList<>(row);
                    newRow.add(v);
                    newTable.add(newRow);
                }
            }
        }
        table = newTable;
    }



    public void expandIntoList(String fromKey1, String fromKey2,
                               String toKey, ObjectType keyType,
                               Map<String, Map<String, List<String>>> values){
        List<List<String>> newTable = new ArrayList<>();
        keyToIdx.put(toKey, keyToIdx.size());
        keyToType.put(toKey, keyType);
        Integer fromIdx1 = keyToIdx.get(fromKey1), fromIdx2 = keyToIdx.get(fromKey2);
        for(List<String> row : table){
            String val1 = row.get(fromIdx1), val2 = row.get(fromIdx2);
            if(values.containsKey(val1) && values.get(val1).containsKey(val2)){
                List<String> val = values.get(val1).get(val2);
                for(String v: val){
                    List<String> newRow = new ArrayList<>(row);
                    newRow.add(v);
                    newTable.add(newRow);
                }
            }
        }
        table = newTable;
    }

    public List<String> getAll(String key){
        List<String> result = new ArrayList<>(cols());
        Integer keyIndex = keyToIdx.get(key);
        table.forEach(line -> result.add(line.get(keyIndex)));
        return result;
    }


    public int rows(){
       return this.table.size();
    }

    public int cols(){
        if(table.size() == 0){
            return 0;
        }
        return table.get(0).size();
    }

    public List<String> getKeys(){
        List<String> keys = new ArrayList<>();
        for(int i = 0, size = cols() ; i < size ; i++){
            for(String key : keyToIdx.keySet()){
                if(keyToIdx.get(key).equals(i)){
                    keys.add(key);
                    break;
                }
            }
        }
        return keys;
    }

    public Map<String, Integer> getKeyIdxMap(){
        return this.keyToIdx;
    }

    public Map<String, ObjectType> getKeyToType(){
        return this.keyToType;
    }

    public List<List<String>> getTable(){
        return this.table;
    }


    public Map<String, List<List<String>> > generateJoinMap(List<String> keys){

        Map<String, List<List<String>>> result = new HashMap<>();
        List<Integer> keyIdx = new ArrayList<>();
        keys.forEach(key -> keyIdx.add(keyToIdx.get(key)));
        List<Integer> valueIdx = new ArrayList<>();

        List<String> valueFields = getKeys();
        valueFields.removeIf(keys::contains);
        valueFields.forEach(key -> valueIdx.add(keyToIdx.get(key)));

        for(List<String> row : table){
            List<String> indexString = new ArrayList<>();
            keyIdx.forEach(idx -> indexString.add(row.get(idx)));

            String hashKey = String.join(", ", indexString);
            // Generate hashes
            if(!result.containsKey(hashKey)){
                result.put(hashKey, new ArrayList<>());
            }
            List<String> valueString = new ArrayList<>();
            valueIdx.forEach(idx -> valueString.add(row.get(idx)));
            result.get(hashKey).add(valueString);
        }
        return result;
    }

    private Map<Integer, List<Integer>> listToMap(List<Integer> hashList){
        Map<Integer, List<Integer>> result = new HashMap<>();
        for(int i = 0, size = hashList.size() ; i < size; i++){
            if(!result.containsKey(hashList.get(i))){
                result.put(hashList.get(i), new ArrayList<>());
            }
            result.get(hashList.get(i)).add(i);
        }
        return  result;
    }
    public void hashJoin(List<Integer> selfHash, List<Integer> otherHash,
                         List<String> addedFields, List<ObjectType> addedTypes,
                         ResultTable otherTable){
        assert selfHash.size() == rows();

        // Insert metadata
        for(int i = 0, size = addedFields.size() ; i < size ; i++){
            String key = addedFields.get(i);
            keyToIdx.put(key, keyToIdx.size());
            keyToType.put(key, addedTypes.get(i));
        }

        // Fetch indices of additional fields in the other table.
        List<List<String>> other = otherTable.getTable();
        List<Integer> otherFieldIdx = new ArrayList<>();
        addedFields.forEach(key-> otherFieldIdx.add(otherTable.getKeyIdxMap().get(key)));

        // Convert hash list to map.
        Map<Integer, List<Integer>> otherHashMap = listToMap(otherHash);
        Set<Integer> otherHashSet = otherHashMap.keySet();

        // Join values.
        List<List<String>> newTable = new ArrayList<>(rows());
        for(int i = 0, size = rows() ; i < size ; i++){
            Integer rowHash = selfHash.get(i);
            List<String> row = table.get(i);
            if(otherHashSet.contains(rowHash)){
                for(Integer otherIdx : otherHashMap.get(rowHash)){
                    List<String> newRow = new ArrayList<>(row);
                    List<String> otherRow = new ArrayList<>();
                    for (Integer fieldIdx : otherFieldIdx) {
                        otherRow.add(other.get(otherIdx).get(fieldIdx));
                    }
                    newRow.addAll(otherRow);
                    newTable.add(newRow);
                }
            }
        }

        table = newTable;
    }

    public List<String> getAdditionalFields(List<String> keys){
        List<String> valueFields = getKeys();
        valueFields.removeIf(keys::contains);
        return valueFields;
    }

    public List<ObjectType> getAdditionalTypes(List<String> keys){
        boolean hasJoinPath = false;
        for(String key : keys){
            hasJoinPath = hasJoinPath || keyToType.get(key).equals(ObjectType.RangePath);
        }
        assert !hasJoinPath;

        List<String> valueFields = getKeys();
        valueFields.removeIf(keys::contains);
        List<ObjectType> valueTypes = new ArrayList<>();
        valueFields.forEach(field -> valueTypes.add(keyToType.get(field)));
        return valueTypes;
    }

    public void hashJoin(List<String> joinParams, List<String> addedFields, List<ObjectType> addedTypes,
                         Map<String, List<List<String>>> addedValues){

        List<Integer> keyIdx = new ArrayList<>();
        joinParams.forEach(key -> keyIdx.add(keyToIdx.get(key)));

        // Find if added fields contain path.


        // Insert metadata
        for(int i = 0, size = addedFields.size() ; i < size ; i++){
            String key = addedFields.get(i);
            keyToIdx.put(key, keyToIdx.size());
            keyToType.put(key, addedTypes.get(i));
        }

        // Insert data
        List<List<String>> newTable = new ArrayList<>();
        for(List<String> row : table) {
            List<String> indexString = new ArrayList<>();

            keyIdx.forEach(idx -> indexString.add(row.get(idx)));
            String hashKey = String.join(", ", indexString);

            if(addedValues.containsKey(hashKey)){
                List<List<String>> newVals = addedValues.get(hashKey);
                for(List<String> appendVals : newVals){
                    List<String> newRow = new ArrayList<>(row);
                    newRow.addAll(appendVals);
                    newTable.add(newRow);
                }
            }
        }
        this.table = newTable;
    }

    public void cartesianJoin(ResultTable other){
        other.getKeys().forEach(key -> keyToIdx.put(key, keyToIdx.size()));
        other.getKeyToType().forEach((k, v) -> keyToType.put(k, v));

        List<List<String>> newTable = new ArrayList<>(cols() * other.cols());
        for(List<String> row : table){
            for(List<String> otherRow : other.getTable()){
                row.addAll(otherRow);
            }
            newTable.add(row);
        }
        this.table = newTable;
    }

    @Override
    public String toString(){

        StringBuilder result = new StringBuilder();
        if(finalVars == null){
            List<String> keys = getKeys();
            result.append(String.join("\t", keys)).append("\n");
            for(List<String> row : table){
                result.append(String.join("\t", row));
                result.append("\n");
            }

        }else{
            for (String finalVar : finalVars) {
                result.append(finalVar).append("\t");
            }
            for(List<Object> row : finalResult){
                for(Object col : row){
                    result.append(col.toString()).append("\t");
                }
                result.append("\n");
            }
        }
        return result.toString();
    }

}
