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
import com.evanho9.boxmatch.BoxMatch;
import com.evanho9.game.GameLogic;


public class GameScreen implements Screen {

    private BoxMatch game;
    private GameLogic gameLogic;

    private Stage hud;
    private OrthographicCamera camera;

    private SpriteBatch batch;
    private float clock;
    private float delay;
    private boolean dead;

    private ImageButton pauseButton;
    private ImageButton replayButton;
    private TextureRegion background;

    public GameScreen(BoxMatch game) {
        this.game = game;
        this.gameLogic = new GameLogic(game.getAssetManager());
        clock = 0;
        delay = 1;
        dead = false;
        background = game.getTextureAtlas().findRegion("background");
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
        
        pauseButton = new ImageButton(buttonSkin.getDrawable("pausebutton"));
        pauseButton.setSize(Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight()/8);
        pauseButton.setPosition((float)(Gdx.graphics.getWidth()-pauseButton.getWidth()),(float)(Gdx.graphics.getHeight()-pauseButton.getHeight()));
        hud.addActor(pauseButton);

        replayButton = new ImageButton(buttonSkin.getDrawable("playbutton"));
        //replayButton.setSize(Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight()/8);
        replayButton.setPosition((float)(Gdx.graphics.getWidth()/2-replayButton.getWidth()/2),(float)(Gdx.graphics.getHeight()/2-replayButton.getHeight()/2));
        replayButton.setVisible(false);
        replayButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("pls");
                game.setScreen(new GameScreen(game));
                return false;
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
        if (!dead) {
            if (gameLogic.isBoardFull()) {
                replayButton.setVisible(true);
                dead = true;
                Gdx.input.setInputProcessor(hud);
                //game.setScreen(new DeathScreen(game));
            }
            clock += delta;
            if (clock >= delay) {
                gameLogic.update();
                clock = 0;
                if (delay >= 0.1)
                    delay -= 0.025;
            }
            Gdx.gl.glClearColor(0, 0, 0.2f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.end();
            gameLogic.render(batch);
            hud.draw();
        }
        hud.act();
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
