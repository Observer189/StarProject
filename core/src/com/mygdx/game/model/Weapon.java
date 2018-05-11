package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.Ammos.GreenLaserAmmo;

import java.util.ArrayList;

/**
 * Created by Sash on 09.05.2018.
 */

public class Weapon extends GameObject {
    ArrayList<Ammo> ammos;
    int counter;
    TextureRegion ammoRegion;
    Ammo ammo;

    public Weapon(TextureRegion weaponRegion,  float x, float y, float width,float height,float attackSpeed,Ammo ammo)
    {
        super(weaponRegion, x, y, width, height);
        this.ammo=new Ammo(ammo);

        ammos=new ArrayList<Ammo>();
        counter=0;
    }
    public void shot()
    {

    }
    public void update()
    {
          if(ammos.size()!=0) {
              for(Ammo i:ammos)
              i.move();
          }
          System.out.println(GreenLaserAmmo.counter);
        System.out.println(ammos.size());
    }

    @Override
    public void draw(SpriteBatch batch) {
        if(ammos.size()!=0) {
            for(Ammo i:ammos)
                i.draw(batch);
        }
        super.draw(batch);

    }

    public ArrayList<Ammo> getAmmos() {
        return ammos;
    }
}
