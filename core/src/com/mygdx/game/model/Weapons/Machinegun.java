package com.mygdx.game.model.Weapons;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.model.Ammos.Bullet;
import com.mygdx.game.model.Weapon;

/**
 * Created by Sash on 18.05.2018.
 */

public class Machinegun extends Weapon {
    int cost;
    String name;
    TextureAtlas textureAtlas;
    int counter;
    private float attackSpeed;
    public Machinegun(TextureAtlas textureAtlas, float x, float y) {
        super(textureAtlas.findRegion("Machinegun"), x, y, 3f, 5f,10,
                new Bullet(textureAtlas.findRegion("Bullet"),x+2/2,y+7/2));
        this.textureAtlas=textureAtlas;
        counter=0;
        attackSpeed=10;
        cost=1250;
        name="Machinegun";
    }

    @Override
    public void shot() {
        counter++;
        if(counter==1000/attackSpeed)
        {
            if(getRotationPosition()==1) {
                getAmmos().add(new Bullet(textureAtlas.findRegion("Bullet"), getX() +getWidth(), getY(), getRotationPosition()));
            }
            if(getRotationPosition()==2) {
                getAmmos().add(new Bullet(textureAtlas.findRegion("Bullet"), getX(), getY(), getRotationPosition()));
            }

            counter = 0;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return getName();
    }
}
