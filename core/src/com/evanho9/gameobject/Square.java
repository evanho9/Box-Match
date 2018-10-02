package com.evanho9.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Square extends Actor {

    private Color color;
    private int row;
    private int col;
    private Texture shape;

    public Square (Color color, int row, int col) {
        this.color = color;
        this.row = row;
        this.col = col;
        shape = new Texture(Gdx.files.internal("raw/square.png"));

        setSize(Gdx.graphics.getWidth()/15, (float)(Gdx.graphics.getHeight()/26.6875));
        setPosition((float)0.166667*(Gdx.graphics.getWidth()) + getWidth() * col, (float)0.3126*(Gdx.graphics.getHeight()) + getHeight() * row);
    }

    public void render(SpriteBatch batch) {
        batch.setColor(color);
        batch.begin();
        batch.draw(shape, getX(), getY(), getWidth(), getHeight());
        batch.end();
        batch.setColor(Color.WHITE);
    }
    
    public Color getColor() {
        return color;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }

}
