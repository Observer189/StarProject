package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Sash on 02.06.2018.
 */

public class MiniMap {
    private float x;
    private float y;
    private float width;
    private float height;

    private Ship playerShip;
    private Ship enemyShip;

    SpriteBatch batch;
    TextureAtlas textureAtlas;

    private TextureRegion mapRegion;
    private TextureRegion playerMarker;
    private TextureRegion enemyMarker;

    private Map map;
    public MiniMap(SpriteBatch batch,TextureAtlas textureAtlas,float x, float y, float width, float height,Ship playerShip,Ship enemyShip,Map map)
    {
        this.batch=batch;
        this.textureAtlas=textureAtlas;
        this.mapRegion=textureAtlas.findRegion("MiniMap");
        this.playerMarker=textureAtlas.findRegion("PlayerMarker");
        this.enemyMarker=textureAtlas.findRegion("EnemyMarker");

        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.playerShip=playerShip;
        this.enemyShip=enemyShip;
        this.map=map;
    }

    public void draw(Ship playerShip,Ship enemyShip)
    {
        batch.begin();
        batch.draw(mapRegion,x,y,width,height);
        if(playerShip.getCurrentHp()>0) {
            batch.draw(playerMarker, x +width/(map.getWidth()/playerShip.getCenterX()),y+height/(map.getHeight()/playerShip.getCenterY()),width/100,width/100);
        }
        if(enemyShip.getCurrentHp()>0) {
            batch.draw(enemyMarker, x +width/(map.getWidth()/enemyShip.getCenterX()),y+height/(map.getHeight()/enemyShip.getCenterY()),width/100,width/100);
        }
        batch.end();
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}
