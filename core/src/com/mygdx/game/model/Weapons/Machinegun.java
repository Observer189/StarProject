package com.mygdx.game.model.Weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.model.Ammos.Bullet;
import com.mygdx.game.model.Weapon;

/**
 * Created by Sash on 18.05.2018.
 */

public class Machinegun extends Weapon {
    String name;
    int cost;
    private float bulletRotation;
    private float spread;
    TextureAtlas textureAtlas;
    int counter;
    int recomendedw= Gdx.graphics.getWidth()/16;
    float recomendedh= (float) (Gdx.graphics.getHeight()/3.6);

    private float attackSpeed;
    public Machinegun(TextureAtlas textureAtlas, float x, float y) {
        super(textureAtlas.findRegion("Machinegun"), x, y, 3f, 6f,50,
                new Bullet(textureAtlas.findRegion("Bullet"),x,y,0));
        this.textureAtlas=textureAtlas;
        counter=0;
        cost=12500;
        attackSpeed=50;

        name="Machinegun";
        spread=5;
        setTextureAtlas(textureAtlas);
    }

    @Override
    public void shot() {
        counter++;
        if(counter==1000/attackSpeed)
        {
             bulletRotation=(float)(Math.random()*spread-spread/2+getRotation());
            /*getAmmos().add(new Bullet(textureAtlas.findRegion("Bullet"),
                    getX()+Math.abs((float)((getWidth()/2-0.5)*Math.cos(Math.toRadians(getRotation())))) -(float)( Math.sqrt(Math.pow(getWidth()/2-0.5f,2)+Math.pow(getHeight(),2))*Math.sin(Math.toRadians(getRotation()))),
                    getY()-Math.abs((float)((getWidth()-0.5f)*Math.sin(Math.toRadians(getRotation())))) +(float)(Math.sqrt(Math.pow(getWidth()/2-0.5f,2)+Math.pow(getHeight(),2))*Math.cos(Math.toRadians(getRotation()))),
                    getRotation()));*/

                 getAmmos().add(new Bullet(textureAtlas.findRegion("Bullet"),
                         getCenterX()-(float)(getAmmo().getWidth()/2*Math.cos(Math.toRadians(bulletRotation))),
                         getCenterY()-(float)(getAmmo().getWidth()/2*Math.sin(Math.toRadians(bulletRotation))),
                         bulletRotation));

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
    public float getAttackSpeed() {
        return attackSpeed;
    }

    @Override
    public float getRecomendedw() {
        return recomendedw;
    }

    @Override
    public float getRecomendedh() {
        return (float)recomendedh;
    }
}
