package Utility;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by liuche on 6/4/17.
 */
public class DBSchema {
    public String fileName = "";
    public Map<String, String> keyType = new HashMap<>();
    public Map<String, String> keyReference = new HashMap<>();
    // Data structures only used in run()
    public Map<Integer, Set<String>> typeToProperties = new HashMap<>();
    public Set<String> globalPropertiesSet = new HashSet<>();
    public Map<Set<String>, Integer> nodelabelToType = new HashMap<>();
    public Map<String, String> propertyToSQLType = new HashMap<>();
}
