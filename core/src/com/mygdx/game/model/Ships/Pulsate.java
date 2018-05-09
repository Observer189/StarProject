package com.mygdx.game.model.Ships;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.Ship;

/**
 * Created by Sash on 09.05.2018.
 */

public class Pulsate extends Ship {
    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }
    public Pulsate(TextureAtlas textureAtlas, float x, float y) {
        super(textureAtlas.findRegion("1"), x, y, 15, 15, "Pulsate", 50000, 170, 2.5f, 70);
    }

}
