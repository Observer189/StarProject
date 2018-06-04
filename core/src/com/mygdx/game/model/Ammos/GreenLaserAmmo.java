package com.mygdx.game.model.Ammos;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.Ammo;

/**
 * Created by Sash on 11.05.2018.
 */

public class GreenLaserAmmo extends Ammo {
    public static int counter=0;
    /* GreenLaserAmmo(TextureRegion textureRegion, float x, float y) {
        super(textureRegion, x, y, 1, 10, 7f, 15);
        counter++;
    }*/
    public GreenLaserAmmo(TextureRegion textureRegion, float x, float y,float rotation) {
        super(textureRegion, x, y, 1, 10, 7f, 15,700,rotation);
        counter++;
    }
}
