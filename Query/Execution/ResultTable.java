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
        for(List<String> list : table){
            if(values.contains(list.get(index))){
               newTable.add(list);
            }
        }
        table = newTable;
    }

    public List<String> getAll(String key){
        List<String> result = new ArrayList<>();
        table.forEach(list -> result.add(list.get(keyToIdx.get(key))));
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
