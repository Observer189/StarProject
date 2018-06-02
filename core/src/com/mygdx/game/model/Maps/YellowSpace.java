package com.mygdx.game.model.Maps;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.model.Map;

/**
 * Created by Sash on 22.05.2018.
 */

public class YellowSpace extends Map {
    public YellowSpace(SpriteBatch batch, TextureAtlas textureAtlas) {
        super(batch, textureAtlas.findRegion("YellowSpace"), 1000, 600);
    }
}

