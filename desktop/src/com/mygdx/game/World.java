package com.mygdx.game;

import java.util.Random;

public class World {

    private int[][] world;
    private Snake snake;

    public World(int width, int height){
        world = new int[width][height];
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if (i == 0 || j == 0 || i == width-1 || j == height-1){
                    world[i][j] = -1;
                }
            }
        }
        snake = new Snake(width*height);
    }

    public Snake getSnake(){
        return snake;
    }

    private int[][] _update() {
        int[] excl = new int[world.length*world[0].length];
        BodyPart[] body = snake.getBody();

        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                if (world[i][j] != -1 && world[i][j] != 999) {
                    world[i][j] = 0;
                    excl[i*world[i].length + j] = 99;
                }
            }
        }

        for (int i = 0; i < snake.getSize(); i++) {
            world[body[i].getX()][body[i].getY()] = 1;
            excl[body[i].getX()*world[0].length + body[i].getY()] = 9;
        }

        Random p = new Random();
        int pp = p.nextInt(10);
        if (pp == 5) {
            int position = p.nextInt(excl.length);
            while (excl[position] != 99) {
                position = p.nextInt(excl.length);
            }
            this.world[position / world[0].length][position % world[0].length] = 999;
        }
        return this.world;
    }

    public void updateWorld(){
        int [] r = snake.getDirection();
        snake.moveSnake(this.world);
        _update();
    }

    public int[][] getWorld(){
        return world;
    }

    public int getCell(int x, int y){
        return world[x][y];
    }


}
