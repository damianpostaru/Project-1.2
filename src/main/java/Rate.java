public class Rate implements RateInterface {
    Vector3d[] acceleration;

    public Rate(Vector3d[] acceleration) {
        this.acceleration = acceleration;
    }

    public Vector3d[] getAcceleration() {
        return acceleration;
    }
}
