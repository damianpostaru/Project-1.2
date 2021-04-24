package titan.space;

import titan.interfaces.Vector3dInterface;

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

    public Vector3d calcEngineAcc(double t, double h) {
        for (int i = 0; i < engineTimings.length; i++) {
            if(engineTimings[i][2] < t && engineTimings[i][1] >= t) {//check if the engine needs to be fired and return the acceleration
                double force = engineTimings[i][0];
                Vector3d dir = engineDirections[i];
                double fuelConsumption = force / EFF_EXH_VEL;
                double mass = calcFuelMass(t) + SolarSystemData.masses[11];
                return (Vector3d) (dir.mul(force)).mul(1.0 / mass);
            }
        }
        return new Vector3d(0,0,0); //if no engine is used return no acceleration
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
