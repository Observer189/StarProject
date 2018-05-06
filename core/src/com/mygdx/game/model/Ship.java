package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.utils.Vector2D;

/**
 * Created by Sash on 01.05.2018.
 */

public class Ship extends GameObject {
    String name;
    int cost;
    int hp;

    Vector2D movementVector;
    public Ship(TextureRegion textureRegion, float x, float y, float width, float height) {
        super(textureRegion, x, y, width, height);
    }
    public Ship(TextureRegion textureRegion, float x, float y) {
        super(textureRegion, x, y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }
}
