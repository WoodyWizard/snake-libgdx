package com.mygdx.game;

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
        snake = new Snake();
    }

    public Snake getSnake(){
        return snake;
    }

    private int[][] handleSnake() {
        BodyPart[] body = snake.getBody();
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                if (world[i][j] != -1)
                    world[i][j] = 0;
            }
        }
        for (int i = 0; i < snake.getSize(); i++) {
            world[body[i].getX()][body[i].getY()] = 1;
        }
        return this.world;
    }

    public void updateWorld(){
        snake.moveSnake();
        handleSnake();
    }

    public int[][] getWorld(){
        return world;
    }

    public int getCell(int x, int y){
        return world[x][y];
    }


}
