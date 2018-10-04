package com.evanho9.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.evanho9.boxmatch.BoxMatch;
import com.evanho9.game.GameLogic;
import com.evanho9.gameobject.Square;

import javax.swing.Box;


/**
 * Created by Evan on 4/15/2017.
 */

public class DeathScreen implements Screen {

    private BoxMatch game;

    private Stage hud;


    private ImageButton replayButton;
    private TextureRegion background;

    public DeathScreen(BoxMatch game) {
        this.game = game;
        background = game.getTextureAtlas().findRegion("background");
        System.out.println("death");
        initHUD();
    }

    public void initHUD() {
        hud = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        Skin buttonSkin = new Skin();
        buttonSkin.addRegions(game.getAssetManager().get(BoxMatch.MASTER_PATH, TextureAtlas.class));

        replayButton = new ImageButton(buttonSkin.getDrawable("playbutton"));
        replayButton.setSize(Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/3);
        replayButton.setPosition(Gdx.graphics.getWidth()/2-replayButton.getWidth()/2, Gdx.graphics.getHeight()/2-replayButton.getHeight()/2);
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
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.input.setInputProcessor(hud);
        hud.act();

        hud.getBatch().begin();
        hud.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        hud.getBatch().end();
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
