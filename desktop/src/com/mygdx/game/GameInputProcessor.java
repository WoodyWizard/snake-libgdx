package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameInputProcessor implements InputProcessor {

    private OrthographicCamera camera;
    private Snake snake;
    private int camera_speed = 5;

    public GameInputProcessor(OrthographicCamera camera, Snake snake) {
        this.camera = camera;
        this.snake = snake;
    }

    @Override
    public boolean keyDown(int keycode) {
        int [] l = snake.getDirection().clone();

        int [] delta = new int[]{0,0};

        if ((Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) &&
                l[1] == 0
        )
            delta[1] = -1;
            //camera.translate(0, camera_speed);
        if ((Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) &&
                l[1] == 0
        )
            delta[1] = 1;
            //camera.translate(0, -camera_speed);
        if ((Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) &&
                l[0] == 0
        )
            delta[0] = -1;
            //camera.translate(-camera_speed, 0);
        if ((Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) &&
                l[0] == 0
        )
            delta[0] = 1;
            //camera.translate(camera_speed, 0);

        l[0] = l[0] + delta[0];
        l[1] = l[1] + delta[1];

        if (l[0] != 0 && l[1] != 0) {
            snake.setNewDirection(delta);
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
