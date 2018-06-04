package com.mygdx.game.model.Ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.model.FixingPoint;
import com.mygdx.game.model.Ship;
import com.mygdx.game.model.Weapons.BlueImpulseLaser;
import com.mygdx.game.model.Weapons.Machinegun;
import com.mygdx.game.model.Weapons.RocketLauncher;
import com.mygdx.game.model.Weapons.Shotgun;

/**
 * Created by Sash on 28.05.2018.
 */

public class Dashing extends Ship {
    public float realw= (Gdx.graphics.getWidth()/182.85714286f*6);
    public float realh=  (Gdx.graphics.getHeight()/55.38461538f*6);


    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    public Dashing(TextureAtlas textureAtlas, float x, float y) {
        super(textureAtlas.findRegion("Dashing"), x, y, 7, 13, "Dashing", 5000, 700,
                1.5f, 60,1.2f, new FixingPoint[]{
                        new FixingPoint(x, y, 7, 13, 2, 0, new Machinegun(textureAtlas, x, y)),
                        new FixingPoint(x, y, 7, 13, -2f, 0, new Machinegun(textureAtlas, x, y)),

                });

    }
    @Override
    public float getRealh() {
        return realh;
    }
    @Override
    public float getRealw() {
        return realw;
    }
}
