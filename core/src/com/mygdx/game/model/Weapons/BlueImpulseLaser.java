package com.mygdx.game.model.Weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.model.Ammos.BlueLaserAmmo;
import com.mygdx.game.model.Weapon;

/**
 * Created by Sash on 27.05.2018.
 */

public class BlueImpulseLaser extends Weapon {
    int cost;
    String name;
    TextureAtlas textureAtlas;
    int counter;
    int recomendedw= (int) (Gdx.graphics.getWidth()/20.31746032);
    double recomendedh=Gdx.graphics.getHeight()/3.6;

    private float attackSpeed;
    public BlueImpulseLaser(TextureAtlas textureAtlas, float x, float y) {
        super(textureAtlas.findRegion("BlueImpulseLaser"), x, y, 2f, 7f,25,
                new BlueLaserAmmo(textureAtlas.findRegion("BlueLaserAmmo"),x,y,0));
        this.textureAtlas=textureAtlas;
        counter=0;
        attackSpeed=10;
        cost=35000;

        name="BlueImpulseLaser";
        setTextureAtlas(textureAtlas);
    }

    @Override
    public void shot() {
        counter++;
        if(counter==1000/attackSpeed)
        {
            getAmmos().add(new BlueLaserAmmo(textureAtlas.findRegion("BlueLaserAmmo"),
                    getCenterX()-(float)(getAmmo().getWidth()/2*Math.cos(Math.toRadians(getRotation()))),
                    getCenterY()-(float)(getAmmo().getWidth()/2*Math.sin(Math.toRadians(getRotation()))),
                    getRotation()));
                /*getAmmos().add(new BlueLaserAmmo(textureAtlas.findRegion("BlueLaserAmmo"),
                        getX()+Math.abs((float)((getWidth()/2-getAmmo().getWidth()/2)*Math.cos(Math.toRadians(getRotation())))) -(float)( getHeight()*Math.sin(Math.toRadians(getRotation()))),
                        getY()-Math.abs((float)((getWidth()-getAmmo().getWidth()/2)*Math.sin(Math.toRadians(getRotation())))) +(float)(getHeight()*Math.cos(Math.toRadians(getRotation()))),
                           getRotation()));*/
            /*getAmmos().add(new BlueLaserAmmo(textureAtlas.findRegion("BlueLaserAmmo"),
                    getX()+Math.abs((float)((getWidth()/2-0.5)*Math.cos(Math.toRadians(getRotation())))) -(float)( Math.sqrt(Math.pow(getWidth()/2-0.5f,2)+Math.pow(getHeight(),2))*Math.sin(Math.toRadians(getRotation()))),
                    getY()-Math.abs((float)((getWidth()-0.5f)*Math.sin(Math.toRadians(getRotation())))) +(float)(Math.sqrt(Math.pow(getWidth()/2-0.5f,2)+Math.pow(getHeight(),2))*Math.cos(Math.toRadians(getRotation()))),
                    getRotation()));*/

            counter = 0;
        }
    }

    @Override
    public float getAttackSpeed() {
        return attackSpeed;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
    @Override
    public float getRecomendedw() {
        return recomendedw;
    }

    @Override
    public float getRecomendedh() {
        return (float) recomendedh;
    }


}
