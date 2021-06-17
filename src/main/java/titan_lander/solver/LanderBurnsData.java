package titan_lander.solver;

public class LanderBurnsData {
    private double u;
    private double v;
    private double timeStart;
    private double timeEnd;

    public LanderBurnsData(double u, double v, double timeStart, double timeEnd) {
        this.u = u;
        this.v = v;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public double getU() {
        return u;
    }

    public double getV() {
        return v;
    }

    public double getTimeStart() {
        return timeStart;
    }

    public double getTimeEnd() {
        return timeEnd;
    }

    public boolean isTimeInRange(double time) {
        return time < timeEnd && time >= timeStart;
    }


}
