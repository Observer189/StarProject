package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.utils.Vector2D;
import com.mygdx.game.view.Battle;

/**
 * Created by Sash on 01.05.2018.
 */

public class Ship extends GameObject {
    String name;
    int cost;
    int maxHp;
    int currentHp;
    float speedX;
    float speedY;
    float velocity;
    float maxSpeed;
    Vector2D movementVector;
    public Ship(TextureRegion textureRegion, float x, float y, float width, float height,String name,int cost,int maxHp,float velocity,float maxSpeed) {
        super(textureRegion, x, y, width, height);
        this.name=name;
        this.cost=cost;
        this.maxHp=maxHp;
        currentHp=maxHp;
        this.velocity=velocity;
        this.maxSpeed=maxSpeed;
    }


    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }
    public void move()
    {
        //speedX=speedX+velocity*movementVector.vX;
        //speedY=speedY+velocity*movementVector.vY;
        speedX=2;
        speedY=2;

        bounds.setPosition(bounds.getX()+speedX* Battle.delta,bounds.getY()+speedY*Battle.delta);
    }

    public void setMovementVector(Vector2D movementVector) {
        this.movementVector = movementVector;
    }
}
