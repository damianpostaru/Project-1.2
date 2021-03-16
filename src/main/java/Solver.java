public class Solver implements ODESolverInterface {

    public Solver() {
    }

    @Override
    public StateInterface[] solve(ODEFunctionInterface f, StateInterface y0, double[] ts) {
        StateInterface[] states = new StateInterface[ts.length];
        states[0] = y0;
        for (int i = 1; i < states.length; i++) {

            // leave the step size as 0.1 for now

            // test to improve the step size (manual 2.1 Testing(p.4))
            states[i] = step(f, ts[i], states[i - 1], 0.1);
        }

        return states;
    }

    @Override
    public StateInterface[] solve(ODEFunctionInterface f, StateInterface y0, double tf, double h) {
        StateInterface[] states = new StateInterface[(int) (tf / h) + 1];
        states[0] = y0;
        double t = 0;
        int i = 1;
        while (t <= tf) {
            states[i] = step(f, t, states[i - 1], 0.1);
            i++;
            t += h;
        }

        return states;
    }

    @Override
    public StateInterface step(ODEFunctionInterface f, double t, StateInterface y, double h) {
        return y.addMul(h, f.call(t, y));
    }
}
