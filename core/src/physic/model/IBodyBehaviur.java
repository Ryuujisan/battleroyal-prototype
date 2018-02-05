package physic.model;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

public interface IBodyBehaviur {

    BodyDef createBody();
    void update(float dt);
    Body getBody();
}
