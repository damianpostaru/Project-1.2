package io.group8.titan.space;

import io.group8.titan.interfaces.Vector3dInterface;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

//@Entity
@NoArgsConstructor
public class Planet {

//    @Id
//    @GeneratedValue
    private Long id;
//    @OneToOne(cascade = ALL)
    private List<Vector3d> positions;
//    @OneToOne(cascade = ALL)
    private List<Vector3d> velocities;
    private String name;
    private int stateIndex;

    public Planet(String name, Vector3d initialPosition, Vector3d initialVelocity) {
        positions = new ArrayList<>();
        velocities = new ArrayList<>();
        positions.add(initialPosition);
        velocities.add(initialVelocity);
        this.name = name;
        stateIndex = 0;
    }

    public void update(double step, Vector3dInterface acceleration) {
        velocities.add((Vector3d) velocities.get(stateIndex).addMul(step, acceleration));
        positions.add((Vector3d) positions.get(stateIndex).addMul(step, velocities.get(stateIndex)));
        stateIndex++;
    }

    public void addMulPos(double scalar, Vector3dInterface other) {
        positions.add((Vector3d) positions.get(stateIndex).addMul(scalar, other));
        stateIndex++;
    }

    public void setPosition(Vector3d position) {
        positions.set(stateIndex, position);
    }


    public Vector3dInterface getPosition() {
        return positions.get(stateIndex);
    }

//    public void setPosition(Vector3d position) {
//        this.position = position;
//    }
//
//    public void setVelocity(Vector3d velocity) {
//        this.velocity = velocity;
//    }

    public Vector3dInterface getVelocity() {
        return velocities.get(stateIndex);
    }

    public String toString() {
        return "[ name: " + name + ", pos: " + positions.toString() + " vel: " + velocities.toString() + "]";
    }
}
