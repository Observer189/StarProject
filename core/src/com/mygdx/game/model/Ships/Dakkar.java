package com.mygdx.game.model.Ships;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.model.FixingPoint;
import com.mygdx.game.model.Ship;
import com.mygdx.game.model.Weapons.Machinegun;

/**
 * Created by Sash on 09.05.2018.
 */

public class Dakkar extends Ship {
    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }
    public Dakkar(TextureAtlas textureAtlas, float x, float y)
    {
        super(textureAtlas.findRegion("Dakkar"),x,y,15,25,"Dakkar",0,1020,1f,50,0.7f,new FixingPoint[]{new FixingPoint(x,y,7.5f,0f,new Machinegun(textureAtlas,x,y)),
                new FixingPoint(x,y,7.5f,8.2f,new Machinegun(textureAtlas,x,y))});
    }
    /*@Override
    public void setRotationPosition(int rotationPosition) {
        super.setRotationPosition(rotationPosition);
        if(rotationPosition==1) {
            getFixingPoints()[0].setOffset(7.5f, 3f);
            getFixingPoints()[1].setOffset(7.5f, 6f);
        }
        if(rotationPosition==2) {
            getFixingPoints()[0].setOffset(5f, 3.5f);
            getFixingPoints()[1].setOffset(5f, 6.5f);
        }
    }*/
}
