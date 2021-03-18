public class ProbeSimulator implements ProbeSimulatorInterface {
    @Override
    public Vector3dInterface[] trajectory(Vector3dInterface initialPosition, Vector3dInterface initialVelocity, double[] outputTimes) {
        Vector3dInterface[] trajectory = new Vector3d[outputTimes.length];
        State initialState = new State(initialPosition, initialVelocity);
        Solver solver = new Solver();
        State[] states = (State[]) solver.solve(new Function(), initialState, outputTimes);
        for (int i = 0; i < states.length; i++) {
            trajectory[i] = states[i].getSolarSystem().getShuttle().getPosition();
        }
        return trajectory;
    }

    @Override
    public Vector3dInterface[] trajectory(Vector3dInterface initialPosition, Vector3dInterface initialVelocity, double finalTime, double stepSize) {
        Vector3dInterface[] trajectory = new Vector3d[(int) Math.ceil(finalTime / stepSize)];
        State initialState = new State(initialPosition, initialVelocity);
        System.out.println("Probe starting position: " + initialPosition);
        System.out.println("Probe starting velocity: " + initialVelocity);
        System.out.println("Probe trajectory: ");
        Solver solver = new Solver();
        State[] states = (State[]) solver.solve(new Function(), initialState, finalTime, stepSize);
        double bestDist = Double.MAX_VALUE;
        double bestTime = 0;
        for (int i = 0; i < states.length; i++) {
            trajectory[i] = states[i].getSolarSystem().getShuttle().getPosition();
//            System.out.println(states[i].getSolarSystem().getShuttle().getPosition());
            double dist = states[i].getSolarSystem().get(8).getPosition().dist(states[i].getSolarSystem().getShuttle().getPosition());
            if(dist < bestDist)
            {
                bestDist = dist;
                bestTime = i * stepSize;
            }
        }
        System.out.println("Time of closest approach: " + bestTime);
        System.out.println("Distance of closest approach: " + bestDist);
        if(bestDist <= states[states.length-1].getSolarSystem().get(8).getRadius())
        {
            System.out.println("Titan has been hit");
        }
        return trajectory;
    }
}
