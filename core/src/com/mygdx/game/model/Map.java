package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Sash on 09.05.2018.
 */

public class Map {

    private float width;
    private float height;
    TextureRegion texture;
    SpriteBatch batch;
    public Map(SpriteBatch batch,TextureRegion texture,float width, float height)
    {
        this.batch=batch;
        this.texture=texture;
        this.width=width;
        this.height=height;

    }
    public void draw()
    {
        batch.begin();
        batch.draw(texture,0f,0f,width,height);
        batch.end();
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
