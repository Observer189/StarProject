package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

/**
 * Created by Sash on 09.05.2018.
 */

public class Weapon extends GameObject {
    ArrayList<Ammo> ammos;
    public Weapon(TextureRegion textureRegion, float x, float y, float width, float height)
    {
        super(textureRegion, x, y, width, height);
    }
    public void shot()
    {

    }
    public class Ammo extends GameObject
    {

        public Ammo(TextureRegion textureRegion, float x, float y, float width, float height) {
            super(textureRegion, x, y, width, height);
        }
    }
}
