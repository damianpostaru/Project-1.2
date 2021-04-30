package titan.space;

import titan.interfaces.Vector3dInterface;

public class Shuttle extends Planet {
    //contains data about each burn in the simulation stored as {force, start time , end time}
    //NOTE: make sure that the burns do not overlap in their times
    //NOTE: make sure engineDirections and engineTimings arrays are the same length
    public static double[][] engineTimings = new double[][]{{80e6,0,960}};
    public static Vector3d[] engineDirections = new Vector3d[]{new Vector3d(0.5235764373087488,-0.8516469345376796,-0.02377421266101488)};

    private final double fuelMass;
    private final double EFF_EXH_VEL = 40000;

    public Shuttle(Vector3d initialPosition, Vector3d initialVelocity, double fuelMass) {
        super(initialPosition, initialVelocity);
        this.fuelMass = fuelMass;
    }

    public Vector3d calcEngineAcc(double t) {

        for (int i = 0; i < engineTimings.length; i++) {
            //System.out.println(t + " : " + engineTimings[i][1] + " : " + engineTimings[i][2]);
            if(engineTimings[i][2] >= t && engineTimings[i][1] < t) {//check if the engine needs to be fired and return the acceleration
                System.out.println("Fire the engines!!!");
                double force = engineTimings[i][0];
                Vector3d dir = engineDirections[i];
                double mass = calcFuelMass(t) + SolarSystemData.masses[11];
                System.out.println(mass);
                return (Vector3d) (dir.mul(force)).mul(1.0 / mass);

            }
        }
        return new Vector3d(0,0,0); //if no engine is used return no acceleration
    }

    public double calcFuelMass(double t) {
        double burnedFuelMass = 0;
        for (int i = 0; i < engineTimings.length; i++) {
            if (engineTimings[i][1] < t){//if a burn has been or is being executed
                double time;
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
