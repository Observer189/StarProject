package com.mygdx.game.model.Weapons;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.Ammo;
import com.mygdx.game.model.Ammos.GreenLaserAmmo;
import com.mygdx.game.model.Weapon;

/**
 * Created by Sash on 10.05.2018.
 */

public class GreenImpulseLaser extends Weapon {
    TextureAtlas textureAtlas;
    int counter;
    private float attackSpeed;
    public GreenImpulseLaser(TextureAtlas textureAtlas, float x, float y) {
        super(textureAtlas.findRegion("GreenLaser"), x, y, 2, 7,25,
                new GreenLaserAmmo(textureAtlas.findRegion("GreenLaserAmmo"),x+2/2,y+7/2));
        this.textureAtlas=textureAtlas;
        counter=0;
        attackSpeed=25;
    }

    @Override
    public void shot() {
        counter++;
        if(counter==1000/attackSpeed)
        {
            getAmmos().add( new GreenLaserAmmo(textureAtlas.findRegion("GreenLaserAmmo"),getX()+2/2,getY()+7/2));

            counter = 0;
        }
    }
}
