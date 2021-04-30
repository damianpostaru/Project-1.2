package titan.solver;

import titan.gui.PlanetTransition;
import titan.interfaces.ODEFunctionInterface;
import titan.interfaces.ODESolverInterface;
import titan.interfaces.StateInterface;
import titan.space.SolarSystem;
import titan.space.Vector3d;

import java.util.ArrayList;
import java.util.List;

public class Solver implements ODESolverInterface {

    // List to be accessed in the timer functionality in the GuiMain.
    public static List<Double> accessTimes = new ArrayList<>();

    @Override
    public StateInterface[] solve(ODEFunctionInterface function, StateInterface initialState, double[] outputTimes) {
        StateInterface[] states = new StateInterface[outputTimes.length];
        states[0] = initialState;
        states[1] = step(function, outputTimes[1], initialState, 60);
        for (int i = 2; i < states.length; i++) {
            states[i] = verletStep(function, outputTimes[i], states[i - 2], states[i - 1], outputTimes[i] - outputTimes[i - 1]);
            PlanetTransition.addPath((State) states[i]);
            accessTimes.add(outputTimes[i]);
        }
        return states;
    }

    @Override
    public StateInterface[] solve(ODEFunctionInterface function, StateInterface initialState, double finalTime, double stepSize) {
        StateInterface[] states = new State[(int) Math.ceil(finalTime / stepSize) + 1];
        states[0] = initialState;
        double time = 0;
        // bootstrap to get the last 2 positions using Runge-Kutta Solver
        states[1] = step(function, time, initialState, stepSize);

        time = time + stepSize;

        for (int i = 2; i < states.length; i++) {
            states[i] = verletStep(function, time, states[i - 2], states[i - 1], stepSize);
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

    // Runge-Kutta Step
    public StateInterface step(ODEFunctionInterface function, double time, StateInterface state, double stepSize) {
        Rate k1 = (Rate) function.call(time, state);
        Rate k2 = (Rate) function.call(time + stepSize / 2, state.addMul(stepSize, k1.mul(0.5)));
        Rate k3 = (Rate) function.call(time + stepSize / 2, state.addMul(stepSize, k2.mul(0.5)));
        Rate k4 = (Rate) function.call(time + stepSize, state.addMul(stepSize, k3));
        Rate k = k1.add(k2.mul(2).add(k3.mul(2).add(k4)));

        return state.addMul(stepSize, k.mul(1.0 / 6.0));
    }


    // Verlet solver implementation
    public StateInterface verletStep(ODEFunctionInterface f, double t, StateInterface initialState, StateInterface currentState, double h) {

        State nextState = ((State) currentState).cloneState();

        SolarSystem previousSystem = ((State) initialState).getSolarSystem();
        SolarSystem currentSystem = ((State) currentState).getSolarSystem();
        SolarSystem nextSystem = (nextState).getSolarSystem();

        // 2y - lastY + a * h^2
        for (int i = 0; i < currentSystem.size(); i++) {
            nextSystem.get(i).mulPos(2);
            nextSystem.get(i).subPos(previousSystem.get(i).getPosition());
        }

        // why a new object for the next state
        State newNextState = nextState.cloneState();
        SolarSystem solarSystem = newNextState.getSolarSystem();
        Rate r = (Rate) f.call(t, currentState);
        Vector3d[] acceleration = r.getAcceleration();
        for (int i = 0; i < solarSystem.size(); i++) {
            solarSystem.get(i).addMulPos(h * h, acceleration[i]);
        }

        return newNextState;
    }

    public static List<Double> getAccessTimes() {
        return accessTimes;
    }
}
