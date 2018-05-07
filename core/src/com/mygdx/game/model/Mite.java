package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Sash on 03.05.2018.
 */

public class Mite extends Ship {




    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }
    public Mite(TextureRegion textureRegion, float x, float y)
    {
        super(textureRegion,x,y,10,10,"Mite",75000,520,15f,30);


    }

    public void teleport()
    {
       setPosition((float)(Math.random()*800+1),(float)(Math.random()*800+1));
    }
}
