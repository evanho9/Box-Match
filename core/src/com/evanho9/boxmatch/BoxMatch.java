package com.evanho9.boxmatch;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evanho9.screen.DeathScreen;
import com.evanho9.screen.GameScreen;

public class BoxMatch extends Game {
	public final static String MASTER_PATH = "master.pack";
	
	private AssetManager assetManager;
    private Preferences preferences;
	
	@Override
	public void create () {
		//Gdx.graphics.setContinuousRendering(false);
		assetManager = new AssetManager();
		preferences = Gdx.app.getPreferences("boxMatch");
		loadAssets();
        //setScreen(new GameScreen(this));
		setScreen(new DeathScreen(this));
	}

	public void loadAssets() {
		assetManager.load(MASTER_PATH, TextureAtlas.class);
		assetManager.finishLoading();
	}
	
	@Override
    public void dispose() {
        super.dispose();
        assetManager.dispose();
    }
	
	public AssetManager getAssetManager() {
		return assetManager;
	}
	
	public TextureAtlas getTextureAtlas() {
	    return assetManager.get(MASTER_PATH);
	}
}
