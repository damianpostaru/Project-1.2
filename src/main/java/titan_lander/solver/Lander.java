package titan_lander.solver;


import titan.interfaces.RateInterface;
import titan.interfaces.StateInterface;
import titan.space.Vector3d;
import titan_lander.interfaces.AbstractLander;
import titan_lander.interfaces.ControllerInterface;

public class Lander extends AbstractLander {


    public Lander(ControllerInterface controller, Vector3d initialPos, Vector3d initialVel) {
        super(controller, initialPos, initialVel);
    }

    public void update(double step, Vector3d acc) {
        super.setVelocity((Vector3d) super.getVelocity().addMul(step, acc));
        super.setPosition((Vector3d) super.getPosition().addMul(step, super.getVelocity()));
    }

    public Vector3d calcAcc(double t){
        // calculation of the acceleration is a matter of the controller
        // Controller controller = new Controller(t);
        // return new Vector3d(controller.getX(),controller.getY(),controller.getTheta());
        return null;
    }


    @Override
    public StateInterface addMul(double step, RateInterface rate) {
        return null;
    }
}
