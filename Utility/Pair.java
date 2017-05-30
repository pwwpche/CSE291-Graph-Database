package Utility;

public class Pair<T1, T2> {

    private T1 v0;
    private T2 t21;

    public static <K, V> Pair<K, V> mkPair(K v0, V v1) {
        return new Pair<K, V>(v0, v1);
    }

    public Pair(T1 e0, T2 e1) {
        this.v0 = e0;
        this.t21 = e1;
    }

    public T1 getV0() {
        return v0;
    }

    public T2 getV1() {
        return t21;
    }

    public void setV0(T1 e0) { v0 = e0; }

    public void setV1(T2 e1) { t21 = e1; }

    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (!(o instanceof Pair)) {
            return false;
        }
        Pair<T1, T2> other = (Pair<T1, T2>) o;
        return v0.equals(other.getV0()) && t21.equals(other.getV1());
    }

    @Override
    public int hashCode(){
        return v0.hashCode() * t21.hashCode();
    }


}