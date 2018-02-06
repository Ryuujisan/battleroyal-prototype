package com.yuri.portablewarrior;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.yuri.portablewarrior.input.Joystick;
import physic.Physick;
import physic.model.Warrior;
public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;

	Joystick joystick ;


	private Physick physick;


	@Override
	public void create () {

		batch = new SpriteBatch();
		img = new Texture("data/touchBackground.png");

		physick = new Physick();

		Warrior warrior = new Warrior();

		physick.addBody(warrior);

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(30, 30 * (h /w));
		//debugRenderer = new Box2DDebugRenderer(true, true, true, true, true, true);
		debugRenderer = new Box2DDebugRenderer();
		//camera.setToOrtho(false);
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight /2f, 0);
		camera.update();
		System.out.println(camera.position);

		joystick = new Joystick(camera);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		camera.update();
		debugRenderer.render(physick.getWorld(), camera.combined);
		System.out.println(getInput());
		joystick.renderTouchpad();
	}

	@Override
	public void dispose () {
	}

	public Vector2 getInput() {
		return joystick.getPosition();
	}
}
