package com.yuri.portablewarrior.ai.astar;

import com.badlogic.gdx.math.Vector2;
import com.yuri.portablewarrior.MyGdxGame;

/* Liście drzewa binarnego są umieszczone taile tylko z możliwościom chodzenia */

public class Node {
    private Node    parent;
    private Node    leftLeaf;
    private Node    rightLeaf;
    private Node    target;

    private Vector2 pos;

    private int     x;
    private int     y;

    private float   h;

    public Node(Node parent, Vector2 pos, int x, int y, Node target) {
        this.parent = parent;
        this.pos    = pos;
        this.x      = x;
        this.y      = y;
        this.target = target;

        this.h      = hauristick();
    }

    public Node(Node parent, Vector2 pos, int x, int y) {
        this.parent = parent;
        this.pos    = pos;
        this.x      = x;
        this.y      = y;
    }

    public void addNode(Node node) {

        if(h > node.getH()) {
            if(rightLeaf == null) {
                node.setParent(this);
                rightLeaf = node;
            } else {
                node.addNode(node);
            }
        } else if(h <= node.getH()) {
            if(leftLeaf == null) {
                leftLeaf = node;
                node.setParent(this);
            } else {
                node.addNode(node);
            }
        }
    }

    private float hauristick() {
        Vector2 positionTarget = target.getPos();
        return (float) Math.sqrt(Math.pow(positionTarget.x - pos.x, 2) + Math.pow(positionTarget.y - pos.y,2));
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeftLeaf() {
        return leftLeaf;
    }

    public void setLeftLeaf(Node leftLeaf) {
        this.leftLeaf = leftLeaf;
    }

    public Node getRightLeaf() {
        return rightLeaf;
    }

    public void setRightLeaf(Node rightLeaf) {
        this.rightLeaf = rightLeaf;
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

    public Node getTarget() {
        return target;
    }

    public void setTarget(Node target) {
        this.target = target;
        this.h      = hauristick();
    }
}
