package physic.model;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public interface IBodyBehaviur {

    Body createBody(World world);
    void update(float dt);
    Body getBody();
}
