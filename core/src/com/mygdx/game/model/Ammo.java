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
    private float rotation;
    public Ammo(TextureRegion textureRegion, float x, float y, float width, float height, float speed, float damage) {
        super(textureRegion, x, y, width, height);
        this.speed=speed;
        this.damage=damage;
        this.textureRegion=textureRegion;
        rotationPosition=1;
    }
    public Ammo(TextureRegion textureRegion, float x, float y, float width, float height, float speed, float damage,float rotation) {
        super(textureRegion, x, y, width, height);
        this.speed=speed;
        this.damage=damage;
        this.textureRegion=textureRegion;
        this.rotation=rotation;
        setRotation(rotation);
        setRotationPosition(rotationPosition);
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