package com.mygdx.game.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class StarGen {
    int RandX = GenX();
    int RandY = GenY();
    int currentExplosionFrame = 0;
    int explosionCounter = 4;
    TextureAtlas textureAtlas;
    SpriteBatch batch;
    TextureRegion explosionRegion;

    public StarGen(TextureAtlas textureAtlas, SpriteBatch batch) {
        this.textureAtlas = textureAtlas;
        this.batch = batch;
    }

    // TextureRegion explosionRegion = null;
    public void draw() {
        if (currentExplosionFrame <= 15) {

            if (explosionCounter == 4) {
                currentExplosionFrame++;
                explosionRegion = textureAtlas.findRegion("Star" + currentExplosionFrame);
                explosionCounter = 0;
            }
            batch.begin();
            batch.draw(explosionRegion, RandX, RandY, 200, 200);
            batch.end();
            explosionCounter++;
        }
        //System.out.println("ExpCountFrame: "+currentExplosionFrame+" expCount: "+explosionCounter);
        if (currentExplosionFrame == 15) {
            RandX = GenX();
            RandY = GenY();
            currentExplosionFrame = 0;
        }


    }


    public static int GenX(){
        return  (int) (Math.random()*1080);

    }
    public static int GenY(){
        return  (int) (Math.random()*520);
    }
}
