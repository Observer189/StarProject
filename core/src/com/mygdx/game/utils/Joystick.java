package com.mygdx.game.utils;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.control.BattleProcessor;
import com.mygdx.game.view.Battle;

/**
 * Created by Sash on 06.05.2018.
 */

public class Joystick {
    public Stick staticPart;
    public Stick dynamicPart;
    boolean isActive;
    Vector2 vector;
    public Joystick(SpriteBatch batch,float x, float y,TextureRegion staticRegion,TextureRegion dynamicRegion)
    {
       staticPart=new Stick(batch,x,y,20,20,staticRegion);
       dynamicPart=new Stick(batch,staticPart.centerX-2.5f,staticPart.centerY-2.5f,10,10,dynamicRegion);//установка динамической части в центр статической
        isActive=false;
        vector = new Vector2(dynamicPart.centerX - staticPart.centerX, dynamicPart.centerY - staticPart.centerY);
    }
    public void draw()
    {
        if (dynamicPart.centerX>staticPart.centerX+staticPart.width/2)dynamicPart.setCenterX(staticPart.centerX+staticPart.width/2);
        if (dynamicPart.centerX<staticPart.centerX-staticPart.width/2)dynamicPart.setCenterX(staticPart.centerX-staticPart.width/2);
        if (dynamicPart.centerY>staticPart.centerY+staticPart.height/2)dynamicPart.setCenterY(staticPart.centerY+staticPart.height/2);
        if (dynamicPart.centerY<staticPart.centerY-staticPart.height/2)dynamicPart.setCenterY(staticPart.centerY-staticPart.height/2);
        if(isActive) {
            staticPart.draw();
            dynamicPart.draw();
        }
    }
    public Vector2 getVector()
    {
        if(isActive) {

            vector.nor();
            return vector;
        }
        else return new Vector2(0,0);

    }
    public void setActive(boolean b)
    {
        isActive=b;
    }
    public void create(float x, float y)
    {
        staticPart.setCenter(x,y);
        dynamicPart.setCenter(x,y);
        setActive(true);
    }
    public void update(float offsetX, float offsetY,float offsetDynamicX,float offsetDynamicY)
    {
        staticPart.setCenter((Battle.camX-Battle.widthCamera/2)+offsetX,(Battle.camY-Battle.heightCamera/2)+offsetY);
        dynamicPart.setCenter(staticPart.centerX+offsetDynamicX,staticPart.centerY+offsetDynamicY);
        vector.x=dynamicPart.centerX - staticPart.centerX;
        vector.y=dynamicPart.centerY - staticPart.centerY;

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
            centerY=y+height/2;       }
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

        public void setCenterX(float centerX) {
            x=centerX-width/2;
            this.centerX = centerX;
        }

        public void setCenterY(float centerY) {
            y=centerY-height/2;
            this.centerY = centerY;
        }

        public void setCenter(float centerX, float centerY)
        {
            x=centerX-width/2;
            y=centerY-height/2;
            this.centerX=centerX;
            this.centerY=centerY;
        }

    }

}
