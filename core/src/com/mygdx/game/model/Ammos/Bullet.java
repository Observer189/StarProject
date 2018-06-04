package com.mygdx.game.model.Ammos;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.Ammo;

/**
 * Created by Sash on 18.05.2018.
 */

public class Bullet extends Ammo {
    public static int counter=0;
    /*public Bullet(TextureRegion textureRegion, float x, float y) {
        super(textureRegion, x, y, 0.8f, 2.8f, 3f, 6);
        counter++;
    }*/
    public Bullet(TextureRegion textureRegion, float x, float y,float rotation) {
        super(textureRegion, x, y, 0.8f, 2.8f, 3f, 22,300,rotation);
        counter++;
    }
}
