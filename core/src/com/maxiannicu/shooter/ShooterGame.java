package com.maxiannicu.shooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.maxiannicu.shooter.bodies.Ground;
import com.maxiannicu.shooter.bodies.Player;
import com.maxiannicu.shooter.bodies.action.ActionManager;
import com.maxiannicu.shooter.controller.PlayerController;
import com.maxiannicu.shooter.controller.ZombieController;
import com.maxiannicu.shooter.rendering.Renderer;

public class ShooterGame extends ApplicationAdapter {
    private Player player;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private ActionManager actionManager;
    private Ground ground;
    private PlayerController processor;
    private Renderer renderer;
    private ZombieController zombieController;

    @Override
    public void create() {
        resolveDependencies();
        player.setX(100);
        player.setY(100);

        Gdx.graphics.setResizable(false);
        Gdx.input.setInputProcessor(processor);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        if (ground != null)
            setGroundSize(width, height);
    }

    private void setGroundSize(int width, int height) {
        ground.setWidth(width);
        ground.setHeight(height);
    }

    @Override
    public void render() {
        clean();
        zombieController.step();
        simulatePhysics();
        renderer.render();
        debugRenderer.render(world,renderer.getBatch().getProjectionMatrix());
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

    private void simulatePhysics() {
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
        actionManager.step();
    }

    private void clean() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void resolveDependencies() {
        world = new World(new Vector2(0, 0), true);
        player = new Player(world);
        debugRenderer = new Box2DDebugRenderer();
        actionManager = new ActionManager();
        ground = new Ground();
        processor = new PlayerController(player, actionManager);
        renderer = new Renderer();
        zombieController = new ZombieController(world,actionManager,player, renderer);

        renderer.add(0,ground);
        renderer.add(1,player);
    }


}
