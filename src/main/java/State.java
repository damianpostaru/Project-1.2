public class State implements StateInterface{

    SolarSystem ss;


    public State() {
        ss = new SolarSystem();
    }

    public StateInterface addMul(double step, RateInterface rate) {
        State nextState = cloneState();
        SolarSystem sol = nextState.getSolarSystem();
        Rate r = (Rate) rate;
        Vector3d[] acc = r.getAcc();
        for (int i = 0; i < sol.size(); i++)
        {
            sol.get(i).update(acc[i],step);
        }

        return nextState;
    }

    public String toString() {

        return "Solar system: " + ss.toString();
    }

    public SolarSystem getSolarSystem() {
        return ss;
    }

    private State cloneState()
    {
        State newState = new State();
        SolarSystem sol = newState.getSolarSystem();
        for (int i = 0; i < ss.size(); i++)
        {
            Vector3dInterface pos = ss.get(i).getPos();
            Vector3dInterface vel = ss.get(i).getVel();
            sol.get(i).setPos(new Vector3d(pos.getX(),pos.getY(), pos.getZ()));
            sol.get(i).setVel(new Vector3d(vel.getX(),vel.getY(), vel.getZ()));
        }

        return newState;
    }
}
