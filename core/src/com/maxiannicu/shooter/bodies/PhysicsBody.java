package com.maxiannicu.shooter.bodies;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by nicu on 3/1/17.
 */
public abstract class PhysicsBody extends SpriteBody {
    protected Body body;
    protected Fixture fixture;

    public PhysicsBody(World world, String spriteTexture) {
        super(spriteTexture);
        body = world.createBody(getBodyDef());
        fixture = body.createFixture(getFixtureDef());
    }

    @Override
    public void render(Batch batch){
        updateSpritePosition();
        updateSpriteRotation();

        super.render(batch);
    }


    @Override
    public void setX(float value) {
        body.setTransform(value,getY(),0);
        updateSpritePosition();
    }

    @Override
    public void setY(float value) {
        body.setTransform(getX(),value,0);
        updateSpritePosition();
    }

    private void updateSpriteRotation() {
        sprite.setRotation((float)Math.toDegrees(body.getAngle()));
    }

    private void updateSpritePosition() {
        sprite.setX(body.getPosition().x-getWidth()/2);
        sprite.setY(body.getPosition().y-getHeight()/2);
    }

    public abstract BodyDef getBodyDef();
    public abstract FixtureDef getFixtureDef();

}
