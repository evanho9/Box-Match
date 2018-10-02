package com.evanho9.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.evanho9.boxmatch.BoxMatch;
import com.evanho9.game.GameLogic;
import com.evanho9.gameobject.Square;


/**
 * Created by Evan on 4/15/2017.
 */

public class DeathScreen implements Screen {

    private BoxMatch game;

    private Stage hud;
    private OrthographicCamera camera;

    private SpriteBatch batch;

    private ImageButton replayButton;
    private TextureRegion background;

    public DeathScreen(BoxMatch game) {
        this.game = game;
        background = game.getTextureAtlas().findRegion("background");
        System.out.println("death");
        initHUD();
    }

    public void initHUD() {
        hud = new Stage();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        Skin buttonSkin = new Skin();
        buttonSkin.addRegions(game.getTextureAtlas());

        replayButton = new ImageButton(buttonSkin.getDrawable("playbutton"));
        replayButton.setSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        replayButton.setPosition(Gdx.graphics.getWidth()/2-replayButton.getWidth(), Gdx.graphics.getHeight()/2-replayButton.getHeight());
        replayButton.setVisible(true);
        replayButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game));
                System.out.println("touched");
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game));
                System.out.println("touched");
            }
        });
        hud.addActor(replayButton);
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(float delta) {
        hud.act();

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        hud.draw();
    }


    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }
}
