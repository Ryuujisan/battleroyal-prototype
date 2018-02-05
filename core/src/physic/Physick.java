package physic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Physick {

    private World world;

    public Physick() {
        world = new World(new Vector2(0.0f, 0.0f), true);
    }


    public World getWorld() {
        return world;
    }
}
