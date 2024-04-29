package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.MyGdxGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {


	public static void main(String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("My GDX Game");
		new Lwjgl3Application(new MyGame(), config);
	}

}

class MyGame extends ApplicationAdapter {

	FitViewport viewport;
	OrthographicCamera camera;
	ShapeRenderer shapeRenderer;

	SpriteBatch batch;
	BitmapFont font;
	GameInputProcessor myInput;
	World world;

	int worldUnits = 20;

	@Override
	public void create() {
		world = new World(30,30);
		world.updateWorld();

		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		camera.position.set(15,15,0);
		camera.setToOrtho(true, 30, 30);
		//camera.zoom = 0.8f;
		camera.update();

		myInput = new GameInputProcessor(camera, world.getSnake());
		Gdx.input.setInputProcessor(myInput);

		viewport = new FitViewport(30, 30, camera);

		shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();

		font = new BitmapFont();
		font.setColor(1,1,1,1);


		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				world.updateWorld();
			}
		}, 0.2f , 0.2f);
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width,height);
	}

	@Override
	public void render() {
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);
		//world.updateWorld();
		int [][] renderedWorld = world.getWorld();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
			shapeRenderer.setColor(0.2f, 0.2f, 0.2f, 1);

			for (int i = 0; i < renderedWorld.length; i++) {
				for (int j = 0; j < renderedWorld[i].length; j++) {
					if (renderedWorld[i][j] == 1) {
						shapeRenderer.setColor(1f, 1f, 1f, 1);
						shapeRenderer.rect(i, j, 20*worldUnits, 20*worldUnits);
					}
					if (renderedWorld[i][j] == -1) {
						shapeRenderer.setColor(0.5f, 0.1f, 0.1f, 1);
						shapeRenderer.rect(i, j, 20, 20);
					}
					if (renderedWorld[i][j] == 0) {
						shapeRenderer.setColor(0.2f, 0.2f, 0.2f, 1);
						shapeRenderer.rect(i, j, 20, 20);
					}
					if (renderedWorld[i][j] == 999) {
						shapeRenderer.setColor(1.0f, 0.5f, 0.1f, 1);}
						shapeRenderer.rect(i, j, 20, 20);
					}
			}
			shapeRenderer.setColor(1,1,1,1);
		shapeRenderer.end();

		batch.begin();
		batch.end();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}
