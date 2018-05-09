package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private int maxHp;
    private int currentHp;
    private float speedX;
    private float speedY;
    private float velocity;
    private float maxSpeed;
    Vector2 movementVector;
    public Ship(TextureRegion textureRegion, float x, float y, float width, float height,String name,int cost,int maxHp,float velocity,float maxSpeed) {
        super(textureRegion, x, y, width, height);

        this.cost=cost;
        this.maxHp=maxHp;
        currentHp=maxHp;
        this.velocity=velocity;
        this.maxSpeed=maxSpeed;
        isShipInRedZone=false;
        movementVector=new Vector2(0,0);
    }


    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);

    }
    public void move(Map map)
    {
        speedX=speedX+velocity*movementVector.x;
        speedY=speedY+velocity*movementVector.y;
        //speedX=2;
        //speedY=2;
        if(speedX>maxSpeed)speedX=maxSpeed;
        if(speedX<-maxSpeed)speedX=-maxSpeed;
        if(speedY>maxSpeed)speedY=maxSpeed;
        if(speedY<-maxSpeed)speedY=-maxSpeed;
        bounds.setPosition(bounds.getX()+speedX* Battle.delta,bounds.getY()+speedY*Battle.delta);


        if((bounds.getX()>0+map.getWidth()*0.85)||(bounds.getX()<0+map.getWidth()*0.15)||(bounds.getY()>0+map.getHeight()*0.85)||(bounds.getY()<0+map.getHeight()*0.15))
    {
        isShipInRedZone=true;
    }
    else isShipInRedZone=false;
    }

    public void setMovementVector(Vector2 movementVector) {

        this.movementVector.x=movementVector.x;

        this.movementVector.y=movementVector.y;
    }

    public float getSpeedX() {
        return speedX;
    }

    public float getSpeedY() {
        return speedY;
    }
    public boolean getIsShipInRedZone()
    {
        return isShipInRedZone;
    }
}
