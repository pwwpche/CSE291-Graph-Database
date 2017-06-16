package Query.Execution;

import java.util.*;

/**
 * Created by liuche on 6/15/17.
 */
public class ResultTableCol {
    public enum  ObjectType {OBJECT, Constant, PropertyLookup, List, ID, Node_ID, Edge_ID, EMPTY, RangePath}

    private Map<String, Integer> keyToIdx = new HashMap<>();
    private Map<String, ResultTable.ObjectType> keyToType = new HashMap<>();
    private List<List<String>> colTable = new ArrayList<>();

    //Range paths are represented by IDs in colTable.
    private List<RangePath> rangePaths = new ArrayList<>();

    // Note that this is only used in the final step.
    // TODO: Generate them in the last step
    private List<List<Object>> finalResult = new ArrayList<>();
    private List<ResultTable.ObjectType> finalType = new ArrayList<>();
    private List<String> finalVars = new ArrayList<>();

    public List<RangePath> getRangePaths() {
        return rangePaths;
    }

    public List<List<Object>> getFinalResult() {
        return finalResult;
    }

    public List<ResultTable.ObjectType> getFinalType() {
        return finalType;
    }

    public void setFinals(List<String> vars, List<ResultTable.ObjectType> finalType, List<List<Object>> resVal){
        this.finalVars = finalVars;
        this.finalType = finalType;
        this.finalResult = resVal;
    }


    public void putAll(String key, ResultTable.ObjectType type, List<String> value){
        keyToIdx.put(key, keyToIdx.size());
        keyToType.put(key, type);
        colTable.add(value);
    }


    public void shrinkBySet(String key, Set<String> values){
        List<String> targetCol = colTable.get(keyToIdx.get(key));
        List<List<String>> newTable = new ArrayList<>();
        colTable.forEach(col -> newTable.add(new ArrayList<>()));
        for(int i = 0 , size = cols(); i < size ; i++){
            if(values.contains(targetCol.get(i))){
                for(int j = 0, size2 = rows() ; j < size2 ; j++){
                    newTable.get(j).add(colTable.get(j).get(i));
                }
            }
        }

        colTable = newTable;
    }

    public void shrinkByEquality(String key1, String key2, String equality){
        int index1 = keyToIdx.get(key1), index2 = keyToIdx.get(key2);
        List<List<String>> newTable = new ArrayList<>();
        for(List<String> row : colTable){
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
        colTable = newTable;
    }


    public void expand(String startNode, List<String> endKeys,
                       List<ResultTable.ObjectType> keyTypes,
                       Map<String, List<List<String>>> values){

        List<List<String> > newTable = new ArrayList<>();
        for(int i = 0, size = keyTypes.size() ; i < size ; i++){
            String key = endKeys.get(i);
            ResultTable.ObjectType type = keyTypes.get(i);
            keyToIdx.put(key, keyToIdx.size());
            keyToType.put(key, type);
        }
        Integer fromIndex = keyToIdx.get(startNode);

        for(List<String> row : colTable){
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
        colTable = newTable;
    }


    public void expandPath(String startNode, String endNode, String relation,
                           Map<String, List<RangePath>> newPaths){
        List<List<String>> newTable = new ArrayList<>();
        keyToIdx.put(relation, keyToIdx.size());
        keyToIdx.put(endNode, keyToIdx.size());
        keyToType.put(relation, ResultTable.ObjectType.RangePath);
        keyToType.put(endNode, ResultTable.ObjectType.Node_ID);
        Integer fromIndex = keyToIdx.get(startNode);
        for(List<String> row : colTable){
            String targetValue = row.get(fromIndex);
            if(newPaths.containsKey(targetValue)){
                List<List<String>> newRows = new ArrayList<>();

                for(RangePath path: newPaths.get(targetValue)){
                    String end = path.backNode();
                    String pathIdx = String.valueOf(newPaths.size());
                    rangePaths.add(path);
                    List<String> newRow = new ArrayList<>(row);
                    newRow.add(pathIdx);
                    newRow.add(end);
                    newRows.add(newRow);
                }
                newTable.addAll(newRows);
            }
        }
        this.colTable = newTable;
    }


    public void expandIntoPaths(String fromKey1, String fromKey2,
                                String toKey, ResultTable.ObjectType keyType,
                                Map<String, Map<String, List<RangePath>>> values){
        List<List<String>> newTable = new ArrayList<>();
        keyToIdx.put(toKey, keyToIdx.size());
        keyToType.put(toKey, keyType);
        Integer fromIdx1 = keyToIdx.get(fromKey1), fromIdx2 = keyToIdx.get(fromKey2);
        for(List<String> row : colTable){
            String val1 = row.get(fromIdx1), val2 = row.get(fromIdx2);
            if(values.containsKey(val1) && values.get(val1).containsKey(val2)){
                List<RangePath> paths = values.get(val1).get(val2);
                List<String> val = new ArrayList<>();
                for(RangePath path : paths){
                    val.add(Integer.toString(rangePaths.size()));
                    rangePaths.add(path);
                }
                for(String v: val){
                    List<String> newRow = new ArrayList<>(row);
                    newRow.add(v);
                    newTable.add(newRow);
                }
            }
        }
        colTable = newTable;
    }



    public void expandIntoList(String fromKey1, String fromKey2,
                               String toKey, ResultTable.ObjectType keyType,
                               Map<String, Map<String, List<String>>> values){
        List<List<String>> newTable = new ArrayList<>();
        keyToIdx.put(toKey, keyToIdx.size());
        keyToType.put(toKey, keyType);
        Integer fromIdx1 = keyToIdx.get(fromKey1), fromIdx2 = keyToIdx.get(fromKey2);
        for(List<String> row : colTable){
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
        colTable = newTable;
    }

    public List<String> getAll(String key){
        List<String> result = new ArrayList<>();
        Integer keyIndex = keyToIdx.get(key);
        colTable.forEach(line -> result.add(line.get(keyIndex)));
        return result;
    }


    public int rows(){
        return this.colTable.size();
    }

    public int cols(){
        if(colTable.size() == 0){
            return 0;
        }
        return colTable.get(0).size();
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

    public Map<String, ResultTable.ObjectType> getKeyToType(){
        return this.keyToType;
    }

    public List<List<String>> getTable(){
        return this.colTable;
    }


    public Map<String, List<List<String>> > generateJoinMap(List<String> keys){

        Map<String, List<List<String>>> result = new HashMap<>();
        List<Integer> keyIdx = new ArrayList<>();
        keys.forEach(key -> keyIdx.add(keyToIdx.get(key)));
        List<Integer> valueIdx = new ArrayList<>();

        List<String> valueFields = getKeys();
        valueFields.removeIf(keys::contains);
        valueFields.forEach(key -> valueIdx.add(keyToIdx.get(key)));

        for(List<String> row : colTable){
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

    public List<List<String>> getTableByKeys(List<String> keys){
        List<List<String>> newRows = new ArrayList<>();
        List<Integer> keyIdx = new ArrayList<>();
        keys.forEach(key -> keyIdx.add(keyToIdx.get(key)));
        for(List<String> row : colTable){
            List<String> newRow = new ArrayList<>();
            keyIdx.forEach(idx -> newRow.add(row.get(idx)));
            newRows.add(newRow);
        }
        return newRows;
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
                         List<String> addedFields, List<ResultTable.ObjectType> addedTypes,
                         List<List<String>> addedValues){
        assert selfHash.size() == rows();

        // Insert metadata
        for(int i = 0, size = addedFields.size() ; i < size ; i++){
            String key = addedFields.get(i);
            keyToIdx.put(key, keyToIdx.size());
            keyToType.put(key, addedTypes.get(i));
        }

        Map<Integer, List<Integer>> otherHashMap = listToMap(otherHash);
        Set<Integer> otherHashSet = otherHashMap.keySet();
        List<List<String>> newTable = new ArrayList<>();
        for(int i = 0, size = rows() ; i < size ; i++){
            Integer rowHash = selfHash.get(i);
            List<String> row = colTable.get(i);
            if(otherHashSet.contains(rowHash)){
                for(Integer otherIdx : otherHashMap.get(rowHash)){
                    List<String> newRow = new ArrayList<>(row);
                    newRow.addAll(addedValues.get(otherIdx));
                    newTable.add(newRow);
                }
            }
        }

        colTable = newTable;
    }

    public List<String> getAdditionalFields(List<String> keys){
        List<String> valueFields = getKeys();
        valueFields.removeIf(keys::contains);
        return valueFields;
    }

    public List<ResultTable.ObjectType> getAdditionalTypes(List<String> keys){
        List<String> valueFields = getKeys();
        valueFields.removeIf(keys::contains);
        List<ResultTable.ObjectType> valueTypes = new ArrayList<>();
        valueFields.forEach(field -> valueTypes.add(keyToType.get(field)));
        return valueTypes;
    }

    public void hashJoin(List<String> joinParams, List<String> addedFields, List<ResultTable.ObjectType> addedTypes,
                         Map<String, List<List<String>>> addedValues){

        List<Integer> keyIdx = new ArrayList<>();
        joinParams.forEach(key -> keyIdx.add(keyToIdx.get(key)));

        // Insert metadata
        for(int i = 0, size = addedFields.size() ; i < size ; i++){
            String key = addedFields.get(i);
            keyToIdx.put(key, keyToIdx.size());
            keyToType.put(key, addedTypes.get(i));
        }

        // Insert data
        List<List<String>> newTable = new ArrayList<>();
        for(List<String> row : colTable) {
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
        this.colTable = newTable;
    }

    public void cartesianJoin(ResultTable other){
        other.getKeys().forEach(key -> keyToIdx.put(key, keyToIdx.size()));
        other.getKeyToType().forEach((k, v) -> keyToType.put(k, v));

        List<List<String>> newTable = new ArrayList<>();
        for(List<String> row : colTable){
            for(List<String> otherRow : other.getTable()){
                row.addAll(otherRow);
            }
            newTable.add(row);
        }
        this.colTable = newTable;
    }

    @Override
    public String toString(){

        StringBuilder result = new StringBuilder();
        if(finalVars == null){
            List<String> keys = getKeys();
            result.append(String.join("\t", keys)).append("\n");
            for(List<String> row : colTable){
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
