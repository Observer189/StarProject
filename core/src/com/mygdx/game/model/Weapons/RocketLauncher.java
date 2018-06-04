package com.mygdx.game.model.Weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.model.Ammos.Rocket;
import com.mygdx.game.model.Weapon;

/**
 * Created by Sash on 30.05.2018.
 */

public class RocketLauncher extends Weapon {
    int cost;
    String name;
    TextureAtlas textureAtlas;
    int counter;
    float recomendedw= (float) (Gdx.graphics.getWidth()/8.59060403);
    float recomendedh= (float) (Gdx.graphics.getHeight()/4.5);

    private float attackSpeed;
    public RocketLauncher(TextureAtlas textureAtlas, float x, float y) {
        super(textureAtlas.findRegion("RocketLauncher"), x, y, 3f, 4f,5,
                new Rocket(textureAtlas.findRegion("Rocket"),x,y,0));
        this.textureAtlas=textureAtlas;
        counter=0;
        attackSpeed=5f;
        cost=50000;

        name="RocketLauncher";
        setTextureAtlas(textureAtlas);
    }

    @Override
    public void shot() {
        counter++;
        if(counter==1000/attackSpeed)
        {
            getAmmos().add(new Rocket(textureAtlas.findRegion("Rocket"),
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
        return (float)recomendedw;
    }

    @Override
    public float getRecomendedh() {
        return recomendedh;
    }


}
