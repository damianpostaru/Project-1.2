package io.group8.titan.space;

import io.group8.titan.interfaces.Vector3dInterface;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor
public class Planet {

    @Id
    private Long id;
    @OneToOne
    private Vector3d position;
    @OneToOne
    private Vector3d velocity;
    private String name;

    public Planet(String name, Vector3d initialPosition, Vector3d initialVelocity) {
        position = initialPosition;
        velocity = initialVelocity;
        this.name = name;
    }

    public void update(double step, Vector3dInterface acceleration) {
        velocity = (Vector3d) velocity.addMul(step, acceleration);
        position = (Vector3d) position.addMul(step, velocity);
    }

    public void addMulPos(double scalar, Vector3dInterface other) {
        position = (Vector3d) position.addMul(scalar, other);
    }

    // two helper methods for the computation of Verlet
    public void mulPos(double scalar) {
        position = (Vector3d) position.mul(scalar);
    }

    public void subPos(Vector3dInterface pos) {
        position = (Vector3d) position.sub(pos);
    }

    public Vector3dInterface getPosition() {
        return position;
    }

    public void setPosition(Vector3d position) {
        this.position = position;
    }

    public void setVelocity(Vector3d velocity) {
        this.velocity = velocity;
    }

    public Vector3dInterface getVelocity() {
        return velocity;
    }

    public String toString() {
        return "[" + " pos: " + position.toString() + " vel: " + velocity.toString() + "]";
    }
}