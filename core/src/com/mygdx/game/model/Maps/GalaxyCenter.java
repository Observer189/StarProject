package com.mygdx.game.model.Maps;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.model.Map;

/**
 * Created by Sash on 22.05.2018.
 */

public class GalaxyCenter extends Map {
    public GalaxyCenter(SpriteBatch batch, TextureAtlas textureAtlas) {
        super(batch, textureAtlas.findRegion("GalaxyCenter"), 1000, 600);
    }
}
