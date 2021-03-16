public class Rate implements RateInterface {
    Vector3d[] acc;

    public Rate(Vector3d[] a) {
        acc = a;
    }

    public Vector3d[] getAcc() {
        return acc;
    }
}
