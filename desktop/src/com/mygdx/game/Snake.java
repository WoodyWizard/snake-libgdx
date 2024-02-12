package com.mygdx.game;

public class Snake {
    private BodyPart[] body;
    private BodyPart head;

    private int size = 0;




    public Snake() {
        this.head = new BodyPart(null,0,0,new int[]{1,0});
        this.body = new BodyPart[100];
        this.body[0] = this.head;
        this.body[1] = new BodyPart(this.head,-1,0, this.body[0].getDirection());
        this.body[2] = new BodyPart(this.head,-2,0, this.body[1].getDirection());
        this.body[3] = new BodyPart(this.head,-3,0, this.body[2].getDirection());
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
    }
}
