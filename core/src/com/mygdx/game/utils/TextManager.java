package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Sash on 24.04.2018.
 */


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class TextManager {

   public  BitmapFont font;
   public float width,height;
   public FreeTypeFontGenerator gen;
    public FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;

    public TextManager(float width,float height){
        this.width=width;
        this.height=height;
        font = new BitmapFont();
        gen = new FreeTypeFontGenerator(Gdx.files.internal("Insight Sans SSi.ttf"));
        fontParameter=new FreeTypeFontGenerator.FreeTypeFontParameter();

    }


    public void displayMessage(SpriteBatch batch,String str,int x,int y){
        fontParameter.color = Color.BLUE;
        fontParameter.size=30;
        font=gen.generateFont(fontParameter);

        batch.begin();
        font.draw(batch,str, x,y);
        batch.end();
    }
    public void displayMessage(SpriteBatch batch,String str,Color color,int size,int x,int y){
        fontParameter.color = color;
        fontParameter.size=size;
        font=gen.generateFont(fontParameter);

       // batch.begin();
        font.draw(batch, str, x,y);


       // batch.end();
    }

}

