package com.mygdx.game.model.Ammos;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.Ammo;
import com.mygdx.game.model.Weapon;

/**
 * Created by Sash on 11.05.2018.
 */

public class GreenLaserAmmo extends Ammo {
    public static int counter=0;
    public GreenLaserAmmo(TextureRegion textureRegion, float x, float y) {
        super(textureRegion, x, y, 1, 10, 3f, 5);
        counter++;
    }
    public GreenLaserAmmo(TextureRegion textureRegion, float x, float y,int rotationPosition) {
        super(textureRegion, x, y, 1, 10, 5f, 5,rotationPosition);
        counter++;
    }
}
