package com.mygdx.game.model.Ships;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.model.FixingPoint;
import com.mygdx.game.model.Ship;
import com.mygdx.game.model.Weapons.RocketLauncher;

/**
 * Created by Sash on 01.06.2018.
 */

public class Rock extends Ship {


    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    public Rock(TextureAtlas textureAtlas, float x, float y) {
        super(textureAtlas.findRegion("Rock"), x, y, 30, 50, "Rock", 500000, 3500,
                0.4f, 15,0.3f, new FixingPoint[]{
                        new FixingPoint(x, y, 30, 50, 0, 5, new RocketLauncher(textureAtlas, x, y)),
                        new FixingPoint(x, y, 30, 50, 0, -5f, new RocketLauncher(textureAtlas, x, y)),
                        new FixingPoint(x, y, 30, 50, 10, 5f, new RocketLauncher(textureAtlas, x, y)),
                        new FixingPoint(x, y, 30, 50, 10, -5f, new RocketLauncher(textureAtlas, x, y)),
                        new FixingPoint(x, y, 30, 50, -10, 5f, new RocketLauncher(textureAtlas, x, y)),
                        new FixingPoint(x, y, 30, 50, -10, -5f, new RocketLauncher(textureAtlas, x, y)),

                });

    }
}

