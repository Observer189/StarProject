package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Sash on 11.05.2018.
 */

public class Ammo extends GameObject
{
    private float speed;
    private float damage;
    private TextureRegion textureRegion;
    private int rotationPosition;
    public Ammo(TextureRegion textureRegion, float x, float y, float width, float height, float speed, float damage) {
        super(textureRegion, x, y, width, height);
        this.speed=speed;
        this.damage=damage;
        this.textureRegion=textureRegion;
        rotationPosition=1;
    }
    public Ammo(TextureRegion textureRegion, float x, float y, float width, float height, float speed, float damage,int rotationPosition) {
        super(textureRegion, x, y, width, height);
        this.speed=speed;
        this.damage=damage;
        this.textureRegion=textureRegion;
        this.rotationPosition=rotationPosition;
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
        if(rotationPosition==1) {
            bounds.setPosition(bounds.getX() + speed, bounds.getY());
        }
        if(rotationPosition==2) {
            bounds.setPosition(bounds.getX() - speed, bounds.getY());
        }

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

    public void setRotationPosition(int rotationPosition) {
        this.rotationPosition = rotationPosition;
        if(rotationPosition==1)
        {
            setRotation(270);
        }
        if(rotationPosition==2)
        {
            setRotation(90);
        }
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }
}