package titan_lander.solver;

import titan.interfaces.ODEFunctionInterface;
import titan.interfaces.StateInterface;
import titan.solver.Solver;

public class LanderSolver extends Solver {
    // potential problem with extends: the static accessTime variable for GUI

    // Runge-Kutta step with new Rate
    @Override
    public StateInterface step(ODEFunctionInterface f, double t, StateInterface y, double h) {
        LanderRate k1 = (LanderRate) f.call(t, y);
        LanderRate k2 = (LanderRate) f.call(t + h / 2, y.addMul(h, k1.mul(0.5)));
        LanderRate k3 = (LanderRate) f.call(t + h / 2, y.addMul(h, k2.mul(0.5)));
        LanderRate k4 = (LanderRate) f.call(t + h, y.addMul(h, k3));
        LanderRate k = k1.add(k2.mul(2).add(k3.mul(2).add(k4)));
        return y.addMul(h, k.mul(1.0 / 6.0));
    }

    // implement the Verlet step with new Rate later if necessary
}
