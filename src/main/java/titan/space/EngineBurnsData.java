package titan.space;

public class EngineBurnsData {
    final double force;
    final double startTime;
    final double endTime;
    final double effExhVel;
    final Vector3d dir;

    public EngineBurnsData(double f, double t1, double t2, double v, Vector3d d) {
        force = f;
        startTime = t1;
        endTime = t2;
        effExhVel = v;
        dir = d;
    }

    public double getForce() {
        return force;
    }

    public double getStartTime() {
        return startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public double getEffExhVel() {
        return effExhVel;
    }

    public Vector3d getDir() {
        return dir;
    }


    public Vector3d calcAcceleration(double mass) {
        Vector3d unitDir = (Vector3d) dir.mul(1 / dir.norm());
        return (Vector3d) unitDir.mul(force / mass);
    }


    //returns a value of 1 if t > endTime, value of 0 when startTime < t <= endTime and -1 if t <= startTime
    public int compareTime(double t) {
        if (startTime < t && t <= endTime) {
            return 0;
        } else if (t <= startTime) {
            return -1;
        } else {
            return 1;
        }

    }
}
