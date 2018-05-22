package com.mygdx.game.model.Maps;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.Map;

/**
 * Created by Sash on 22.05.2018.
 */

public class ClassicSpace extends Map {
    public ClassicSpace(SpriteBatch batch, TextureAtlas textureAtlas) {
        super(batch, textureAtlas.findRegion("ClassicSpace"), 1000, 600);
    }
}
