package com.mygdx.game.model.Ships;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.FixingPoint;
import com.mygdx.game.model.Ship;

/**
 * Created by Sash on 09.05.2018.
 */

public class Dakkar extends Ship {
    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }
    public Dakkar(TextureAtlas textureAtlas, float x, float y,FixingPoint[] fixingPoints)
    {
        super(textureAtlas.findRegion("Dakkar"),x,y,15,15,"Dakkar",0,250,1f,50,fixingPoints);


    }
}
