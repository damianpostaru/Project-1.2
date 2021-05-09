package titan.space;

import titan.interfaces.Vector3dInterface;


public class Shuttle extends Planet {
    private static EngineBurnsData[] engineBurns = new EngineBurnsData[]{new EngineBurnsData(30e6, 0, 780, 20000, new Vector3d(18044.44, -29351.0, -819.35))};

    private static final double fuelMass = 1.68e6;

    public Shuttle(Vector3d initialPosition, Vector3d initialVelocity) {
        super(initialPosition, initialVelocity);
    }

    public Vector3d calcEngineAcc(double t) {

        for (int i = 0; i < engineBurns.length; i++) {
            if (engineBurns[i].compareTime(t) == 0) {//check if the engine needs to be fired and return the acceleration
                double mass = calcFuelMass(t) + SolarSystemData.masses[11];
                return engineBurns[i].calcAcceleration(mass);
            }
        }
        return new Vector3d(0, 0, 0); //if no engine is used return no acceleration
    }

    public double calcFuelMass(double t) {
        double burnedFuelMass = 0;
        for (int i = 0; i < engineBurns.length; i++) {
            if (engineBurns[i].compareTime(t) >= 0) {//if a burn has been or is being executed
                double time;
                if (engineBurns[i].compareTime(t) > 0)//entire burn has been executed
                {
                    time = engineBurns[i].getEndTime() - engineBurns[i].getStartTime();
                } else {
                    time = t - engineBurns[i].getStartTime();
                }
                double fuelConsumption = engineBurns[i].getForce() / engineBurns[i].getEffExhVel();
                burnedFuelMass += time * fuelConsumption;
            }
        }
        return fuelMass - burnedFuelMass;
    }
}
