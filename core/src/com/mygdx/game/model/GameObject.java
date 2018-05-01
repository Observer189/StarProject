package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;

/**
 * Created by Sash on 01.05.2018.
 */

public abstract class GameObject {
    Polygon bounds;
    Sprite sprite;
    public GameObject(TextureRegion textureRegion, float x,float y,float width,float height)
    {
       sprite = new Sprite(textureRegion);
       sprite.setSize(width,height);
       sprite.setOrigin(width/2,height/2);
       sprite.setPosition(x,y);

       bounds=new Polygon(new float[]{0.f,0.f,width,0.f,width,height,0.f,height});
       bounds.setPosition(x,y);
       bounds.setOrigin(width/2,height/2);
    }
    public void draw(SpriteBatch batch)
    {
        sprite.setPosition(bounds.getX(),bounds.getY());
        sprite.setRotation(bounds.getRotation());
        sprite.draw(batch);
    }
}