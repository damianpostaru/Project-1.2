public class Planet {
    private final Vector3d pos;
    private final Vector3d vel;
    private final String name;
    private final double mass;

    public Planet(Vector3d p0, Vector3d v0, double m, String n) {
        pos = p0;
        vel = v0;
        name = n;
        mass = m;
    }

    public void updatePos(Vector3d dp) {
        pos.add(dp);
    }

    public void updateVel(Vector3d dv) {
        vel.add(dv);
    }

    public double getMass() {
        return mass;
    }

    public Vector3dInterface getPos() {
        return pos;
    }

    public Vector3dInterface getVel() {
        return vel;
    }

    public String toString() {
        return "[" + name + " pos: " + pos.toString() + " vel: " + vel.toString() + "]";
    }
}
