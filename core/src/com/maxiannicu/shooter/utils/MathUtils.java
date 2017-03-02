package com.maxiannicu.shooter.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by nicu on 3/2/17.
 */
public class MathUtils {
    public static float getAngle(Vector2 a,Vector2 b){
        return (float)Math.atan2(b.y - a.y, b.x- a.x);
    }
}
