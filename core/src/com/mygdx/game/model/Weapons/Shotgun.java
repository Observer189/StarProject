package com.mygdx.game.model.Weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.model.Ammos.Bullet;
import com.mygdx.game.model.Weapon;

/**
 * Created by Sash on 29.05.2018.
 */

public class Shotgun extends Weapon {
    String name;
    int cost;
    private float bulletRotation;
    private float spread;
    private int fractionNumber;
    TextureAtlas textureAtlas;
    int counter;
    float recomendedw=(float) (Gdx.graphics.getWidth()/8.59060403);;
    float recomendedh= (float) (Gdx.graphics.getHeight()/3.6);

    private float attackSpeed;
    public Shotgun(TextureAtlas textureAtlas, float x, float y) {
        super(textureAtlas.findRegion("Shotgun"), x, y, 3f, 6f,5,
                new Bullet(textureAtlas.findRegion("Bullet"),x,y,0));
        this.textureAtlas=textureAtlas;
        counter=0;
        cost=40000;
        attackSpeed=5;

        name="Shotgun";
        spread=15;
        fractionNumber=6;
        setTextureAtlas(textureAtlas);
    }

    @Override
    public void shot() {
        counter++;
        if(counter==1000/attackSpeed)
        {

            /*getAmmos().add(new Bullet(textureAtlas.findRegion("Bullet"),
                    getX()+Math.abs((float)((getWidth()/2-0.5)*Math.cos(Math.toRadians(getRotation())))) -(float)( Math.sqrt(Math.pow(getWidth()/2-0.5f,2)+Math.pow(getHeight(),2))*Math.sin(Math.toRadians(getRotation()))),
                    getY()-Math.abs((float)((getWidth()-0.5f)*Math.sin(Math.toRadians(getRotation())))) +(float)(Math.sqrt(Math.pow(getWidth()/2-0.5f,2)+Math.pow(getHeight(),2))*Math.cos(Math.toRadians(getRotation()))),
                    getRotation()));*/
            for(int i=0;i<fractionNumber;i++) {
                bulletRotation=(float)(Math.random()*spread-spread/2+getRotation());
                getAmmos().add(new Bullet(textureAtlas.findRegion("Bullet"),
                        getCenterX() - (float) (getAmmo().getWidth() / 2 * Math.cos(Math.toRadians(bulletRotation))),
                        getCenterY() - (float) (getAmmo().getWidth() / 2 * Math.sin(Math.toRadians(bulletRotation))),
                        bulletRotation));
            }

            counter = 0;
        }
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
        return recomendedh;
    }

}

