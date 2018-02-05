package physic;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import physic.model.IBodyBehaviur;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BodyManager {
    private World world;
    private Physick physick;
    private List<IBodyBehaviur> bodyScholdDestroyed = new ArrayList<IBodyBehaviur>();


    public BodyManager(Physick physick){
        this.world = physick.getWorld();
        this.physick = physick;
    }


    public Body addBodyToWorld(IBodyBehaviur model) {
        BodyDef bodyDef = model.createBody();

        return world.createBody(bodyDef);
    }

    public void removeScheduledBodies() {
        if(!world.isLocked()) {
            world.step(0.0f, 0, 0);
            Iterator iterator = bodyScholdDestroyed.iterator();

            while (iterator.hasNext()) {
                IBodyBehaviur player = (IBodyBehaviur) iterator.next();
                physick.removeFromSimulator(player);
                world.destroyBody(player.getBody());
                iterator.remove();
            }

            bodyScholdDestroyed.clear();
        }
    }



}
