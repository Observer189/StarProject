package com.mygdx.game.model.Ships;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.model.FixingPoint;
import com.mygdx.game.model.Ship;

/**
 * Created by Sash on 03.05.2018.
 */

public class Mite extends Ship {




    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }
    public Mite(TextureAtlas textureAtlas, float x, float y,FixingPoint[] fixingPoints)
    {
        super(textureAtlas.findRegion("Mite"),x,y,10,10,"Mite",75000,520,15f,30,2.5f,fixingPoints);


    }

    public void teleport()
    {
       setPosition((float)(Math.random()*800+1),(float)(Math.random()*800+1));
    }
}
