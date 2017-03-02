package com.maxiannicu.shooter.bodies;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by nicu on 3/2/17.
 */
public class Zombie extends DynamicPhysicsBody {
    private final static int MAX_VELOCITY = 200;

    public Zombie(World world) {
        super(world, "zoimbie1_stand.png");
    }

    public int getAttack(){
        return 10+(int)(Math.random() * 10);
    }

    @Override
    public float getMaxVelocity() {
        return MAX_VELOCITY;
    }

    @Override
    public FixtureDef getFixtureDef() {
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(Math.max(getWidth()/2,getHeight()/2));
        fixtureDef.shape = circleShape;
        return fixtureDef;
    }
}
