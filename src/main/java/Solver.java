public class Solver implements SolverInterface{



    @Override
    public Vector3dInterface[] solve(FunctionInterface f, Vector3dInterface x0, double h, int nSteps) {
        return new Vector3dInterface[0];
    }

    @Override
    public Vector3dInterface step(FunctionInterface f, double t, Vector3dInterface x, double h) {
        return x.add(f.call(t, x).mul(h));
    }
}
