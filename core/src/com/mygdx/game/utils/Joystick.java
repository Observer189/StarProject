package com.mygdx.game.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Sash on 06.05.2018.
 */

public class Joystick {
    Stick staticPart;
    Stick dynamicPart;
    boolean isActive;
    public Joystick(SpriteBatch batch,int x, int y,TextureRegion staticRegion,TextureRegion dynamicRegion)
    {
       staticPart=new Stick(batch,x,y,10,10,staticRegion);
       dynamicPart=new Stick(batch,staticPart.centerX-5/2,staticPart.centerY-5/2,5,5,dynamicRegion);//установка динамической части в центр статической
        isActive=false;
    }
    public void draw()
    {
        if(isActive) {
            staticPart.draw();
            dynamicPart.draw();
        }
    }
    public Vector2D getVector()
    {
        if(isActive)
        return new Vector2D(dynamicPart.centerX-staticPart.centerX,dynamicPart.centerY-staticPart.centerY);
        else return new Vector2D(0,0);

    }
    public class Stick{
        private float x;
        private float y;
        private float centerX;
        private float centerY;
        private float width;
        private float height;
        TextureRegion vision;
        SpriteBatch batch;
        Stick(SpriteBatch batch,float x, float y,float width,float height, TextureRegion textureRegion)
        {
            this.batch=batch;
            this.x=x;
            this.y=y;
            this.width=width;
            this.height=height;
            vision=textureRegion;
            centerX=x+width/2;
            centerY=y+height/2;
        }
        public void draw()
        {
            batch.begin();
            batch.draw(vision,x,y,width,height);
            batch.end();
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }

        public void setX(float x) {
            this.x = x;
        }

        public void setY(float y) {
            this.y = y;
        }

        public float getCenterX() {
            return centerX;
        }

        public float getCenterY() {
            return centerY;
        }
        public void setCenter(float centerX,float centerY)
        {
            x=centerX-width/2;
            y=centerY-height/2;
            this.centerX=centerX;
            this.centerY=centerY;
        }
    }

}
