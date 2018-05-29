package com.mygdx.game.model.Ships;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.model.FixingPoint;
import com.mygdx.game.model.Ship;
import com.mygdx.game.model.Weapons.Shotgun;

/**
 * Created by Sash on 28.05.2018.
 */

public class Dashing extends Ship {


    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    public Dashing(TextureAtlas textureAtlas, float x, float y) {
        super(textureAtlas.findRegion("Dashing"), x, y, 7, 13, "Dashing", 0, 700,
                1.5f, 60, new FixingPoint[]{
                        new FixingPoint(x, y, 7, 13, 0, 2, new Shotgun(textureAtlas, x, y)),
                        new FixingPoint(x, y, 7, 13, 0, -2f, new Shotgun(textureAtlas, x, y)),

                });

    }
}
