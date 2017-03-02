package com.maxiannicu.shooter.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.maxiannicu.shooter.bodies.Bullet;
import com.maxiannicu.shooter.bodies.Player;
import com.maxiannicu.shooter.bodies.Zombie;
import com.maxiannicu.shooter.rendering.Renderer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicu on 3/2/17.
 */
public class BulletController implements Controller {
    private final World world;
    private final Renderer renderer;
    private final List<Bullet> bulletList = new ArrayList<Bullet>();
    private final Rectangle rectangle;

    public BulletController(World world, Renderer renderer) {
        this.world = world;
        this.renderer = renderer;
        this.rectangle = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void shoot(Player player, Vector2 target){

        Vector2 playerPosition = player.getPosition();
        Vector2 extra = target.cpy().sub(playerPosition).setLength(Math.max(player.getWidth(),player.getHeight()));
        Vector2 spawnPosition = playerPosition.add(extra);

        Bullet bullet = new Bullet(world, spawnPosition, target);
        bulletList.add(bullet);
        renderer.add(2,bullet);
    }

    @Override
    public void step(){
        List<Bullet> removeList = new ArrayList<Bullet>();
        for (Bullet bullet : bulletList){
            if (!rectangle.contains(bullet.getPosition())){
                removeList.add(bullet);
            }
        }

        for (Bullet bullet : removeList){
            remove(bullet);
        }
    }

    public void remove(Bullet bullet){
        bulletList.remove(bullet);
        renderer.remove(2,bullet);
        bullet.destroy();
    }
}
