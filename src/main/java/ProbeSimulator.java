public class ProbeSimulator implements ProbeSimulatorInterface {
    @Override
    public Vector3dInterface[] trajectory(Vector3dInterface p0, Vector3dInterface v0, double[] ts) {

        return new Vector3dInterface[0];
    }

    @Override
    public Vector3dInterface[] trajectory(Vector3dInterface p0, Vector3dInterface v0, double tf, double h) {
        Vector3dInterface[] trajectory = new Vector3d[(int) Math.ceil(tf / h)];
        State initialState = new State(p0, v0);
        Solver solver = new Solver();
        State[] states = (State[]) solver.solve(new Function(), initialState, tf, h);
        for (int i = 0; i < states.length; i++) {
            trajectory[i] = states[i].getSolarSystem().getShuttle().getPos();
            //System.out.println(states[i].getSolarSystem().getShuttle().getVel());
        }
//        System.out.println(states[states.length-1].getSolarSystem().get(8).getPos().dist(states[states.length-1].getSolarSystem().getShuttle().getPos()));
//        System.out.println(states[states.length-1].getSolarSystem().get(8));
        return trajectory;
    }
}
