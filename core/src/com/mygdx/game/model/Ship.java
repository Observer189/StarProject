package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Sash on 01.05.2018.
 */

public class Ship extends GameObject {
    String name;
    int cost;
    public Ship(TextureRegion textureRegion, float x, float y, float width, float height) {
        super(textureRegion, x, y, width, height);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }
}
