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
    String name;
    private float width;
    private float height;
    private float centerX;
    private float centerY;
    public GameObject(TextureRegion textureRegion, float x,float y,float width,float height,float originX,float originY)
    {

        sprite = new Sprite(textureRegion);
        sprite.setSize(width,height);
        sprite.setOrigin(originX,originY);
        sprite.setPosition(x,y);

        bounds=new Polygon(new float[]{0.f,0.f,width,0.f,width,height,0.f,height});
        bounds.setPosition(x,y);
        bounds.setOrigin(originX,originY);

        this.width=width;
        this.height=height;
        centerX=x+width/2;
        centerY=y+height/2;
    }
    public GameObject(TextureRegion textureRegion, float x,float y,float width,float height)
    {

        sprite = new Sprite(textureRegion);
        sprite.setSize(width,height);
        sprite.setOrigin(width/2,height/2);
        sprite.setPosition(x,y);

        bounds=new Polygon(new float[]{0.f,0.f,width,0.f,width,height,0.f,height});
        bounds.setPosition(x,y);
        bounds.setOrigin(width/2,height/2);

        this.width=width;
        this.height=height;
        centerX=x+width/2;
        centerY=y+height/2;
    }



    public void draw(SpriteBatch batch)
    {
        sprite.setPosition(bounds.getX(),bounds.getY());
        sprite.setRotation(bounds.getRotation());
        centerX=bounds.getX()+width/2;
        centerY=bounds.getY()+height/2;
        batch.begin();
        sprite.draw(batch);
        batch.end();

    }
    public float getX()
    {
       return  bounds.getX();
    }
    public float getY()
    {
        return  bounds.getY();
    }

    public void setPosition(float x,float y) {
        bounds.setPosition(x,y);
    }

    public float getHeight() {
        return height;
    }
    public void setRotation(float degrees)
    {
        bounds.setRotation(degrees);
        sprite.setRotation(degrees);
    }

    public float getWidth() {
        return width ;
    }

    public Polygon getBounds() {
        return bounds;
    }
    public float getCenterX() {
        return centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public void setCenter(float centerX,float centerY)
    {
        bounds.setPosition(centerX-width/2,centerY-height/2);
        this.centerX=centerX;
        this.centerY=centerY;
    }


    public float getRotation()
    {
        return bounds.getRotation();
    }
}
