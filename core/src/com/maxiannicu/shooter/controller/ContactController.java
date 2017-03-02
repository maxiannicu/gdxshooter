package com.maxiannicu.shooter.controller;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.maxiannicu.shooter.bodies.Bullet;
import com.maxiannicu.shooter.bodies.Player;
import com.maxiannicu.shooter.bodies.Zombie;
import com.maxiannicu.shooter.utils.AttackUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by nicu on 3/2/17.
 */

public class ContactController implements ContactListener,Controller {
    private final Set<Bullet> bulletsToRemove = new HashSet<Bullet>();
    private final Set<Zombie> zombiesToRemove = new HashSet<Zombie>();
    private final ZombieController zombieController;
    private final BulletController bulletController;
    private final ActionController actionController;
    private final Player player;

    public ContactController(ZombieController zombieController, BulletController bulletController, ActionController actionController, Player player) {
        this.zombieController = zombieController;
        this.bulletController = bulletController;
        this.actionController = actionController;
        this.player = player;
    }

    @Override
    public void step(){
        for (Bullet bullet : bulletsToRemove){
            bulletController.remove(bullet);
            actionController.removeExistingActions(bullet);
        }
        for (Zombie zombie: zombiesToRemove){
            zombieController.remove(zombie);
            actionController.removeExistingActions(zombie);

        }
        bulletsToRemove.clear();
        zombiesToRemove.clear();
    }

    @Override
    public void beginContact(Contact contact) {
        Object a = contact.getFixtureA().getUserData();
        Object b = contact.getFixtureB().getUserData();
        if (a instanceof Bullet && b instanceof Zombie){
            bulletsToRemove.add((Bullet)a);
            zombiesToRemove.add((Zombie)b);
            player.setKills(player.getKills()+1);
        } else if (a instanceof Zombie && b instanceof Bullet){
            bulletsToRemove.add((Bullet)b);
            zombiesToRemove.add((Zombie)a);
            player.setKills(player.getKills()+1);
        } else if (a instanceof Player && b instanceof Zombie) {
            AttackUtils.attack((Zombie)b,(Player)a);
        } else if (a instanceof Zombie && b instanceof Player){
            AttackUtils.attack((Zombie)a,(Player)b);
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

}
