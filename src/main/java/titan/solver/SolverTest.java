package titan.solver;

public class SolverTest {
    public static double rungeKuttaStep(TestFunction function, double time, double yn, double stepSize) {
        double k1 = stepSize * function.call(time, yn);
        double k2 = stepSize * function.call(time + stepSize / 2, yn + k1 / 2);
        double k3 = stepSize * function.call(time + stepSize / 2, yn + k2 / 2);
        double k4 = stepSize * function.call(time + stepSize, yn + k3);
        return yn + (k1 + 2 * k2 + 2 * k3 + k4) / 6;
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
        double[] states = new double[(int) (finalTime / stepSize) + 1];
        states[0] = y0;
        double time = 0;

        for (int i = 0; i < states.length - 1; i++) {
            states[i + 1] = rungeKuttaStep(function, time, states[i], stepSize);
//            if ((finalTime - time) / stepSize < 1) {
//                time += (finalTime - time) % stepSize;
//            } else {
            time += stepSize;
            System.out.println(time);
//            }
        }
        System.out.println("-------------");
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
