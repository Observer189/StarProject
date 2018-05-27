package com.mygdx.game.model.Weapons;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.model.Ammos.BlueLaserAmmo;
import com.mygdx.game.model.Ammos.GreenLaserAmmo;
import com.mygdx.game.model.Weapon;

/**
 * Created by Sash on 27.05.2018.
 */

public class BlueImpulseLaser extends Weapon {
    int cost;
    String name;
    TextureAtlas textureAtlas;
    int counter;

    private float attackSpeed;
    public BlueImpulseLaser(TextureAtlas textureAtlas, float x, float y) {
        super(textureAtlas.findRegion("BlueImpulseLaser"), x, y, 2f, 7f,25,
                new BlueLaserAmmo(textureAtlas.findRegion("BlueLaserAmmo"),x+2/2,y+7/2));
        this.textureAtlas=textureAtlas;
        counter=0;
        attackSpeed=25;
        cost=0;

        name="BlueLaser";
    }

    @Override
    public void shot() {
        counter++;
        if(counter==1000/attackSpeed)
        {

                getAmmos().add(new BlueLaserAmmo(textureAtlas.findRegion("BlueLaserAmmo"), getX() +getHeight(), getY() - 1, getRotation()));


            counter = 0;
        }
    }

    @Override
    public float getAttackSpeed() {
        return attackSpeed;
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
