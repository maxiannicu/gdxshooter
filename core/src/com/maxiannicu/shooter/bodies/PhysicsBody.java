package com.maxiannicu.shooter.bodies;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by nicu on 3/1/17.
 */
public abstract class PhysicsBody extends SpriteBody {
    private final World world;
    protected Body body;
    protected Fixture fixture;

    public PhysicsBody(World world, String spriteTexture) {
        super(spriteTexture);
        this.world = world;
        body = this.world.createBody(getBodyDef());
        fixture = body.createFixture(getFixtureDef());
        fixture.setUserData(this);
    }

    public void destroy(){
        world.destroyBody(body);
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

    public void setRotationAngle(float angle){
        body.setTransform(body.getPosition(),angle);
    }

    public float getRotationAngle(){
        return body.getAngle();
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
