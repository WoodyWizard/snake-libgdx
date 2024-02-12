package com.mygdx.game;

public class BodyPart {

    private BodyPart next;
    private int x;
    private int y;
    private int[] direction;

    public BodyPart(BodyPart next ,int x, int y, int[] direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.next = next;
    }

    public void setNewPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setNewDirection(int[] direction) {
        this.direction = direction;
    }

    public int[] getDirection() {
        return this.direction;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

}
