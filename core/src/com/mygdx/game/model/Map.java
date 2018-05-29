package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.Maps.ClassicSpace;
import com.mygdx.game.model.Maps.GalaxyCenter;
import com.mygdx.game.model.Maps.YellowSpace;

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
    public static Map generateMap(SpriteBatch batch, TextureAtlas textureAtlas) {
        int rand = (int) (Math.random() * 3 + 1);
        switch (rand) {
            case 1:
                return new ClassicSpace(batch, textureAtlas);
            case 2:
                return new GalaxyCenter(batch, textureAtlas);
            case 3:
                return new YellowSpace(batch,textureAtlas);
            default:
                return null;
        }
    }
    public static Map generateMap(SpriteBatch batch, TextureAtlas textureAtlas, int rand)//функция с заданной картой для отладки
    {

        switch (rand) {
            case 1:
                return new ClassicSpace(batch, textureAtlas);
            case 2:
                return new GalaxyCenter(batch,textureAtlas);
            case 3:
                return new YellowSpace(batch,textureAtlas);
            default:return null;
        }



    }
}
