package com.mygdx.game.model.Weapons;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.model.Ammos.BlueLaserAmmo;
import com.mygdx.game.model.Ammos.Bullet;
import com.mygdx.game.model.Weapon;

/**
 * Created by Sash on 18.05.2018.
 */

public class Machinegun extends Weapon {
    String name;
    int cost;
    TextureAtlas textureAtlas;
    int counter;

    private float attackSpeed;
    public Machinegun(TextureAtlas textureAtlas, float x, float y) {
        super(textureAtlas.findRegion("Machinegun"), x, y, 3f, 6f,50,
                new Bullet(textureAtlas.findRegion("Bullet"),x,y,0));
        this.textureAtlas=textureAtlas;
        counter=0;
        cost=1250;
        attackSpeed=50;

        name="Machinegun";
    }

    @Override
    public void shot() {
        counter++;
        if(counter==1000/attackSpeed)
        {

            /*getAmmos().add(new Bullet(textureAtlas.findRegion("Bullet"),
                    getX()+Math.abs((float)((getWidth()/2-0.5)*Math.cos(Math.toRadians(getRotation())))) -(float)( Math.sqrt(Math.pow(getWidth()/2-0.5f,2)+Math.pow(getHeight(),2))*Math.sin(Math.toRadians(getRotation()))),
                    getY()-Math.abs((float)((getWidth()-0.5f)*Math.sin(Math.toRadians(getRotation())))) +(float)(Math.sqrt(Math.pow(getWidth()/2-0.5f,2)+Math.pow(getHeight(),2))*Math.cos(Math.toRadians(getRotation()))),
                    getRotation()));*/
                 getAmmos().add(new Bullet(textureAtlas.findRegion("Bullet"),
                         getCenterX(),
                         getCenterY()-getWidth()/2,
                         getRotation()));

            counter = 0;
        }
    }
    public int getCost() {
        return cost;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }

}
