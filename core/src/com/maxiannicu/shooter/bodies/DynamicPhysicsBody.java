package com.maxiannicu.shooter.bodies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by nicu on 3/2/17.
 */
public abstract class DynamicPhysicsBody extends PhysicsBody {
    public DynamicPhysicsBody(World world, String spriteTexture) {
        super(world, spriteTexture);
    }

    @Override
    public BodyDef getBodyDef() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        return bodyDef;
    }

    public void setVelocity(Vector2 velocity){
        body.setLinearVelocity(velocity.x,velocity.y);
    }

    public Vector2 getVelocity(){
        return body.getLinearVelocity();
    }

    public abstract float getMaxVelocity();
}
