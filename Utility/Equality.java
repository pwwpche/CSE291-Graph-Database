package Utility;

/**
 * Created by liuche on 5/28/17.
 */
public class Equality {
    public String var1 = "", var2 = "";
    public String property1 = "", property2 = "";
    public String equality = "";
    public String type = "";

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Equality)){
            return false;
        }
        Equality o = (Equality) obj;
        if(this.var1.equals(o.var1) &&
                this.var2.equals(o.var2) &&
                this.property1.equals(o.property1) &&
                this.property2.equals(o.property2) &&
                this.equality.equals(o.equality) &&
                this.type.equals(o.type)){
            return true;
        }

        if(this.var1.equals(o.var2) &&
                this.var2.equals(o.var1) &&
                this.property1.equals(o.property2) &&
                this.property2.equals(o.property1) &&
                this.equality.equals(o.equality) &&
                this.type.equals(o.type)){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if(this.property1.equals("")){
            return this.var1 + " " + this.equality + " " + this.var2;
        }else{
            return this.var1 + "." + this.property1 + " " + this.equality + " " + this.var2 + "." + this.property2;
        }
    }

    public Equality(Equality other){
        this.var1 = other.var1;
        this.var2 = other.var2;
        this.equality = other.equality;
        this.type = other.type;
        this.property1 = other.property1;
        this.property2 = other.property2;
    }

    public Equality(String var1, String var2, String equality){
        this.var1 = var1;
        this.var2 = var2;
        this.equality = equality;
        this.type = "var2var";
    }

    public Equality(String var1, String var2, String equality, String type){
        this.var1 = var1;
        this.var2 = var2;
        this.equality = equality;
        this.type = type;
    }

    public Equality(String var1, String var2, String equality, String prop1, String prop2){
        this.var1 = var1;
        this.var2 = var2;
        this.equality = equality;
        this.property1 = prop1;
        this.property2 = prop2;
        this.type = "val2val";
    }

    public void swap(){
        String temp = var1; var1 = var2; var2 = temp;
        temp = property1; property1 = property2; property2 = temp;
    }
}
