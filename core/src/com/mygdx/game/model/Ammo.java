package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Sash on 11.05.2018.
 */

public class Ammo extends GameObject
{
    private float centerX;
    private float centerY;
    private float width;
    private float height;

    private float speed;
    private float damage;
    private TextureRegion textureRegion;

    private float rotation;
    /*public Ammo(TextureRegion textureRegion, float x, float y, float width, float height, float speed, float damage) {
        super(textureRegion, x, y, width, height);
        this.speed=speed;
        this.damage=damage;
        this.textureRegion=textureRegion;

    }*/
    public Ammo(TextureRegion textureRegion, float x, float y, float width, float height, float speed, float damage,float rotation) {
        super(textureRegion, x, y, width, height);
        this.speed=speed;
        this.damage=damage;
        this.width=width;
        this.height=height;
        centerX=x+width/2;
        centerY=y+height/2;
        this.textureRegion=textureRegion;
        this.rotation=rotation;

        setRotation(rotation);

    }
    public Ammo(Ammo ammo)
    {
        super(ammo.getTextureRegion(),ammo.getX(),ammo.getY(),ammo.getWidth(),ammo.getHeight());
        speed=ammo.speed;
        damage=ammo.damage;
        textureRegion=ammo.getTextureRegion();
    }
    public void move()
    {

        bounds.setPosition(bounds.getX() - (float)(speed*Math.sin(Math.toRadians(rotation))), bounds.getY()+(float) (speed*Math.cos(Math.toRadians(rotation))));

    }

    public float getSpeed() {
        return speed;
    }

    public float getDamage() {
        return damage;
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);

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



    public TextureRegion getTextureRegion() {
        return textureRegion;
    }
}