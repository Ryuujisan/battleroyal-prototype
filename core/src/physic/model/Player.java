package physic.model;

import com.badlogic.gdx.physics.box2d.*;


public abstract class Player implements IBodyBehaviur {

    protected final float speed = 3f;
    protected float speedMultiple = 1f;

    protected Body body;

    @Override
    public Body createBody(World world) {
        //Create Body def
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(15, 11.25f);

        //add to world
        body = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(0.75f);

        FixtureDef fixtureDef  = new FixtureDef();
        fixtureDef.shape       = shape;
        fixtureDef.density     = 0.3f; // gestosc
        fixtureDef.friction    = 0.4f; // tarcie
        fixtureDef.restitution = 0.0f; // odbijanie;

        body.createFixture(fixtureDef);
        body.setUserData(this);

        shape.dispose();

        return body;
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public Body getBody() {
        return body;
    }
}
