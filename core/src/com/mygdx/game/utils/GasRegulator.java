package com.mygdx.game.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.Ship;

/**
 * Created by Sash on 03.06.2018.
 */

public class GasRegulator {
    private float x;
    private float y;

    private float width;
    private float height;

    SpriteBatch batch;

    TextureRegion gasRegion;
    TextureRegion stopRegion;
    TextureRegion backRegion;

    int movementPosition;//0-стоп 1-газ -1-назад

    Ship playerShip;
    public GasRegulator(SpriteBatch batch, float x, float y, float width, float height, TextureAtlas textureAtlas,Ship playerShip)
    {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.batch=batch;

        gasRegion=textureAtlas.findRegion("Gas");
        stopRegion=textureAtlas.findRegion("Stop");
        backRegion=textureAtlas.findRegion("Back");

        this.playerShip=playerShip;
        movementPosition=0;
    }

    public void draw()
    {
        batch.begin();
        if(movementPosition==-1)
            batch.draw(backRegion,x,y,width,height);
        if(movementPosition==0)
            batch.draw(stopRegion,x,y,width,height);
        if(movementPosition==1)
            batch.draw(gasRegion,x,y,width,height);
        batch.end();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setMovementPosition(int movementPosition) {
        this.movementPosition = movementPosition;
        playerShip.setMovementPosition(movementPosition);
    }

    public int getMovementPosition() {
        return movementPosition;
    }
}
