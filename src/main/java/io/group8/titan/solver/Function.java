package io.group8.titan.solver;

import io.group8.titan.space.SolarSystem;
import io.group8.titan.interfaces.ODEFunctionInterface;
import io.group8.titan.interfaces.RateInterface;
import io.group8.titan.interfaces.StateInterface;

public class Function implements ODEFunctionInterface {
    public RateInterface call(double t, StateInterface y) {
        State state = (State) y;
        return new Rate(state.calcAcc(t));
    }
}
