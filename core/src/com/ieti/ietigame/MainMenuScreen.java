package com.ieti.ietigame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainMenuScreen implements Screen {
    final IetiGame game;
    OrthographicCamera camera;

    public MainMenuScreen(final IetiGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,800,480);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0.2f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.begin();
        game.font.draw(game.batch, "Bevinguts a IETI GAMES !",100,150);
        game.font.draw(game.batch,"Toca on vulguis per començar :)",100,100);
        game.font.draw(game.batch,"Delta="+Float.toString(delta),100,50);
        game.batch.end();

        if( Gdx.input.isTouched() ) {
            game.setScreen(new Level1Screen(game) );
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        // TODO: music play
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

}
