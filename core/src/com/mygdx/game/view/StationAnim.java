package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;



public class StationAnim {
    int currentExplosionFrame = 0;
    int explosionCounter = 4;
    TextureAtlas textureAtlas;
    SpriteBatch batch;
    TextureRegion explosionRegion;
    Stage stage;
    Image img;

    public StationAnim(TextureAtlas textureAtlas, SpriteBatch batch){
        this.textureAtlas=textureAtlas;

        this.batch  =batch;
        img=new Image(textureAtlas.findRegion("WB_baseu2_d0"));
        img.setSize(550,550);
        img.setPosition(Gdx.graphics.getWidth()/2-img.getWidth()/2,Gdx.graphics.getHeight()/2-img.getHeight()/2);
        }




    public  void draw(){


        if (currentExplosionFrame <= 31) {

            if (explosionCounter == 4) {
                currentExplosionFrame++;
                explosionRegion = textureAtlas.findRegion(String.valueOf(20000+(currentExplosionFrame-1)));
                explosionCounter = 0;
            }
            batch.begin();
            img.draw(batch,1);
            batch.draw(explosionRegion, Gdx.graphics.getWidth()/2-100, Gdx.graphics.getHeight()/2-100, 200, 200);
            batch.end();
            explosionCounter++;
        }
        //System.out.println("ExpCountFrame: "+currentExplosionFrame+" expCount: "+explosionCounter);
        if (currentExplosionFrame == 31) {

            currentExplosionFrame = 0;
        }
    }
}
