package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Sash on 11.05.2018.
 */

public class Ammo extends GameObject
{

    private float width;
    private float height;

    private float speed;
    private float damage;
    private float appliedDistance;
    private float maxRange;
    private TextureRegion textureRegion;


    /*public Ammo(TextureRegion textureRegion, float x, float y, float width, float height, float speed, float damage) {
        super(textureRegion, x, y, width, height);
        this.speed=speed;
        this.damage=damage;
        this.textureRegion=textureRegion;

    }*/
    public Ammo(TextureRegion textureRegion, float x, float y, float width, float height, float speed, float damage,float maxRange,float rotation) {
        super(textureRegion, x, y, width, height,0,0);
        this.speed=speed;
        this.damage=damage;
        this.width=width;
        this.height=height;

        this.textureRegion=textureRegion;


        setRotation(rotation);
        this.maxRange=maxRange;
        appliedDistance=0;
    }
    public Ammo(Ammo ammo)
    {
        super(ammo.getTextureRegion(),ammo.getX(),ammo.getY(),ammo.getWidth(),ammo.getHeight());
        speed=ammo.speed;
        damage=ammo.damage;
        textureRegion=ammo.getTextureRegion();
    }
    public void move(Ship enemyShip)
    {

        bounds.setPosition(bounds.getX() - (float)(speed*Math.sin(Math.toRadians(getRotation()))), bounds.getY()+(float) (speed*Math.cos(Math.toRadians(getRotation()))));
        appliedDistance+=speed;
    }

    public float getSpeed() {
        return speed;
    }

    public float getDamage() {
        return damage;
    }

    public float getAppliedDistance() {
        return appliedDistance;
    }

    public float getMaxRange() {
        return maxRange;
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);

    }





    public TextureRegion getTextureRegion() {
        return textureRegion;
    }
}