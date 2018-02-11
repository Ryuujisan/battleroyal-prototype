package com.yuri.portablewarrior.ai.pathfanding;

import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;
import java.util.List;

public class Astar {
    private Node[][] map;

    private int      height;
    private int      width;

    public Astar(boolean[][] map) {
        this.height = map.length;
        this.width  = map[0].length;
        this.map    = new Node[height][width];

        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                this.map[x][y] = new Node(x, y, map[x][y]);
            }
        }
    }

    public Node getNode(int x, int y) {
        if(x >= 0 && x < width && y >= 0 && y < height) {
            return map[x][y];
        }
        return null;
    }

    public List<Node> findPath(int startX, int startY, int goalX, int golaY) {

        if(startX == goalX && startY == golaY) {
           return new LinkedList<>();
        }

        List<Node> openList  = new LinkedList<>();
        List<Node> closeList = new LinkedList<>();

        openList.add(map[startX][startY]);
        while (true) {
            Node current = lowestFinalList(openList);
            openList.remove(current);

            closeList.add(current);

            if((current.getX() == goalX) && (current.getY() == golaY)) {
                System.out.println("Weszłó");
                return calcPath(map[startX][startY], current);
            }

            List<Node> adjacentNodes = getAdjacent(current, closeList);

            int cIf = 0, cElse = 0;
            for(Node adjacent : adjacentNodes) {

                if (!openList.contains(adjacent)) {
                    adjacent.setParent(current);
                    adjacent.setH(map[goalX][golaY]);
                    adjacent.setG(current);
                    openList.add(adjacent);
                    cIf++;
                } else if(adjacent.getG() > adjacent.calculateG(current)) {
                    adjacent.setParent(current);
                    adjacent.setG(current);
                    cElse++;

                }
            }

            if(openList.isEmpty()) {
                break;
            }

        }

        return openList;
    }

    private List<Node> calcPath(Node start, Node goal) {
        LinkedList<Node> path = new LinkedList<>();

        Node    node = goal;
        boolean done = false;

        while(!done) {
            path.addFirst(node);
            node = node.getParent();

            if(node.equals(start)) {
                done = true;
            }
        }


        return path;
    }

    private Node lowestFinalList(List<Node> list) {
        Node cheapest = list.get(0);

        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).getF() < cheapest.getF()) {
                cheapest = list.get(i);
            }
        }

        return cheapest;
    }

    private List<Node> getAdjacent(Node node, List<Node> closedList) {
        List<Node> adjacentNodes = new LinkedList<>();
        int x                    = node.getX();
        int y                    = node.getY();

        Node adjacent;

        if(x > 0) {
            adjacent = getNode(x - 1, y);

            if(adjacent != null && adjacent.isWalkable() && !closedList.contains(adjacent)) {
                adjacentNodes.add(adjacent);
            }
        }

        if(x < width) {
            adjacent = getNode(x + 1, y);
            if(adjacent != null && adjacent.isWalkable() && !closedList.contains(adjacent)) {
                adjacentNodes.add(adjacent);
            }
        }

        if(y > 0) {
            adjacent = this.getNode(x, y -1);
            if (adjacent != null && adjacent.isWalkable() && !closedList.contains(adjacent)) {
                adjacentNodes.add(adjacent);
            }
        }

        if(y < height) {
            adjacent = this.getNode(x, y + 1);
            if(adjacent != null && adjacent.isWalkable() && !closedList.contains(adjacent)) {
                adjacentNodes.add(adjacent);
            }
        }

        return adjacentNodes;
    }
}
