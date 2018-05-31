package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.model.Player;


public class StationAnim {
    int currentExplosionFrame = 0;
    int explosionCounter = 4;
    TextureAtlas textureAtlas;
    SpriteBatch batch;
    TextureRegion explosionRegion;
    Stage stage;
    Image img,img2;
    float x;
    float y;
    public StationAnim(TextureAtlas textureAtlas, SpriteBatch batch, float x, float y, Player player){
        this.textureAtlas=textureAtlas;
        this.x=x;
        this.y=y;
        this.batch  =batch;
        img=new Image(textureAtlas.findRegion("WB_baseu2_d0"));
        img.setSize((float) (Gdx.graphics.getWidth()/1.86),(float) (Gdx.graphics.getWidth()/1.86));
        img.setPosition(x,y);
        if (player.getCurrentShip().getName().equals("1"))
        img2=new Image(textureAtlas.findRegion("Pulsate"));
        else
            img2=new Image((textureAtlas.findRegion(player.getCurrentShip().getName())));
        img2.setSize((float) (Gdx.graphics.getHeight()/5.2),(float) (Gdx.graphics.getHeight()/4.8));
        img2.setPosition(img.getX()+img.getWidth()/2-img2.getWidth()/2, img.getY()+img.getHeight()/2-img2.getHeight()/2);
        }




    public  void draw(){


      /*  if (currentExplosionFrame <= 31) {

            if (explosionCounter == 4) {
                currentExplosionFrame++;
                explosionRegion = textureAtlas.findRegion(String.valueOf(20000+(currentExplosionFrame-1)));
                explosionCounter = 0;
            }*/
            batch.begin();
            img.setPosition(x,y);


         //   batch.draw(explosionRegion, img.getX()+img.getWidth()/2-100, img.getY()+img.getHeight()/2-100, 200, 200);
            img.draw(batch,1);
            img2.draw(batch,1);
            batch.end();
            explosionCounter++;
        }
        //System.out.println("ExpCountFrame: "+currentExplosionFrame+" expCount: "+explosionCounter);
      //  if (currentExplosionFrame == 31) {

        //    currentExplosionFrame = 0;
      //  }
  //  }
}
