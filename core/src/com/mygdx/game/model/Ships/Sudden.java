package com.mygdx.game.model.Ships;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.model.FixingPoint;
import com.mygdx.game.model.Ship;
import com.mygdx.game.model.Weapons.BlueImpulseLaser;
import com.mygdx.game.model.Weapons.Shotgun;

/**
 * Created by Sash on 04.06.2018.
 */

public class Sudden extends Ship {


    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    public Sudden(TextureAtlas textureAtlas, float x, float y) {
        super(textureAtlas.findRegion("Sudden"), x, y, 10, 22, "Sudden", 60000, 1400,
                1.1f, 50, 1.3f, new FixingPoint[]{
                        new FixingPoint(x, y, 10, 22, 3, 2, new Shotgun(textureAtlas, x, y)),
                        new FixingPoint(x, y, 10, 22, -3, 2, new Shotgun(textureAtlas, x, y)),
                        new FixingPoint(x, y, 10, 22, 1, 6, new Shotgun(textureAtlas, x, y)),
                        new FixingPoint(x, y, 10, 22, -1, 6, new Shotgun(textureAtlas, x, y))
                });

    }
}