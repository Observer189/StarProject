package com.mygdx.game.model.Ships;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.model.FixingPoint;
import com.mygdx.game.model.Ship;

/**
 * Created by Sash on 09.05.2018.
 */

public class Bat extends Ship {

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }
    public Bat(TextureAtlas textureAtlas, float x, float y, FixingPoint[] fixingPoints) {
        super(textureAtlas.findRegion("Bat"), x, y, 15, 15, "Bat", 15000, 220, 1.2f, 60,fixingPoints);
    }
}
