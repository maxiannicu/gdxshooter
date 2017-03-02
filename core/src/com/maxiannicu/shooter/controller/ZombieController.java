package com.maxiannicu.shooter.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.maxiannicu.shooter.bodies.Player;
import com.maxiannicu.shooter.bodies.Zombie;
import com.maxiannicu.shooter.bodies.action.MoveToWithRotationAction;
import com.maxiannicu.shooter.rendering.Renderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by nicu on 3/2/17.
 */
public class ZombieController implements Controller {
    private final List<Zombie> list = new ArrayList<Zombie>();
    private final World world;
    private final ActionController actionController;
    private final Player player;
    private final Renderer renderer;
    private final Random random = new Random();
    private int maxZombies = 5;

    public ZombieController(World world, ActionController actionController, Player player, Renderer renderer) {
        this.world = world;
        this.actionController = actionController;
        this.player = player;
        this.renderer = renderer;
    }

    @Override
    public void step(){
        spawnZombies();
        for (Zombie zombie : list){
            actionController.addAction(new MoveToWithRotationAction(player.getPosition(),zombie),true);
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
        Vector2 position = Vector2.Zero;
        switch (random.nextInt(4)){
            case 1:
                position = new Vector2(random.nextInt(Gdx.graphics.getWidth()),-100);
                break;
            case 2:
                position = new Vector2(Gdx.graphics.getWidth()+100,random.nextInt(Gdx.graphics.getHeight()));
                break;
            case 3:
                position = new Vector2(random.nextInt(Gdx.graphics.getWidth()),Gdx.graphics.getHeight()+100);
                break;
            case 4:
                position = new Vector2(-100,random.nextInt(Gdx.graphics.getHeight()));
                break;
        }

        return position;
    }

}
