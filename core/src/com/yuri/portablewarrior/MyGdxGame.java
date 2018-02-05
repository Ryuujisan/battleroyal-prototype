package com.yuri.portablewarrior;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import physic.Physick;
import physic.model.Warrior;

public class MyGdxGame extends ApplicationAdapter {

	private Physick physick;

	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;

	@Override
	public void create () {
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

	}



	@Override
	public void render () {
		physick.update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		debugRenderer.render(physick.getWorld(), camera.combined);

	}
	
	@Override
	public void dispose () {

	}
}
