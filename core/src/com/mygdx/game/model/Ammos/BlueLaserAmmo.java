package com.mygdx.game.model.Ammos;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.Ammo;

/**
 * Created by Sash on 27.05.2018.
 */

public class BlueLaserAmmo extends Ammo {
    public static int counter=0;
    /*public BlueLaserAmmo(TextureRegion textureRegion, float x, float y) {
        super(textureRegion, x, y, 1, 10, 0.5f, 15);
        counter++;
    }*/
    public BlueLaserAmmo(TextureRegion textureRegion, float x, float y,float rotation) {
        super(textureRegion, x, y, 1, 10, 5f, 50,700,rotation);
        counter++;
    }
}
