package com.maxiannicu.shooter.bodies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.maxiannicu.shooter.rendering.Renderable;

/**
 * Created by nicu on 3/1/17.
 */
public class SpriteBody implements Renderable {
    protected Sprite sprite;

    public SpriteBody(String spriteTexture) {
        sprite = new Sprite(new Texture(spriteTexture));
    }

    @Override
    public void render(Batch batch){
        sprite.draw(batch);
    }

    public void setX(float value){
        sprite.setX(value - getWidth()/2);
    }

    public void setY(float value){
        sprite.setY(value - getHeight()/2);
    }

    public float getX(){
        return sprite.getX() + getWidth()/2;
    }

    public float getY(){
        return sprite.getY() + getHeight()/2;
    }

    public Vector2 getPosition(){
        return new Vector2(getX(),getY());
    }

    public void setWidth(float width){
        sprite.setSize(width,sprite.getHeight());
    }

    public void setHeight(float height){
        sprite.setSize(sprite.getWidth(),height);
    }

    public float getWidth(){
        return sprite.getWidth();
    }

    public float getHeight(){
        return sprite.getHeight();
    }
}
