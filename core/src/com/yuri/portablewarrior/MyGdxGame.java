package com.yuri.portablewarrior;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.yuri.portablewarrior.input.Joystick;
import com.yuri.portablewarrior.mapGenerator.MapGenerator;
import physic.Physick;
import physic.Utils;
import physic.model.Player;
import physic.model.Wall;
import physic.model.Warrior;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {

	SpriteBatch batch;
	Texture img;

	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;

	Joystick 				   joystick ;


	private Physick 		   physick;
	private Player 			   player;
	private List<Player> 	   players;
	private int 			   count = 8;
	@Override
	public void create () {
		batch 		  = new SpriteBatch();
		img 		  = new Texture("data/touchBackground.png");

		physick 	  = new Physick();

		player 		  = new Warrior();
		players 	  = new ArrayList<>();

		///physick.mapBuilder(MapGenerator.initialiseMap(100, 100));

		physick.addBody(player);


		for(int i = 0; i < count; i++){

		}

		float w = Gdx.graphics.getWidth() ;
		float h = Gdx.graphics.getHeight() ;

		camera = new OrthographicCamera();
		//debugRenderer = new Box2DDebugRenderer(true, true, true, true, true, true);
		debugRenderer = new Box2DDebugRenderer();
		camera.setToOrtho(false, (w / 2) / Utils.PPM, (h / 2) / Utils.PPM);
		//camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight /2f, 0);
		camera.update();
		System.out.println(camera.position);

		//Wall wall = new Wall(0.2f,0.2f);
		//physick.addBody(wall);

		joystick = new Joystick(camera);
	}

	@Override
	public void render () {
		if(!physick.mapReady) {
			physick.mapBuilder(MapGenerator.generateMap(100, 100));
		}

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		addEnemy();

		player.setDir(getInput());
		physick.update();

		debugRenderer.render(physick.getWorld(), camera.combined);
		updateCamera();
		joystick.renderTouchpad();
	}

	@Override
	public void dispose () {
	}

	public Vector2 getInput() {
		return joystick.getPosition();
	}

	public void updateCamera() {
		Vector3 position = camera.position;
		position.x 		 = player.getBody().getPosition().x;
		position.y	 	 = player.getBody().getPosition().y;
		camera.update();
	}

	public void addEnemy() {
		if(players.size() <= count) {
			RandomXS128 random = new RandomXS128();
			Warrior enemy = new Warrior();

			physick.addBody(enemy);
			players.add(enemy);

			Vector2 newPos = player.getBody().getPosition();

			newPos.x 	  += random.nextInt(100);
			newPos.y 	  += random.nextInt(100);

			enemy.setPosition(newPos);
		}
	}
}
