package physic.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import physic.Utils;
import physic.handle.CategoryBits;


public abstract class Player implements IBodyBehaviur {

    //Player stats
    protected float   speed       = 100f / Utils.PPM;
    protected float   angle       = 0f;
    protected float   attackRatio = 5f;
    protected float   attackRange = 10f / Utils.PPM;
    protected float   maxHp       = 100f;
    protected float   currentHp;

    protected Vector2 dir         = new Vector2();

    protected Body    body;

    protected short   bitType     = CategoryBits.PLAYER;
    protected short   mask        = CategoryBits.EVERYTHING;

    public Player() {
        maxHp     = 100;
        currentHp = maxHp;
    }


    @Override
    public Body createBody(World world) {
        //Create Body def
        BodyDef bodyDef       = new BodyDef();
        bodyDef.type          = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;
        bodyDef.position.set(0 , 0);


        //add to world
        body = world.createBody(bodyDef);

        CircleShape shape              = new CircleShape();
        shape.setRadius(10f / Utils.PPM);

        // Główne Ciało
        FixtureDef fixtureDef          = new FixtureDef();
        fixtureDef.shape               = shape;
        fixtureDef.density             = 0.0f; // gestosc
        fixtureDef.friction            = 0.0f; // tarcie
        fixtureDef.restitution         = 0.0f; // odbijanie;
        fixtureDef.filter.categoryBits = bitType;
        fixtureDef.filter.maskBits     = mask;

        //Sensor front;
        FixtureDef   sensorDef         = new FixtureDef();
        PolygonShape box               = new PolygonShape();

        box.setAsBox(0.1f / Utils.PPM, attackRange , new Vector2(0, -shape.getRadius()), 0);

        sensorDef.isSensor             = true;
        sensorDef.shape                = box;
        sensorDef.filter.categoryBits  = bitType;
        sensorDef.filter.maskBits      = CategoryBits.PLAYER;


        body.createFixture(fixtureDef).setUserData(this);
        body.createFixture(sensorDef).setUserData(this);
        body.setUserData(this);

        shape.dispose();
        box.dispose();
        return body;
    }

    @Override
    public void update(float dt) {
        LinearVelocity();
        rotate();
    }

    public void rotate() {

        if(dir.len() != 0) {
            float angle = (float) Math.atan2(dir.x, -dir.y);
            body.setTransform(body.getPosition(), angle);
        }


    }

    private void LinearVelocity() {
        Vector2 force = dir;
        force.x      *= speed;
        force.y      *= speed;

        // System.out.println(dir.y);
        body.setLinearVelocity(force);
        /*if(dir.len() != 0) {
            body.setLinearVelocity(force);
        } else {
            force = body.getLinearVelocity();

            force.x *= -1;
            force.y *= -1;

            body.applyLinearImpulse(force, body.getPosition(), true);
        }*/
    }

    @Override
    public Body getBody() {
        return body;
    }

    public void setPosition(Vector2 pos) {
        body.getPosition().set(pos);
    }

    public void setDir(Vector2 dir) {
        //System.out.print(dir);
        this.dir = dir;
    }
}
