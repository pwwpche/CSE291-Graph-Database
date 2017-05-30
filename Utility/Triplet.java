package Utility;

/**
 * Created by liuche on 5/24/17.
 */
public class Triplet<T0, T1, T2> {
    private final T0 v0;
    private final T1 v1;
    private final T2 v2;

    public static <T0, T1, T2> Triplet<T0, T1, T2> mkTriplet(T0 v0, T1 v1, T2 v2) {
        return new Triplet<>(v0, v1, v2);
    }

    public Triplet(T0 e0, T1 e1, T2 e2) {
        this.v0 = e0;
        this.v1 = e1;
        this.v2 = e2;
    }

    public T0 getV0() {
        return v0;
    }

    public T1 getV1() {
        return v1;
    }

    public T2 getV2() {
        return v2;
    }

    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (!(o instanceof Triplet)) {
            return false;
        }
        Triplet<T0, T1, T2> other = (Triplet<T0, T1, T2>) o;
        return v0.equals(other.getV0()) &&
                v1.equals(other.getV1()) &&
                v2.equals(other.getV2());
    }

    @Override
    public int hashCode(){
        return v0.hashCode() * v1.hashCode() * v2.hashCode();
    }

}
