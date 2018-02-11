package com.yuri.portablewarrior.ai.astar;

import com.badlogic.gdx.math.Vector2;
import com.yuri.portablewarrior.utils.BinaryTree;
import physic.Utils;

import java.util.ArrayList;

public class Astar {

    private boolean[][]      map;
    private Node             root;
    private Node             target;
    private ArrayList<Node>  nodes;



    public Astar(boolean[][] map) {
        this.map = map;
        nodes    = new ArrayList<>();

    }

    public void findPath(Vector2 position, Vector2 destinyPosition) {
        clearTree();
        makeTree(position, destinyPosition);
    }

    private void makeTree(Vector2 position, Vector2 destinyPosition){
        int rootX = (int) ((position.x / Utils.WALL_SIZE) * Utils.PPM);
        int rootY = (int) ((position.y / Utils.WALL_SIZE) * Utils.PPM);

        int goalX = (int) ((destinyPosition.x / Utils.WALL_SIZE) * Utils.PPM);
        int goalY = (int) ((destinyPosition.y / Utils.WALL_SIZE) * Utils.PPM);

             root = new Node(null, position, rootX, rootY);
        Node node = new Node(null, destinyPosition,rootX,rootY);

        root.setTarget(node);

        for(int x = 0; x < map.length; x++) {
            for(int y = 0; y < map[0].length; y++) {
                if(map[x][y]) {
                  Node  temp = new Node(null, new Vector2((x * Utils.WALL_SIZE) / Utils.PPM, (y * Utils.WALL_SIZE) / Utils.PPM), x, y, node);
                  root.addNode(temp);
                  node.addNode(temp);
                }
            }
        }
    }

    private void clearTree() {
        nodes.forEach(node -> {
            node.setParent(null);
            node.setLeftLeaf(null);
            node.setRightLeaf(null);
        });

        root = null;

        nodes.clear();
    }
}
