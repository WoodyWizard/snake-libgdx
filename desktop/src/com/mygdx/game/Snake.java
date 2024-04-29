package com.mygdx.game;

import com.badlogic.gdx.Gdx;

public class Snake {
    private BodyPart[] body;
    private BodyPart head;
    private int size = 0;


    public BodyPart[] getBody() {
        return this.body;
    }

    public int getSize() {
        return this.size;
    }

    public Snake() {
        this.head = new BodyPart(null,11,5,new int[]{1,0});
        this.body = new BodyPart[100];
        this.body[0] = this.head;
        this.body[1] = new BodyPart(this.body[0],10,5, this.body[0].getDirection());
        this.body[2] = new BodyPart(this.body[1],9,5, this.body[1].getDirection());
        this.body[3] = new BodyPart(this.body[2],8,5, this.body[2].getDirection());
        this.size = 4;

    }

    public void setNewDirection(int[] direction) {
        this.head.setNewDirection(direction);
    }


    public void addToTail() {
        this.body[size] = new BodyPart(this.body[size-1],
                                    this.body[size-1].getX() - this.body[size-1].getDirection()[0],
                                    this.body[size-1].getY() - this.body[size-1].getDirection()[1],
                                        this.body[size-1].getDirection());
        this.size++;
    }


    public void moveSnake(int[][] world) {
        if (world[this.head.getX() + this.head.getDirection()[0]][this.head.getY() + this.head.getDirection()[1]] == 999) {
            this.addToTail();
        }
        if (world[this.head.getX() + this.head.getDirection()[0]][this.head.getY() + this.head.getDirection()[1]] == -1) {
            Gdx.app.exit();
        }
        for (int i = this.size-1; i > 0; i--) {
            this.body[i].setNewPosition(this.body[i-1].getX(), this.body[i-1].getY());
            this.body[i].setNewDirection(this.body[i-1].getDirection());
        }
        this.head.setNewPosition(this.head.getX() + this.head.getDirection()[0], this.head.getY() + this.head.getDirection()[1]);
        for (int i = 1; i < this.size; i++) {
            if (this.body[i].getX() == this.head.getX() && this.body[i].getY() == this.head.getY()) {
                // stopping the game
                Gdx.app.exit();
            }
        }
    }

    public int[] getDirection() {
        return this.head.getDirection();
    }
}
