package Entity;


/**
 * Created by liuche on 4/25/17.
 */
public class Value {
    public Object val;

    public boolean isConstant;

    public String type;

    public Value(Object obj){
        this.val = obj;
        isConstant = false;
        type = "";
    }

    public Value(Object obj, boolean isConstant, String type){
        this.val = obj;
        this.isConstant = isConstant;
        this.type = type;
    }

}
