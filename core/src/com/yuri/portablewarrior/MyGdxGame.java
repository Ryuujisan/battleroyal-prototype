package com.yuri.portablewarrior;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import physic.Physick;

public class MyGdxGame extends ApplicationAdapter {

	private Physick physick;

	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;

	@Override
	public void create () {
		physick = new Physick();
		camera = new OrthographicCamera();
		debugRenderer = new Box2DDebugRenderer();

		camera.setToOrtho(false);

	}



	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		debugRenderer.render(physick.getWorld(), camera.combined);

	}
	
	@Override
	public void dispose () {

	}
}
