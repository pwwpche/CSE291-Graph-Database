package Query.Execution;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuche on 6/5/17.
 */
public class ResultTable {
    public static final String GID = "gid";
    public enum  ObjectType {OBJECT, STRING, LIST, ID}

    public Map<String, List<String>> idTable = new HashMap<>();
    public Map<String, List<Object>> objTable = new HashMap<>();
    ObjectType type = ObjectType.ID;


}
