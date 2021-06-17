package titan_lander.solver;

import titan_lander.interfaces.AbstractLander;
import titan_lander.interfaces.ControllerInterface;

import static java.lang.Math.*;

public class OpenLoopController implements ControllerInterface {

    private final double TITAN_G = 1.352;
    LanderBurnsData[] landerBurnsData = {
            new LanderBurnsData(2.7463, 0, 361.35, 563.2000000000595)
    };

    public OpenLoopController() {
    }

    @Override
    public double getX(AbstractLander lander, double t) {
        double u = findU(t);
        return u * sin(lander.getPosition().getZ());
    }

    @Override
    public double getY(AbstractLander lander, double t) {
        double u = findU(t);
        return u * cos(lander.getPosition().getZ()) - TITAN_G;
    }

    @Override
    public double getTheta(AbstractLander lander, double t) { // maybe no need for parameters
        return findV(t);
    }

    private double findU(double t) {
        for (LanderBurnsData landerBurnsDatum : landerBurnsData) {
            if (landerBurnsDatum.isTimeInRange(t)) {
                return landerBurnsDatum.getU();
            }
        }
        return 0;
    }

    private double findV(double t) {
        for (LanderBurnsData landerBurnsDatum : landerBurnsData) {
            if (landerBurnsDatum.isTimeInRange(t)) {
                return landerBurnsDatum.getV();
            }
        }
        return 0;
    }
}
