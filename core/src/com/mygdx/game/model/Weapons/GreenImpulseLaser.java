package com.mygdx.game.model.Weapons;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.model.Ammos.GreenLaserAmmo;
import com.mygdx.game.model.Weapon;

/**
 * Created by Sash on 10.05.2018.
 */

public class GreenImpulseLaser extends Weapon {
    int cost;
    String name;
    TextureAtlas textureAtlas;
    int counter;
    private float attackSpeed;
    public GreenImpulseLaser(TextureAtlas textureAtlas, float x, float y) {
        super(textureAtlas.findRegion("GreenLaser"), x, y, 2f, 7f,25,
                new GreenLaserAmmo(textureAtlas.findRegion("GreenLaserAmmo"),x+2/2,y+7/2));
        this.textureAtlas=textureAtlas;
        counter=0;
        attackSpeed=25;
        name="GreenLaser";
        cost=0;
    }

    @Override
    public void shot() {
        counter++;
        if(counter==1000/attackSpeed)
        {
            if(getRotationPosition()==1) {
            getAmmos().add(new GreenLaserAmmo(textureAtlas.findRegion("GreenLaserAmmo"), getX() +getHeight(), getY() - 1, getRotationPosition()));
        }
            if(getRotationPosition()==2) {
                getAmmos().add(new GreenLaserAmmo(textureAtlas.findRegion("GreenLaserAmmo"), getX() -getHeight(), getY()-1, getRotationPosition()));
            }

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
