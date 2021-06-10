package titan_lander.interfaces;

/**
 * ControllerInterface
 */
public interface ControllerInterface {

    //methods to get the accelerations of the lander based on the current state and the time
    public double getX(AbstractLander lander, double t);
    public double getY(AbstractLander lander,double t);
    public double getTheta(AbstractLander lander,double t);
    
}