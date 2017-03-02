package com.maxiannicu.shooter.bodies.action;

import com.badlogic.gdx.math.Vector2;
import com.maxiannicu.shooter.bodies.DynamicPhysicsBody;
import com.maxiannicu.shooter.utils.MathUtils;

/**
 * Created by nicu on 3/2/17.
 */
public class MoveToWithRotationAction extends MoveToAction {
    public MoveToWithRotationAction(Vector2 point, DynamicPhysicsBody body) {
        super(point, body);
    }

    @Override
    public void act() {
        super.act();
        float angle = MathUtils.getAngle(getAffectedBody().getPosition(), point);
        body.setRotationAngle(angle);
    }
}
