package com.maxiannicu.shooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.maxiannicu.shooter.bodies.Ground;
import com.maxiannicu.shooter.bodies.Player;
import com.maxiannicu.shooter.bodies.action.ActionManager;
import com.maxiannicu.shooter.controller.BulletController;
import com.maxiannicu.shooter.controller.ContactController;
import com.maxiannicu.shooter.controller.Controller;
import com.maxiannicu.shooter.controller.ZombieController;
import com.maxiannicu.shooter.rendering.PlayerStatsRender;
import com.maxiannicu.shooter.rendering.Renderer;

import java.util.ArrayList;
import java.util.List;

public class ShooterGame extends ApplicationAdapter {
    private Player player;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private ActionManager actionManager;
    private Ground ground;
    private PlayerControl processor;
    private Renderer renderer;
    private ZombieController zombieController;
    private BulletController bulletController;
    private ContactController contactController;
    private List<Controller> controllers = new ArrayList<Controller>();

    @Override
    public void create() {
        resolveDependencies();
        player.setPosition(new Vector2(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2));

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
        stepControllers();
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
        renderer = new Renderer();
        bulletController = new BulletController(world,renderer);
        processor = new PlayerControl(player, actionManager, bulletController);
        zombieController = new ZombieController(world,actionManager,player, renderer);
        contactController = new ContactController(zombieController,bulletController);

        world.setContactListener(contactController);
        renderer.add(0,ground);
        renderer.add(1,player);
        renderer.add(100,new PlayerStatsRender(player));

        controllers.add(contactController);
        controllers.add(bulletController);
        controllers.add(zombieController);
    }

    private void stepControllers() {
        for (Controller controller : controllers){
            controller.step();
        }
    }
}
