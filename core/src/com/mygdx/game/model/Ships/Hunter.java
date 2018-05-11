package com.mygdx.game.model.Ships;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.FixingPoint;
import com.mygdx.game.model.Ship;

/**
 * Created by Sash on 09.05.2018.
 */

public class Hunter extends Ship {
    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }
    public Hunter(TextureAtlas textureAtlas, float x, float y,FixingPoint[] fixingPoints) {
        super(textureAtlas.findRegion("Hunter"), x, y, 25, 25, "Hunter", 70000, 700, 0.5f, 45,fixingPoints);
    }
}
