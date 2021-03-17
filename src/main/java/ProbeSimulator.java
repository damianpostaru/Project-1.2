public class ProbeSimulator implements ProbeSimulatorInterface {
    @Override
    public Vector3dInterface[] trajectory(Vector3dInterface initialPosition, Vector3dInterface initialVelocity, double[] outputTimes) {

        return new Vector3dInterface[0];
    }

    @Override
    public Vector3dInterface[] trajectory(Vector3dInterface initialPosition, Vector3dInterface initialVelocity, double finalTime, double stepSize) {
        Vector3dInterface[] trajectory = new Vector3d[(int) Math.ceil(finalTime / stepSize)];
        State initialState = new State(initialPosition, initialVelocity);
        Solver solver = new Solver();
        State[] states = (State[]) solver.solve(new Function(), initialState, finalTime, stepSize);
        for (int i = 0; i < states.length; i++) {
            trajectory[i] = states[i].getSolarSystem().getShuttle().getPosition();
            //System.out.println(states[i].getSolarSystem().getShuttle().getVel());
        }
//        System.out.println(states[states.length-1].getSolarSystem().get(8).getPos().dist(states[states.length-1].getSolarSystem().getShuttle().getPos()));
//        System.out.println(states[states.length-1].getSolarSystem().get(8));
        return trajectory;
    }
}
