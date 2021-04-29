package titan.solver;

import titan.gui.PlanetTransition;
import titan.interfaces.ODEFunctionInterface;
import titan.interfaces.StateInterface;
import titan.space.SolarSystem;
import titan.space.Vector3d;

public class SolverTest {
    public static double rungeKuttaStep(TestFunction function, double time, double yn, double stepSize) {
        double k1 = function.call(time, yn);
        double k2 = function.call(time + stepSize / 2, yn + stepSize / 2 * k1);
        double k3 = function.call(time + stepSize / 2, yn + stepSize / 2 * k2);
        double k4 = function.call(time + stepSize, yn + stepSize * k3);
        double k = k1 + 2 * k2 + 2 * k3 + k4;

        return yn + (k1 + 2 * k2 + 2 * k3 + k4) * stepSize / 6;
    }

    //    public StateInterface verletStep(ODEFunctionInterface f, double t, double ynmin1, double yn, double h) {
//
//
//        // 2y - lastY + a * h^2
//        double ynplus1 = 2 * yn - ynmin1 +
//
//        return newNextState;
//    }
    public static double[] solve(TestFunction function, double y0, double finalTime, double stepSize) {
        double[] states = new double[(int) Math.ceil(finalTime / stepSize) + 1];
        states[0] = y0;
        double time = 0;

        for (int i = 1; i < states.length; i++) {
            states[i] = rungeKuttaStep(function, time, y0, stepSize);
            if ((finalTime - time) / stepSize < 1) {
                time += (finalTime - time) % stepSize;
            } else {
                time += stepSize;
            }
        }
        return states;
    }

    public static void main(String[] args) {
        TestFunction f = new TestFunction();
        double[] states = solve(f, 0, 10, 0.01);
        for (double state : states) {
            System.out.println(state);
        }
    }
}
