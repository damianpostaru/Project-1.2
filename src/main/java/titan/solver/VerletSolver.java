package titan.solver;

import titan.interfaces.ODEFunctionInterface;
import titan.interfaces.ODESolverInterface;
import titan.interfaces.StateInterface;
import titan.space.SolarSystem;

import java.util.ArrayList;
import java.util.List;

public class VerletSolver implements ODESolverInterface {

    // List to be accessed in the timer functionality in the GuiMain.
    public static List<Double> accessTimes = new ArrayList<>();

    @Override
    public StateInterface[] solve(ODEFunctionInterface f, StateInterface y0, double[] ts) {
        return new StateInterface[0];
    }

    @Override
    public StateInterface[] solve(ODEFunctionInterface f, StateInterface y0, double tf, double h) {
        StateInterface[] states = new StateInterface[(int) Math.ceil(tf / h)];
        double t = h;
        states[0] = y0;

        // bootstrap to get the last 2 positions using Runge-Kutta Solver
        RungeKuttaSolver rk = new RungeKuttaSolver();
        states[1] = rk.step(f, t, y0, h);

        t = t + h;
        // perform the Verlet solver
        for (int i = 2; i < states.length; i++) {
            states[i] = step(f, t, states[i - 2], states[i - 1], h);

            if ((tf - t) / h < 1) {
                t += (tf - t) % h;
            } else {
                t += h;
            }
            accessTimes.add(t);
        }

        return states;
    }

    // not used
    @Override
    public StateInterface step(ODEFunctionInterface f, double t, StateInterface y, double h) {
        return null;
    }

    // Verlet solver implementation
    public StateInterface step(ODEFunctionInterface f, double t, StateInterface y0, StateInterface y1, double h) {

        // 2y - lastY + a * h^2
        SolarSystem system0 = ((State) y0).getSolarSystem();
        SolarSystem system1 = ((State) y1).getSolarSystem();

        for (int i = 0; i < system1.size(); i++) {
            system1.get(i).mulPos(2);
            system1.get(i).subPos(system0.get(i).getPosition());
        }

        y1.addMul(Math.pow(h, 2), f.call(t, y1));

        return y1;
    }
}
