package titan.solver;

import titan.gui.PlanetTransition;
import titan.interfaces.ODEFunctionInterface;
import titan.interfaces.ODESolverInterface;
import titan.interfaces.StateInterface;

import java.util.ArrayList;
import java.util.List;

public class Solver implements ODESolverInterface {
	
	// List to be accessed in the timer functionality in the GuiMain.
	public static List<Double> accessTimes =  new ArrayList<Double>();

    @Override
    public StateInterface[] solve(ODEFunctionInterface function, StateInterface initialState, double[] outputTimes) {
        StateInterface[] states = new StateInterface[outputTimes.length];
        states[0] = initialState;
        for (int i = 1; i < states.length; i++) {
            states[i] = step(function, outputTimes[i], states[i - 1], 60);
        }
        return states;
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
                accessTimes.add(time);
            } else {
                time += stepSize;
                accessTimes.add(time);
            }
        }
        return states;
    }

    @Override
    public StateInterface step(ODEFunctionInterface function, double time, StateInterface state, double stepSize) {
        return state.addMul(stepSize, function.call(time, state));
    }
    
    public static List<Double> getAccessTimes() {
    	return accessTimes;
    }
}
