public class Solver implements ODESolverInterface{
	@Override
	public StateInterface[] solve(ODEFunctionInterface f, StateInterface y0, double[] ts) {
		StateInterface[] states = new StateInterface[ts.length];
		states[0] = y0;
		for (int i = 1; i < states.length; i++) {

			// leave the step size as 0.1 for now

			// TODO Auto-generated method stub
			// test to improve the step size (manual 2.1 Testing(p.4))
			states[i] = step(f, ts[i] - ts[i - 1], states[i - 1], 0.1);
		}

		return states;
	}

    @Override
    public StateInterface[] solve(ODEFunctionInterface f, StateInterface y0, double tf, double h) {
        return new StateInterface[0];
    }

    @Override
    public StateInterface step(ODEFunctionInterface f, double t, StateInterface y, double h) {
        return y.addMul(h, f.call(t, y));
    }
}
