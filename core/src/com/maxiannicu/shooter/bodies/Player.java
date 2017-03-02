package com.maxiannicu.shooter.bodies;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by nicu on 3/1/17.
 */
public class Player extends DynamicPhysicsBody {
    private final static float MAX_VELOCITY = 400;

    public Player(World world) {
        super(world,"hitman1_stand.png");
    }

    @Override
    public FixtureDef getFixtureDef() {
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(Math.max(getWidth()/2,getHeight()/2));

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        return fixtureDef;
    }

    public void setRotationAngle(float angle){
        body.setTransform(body.getPosition(),angle);
    }

    public float getRotationAngle(){
        return body.getAngle();
    }

    @Override
    public float getMaxVelocity() {
        return MAX_VELOCITY;
    }
}
