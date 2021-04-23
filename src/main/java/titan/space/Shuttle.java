package titan.space;

public class Shuttle extends Planet {
    //contains data about each burn in the simulation stored as {force, start time , end time}
    //NOTE: make sure that the burns do not overlap in their times
    public static double[][] engineTimings = new double[][]{};
    public static Vector3d[] engineDirections = new Vector3d[]{};

    private final double fuelMass;
    private final double EFF_EXH_VEL = 40000;

    public Shuttle(Vector3d initialPosition, Vector3d initialVelocity, double fuelMass) {
        super(initialPosition, initialVelocity);
        this.fuelMass = fuelMass;
    }

    public Vector3d calcEngineAcc(double force, Vector3d dir, double t, double h) {
        double fuelConsumption = force / EFF_EXH_VEL;
        double mass = calcFuelMass(t) + SolarSystemData.masses[11];
        return dir.mul(force).mul(1 / mass);
    }

    public double calcFuelMass(double t) {
        double burnedFuelMass = 0;
        for (int i = 0; i < engineTimings.length; i++) {
            if (engineTimings[i][1] < t){//if a burn has been or is being executed
                double time = 0;
                if (engineTimings[i][2] < t)//entire burn has been executed
                {
                    time = engineTimings[i][2] - engineTimings[i][1];
                }
                else
                {
                    time = t - engineTimings[i][1];
                }
                double fuelConsumption = engineTimings[i][0] / EFF_EXH_VEL;
                burnedFuelMass += time * fuelConsumption;
            }
        }
        return fuelMass - burnedFuelMass;
    }
}
