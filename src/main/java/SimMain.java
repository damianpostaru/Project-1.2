public class SimMain
{
    public static void main(String[] args)
    {
        Solver s = new Solver();
        State state0 = new State();
        StateInterface[] states = s.solve(new Function(), state0,31556926 , 3600);
        for (int i = 0; i < states.length; i++)
        {
            System.out.println(((State)states[i]).getSolarSystem().get(3).getPos());
        }
    }
}
