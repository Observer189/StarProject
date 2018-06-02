package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.ServModels.ServFixingPoint;
import com.mygdx.game.model.Weapons.BlueImpulseLaser;
import com.mygdx.game.model.Weapons.Machinegun;
import com.mygdx.game.model.Weapons.RocketLauncher;
import com.mygdx.game.model.Weapons.Shotgun;

/**
 * Created by Sash on 10.05.2018.
 */

public class FixingPoint  {//Точка крепления оружия
    Weapon weapon;
    float x;
    float y;
    float centerX;
    float centerY;
    float width;
    float height;
    float offsetX;
    float offsetY;
    float shipWidth;
    float shipHeight;
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
        this.width=10;
        this.height=10;
        this.offsetX=offsetX;
        this.offsetY=offsetY;
        this.weapon=weapon;
        this.weapon.bounds.setPosition(x,y);

    }
    public FixingPoint( float x, float y,float shipWidth,float shipHeight,float offsetX,float offsetY) {

        this.centerX=x+shipWidth/2+offsetX;
        this.centerY=y+shipHeight/2+offsetY;

        this.width=10;
        this.height=10;
        this.x=centerX-width/2;
        this.y=centerY-height/2;
        this.offsetX=offsetX;
        this.offsetY=offsetY;
        this.shipWidth=shipWidth;
        this.shipHeight=shipHeight;

        lastShipX=x;
        lastShipY=y;
        //System.out.println(shipWidth+"!!!"+shipHeight);
        //this.weapon.bounds.setOrigin(0,0);
    }
    public FixingPoint( float x, float y,float shipWidth,float shipHeight,float offsetX,float offsetY,Weapon weapon) {

        this.centerX=x+shipWidth/2+offsetX;
        this.centerY=y+shipHeight/2+offsetY;

        this.width=10;
        this.height=10;
        this.x=centerX-width/2;
        this.y=centerY-height/2;
        this.offsetX=offsetX;
        this.offsetY=offsetY;
        this.shipWidth=shipWidth;
        this.shipHeight=shipHeight;
        this.weapon=weapon;
        this.weapon.setCenter(this.centerX,this.centerY);
        lastShipX=x;
        lastShipY=y;
        //System.out.println(shipWidth+"!!!"+shipHeight);
        //this.weapon.bounds.setOrigin(0,0);
    }


    public void update(Ship playerShip, Ship enemyShip, Map map)
    {

        //System.out.println(playerShip.getWidth()+"!!!"+playerShip.getHeight());

        weaponX=playerShip.getX()-lastShipX+weapon.getCenterX();
        weaponY=playerShip.getY()-lastShipY+weapon.getCenterY();
        shipOriginX=playerShip.getX()+playerShip.getBounds().getOriginX();
        shipOriginY=playerShip.getY()+playerShip.getBounds().getOriginY();
        lastShipX=playerShip.getX();
        lastShipY=playerShip.getY();
        //angle=Math.toRadians(playerShip.getRotation());
        if(playerShip.getRotationDirection()==1)
            angle=Math.toRadians(-playerShip.getRotationSpeed());
        else if(playerShip.getRotationDirection()==-1)
            angle=Math.toRadians(playerShip.getRotationSpeed());
        else
            angle=Math.toRadians(0);
        this.weapon.setRotation(playerShip.getRotation());
        setCenter((float)((weaponX-shipOriginX)*Math.cos(angle)-(weaponY-shipOriginY)*Math.sin(angle)+shipOriginX),
                (float)((weaponX-shipOriginX)*Math.sin(angle)+(weaponY-shipOriginY)*Math.cos(angle)+shipOriginY));
        this.weapon.update(playerShip,enemyShip,map);

    }
    public void shot()
    {
        weapon.shot();
    }



    public void draw(SpriteBatch batch) {
        if(weapon!=null)
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
        this.weapon.setCenter(centerX,centerY);
    }

    public Weapon getWeapon() {
        return weapon;
    }

   public void setPosition(Ship playerShip)
    {

        this.centerX=playerShip.getX()+shipWidth/2+offsetX;
        this.centerY=playerShip.getY()+shipHeight/2+offsetY;
        setCenter(centerX,centerY);

        lastShipX=playerShip.getX();
        lastShipY=playerShip.getY();
    }
    public void setCenter(float centerX,float centerY)
    {
        this.centerX=centerX;
        this.centerY=centerY;
        x=centerX-width/2;
        y=centerY-height/2;
        weapon.setCenter(centerX,centerY);
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getCenterY() {
        return centerY;
    }

    public float getCenterX() {
        return centerX;
    }

    public float getOffsetY() {
        return offsetY;
    }

    public float getOffsetX() {
        return offsetX;
    }

    ServFixingPoint toServ()
    {
        return new ServFixingPoint(weapon.toServ());
    }
    public void setWeaponByServ(TextureAtlas textureAtlas,ServFixingPoint servFixingPoint)
    {
        if(servFixingPoint.getWeapon().getName().equals("Shotgun"))
        {
            setWeapon(new Shotgun(textureAtlas,0,0));
        }
        else if(servFixingPoint.getWeapon().getName().equals("RocketLauncher"))
        {
            setWeapon(new RocketLauncher(textureAtlas,0,0));
        }
        else if(servFixingPoint.getWeapon().getName().equals("Machinegun"))
        {
            setWeapon(new Machinegun(textureAtlas,0,0));
        }
        else if(servFixingPoint.getWeapon().getName().equals("BlueImpulseLaser"))
        {
            setWeapon(new BlueImpulseLaser(textureAtlas,0,0));
        }
    }
}


