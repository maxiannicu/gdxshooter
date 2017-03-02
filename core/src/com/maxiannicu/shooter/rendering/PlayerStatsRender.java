package com.maxiannicu.shooter.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.maxiannicu.shooter.bodies.Player;

/**
 * Created by nicu on 3/2/17.
 */
public class PlayerStatsRender implements Renderable {
    private final Player player;
    private final BitmapFont font;

    public PlayerStatsRender(Player player) {
        this.player = player;
        font = new BitmapFont();
        font.setColor(Color.WHITE);
    }

    @Override
    public void render(Batch batch) {
        font.draw(batch,String.format("Health : %d\nKills : %d",player.getHealth(),player.getKills()), Gdx.graphics.getWidth()/2,100);
    }
}
