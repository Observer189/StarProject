package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Sash on 30.05.2018.
 */

public class ProgressBar {
    private float x;
    private float y;
    private float width;
    private float height;

    private TextureRegion barTexture;
    private TextureRegion lineTexture;
    SpriteBatch batch;

    private float maxValue;

    public ProgressBar(SpriteBatch batch,TextureRegion barTexture,TextureRegion lineTexture,float x, float y, float width, float height,float maxValue)
    {
     this.batch=batch;
     this.barTexture=barTexture;
     this.lineTexture=lineTexture;
     this.x=x;
     this.y=y;
     this.width=width;
     this.height=height;
     this.maxValue=maxValue;
    }

    public void draw(float currentValue)
    {
        batch.begin();
        batch.draw(lineTexture,x,y,width*(currentValue/maxValue),height);
        batch.draw(barTexture,x,y,width,height);

        batch.end();
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}
