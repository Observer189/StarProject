package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.utils.TextManager;
import com.mygdx.game.utils.Vector2D;
import com.mygdx.game.view.Battle;

import static java.lang.Float.NaN;

/**
 * Created by Sash on 01.05.2018.
 */

public class Ship extends GameObject {

    int cost;
    private boolean isShipInRedZone;
    private boolean isAlive;
    private int maxHp;
    private int currentHp;
    private float speedX;
    private float speedY;
    private float velocity;
    private float maxSpeed;
    private Integer currentExplosionFrame;
    private int explosionCounter;
    TextureRegion explosionRegion;
    Vector2 movementVector;
    FixingPoint[] fixingPoints;

    public Ship(TextureRegion textureRegion, float x, float y, float width, float height, String name, int cost, int maxHp, float velocity, float maxSpeed, FixingPoint[] fixingPoints) {
        super(textureRegion, x, y, width, height);

        this.cost = cost;
        this.maxHp = maxHp;
        currentHp = maxHp;
        this.velocity = velocity;
        this.maxSpeed = maxSpeed;
        this.name = name;
        isShipInRedZone = false;
        isAlive=true;

        currentExplosionFrame=0;
        explosionCounter=7;
        movementVector = new Vector2(0, 0);
        this.fixingPoints = fixingPoints;
    }

    public FixingPoint[] getFixingPoints() {
        return fixingPoints;
    }

    public void setFixingPoints(FixingPoint[] fixingPoints) {
        this.fixingPoints = fixingPoints;
    }


    public void draw(SpriteBatch batch,TextureAtlas textureAtlas) {
        if (currentHp>0) {
            for (int i = 0; i < fixingPoints.length; i++) {
                fixingPoints[i].draw(batch);
            }

            super.draw(batch);

        } else
        {
            destructShip(batch,textureAtlas);
        }


    }
    public void destructShip(SpriteBatch batch,TextureAtlas textureAtlas)
    {
        if(currentExplosionFrame<=17) {
            if (explosionCounter == 7) {
                currentExplosionFrame++;
                explosionRegion = textureAtlas.findRegion("Explosion" + currentExplosionFrame.toString());
                explosionCounter = 0;
            }
            batch.begin();
            batch.draw(explosionRegion, getX(), getY(), getWidth(), getHeight());
            batch.end();
            explosionCounter++;
        }
        else
        {
            isAlive=false;
        }
    }



    public void shot()
    {
        for(int i=0;i<fixingPoints.length;i++)
        {
            fixingPoints[i].shot();
        }
    }

    public void move(Map map) {
        if(currentHp>0) {
            speedX = speedX + velocity * movementVector.x;
            speedY = speedY + velocity * movementVector.y;
            //speedX=2;
            //speedY=2;
            if (speedX > maxSpeed) speedX = maxSpeed;
            if (speedX < -maxSpeed) speedX = -maxSpeed;
            if (speedY > maxSpeed) speedY = maxSpeed;
            if (speedY < -maxSpeed) speedY = -maxSpeed;
            bounds.setPosition(bounds.getX() + speedX * Battle.delta, bounds.getY() + speedY * Battle.delta);


            if ((bounds.getX() > 0 + map.getWidth() * 0.85) || (bounds.getX() < 0 + map.getWidth() * 0.15) || (bounds.getY() > 0 + map.getHeight() * 0.85) || (bounds.getY() < 0 + map.getHeight() * 0.15)) {
                isShipInRedZone = true;
            } else isShipInRedZone = false;

            for (int i = 0; i < fixingPoints.length; i++) {
                fixingPoints[i].update(this, map);
            }

            if ((bounds.getX() > 0 + map.getWidth() * 0.95) || (bounds.getX() < 0 + map.getWidth() * 0.05) || (bounds.getY() > 0 + map.getHeight() * 0.95) || (bounds.getY() < 0 + map.getHeight() * 0.05)) {
                currentHp = 0;
            }

        }

    }

    public void setMovementVector(Vector2 movementVector) {

        this.movementVector.x = movementVector.x;

        this.movementVector.y = movementVector.y;
    }

    public float getSpeedX() {
        return speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public boolean getIsShipInRedZone() {
        return isShipInRedZone;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public int getCurrentHp() {
        return currentHp;
    }
}
