package com.maxiannicu.shooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.maxiannicu.shooter.bodies.Player;
import com.maxiannicu.shooter.controller.ActionController;
import com.maxiannicu.shooter.bodies.action.MoveToAction;
import com.maxiannicu.shooter.controller.BulletController;
import com.maxiannicu.shooter.utils.MathUtils;

/**
 * Created by nicu on 3/1/17.
 */
public class PlayerControl implements InputProcessor {
    private final Player player;
    private final ActionController actionController;
    private final BulletController bulletController;

    public PlayerControl(Player player, ActionController actionController, BulletController bulletController) {
        this.player = player;
        this.actionController = actionController;
        this.bulletController = bulletController;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        switch (button){
            case Input.Buttons.RIGHT :
                actionController.addAction(new MoveToAction(new Vector2(screenX,getMapY(screenY)),player),true);
                break;
            case Input.Buttons.LEFT :
                bulletController.shoot(player,new Vector2(screenX,getMapY(screenY)));
                break;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        player.setRotationAngle(MathUtils.getAngle(player.getPosition(),new Vector2(screenX, getMapY(screenY))));

        return false;
    }
    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private int getMapY(int screenY) {
        return Gdx.graphics.getHeight() - screenY;
    }
}
