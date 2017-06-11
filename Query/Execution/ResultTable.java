package Query.Execution;

import java.util.*;

/**
 * Created by liuche on 6/5/17.
 *
 */
public class ResultTable {
    public enum  ObjectType {OBJECT, STRING, LIST, ID, NODE, RELATIONSHIP}

    private Map<String, Integer> keyToIdx = new HashMap<>();
    private List<List<String>> table = new ArrayList<>();
    // Note that this is only used in the final step.
    // TODO: Generate them in the last step
    private Map<String, List<Object>> objTable = new HashMap<>();
    private Map<String, ObjectType> objType = new HashMap<>();

    public void putAll(String key, List<String> value){
        keyToIdx.put(key, keyToIdx.size());
        for(int i = table.size(), valueSize = value.size(); i < valueSize ; i++){
            assert keyToIdx.size() == 1;
            table.add(new ArrayList<>());
        }
        for(int i = 0 , valueSize = value.size(); i < valueSize ; i++){
            table.get(i).add(value.get(i));
        }
    }

    public void shrink(String key, Set<String> values){
        int index = keyToIdx.get(key);
        List<List<String>> newTable = new ArrayList<>();
        for(List<String> row : table){
            if(values.contains(row.get(index))){
               newTable.add(row);
            }
        }
        table = newTable;
    }

    public void expand(String fromKey, String toKey, Map<String, List<String>> values){
        List<List<String> > newTable = new ArrayList<>();
        keyToIdx.put(toKey, keyToIdx.size());
        for(List<String> row : table){
            String targetValue = row.get(keyToIdx.get(fromKey));
            if(values.containsKey(targetValue)){
                List<List<String>> newRows = new ArrayList<>();
                for(String val : values.get(targetValue)){
                    List<String> newRow = new ArrayList<>(row);
                    newRow.add(val);
                    newRows.add(newRow);
                }
                newTable.addAll(newRows);
            }
        }
        table = newTable;
    }

    public void expand(String fromKey, List<String> toKey, Map<String, List<List<String>>> values){
        // By default. expand by the first key.
        List<List<String> > newTable = new ArrayList<>();
        keyToIdx.put(toKey.get(0), keyToIdx.size());
        for(List<String> row : table){
            String targetValue = row.get(keyToIdx.get(fromKey));
            if(values.containsKey(targetValue)){
                List<List<String>> newRows = new ArrayList<>();
                for(List<String> val : values.get(targetValue)){
                    List<String> newRow = new ArrayList<>(row);
                    newRow.addAll(val);
                    newRows.add(newRow);
                }
                newTable.addAll(newRows);
            }
        }
        table = newTable;
    }

    public void expandMulti(String fromKey1, String fromKey2, String toKey, Map<String, Map<String, String>> values){
        List<List<String>> newTable = new ArrayList<>();
        keyToIdx.put(toKey, keyToIdx.size());
        Integer fromIdx1 = keyToIdx.get(fromKey1), fromIdx2 = keyToIdx.get(fromKey2);
        for(List<String> row : table){
            String val1 = row.get(fromIdx1), val2 = row.get(fromIdx2);
            if(values.containsKey(val1) && values.get(val1).containsKey(val2)){
                String val = values.get(val1).get(val2);
                row.add(val);
                newTable.add(row);
            }
        }
        table = newTable;
    }

    public List<String> getAll(String key){
        List<String> result = new ArrayList<>();
        table.forEach(line -> result.add(line.get(keyToIdx.get(key))));
        return result;
    }

    public Object getObject(String key){
        return objTable.get(key);
    }

    public Object getObjectType(String key){
        return objType.get(key);
    }
    public void putObject(String key, List<Object> value, ObjectType objectType){
        objTable.put(key, value);
        objType.put(key, objectType);
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

}
