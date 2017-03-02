package com.maxiannicu.shooter.bodies.action;

import com.maxiannicu.shooter.bodies.PhysicsBody;

/**
 * Created by nicu on 3/2/17.
 */
public interface Action {
    boolean done();
    void act();
    PhysicsBody getAffectedBody();
}
