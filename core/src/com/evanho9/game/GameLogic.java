package com.evanho9.game;

import java.util.Random;
import java.util.TreeSet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.evanho9.gameobject.Square;

public class GameLogic {
    private final int numRows = 10;
    private final int numCols = 10;
    
    private final Color[] colors = {new Color(1, 0.298f, 0.658f, 1), new Color(0.407f, 1, 0.388f, 1), new Color(0.247f, 0.961f, 1, 1)};

    private AssetManager assetManager;

    private Square[][] squares;
    private Stage stage;
    
    private BitmapFont font;
    private ShapeRenderer shapeRenderer;
    private Random rng;
    
    private double score;
    public Square selectedSquare;  

    public GameLogic(AssetManager assetManager) {
        this.assetManager = assetManager;
        rng = new Random();
        stage = new Stage();
        initGameBoard();
        initFont();
        Gdx.input.setInputProcessor(stage);
    }

    public void initGameBoard() {
        squares = new Square[numRows][numCols];
        for (int i = 0; i < 10; i++) {
            int row = (int) (Math.random() * numRows);
            int col = (int) (Math.random() * numCols);
            squares[row][col] = getRandomColorSquare(row, col);
            squares[row][col].addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    Square clickedSquare = (Square)(event.getTarget());
                    if (selectedSquare == null) {
                        selectedSquare = clickedSquare;
                        return true;
                    }

                    if (clickedSquare != selectedSquare && checkIfSameColor(selectedSquare, clickedSquare)) {
                        selectedSquare.remove();
                        removeSquare(selectedSquare.getRow(), selectedSquare.getCol());
                        removeSquare(clickedSquare.getRow(), clickedSquare.getCol());
                        removeArea(clickedSquare.getColor(), Math.min(selectedSquare.getRow(), clickedSquare.getRow()), Math.min(selectedSquare.getCol(), clickedSquare.getCol()),
                                    Math.max(selectedSquare.getRow(), clickedSquare.getRow()), Math.max(selectedSquare.getCol(), clickedSquare.getCol()));
                    }

                    selectedSquare = null;
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                }
            });
            stage.addActor(squares[row][col]);
        }
    }
    
    
    public boolean checkIfSameColor(Square first, Square second) {
        if (first.getColor() == second.getColor())
            return true;
        return false;
    }
    
    public void removeSquare(int row, int col) {
        squares[row][col].remove();
        squares[row][col] = null;
        score += 50;
    }

    public void removeArea(Color color, int startRow, int startCol, int endRow, int endCol) {
        for (int row = startRow; row <= endRow; row++) {
            for (int col = startCol; col <= endCol; col++) {
                if (squares[row][col] != null && squares[row][col].getColor() == color) {
                    removeSquare(row, col);
                }
            }
        }
    }
    
    public void initFont() {
        //FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("timeburnernormal.ttf"));
        //FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        //parameter.size = 40;
        //parameter.color = Color.BLACK;

        //font = generator.generateFont(parameter);
        //font.setUseIntegerPositions(false);

        shapeRenderer = new ShapeRenderer();
    }

    public Square getRandomColorSquare(int row, int col) {
        Color color = colors[rng.nextInt(colors.length)];
        return new Square(color, row, col);
    }
    
    public boolean isBoardFull() {
        for (Square[] row : squares) {
            for(Square square : row) {
                if (square == null)
                    return false;
            }
        }
        return true;
    }

    public void render(SpriteBatch batch) {
        Gdx.input.setInputProcessor(stage);
        stage.act();
        for (int r = 0; r < squares.length; r++) {
            for (int c = 0; c < squares[0].length; c++) {
                if (squares[r][c] != null)
                    squares[r][c].render(batch);
            }
        }
        batch.begin();
        //TODO remove magic number
        //font.draw(batch, "Score: " + score, Gdx.graphics.getWidth()/10, (float) (5*Gdx.graphics.getHeight()/6));
        batch.end();
        
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.begin(ShapeType.Line);
        if (selectedSquare != null) {
            shapeRenderer.rect(selectedSquare.getX(), selectedSquare.getY(), selectedSquare.getWidth(), selectedSquare.getHeight());
        }
        shapeRenderer.end();
    }
    
    public void update() {
        int row = rng.nextInt(numRows);
        int col = rng.nextInt(numCols);
        if (!isBoardFull()) {
            if (squares[row][col] == null) {
                squares[row][col] = getRandomColorSquare(row, col);
                squares[row][col].addListener(new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        Square clickedSquare = (Square) (event.getTarget());

                        if (selectedSquare == null) {
                            selectedSquare = clickedSquare;
                            return true;
                        }

                        if (clickedSquare != selectedSquare && checkIfSameColor(selectedSquare, clickedSquare)) {
                            selectedSquare.remove();
                            removeSquare(selectedSquare.getRow(), selectedSquare.getCol());
                            removeSquare(clickedSquare.getRow(), clickedSquare.getCol());
                            removeArea(clickedSquare.getColor(), Math.min(selectedSquare.getRow(), clickedSquare.getRow()), Math.min(selectedSquare.getCol(), clickedSquare.getCol()),
                                    Math.max(selectedSquare.getRow(), clickedSquare.getRow()), Math.max(selectedSquare.getCol(), clickedSquare.getCol()));
                        }

                        selectedSquare = null;
                        return true;
                    }

                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                    }
                });
                stage.addActor(squares[row][col]);
            } else {
                update();
            }
        }
    }

    
}
