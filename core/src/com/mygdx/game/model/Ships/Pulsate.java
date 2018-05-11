package com.mygdx.game.model.Ships;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.FixingPoint;
import com.mygdx.game.model.Ship;
import com.mygdx.game.model.Weapons.GreenImpulseLaser;

/**
 * Created by Sash on 09.05.2018.
 */

public class Pulsate extends Ship {


    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }
    public Pulsate(TextureAtlas textureAtlas, float x, float y) {
        super(textureAtlas.findRegion("1"), x, y, 15, 15, "Pulsate", 50000, 170,
                2.5f, 70,new FixingPoint[]{new FixingPoint(150,300,7.5f,0f,new GreenImpulseLaser(textureAtlas,150,300)),
                        new FixingPoint(150,300,7.5f,8.2f,new GreenImpulseLaser(textureAtlas,150,300))});

    }

}