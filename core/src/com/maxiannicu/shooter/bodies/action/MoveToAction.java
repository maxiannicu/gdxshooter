package com.maxiannicu.shooter.bodies.action;

import com.badlogic.gdx.math.Vector2;
import com.maxiannicu.shooter.bodies.DynamicPhysicsBody;
import com.maxiannicu.shooter.bodies.PhysicsBody;

/**
 * Created by nicu on 3/2/17.
 */
public class MoveToAction implements Action {
    protected Vector2 point;
    protected DynamicPhysicsBody body;
    private static final float DONE_DELTA = 10;

    public MoveToAction(Vector2 point, DynamicPhysicsBody body) {
        this.point = point;
        this.body = body;
    }

    @Override
    public boolean done() {
        boolean result = getDiffVector().len() < DONE_DELTA;
        if (result)
            body.setVelocity(Vector2.Zero);
        return result;
    }

    @Override
    public void act() {
        Vector2 difference = getDiffVector();
        float desiredVelocity = body.getMaxVelocity();

        Vector2 velocity = difference.setLength(desiredVelocity);
        body.setVelocity(velocity);
    }

    @Override
    public PhysicsBody getAffectedBody() {
        return body;
    }

    private Vector2 getDiffVector() {
        return point.cpy().sub(body.getPosition());
    }
}
