package com.mygdx.game.model.Ships;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.model.FixingPoint;
import com.mygdx.game.model.Ship;
import com.mygdx.game.model.Weapons.BlueImpulseLaser;
import com.mygdx.game.model.Weapons.GreenImpulseLaser;

/**
 * Created by Sash on 27.05.2018.
 */

public class Axe extends Ship {


    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }
    public Axe(TextureAtlas textureAtlas, float x, float y) {
        super(textureAtlas.findRegion("Axe"), x, y, 15, 25, "Axe", 75000, 1780,
                0.7f, 45,new FixingPoint[]{
                        new FixingPoint(x,y,15,25,0,5.5f,new BlueImpulseLaser(textureAtlas,x,y)),
                        new FixingPoint(x,y,15,25,0,-5,new BlueImpulseLaser(textureAtlas,x,y)),
                        new FixingPoint(x,y,15,25,0,0,new BlueImpulseLaser(textureAtlas,x,y))
        });

    }

    /*@Override
    public void setRotationPosition(int rotationPosition) {
        super.setRotationPosition(rotationPosition);
        if(rotationPosition==1) {
            getFixingPoints()[0].setOffset(0, 3f);
            getFixingPoints()[1].setOffset(0, -7f);
        }
        if(rotationPosition==2) {
            getFixingPoints()[0].setOffset(0, 3f);
            getFixingPoints()[1].setOffset(0, -7f);
        }
    }*/
}
