package com.mygdx.game.model.Ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.Ship;

/**
 * Created by Sash on 03.05.2018.
 */

public class Mite extends Ship {


    public Mite(TextureAtlas textureAtlas, float x, float y) {
        super(textureAtlas.findRegion("Mite"), x, y);
        setWidth(5);
        setHeight(5);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }
    public Mite(TextureRegion textureRegion, float x,float y,float width,float height)
    {
        super(textureRegion,x,y,width,height);


    }

    public void teleport()
    {
       setPosition((float)(Math.random()*800+1),(float)(Math.random()*800+1));
    }
}
