package physic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import physic.handle.ContackListener;
import physic.model.IBodyBehaviur;
import physic.model.Wall;

import java.util.ArrayList;
import java.util.List;

public class Physick {

    private World               world;
    private BodyManager         bodyManager;
    private ContactListener     contactListener;

    private List<IBodyBehaviur> dynamicBodyList         = new ArrayList<IBodyBehaviur>();

    private long                lastSimulationTimestamp = System.currentTimeMillis();
    private float               dt = 0.0f;

    public  boolean             mapReady                = false;

    public Physick() {
        world           = new World(new Vector2(0.0f, 0f), true);
        bodyManager     = new BodyManager(this);
        contactListener = new ContackListener();
        world.setContactListener(contactListener);
    }

    public void update() {
        synchronized (world) {

            dt = (System.currentTimeMillis() - lastSimulationTimestamp) / 1000.0f;

            if(dt != 0f) {
             step();
             simulatorPlayers();
             bodyManager.removeScheduledBodies();
            }
        }
    }

    public void step() {
        world.step(dt, 6, 2);
        lastSimulationTimestamp = System.currentTimeMillis();
    }

    public void simulatorPlayers() {
        dynamicBodyList.forEach(player -> player.update(dt));
    }

    public void addBody(IBodyBehaviur bodyBehaviur) {
        bodyManager.addBodyToWorld(bodyBehaviur);
        dynamicBodyList.add(bodyBehaviur);
    }

    public void mapBuilder(boolean[][] map) {
        for(int x = 0; x < map.length; x++) {
            for(int y = 0; y < map[0].length; y++) {
                if(map[x][y]) {
                    Vector2 pos = new Vector2((float) ((double)(x * Utils.WALL_SIZE ) /(double)Utils.PPM), (float) ((y * (double)Utils.WALL_SIZE ) / (double)Utils.PPM));
                    Wall wall   = new Wall(pos);
                    wall.createBody(world);
                }

            }
        }
        mapReady = true;
    }

    public void removeFromSimulator(IBodyBehaviur body) {
        if(dynamicBodyList.contains(body)) {
            dynamicBodyList.remove(body);
        }
    }

    public World getWorld() {
        return world;
    }
}
