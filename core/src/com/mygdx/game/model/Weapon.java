package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.game.model.Ammos.GreenLaserAmmo;

import java.util.ArrayList;

/**
 * Created by Sash on 09.05.2018.
 */

public class Weapon extends GameObject {
    ArrayList<Ammo> ammos;
    int counter;
    String name;
    Ammo ammo;
    int cost;
    private int rotationPosition;
    private float attackSpeed;

    public Weapon(TextureRegion weaponRegion,  float x, float y, float width,float height,float attackSpeed,Ammo ammo)
    {
        super(weaponRegion, x, y, width, height);
        this.ammo=new Ammo(ammo);
        cost=0;
        ammos=new ArrayList<Ammo>();
        counter=0;
        rotationPosition=1;
        setRotation(270);
    }
    public void shot()
    {

    }
    public void update(Ship playerShip,Ship enemyShip,Map map)
    {
          if(ammos.size()!=0) {
              for(int i=0;i<ammos.size();i++){
                  ammos.get(i).move();

                  if ((ammos.get(i).getX() > map.getWidth())||(ammos.get(i).getX() < 0)||(ammos.get(i).getY() > map.getHeight())||(ammos.get(i).getY() < 0)) {
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
        if(ammos.size()!=0) {
            for(int i=0;i<ammos.size();i++) {
                ammos.get(i).move();
                ammos.get(i).setRotationPosition(rotationPosition);
            }
        }
    }

    public ArrayList<Ammo> getAmmos() {
        return ammos;
    }

    public int getRotationPosition() {
        return rotationPosition;
    }

    public String getName() {
        return name;
    }
    public Drawable getImg(){
        Skin skin=new Skin();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("TexturePack.atlas")));
        return skin.getDrawable(getName());
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public int getCost() {
        return cost;
    }
}
