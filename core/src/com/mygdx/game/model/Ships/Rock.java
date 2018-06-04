package com.mygdx.game.model.Ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.model.FixingPoint;
import com.mygdx.game.model.Ship;
import com.mygdx.game.model.Weapons.RocketLauncher;

/**
 * Created by Sash on 01.06.2018.
 */

public class Rock extends Ship {
    public float realw=  (Gdx.graphics.getWidth()/42.6666666f*6);
    public float realh=  (Gdx.graphics.getHeight()/14.4f*6);


    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    public Rock(TextureAtlas textureAtlas, float x, float y) {
        super(textureAtlas.findRegion("Rock"), x, y, 30, 50, "Rock", 500000, 3500,
                0.4f, 15,0.3f, new FixingPoint[]{
                        new FixingPoint(x, y, 30, 50, 0, 5, new RocketLauncher(textureAtlas, x, y)),
                        new FixingPoint(x, y, 30, 50, -5f, 0f, new RocketLauncher(textureAtlas, x, y)),
                        new FixingPoint(x, y, 30, 50, 5, 10f, new RocketLauncher(textureAtlas, x, y)),
                        new FixingPoint(x, y, 30, 50, -5, 10, new RocketLauncher(textureAtlas, x, y)),
                        new FixingPoint(x, y, 30, 50, 5, -10f, new RocketLauncher(textureAtlas, x, y)),
                        new FixingPoint(x, y, 30, 50, -5, -10f, new RocketLauncher(textureAtlas, x, y)),

                });

    }

    @Override
    public float getRealw() {
        return realw;
    }
    @Override
    public float getRealh() {
        return realh;
    }
}

