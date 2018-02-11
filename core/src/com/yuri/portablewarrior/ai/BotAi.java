package com.yuri.portablewarrior.ai;

import com.badlogic.gdx.math.Vector2;
import com.yuri.portablewarrior.MyGdxGame;
import com.yuri.portablewarrior.ai.pathfanding.Astar;
import com.yuri.portablewarrior.ai.pathfanding.Node;
import physic.Utils;
import physic.model.IBodyBehaviur;
import physic.model.Player;

import java.util.LinkedList;
import java.util.List;

public class BotAi {
    private final long    timeToFindPath   = 500l;

    private long          lastTimeFindPath = System.currentTimeMillis();
    private List<Node>    path;

    private Player        bot;
    private Player        target;
    private Astar         astar;
    private boolean       canAttack;
    private MyGdxGame     game;

    public BotAi(Player bot, boolean[][] map, MyGdxGame game) {
        this.bot = bot;
        this.game = game;
        astar = new Astar(map);
        target = game.getPlayer();
    }

    public void botStearing() {

        if(System.currentTimeMillis() - lastTimeFindPath > timeToFindPath && target != null) {

            int botX         = (int) ((bot.getBody().getPosition().x / Utils.WALL_SIZE) * Utils.PPM);
            int botY         = (int) ((bot.getBody().getPosition().x / Utils.WALL_SIZE) * Utils.PPM);

            int goalX        = (int) ((target.getBody().getPosition().x / Utils.WALL_SIZE) * Utils.PPM);
            int goalY        = (int) ((target.getBody().getPosition().y / Utils.WALL_SIZE) * Utils.PPM);


            path             = astar.findPath(botX, botY, goalX, goalY);

            lastTimeFindPath = System.currentTimeMillis();
        }



        if(path == null || path.isEmpty() || (bot.getBody().getPosition().add(target.getBody().getPosition()).len() <= 4f)) {
            (bot).setDir(new Vector2(0, 0));
        } else {
            System.out.println(path.size());
            Node temp   = (Node) ((LinkedList)path).getFirst();
            Vector2 dir = new Vector2((temp.getX() * Utils.WALL_SIZE) / Utils.PPM, (temp.getY() * Utils.WALL_SIZE) / Utils.PPM);
            Vector2 dir2 = bot.getBody().getPosition();

            dir.x       -= dir2.x;
            dir.y       -= dir2.y;
            //dir    = dir.nor();
            //System.out.println(body.getBody().getPosition().add(target.getBody().getPosition()).len());
            bot.setDir(dir.nor());

            ((LinkedList) path).remove(temp);

        }
    }

    private int getIndexX() {
        return (int) ((bot.getBody().getPosition().x / Utils.WALL_SIZE) * Utils.PPM);
    }

    private int getIndexX(float x) {
        return (int) ((x / Utils.WALL_SIZE) * Utils.PPM);
    }

    private int getIndexY() {
        return (int) ((bot.getBody().getPosition().y / Utils.WALL_SIZE) * Utils.PPM);
    }

    private int getIndexY(float y) {
        return (int) ((y / Utils.WALL_SIZE) * Utils.PPM);
    }
}
