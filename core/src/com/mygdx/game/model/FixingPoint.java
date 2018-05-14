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

    public void update(Ship playerShip,Ship enemyShip,Map map)
    {
        this.weapon.setRotation(playerShip.getRotation());
        this.weapon.setPosition(playerShip.getX()+offsetX,playerShip.getY()+offsetY);
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


