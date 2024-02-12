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

	@Override
	public void create() {
		world = new World(30,30);

		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		camera.position.set(0,0,0);
		camera.update();

		myInput = new GameInputProcessor(camera, world.getSnake());
		Gdx.input.setInputProcessor(myInput);

		viewport = new FitViewport(30*20,30*20, camera);

		shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();

		font = new BitmapFont();
		font.setColor(1,1,1,1);
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

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
			shapeRenderer.setColor(0.2f, 0.2f, 0.2f, 1); // Set your viewport background color here
			shapeRenderer.rect(camera.position.x - viewport.getWorldWidth() / 2,
				camera.position.y - viewport.getWorldHeight() / 2,
				viewport.getWorldWidth(),
				viewport.getWorldHeight());

			shapeRenderer.setColor(1,1,1,1);
			shapeRenderer.rect(100, 10, 50, 50);
		shapeRenderer.end();

		batch.begin();
			font.draw(batch, "Hello, LibGDX!", 0, 0); // Draw text at position (100, 100)
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
