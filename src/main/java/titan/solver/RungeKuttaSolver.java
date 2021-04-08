package titan.solver;

import titan.gui.PlanetTransition;
import titan.interfaces.ODEFunctionInterface;
import titan.interfaces.ODESolverInterface;
import titan.interfaces.StateInterface;

import java.util.ArrayList;
import java.util.List;

public class RungeKuttaSolver implements ODESolverInterface {

    public static List<Double> accessTimes = new ArrayList<>();

    @Override
    public StateInterface[] solve(ODEFunctionInterface f, StateInterface y0, double[] ts) {
        return new StateInterface[0];
    }

    @Override
    public StateInterface[] solve(ODEFunctionInterface function, StateInterface initialState, double finalTime, double stepSize) {
        StateInterface[] states = new State[(int) Math.ceil(finalTime / stepSize)];
        states[0] = initialState;
        double time = 0;
        for (int i = 1; i < states.length; i++) {
            states[i] = step(function, time, states[i - 1], stepSize);
            PlanetTransition.addPath((State) states[i]);
            if ((finalTime - time) / stepSize < 1) {
                time += (finalTime - time) % stepSize;
            } else {
                time += stepSize;
            }
            accessTimes.add(time);
        }
        return states;
    }

    @Override
    public StateInterface step(ODEFunctionInterface function, double time, StateInterface state, double stepSize) {
        Rate k1 = (Rate) function.call(time, state);
        Rate k2 = (Rate) function.call(time + stepSize / 2, state.addMul(stepSize, k1.mul(0.5)));
        Rate k3 = (Rate) function.call(time + stepSize / 2, state.addMul(stepSize, k2.mul(0.5)));
        Rate k4 = (Rate) function.call(time + stepSize, state.addMul(stepSize, k3));
        Rate k = k1.add(k2.mul(2).add(k3.mul(2).add(k4)));

        return state.addMul(stepSize, k.mul(1.0 / 6.0));
    }

    public static List<Double> getAccessTimes() {
        return accessTimes;
    }
}
