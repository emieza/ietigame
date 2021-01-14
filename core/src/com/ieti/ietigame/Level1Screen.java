package com.ieti.ietigame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Level1Screen implements Screen {
    final IetiGame game;
    OrthographicCamera camera;
    Texture ietiSheet;
    Animation<TextureRegion> walkLeft;
    Animation<TextureRegion> walkRight;
    Animation<TextureRegion> walkUp;
    Animation<TextureRegion> walkDown;
    float stateTime;
    int FRAME_ROWS = 5;
    int FRAME_COLS = 4;

    public Level1Screen(final IetiGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,800,480);

        ietiSheet = new Texture("ieti-walk.png");
        TextureRegion tmp[][] = TextureRegion.split( ietiSheet,
                                ietiSheet.getWidth()/FRAME_COLS,
                                ietiSheet.getHeight()/FRAME_ROWS);

        // hi ha una animació en cada fila (i)
        TextureRegion frames[];
        for( int i=0; i<FRAME_ROWS; i++ ) {
            frames = new TextureRegion[FRAME_COLS];
            int index = 0;
            for (int j = 0; j < FRAME_COLS; j++) {
                frames[index++] = tmp[i][j];
            }
            // Guardem animació
            // Animation: 0.25 és el període de duració del cada frame
            float periode = 0.15f;
            switch( i ) {
                case 0:
                    walkDown = new Animation<TextureRegion>(periode, frames);
                    break;
                case 1:
                    walkRight = new Animation<TextureRegion>(periode, frames);
                    break;
                case 2:
                    walkUp = new Animation<TextureRegion>(periode, frames);
                    break;
                case 3:
                    walkLeft = new Animation<TextureRegion>(periode, frames);
                    break;
                default:
                    break;
            }
        }
        stateTime = 0.0f;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // CALCULA
        stateTime += delta;
        camera.update();

        // PINTA
        game.batch.begin();
        TextureRegion frame = walkDown.getKeyFrames()[0];
        // triem animació segons la tecla premuda
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            frame = walkLeft.getKeyFrame(stateTime,true);
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            frame = walkRight.getKeyFrame(stateTime,true);
        else if(Gdx.input.isKeyPressed(Input.Keys.UP))
            frame = walkUp.getKeyFrame(stateTime,true);
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            frame = walkDown.getKeyFrame(stateTime,true);

        game.batch.draw(frame,380,200);
        game.batch.end();

        // Final de nivell
        if( Gdx.input.isTouched() ) {
            game.setScreen(new MainMenuScreen(game) );
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
