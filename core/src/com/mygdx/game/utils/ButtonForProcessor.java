package com.mygdx.game.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Sash on 18.05.2018.
 */

public class ButtonForProcessor {
    private float x;
    private float y;

    private float width;
    private float height;

    SpriteBatch batch;
    TextureRegion vision;
    public ButtonForProcessor(SpriteBatch batch,float x,float y,float width,float height,TextureRegion vision)
    {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.batch=batch;
        this.vision=vision;
    }
    public void draw()
    {
        batch.begin();
        batch.draw(vision,x,y,width,height);
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
}
