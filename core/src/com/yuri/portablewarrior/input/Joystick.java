package com.yuri.portablewarrior.input;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.yuri.portablewarrior.MyGdxGame;

public class Joystick implements ApplicationListener {

    enum Side {
        Left,
        Right;
    }

    private Side side;

    private Touchpad touchpad;
    private Skin skin;
    private Stage stage;
    private OrthographicCamera camera;
    private SpriteBatch spriteBatch;
    private Touchpad.TouchpadStyle touchpadStyle;

    private Drawable touchBacgraund;
    private Drawable touchKnob;

    private Vector2 dir = new Vector2();

    public Joystick(OrthographicCamera camera) {
        this.camera = camera;
        create();
    }

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        skin = new Skin();

        Texture texture = new Texture("data/touchBackground.png");

        System.out.println(texture);

        skin.add("touchBacgraund", new Texture("data/touchBackground.png"));
        skin.add("touchKnob", new Texture(Gdx.files.internal("data/touchKnob.png")));


        touchpadStyle  = new Touchpad.TouchpadStyle();
        touchBacgraund = skin.getDrawable("touchBacgraund");
        touchKnob      = skin.getDrawable("touchKnob");

        touchpadStyle.background = touchBacgraund;
        touchpadStyle.knob = touchKnob;

        touchpad       = new Touchpad(1, touchpadStyle);
        touchpad.setSize(5f, 5f);
        touchpad.setBounds(15f ,11f,125f,125f);

        stage          = new Stage();
        stage.addActor(touchpad);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        stage.act(Gdx.graphics.getDeltaTime());
        dir.set(touchpad.getKnobPercentX(), touchpad.getKnobPercentY());
        stage.draw();
    }
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    public void renderTouchpad() {
        stage.act(Gdx.graphics.getDeltaTime());
        dir.set(touchpad.getKnobPercentX(), touchpad.getKnobPercentY());
        stage.draw();
    }

    public Vector2 getPosition() {
        return dir;
    }
}
