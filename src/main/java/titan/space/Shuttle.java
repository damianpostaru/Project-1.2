package titan.space;

public class Shuttle extends Planet {
    private static final EngineBurnsData[] engineBurns = new EngineBurnsData[]{new EngineBurnsData(30e6, 0, 780, 20000, new Vector3d(18044.44, -29351.0, -819.35))};

    private static final double fuelMass = 1.68e6;

    public Shuttle(Vector3d initialPosition, Vector3d initialVelocity) {
        super(initialPosition, initialVelocity);
    }

    public Vector3d calcEngineAcc(double t) {
        for (EngineBurnsData engineBurn : engineBurns) {
            if (engineBurn.compareTime(t) == 0) { // check if the engine needs to be fired and return the acceleration
                double mass = calcFuelMass(t) + SolarSystemData.masses[11];
                return engineBurn.calcAcceleration(mass);
            }
        }
        return new Vector3d(0, 0, 0); // if no engine is used return no acceleration
    }

    public double calcFuelMass(double t) {
        double burnedFuelMass = 0;
        for (EngineBurnsData engineBurn : engineBurns) {
            if (engineBurn.compareTime(t) >= 0) { // if a burn has been or is being executed
                double time;
                if (engineBurn.compareTime(t) > 0) // entire burn has been executed
                {
                    time = engineBurn.getEndTime() - engineBurn.getStartTime();
                } else {
                    time = t - engineBurn.getStartTime();
                }
                double fuelConsumption = engineBurn.getForce() / engineBurn.getEffExhVel();
                burnedFuelMass += time * fuelConsumption;
            }
        }
        return fuelMass - burnedFuelMass;
    }
}
