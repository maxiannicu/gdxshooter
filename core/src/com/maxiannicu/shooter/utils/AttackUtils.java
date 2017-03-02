package com.maxiannicu.shooter.utils;

import com.maxiannicu.shooter.bodies.Player;
import com.maxiannicu.shooter.bodies.Zombie;

/**
 * Created by nicu on 3/2/17.
 */
public class AttackUtils {
    public static void attack(Zombie zombie, Player player){
        player.receiveAttack(zombie.getAttack());
    }
}
