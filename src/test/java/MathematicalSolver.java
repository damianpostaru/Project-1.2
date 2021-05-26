public class MathematicalSolver {
    public static double rungeKuttaStep(TestFunction function, double time, double yn, double stepSize) {
        double k1 = function.call(time, yn);
        double k2 = function.call(time + stepSize / 2, yn + stepSize * k1 / 2);
        double k3 = function.call(time + stepSize / 2, yn + stepSize * k2 / 2);
        double k4 = function.call(time + stepSize, yn + stepSize * k3);
        return yn + (k1 + 2 * k2 + 2 * k3 + k4) * stepSize / 6;
    }

    public static double[] solveRK(TestFunction function, double y0, double finalTime, double stepSize) {
        double[] states = new double[(int) (finalTime / stepSize) + 1];
        states[0] = y0;
        double time = 0;

        for (int i = 0; i < states.length - 1; i++) {
            states[i + 1] = rungeKuttaStep(function, time, states[i], stepSize);
            if ((finalTime - time) / stepSize < 1) {
                time += (finalTime - time) % stepSize;
            } else {
            time += stepSize;
            System.out.println(time);
            }
        }
        System.out.println("-------------");
        return states;
    }

    // TODO: fix acceleration

//    public static double verletStep(TestFunction f, double t1, double y0, double y1, double h) {
//        // is a the fval(t,y)? or have to include the physics
//        return (2 * y1 - y0 + (y1 - y0)/t1 * h * h);
//    }
//
//    public static double[] solveVerlet(TestFunction f, double y0, double tf, double h) {
//        double[] states = new double[(int) (tf / h) + 1];
//
//        double t = 0;
//        states[0] = y0;
//        states[1] = rungeKuttaStep(f, t, y0, h);
//        t+=h;
//
//        for (int i = 2; i < states.length; i++) {
//            states[i] = verletStep(f, t, states[i-2], states[i-1], h);
//            t += h;
//        }
//
//        return states;
//    }

    public static void main(String[] args) {
        TestFunction f = new TestFunction();
        double[] states = solveRK(f, 2, 6, 1);
        for (double state : states) {
            System.out.println(state);
        }
    }
}
