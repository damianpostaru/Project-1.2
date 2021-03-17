public class Function implements ODEFunctionInterface {
    public RateInterface call(double t, StateInterface y) {
        State state = (State) y;
        SolarSystem solarSystem = state.getSolarSystem();
        return new Rate(solarSystem.calcAcc());
    }
}
