package com.mygdx.game;

public class World {

    private int[][] world;
    private Snake snake;

    public World(int width, int height){
        world = new int[width][height];
        snake = new Snake();
    }

    public Snake getSnake(){
        return snake;
    }


}
