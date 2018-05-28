package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Sash on 10.05.2018.
 */

public class FixingPoint  {//Точка крепления оружия
    Weapon weapon;
    float x;
    float y;
    float offsetX;
    float offsetY;
    private float lastShipX;
    private float lastShipY;
    double weaponX;
    double weaponY;
    double shipOriginX;
    double shipOriginY;
    double angle;


    public FixingPoint( float x, float y) {
        this.x=x;
        this.y=y;

    }
    public FixingPoint( float x, float y,float offsetX,float offsetY,Weapon weapon) {
        this.x=x+offsetX;
        this.y=y+offsetY;
        this.offsetX=offsetX;
        this.offsetY=offsetY;
        this.weapon=weapon;
        this.weapon.bounds.setPosition(x,y);

    }
    public FixingPoint( float x, float y,float shipWidth,float shipHeight,float offsetX,float offsetY,Weapon weapon) {
        this.x=x+shipWidth/2+offsetX;
        this.y=y+shipHeight/2+offsetY;
        this.offsetX=offsetX;
        this.offsetY=offsetY;
        this.weapon=weapon;
        this.weapon.setCenter(x+shipWidth/2+offsetX,y+shipHeight/2+offsetY);
        lastShipX=x;
        lastShipY=y;
        //System.out.println(shipWidth+"!!!"+shipHeight);
        //this.weapon.bounds.setOrigin(0,0);
    }

    public void update(Ship playerShip,Ship enemyShip,Map map)
    {

        System.out.println(playerShip.getWidth()+"!!!"+playerShip.getHeight());

        weaponX=playerShip.getX()-lastShipX+weapon.getCenterX();
        weaponY=playerShip.getY()-lastShipY+weapon.getCenterY();
        shipOriginX=playerShip.getX()+playerShip.getBounds().getOriginX();
        shipOriginY=playerShip.getY()+playerShip.getBounds().getOriginY();
        lastShipX=playerShip.getX();
        lastShipY=playerShip.getY();
        //angle=Math.toRadians(playerShip.getRotation());
        if(playerShip.getRotationDirection()==1)
            angle=Math.toRadians(-1);
        else if(playerShip.getRotationDirection()==-1)
            angle=Math.toRadians(1);
        else
            angle=Math.toRadians(0);
        this.weapon.setRotation(playerShip.getRotation());
        this.weapon.setCenter((float)((weaponX-shipOriginX)*Math.cos(angle)-(weaponY-shipOriginY)*Math.sin(angle)+shipOriginX),
                (float)((weaponX-shipOriginX)*Math.sin(angle)+(weaponY-shipOriginY)*Math.cos(angle)+shipOriginY));
        this.weapon.update(playerShip,enemyShip,map);
    }
    public void shot()
    {
        weapon.shot();
    }



    public void draw(SpriteBatch batch) {
        weapon.draw(batch);


    }

    public void setOffset(float offsetX,float offsetY)
    {
        setOffsetX(offsetX);
        setOffsetY(offsetY);
    }

    public void setOffsetX(float offsetX) {
        this.offsetX = offsetX;
    }

    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}


