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
//    private Long id;
    //    @OneToOne(cascade = ALL)
    protected List<Vector3d> positions;
    //    @OneToOne(cascade = ALL)
    protected List<Vector3d> velocities;
    protected String name;
    protected int stateIndex;

    public Planet(String name, Vector3d initialPosition, Vector3d initialVelocity) {
        positions = new ArrayList<>();
        velocities = new ArrayList<>();
        positions.add(initialPosition);
        velocities.add(initialVelocity);
        this.name = name;
        stateIndex = 0;
    }

    private Planet(String name, List<Vector3d> positions, List<Vector3d> velocities, int stateIndex) {
        this.name = name;
        this.positions = positions;
        this.velocities = velocities;
        this.stateIndex = stateIndex;
    }

    public void update(double step, Vector3dInterface acceleration) {
        velocities.add((Vector3d) velocities.get(stateIndex).addMul(step, acceleration));
        positions.add((Vector3d) positions.get(stateIndex).addMul(step, velocities.get(stateIndex)));
        stateIndex++;
    }

    public void addMulPos(double scalar, Vector3dInterface other) {
        positions.set(stateIndex, (Vector3d) positions.get(stateIndex).addMul(scalar, other));
    }

    public void setPosition(Vector3d position) {
        positions.set(stateIndex, position);
    }

    public void setPosition(Vector3d position, int index) {
        if (index > positions.size() - 1) {
            positions.add(position);
            stateIndex++;
        } else {
            positions.set(index, position);
        }
    }

    public Vector3dInterface getPosition() {
        return positions.get(stateIndex);
    }

    public Vector3dInterface getPosition(int index) {
        return positions.get(index);
    }

    public Vector3dInterface getVelocity() {
        return velocities.get(stateIndex);
    }

    public String toString() {
        return "[ name: " + name + ", pos: " + positions.toString() + " vel: " + velocities.toString() + "]";
    }

    public Planet copy() {
        List<Vector3d> copyPositions = new ArrayList<>();
        for (Vector3d position : positions) {
            copyPositions.add(position.copy());
        }
        List<Vector3d> copyVelocities = new ArrayList<>();
        for (Vector3d velocity : velocities) {
            copyVelocities.add(velocity.copy());
        }
        return new Planet(name, copyPositions, copyVelocities, stateIndex);
    }
}