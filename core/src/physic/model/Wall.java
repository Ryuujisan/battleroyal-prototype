package physic.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import physic.Utils;
import physic.handle.CategoryBits;

public class Wall implements IBodyBehaviur {

    private Vector2 position;
    private short   bitCategory = CategoryBits.WALL;
    private short   maskBit     = CategoryBits.PLAYER;
    private Body    body;

    public Wall(float x, float y) {
        position = new Vector2(x, y);
    }

    public Wall(Vector2 pos) {
        this.position = pos;
        System.out.println(pos);
    }

    @Override
    public Body createBody (World world) {

        BodyDef bodydef  = new BodyDef();
        bodydef.type     = BodyDef.BodyType.StaticBody;

        bodydef.position.set(position);

        body = world.createBody(bodydef);

        PolygonShape shape      = new PolygonShape();
        FixtureDef   fixtureDef = new FixtureDef();

        shape.setAsBox(Utils.WALL_SIZE  / Utils.PPM , Utils.WALL_SIZE / Utils.PPM);

        fixtureDef.shape               = shape;
        fixtureDef.filter.maskBits     = maskBit;
        fixtureDef.filter.categoryBits = bitCategory;

        body.createFixture(fixtureDef).setUserData(this);
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
