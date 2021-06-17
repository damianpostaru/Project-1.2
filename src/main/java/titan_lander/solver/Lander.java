package titan_lander.solver;


import titan.interfaces.RateInterface;
import titan.interfaces.StateInterface;
import titan.space.Vector3d;
import titan_lander.interfaces.AbstractLander;
import titan_lander.interfaces.ControllerInterface;

import static java.lang.Math.PI;
import static java.lang.Math.abs;

public class Lander extends AbstractLander {

    private static boolean hasCrashed;
    private static boolean hasLanded;
    private final double error = 0.1;
    private static boolean wasPrinted;
    private static boolean wasPrinted1;

    public Lander(ControllerInterface controller, Vector3d initialPos, Vector3d initialVel) {
        super(controller, initialPos, initialVel);
    }

    public void update(double step, Vector3d acc) {
        if (hasCrashed) {
            return;
        }
        super.setVelocity((Vector3d) velocity.addMul(step, acc));
        super.setPosition((Vector3d) position.addMul(step, velocity));
    }

    public Vector3d calcAcc(double t) {
        if (hasCrashed) {
            if (!wasPrinted1) {
                System.out.println(t);
                wasPrinted1 = true;
            }
            return new Vector3d(0, 0, 0);
        }
        return new Vector3d(controller.getX(this, t), controller.getY(this, t), controller.getTheta(this, t));
    }

    public void hasLanded() {
        boolean xPos = abs(position.getX()) <= 0.1;
        boolean thetaPos = abs(position.getZ() % (2 * PI)) <= 0.02;
        boolean xVel = abs(velocity.getX()) <= 0.1;
        boolean yVel = abs(velocity.getY()) <= 0.1;
        boolean thetaVel = abs(velocity.getZ()) <= 0.01;
        if (position.getY() <= error) {
            hasCrashed = true;
            if (!wasPrinted) {
                System.out.println("Position: " + position);
                System.out.println("Velocity: " + velocity);
                wasPrinted = true;
            }
            if (xPos & thetaPos & xVel & yVel & thetaVel) {
                hasLanded = true;
            }
        }
    }

    @Override
    public StateInterface addMul(double step, RateInterface rate) {
        Lander newLander = (Lander) cloneLander(this);
        newLander.update(step, ((LanderRate) rate).getAcceleration());
        return newLander;
    }

    public StateInterface cloneLander(Lander lander) {
        return new Lander(lander.getController(), lander.getPosition(), lander.getVelocity());
    }
}
