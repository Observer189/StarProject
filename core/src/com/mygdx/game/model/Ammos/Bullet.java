package com.mygdx.game.model.Ammos;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.Ammo;

/**
 * Created by Sash on 18.05.2018.
 */

public class Bullet extends Ammo {
    public static int counter=0;
    public Bullet(TextureRegion textureRegion, float x, float y) {
        super(textureRegion, x, y, 2, 5, 3f, 2);
        counter++;
    }
    public Bullet(TextureRegion textureRegion, float x, float y,int rotationPosition) {
        super(textureRegion, x, y, 2, 5, 3f, 2,rotationPosition);
        counter++;
    }
}
