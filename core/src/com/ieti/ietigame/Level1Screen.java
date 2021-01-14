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

    Texture background;
    TextureRegion bgRegion;
    public int fonsx;
    public int fonsy;

    Animation<TextureRegion> walkLeft;
    Animation<TextureRegion> walkRight;
    Animation<TextureRegion> walkUp;
    Animation<TextureRegion> walkDown;
    float stateTime;
    // personatge
    Texture ietiSheet;
    int FRAME_ROWS = 5;
    int FRAME_COLS = 4;

    public Level1Screen(final IetiGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,800,480);

        // bg
        background = new Texture(Gdx.files.internal("background12.jpeg"));
        background.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
        bgRegion = new TextureRegion(background,0,0,game.SCR_WIDTH,game.SCR_HEIGHT);
        fonsx = 0;
        fonsy = 0;

        // personatge IETI
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

        // CONTROLS
        TextureRegion frame = walkDown.getKeyFrames()[0];
        // triem animació i movem background segons la tecla premuda
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            frame = walkLeft.getKeyFrame(stateTime, true);
            fonsx -= 2;
            bgRegion.setRegion(fonsx,fonsy,fonsx+game.SCR_WIDTH,fonsy+game.SCR_HEIGHT);
        } else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            frame = walkRight.getKeyFrame(stateTime, true);
            fonsx += 2;
            bgRegion.setRegion(fonsx,fonsy,fonsx+game.SCR_WIDTH,fonsy+game.SCR_HEIGHT);
        } else if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            frame = walkUp.getKeyFrame(stateTime, true);
            fonsy -= 1;
            bgRegion.setRegion(fonsx,fonsy,fonsx+game.SCR_WIDTH,fonsy+game.SCR_HEIGHT);
        } else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            frame = walkDown.getKeyFrame(stateTime, true);
            fonsy += 1;
            bgRegion.setRegion(fonsx,fonsy,fonsx+game.SCR_WIDTH,fonsy+game.SCR_HEIGHT);
        }

        // PINTA
        game.batch.begin();
        game.batch.draw(bgRegion,0,0);
        game.batch.draw(frame,380,200);
        game.batch.end();

        // Final de nivell
        if( Gdx.input.isKeyPressed(Input.Keys.ESCAPE) ) {
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
