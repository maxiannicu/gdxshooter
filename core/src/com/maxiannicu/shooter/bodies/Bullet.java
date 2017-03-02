package com.maxiannicu.shooter.bodies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.maxiannicu.shooter.utils.MathUtils;

/**
 * Created by nicu on 3/2/17.
 */
public class Bullet extends DynamicPhysicsBody {
    private final static float MAX_VELOCITY = 2000;

    public Bullet(World world, Vector2 initial,Vector2 target) {
        super(world, "bullet.png");
        init(initial, target);
    }

    @Override
    public float getMaxVelocity() {
        return MAX_VELOCITY;
    }


    @Override
    public FixtureDef getFixtureDef() {
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(getHeight(),getWidth());
        fixtureDef.shape = polygonShape;
        return fixtureDef;
    }

    private void init(Vector2 initial, Vector2 target) {
        setPosition(initial);
        setVelocity(target.cpy().sub(initial).setLength(MAX_VELOCITY));
        setRotationAngle(MathUtils.getAngle(initial, target));
    }

}
