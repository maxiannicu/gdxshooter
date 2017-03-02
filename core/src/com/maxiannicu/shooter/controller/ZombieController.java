package com.maxiannicu.shooter.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.maxiannicu.shooter.bodies.Player;
import com.maxiannicu.shooter.bodies.Zombie;
import com.maxiannicu.shooter.bodies.action.ActionManager;
import com.maxiannicu.shooter.bodies.action.MoveToAction;
import com.maxiannicu.shooter.bodies.action.MoveToWithRotationAction;
import com.maxiannicu.shooter.rendering.Renderer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicu on 3/2/17.
 */
public class ZombieController implements Controller {
    private final List<Zombie> list = new ArrayList<Zombie>();
    private final World world;
    private final ActionManager actionManager;
    private final Player player;
    private final Renderer renderer;
    private int maxZombies = 5;

    public ZombieController(World world, ActionManager actionManager, Player player, Renderer renderer) {
        this.world = world;
        this.actionManager = actionManager;
        this.player = player;
        this.renderer = renderer;
    }

    @Override
    public void step(){
        spawnZombies();
        for (Zombie zombie : list){
            actionManager.addAction(new MoveToWithRotationAction(player.getPosition(),zombie),true);
        }
    }

    public void setMaxZombies(int maxZombies) {
        this.maxZombies = maxZombies;
    }

    public int getMaxZombies() {
        return maxZombies;
    }

    public void remove(Zombie zombie){
        list.remove(zombie);
        renderer.remove(1,zombie);
        zombie.destroy();
    }

    private void spawnZombies(){
        while (list.size() < maxZombies) {
            Zombie zombie = new Zombie(world);
            Vector2 position = getRandomPosition();
            zombie.setX(position.x);
            zombie.setY(position.y);
            renderer.add(1,zombie);
            list.add(zombie);
        }
    }

    private Vector2 getRandomPosition(){
        float y = (float)Math.random() * Gdx.graphics.getHeight();
        float x = (float)Math.random() * Gdx.graphics.getWidth();

        return new Vector2(x,y);
    }

}
