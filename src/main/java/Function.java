public class Function implements ODEFunctionInterface
{
    public RateInterface call(double t, StateInterface y)
    {
        State state = (State)y;
        SolarSystem ss = state.getSolarSystem();
        Rate rate = new Rate(ss.calcAcc());


        //physics stuff
        return rate;
    }
}
