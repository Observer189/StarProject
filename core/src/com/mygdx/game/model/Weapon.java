package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.game.ServModels.ServWeapon;
import com.mygdx.game.model.Weapons.BlueImpulseLaser;
import com.mygdx.game.model.Weapons.Machinegun;
import com.mygdx.game.model.Weapons.RocketLauncher;
import com.mygdx.game.model.Weapons.Shotgun;

import java.util.ArrayList;

/**
 * Created by Sash on 09.05.2018.
 */

public class Weapon extends GameObject {
    //private float centerX;
    //private float centerY;
    private float width;
    private float height;
    ArrayList<Ammo> ammos;
    int counter;
    String name;
    Ammo ammo;
    int cost;
    int recomendedw=100;
    int recomendedh=200;
    TextureAtlas textureAtlas;

    private float attackSpeed;

    public Weapon(TextureRegion weaponRegion,  float x, float y, float width,float height,float attackSpeed,Ammo ammo)
    {
        super(weaponRegion, x, y, width, height);
        this.ammo=new Ammo(ammo);
        cost=0;
        this.width=width;
        this.height=height;
        //centerX=x+width/2;
        //centerY=y+height/2;
        ammos=new ArrayList<Ammo>();
        counter=0;

        //setRotation(270);
    }


    public void shot()
    {

    }
    public void update(Ship playerShip, Ship enemyShip, Map map)
    {
          if(ammos.size()!=0) {
              for(int i=0;i<ammos.size();i++){
                  ammos.get(i).move(enemyShip);

                  if ((ammos.get(i).getX() > map.getWidth())||
                          (ammos.get(i).getX() < 0)||
                          (ammos.get(i).getY() > map.getHeight())||
                          (ammos.get(i).getY() < 0)||
                          (ammos.get(i).getAppliedDistance()>=ammos.get(i).getMaxRange())) {

                      ammos.remove(i);
                  }
                  else if((Intersector.overlapConvexPolygons(ammos.get(i).getBounds(),enemyShip.getBounds()))&&(enemyShip.getCurrentHp()>0))
                  {
                      enemyShip.setCurrentHp(enemyShip.getCurrentHp()-ammos.get(i).getDamage());
                      playerShip.setAppliedDamage(playerShip.getAppliedDamage()+(int)ammos.get(i).getDamage());
                      ammos.remove(i);
                  }


              }
          }

    }

    @Override
    public void draw(SpriteBatch batch) {
        if(ammos.size()!=0) {
            for (int i = 0; i < ammos.size(); i++) {
                ammos.get(i).draw(batch);
            }
        }
        super.draw(batch);

    }

    /*public void setRotationPosition(int rotationPosition) {
        this.rotationPosition = rotationPosition;
        if(rotationPosition==1)
        {
            setRotation(270);

        }
        if(rotationPosition==2)
        {
            setRotation(90);
        }

    }*/

    public ArrayList<Ammo> getAmmos() {
        return ammos;
    }



    public String getName() {
        return name;
    }
    public Drawable getImg(){
        Skin skin=new Skin();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("TexturePack.atlas")));
        return skin.getDrawable(getName());
    }

    @Override
    public boolean equals(Object o) {
        return getName().equals(o.toString());
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public int getCost() {
        return cost;
    }

    public Ammo getAmmo() {
        return ammo;
    }

    /*public float getCenterX() {
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
    }*/
    public String getRealName(){
        if (getName().equals("BlueLaser")) return "BlueImpulseLaser";
        else return getName();
    }

    public float getRecomendedh() {
        return recomendedh;
    }

    public float getRecomendedw() {
        return recomendedw;
    }

    public void setTextureAtlas(TextureAtlas textureAtlas) {
        this.textureAtlas = textureAtlas;
    }

    @Override
    public String toString() {
        return getName();
    }


    ServWeapon toServ()
    {

        return new ServWeapon(name);
    }
    public Weapon weaponByName()
    {
        if(getName().equals("Shotgun"))
            return new Shotgun(textureAtlas,0,0);
        else if(getName().equals("RocketLauncher"))
            return new RocketLauncher(textureAtlas,0,0);
        else if(getName().equals("Machinegun"))
            return new Machinegun(textureAtlas,0,0);
        else if(getName().equals("BlueImpulseLaser"))
            return new BlueImpulseLaser(textureAtlas,0,0);
        else
        {
            System.out.println("Weapon is not exist");
            return null;
        }
    }

    public Weapon weaponByName(String name)
    {
        if(name.equals("Shotgun"))
            return new Shotgun(textureAtlas,0,0);
        else if(name.equals("RocketLauncher"))
            return new RocketLauncher(textureAtlas,0,0);
        else if(name.equals("Machinegun"))
            return new Machinegun(textureAtlas,0,0);
        else if(name.equals("BlueImpulseLaser"))
            return new BlueImpulseLaser(textureAtlas,0,0);
        else
            {
            System.out.println("Weapon is not exist");
            return null;
        }
    }
}
