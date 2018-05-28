package com.mygdx.game.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class StationAnim {
    int currentExplosionFrame = 0;
    int explosionCounter = 4;
    TextureAtlas textureAtlas;
    SpriteBatch batch;
    TextureRegion explosionRegion;
    Stage stage;
    Image img;
    float x;
    float y;
    public StationAnim(TextureAtlas textureAtlas, SpriteBatch batch,float x,float y){
        this.textureAtlas=textureAtlas;
        this.x=x;
        this.y=y;
        this.batch  =batch;
        img=new Image(textureAtlas.findRegion("WB_baseu2_d0"));
        img.setSize(420,420);
        img.setPosition(x,y);
        }




    public  void draw(){


        if (currentExplosionFrame <= 31) {

            if (explosionCounter == 4) {
                currentExplosionFrame++;
                explosionRegion = textureAtlas.findRegion(String.valueOf(20000+(currentExplosionFrame-1)));
                explosionCounter = 0;
            }
            batch.begin();
            img.setPosition(x,y);


            batch.draw(explosionRegion, img.getX()+img.getWidth()/2-100, img.getY()+img.getHeight()/2-100, 200, 200);
            img.draw(batch,1);
            batch.end();
            explosionCounter++;
        }
        //System.out.println("ExpCountFrame: "+currentExplosionFrame+" expCount: "+explosionCounter);
        if (currentExplosionFrame == 31) {

            currentExplosionFrame = 0;
        }
    }
}
