package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Sash on 24.04.2018.
 */


import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class TextManager {


    static float width,height;
    FreeTypeFontGenerator gen;
    FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    public TextManager(float width,float height){
        this.width=width;
        this.height=height;

        gen = new FreeTypeFontGenerator(Gdx.files.internal("Insight Sans SSi.ttf"));
        fontParameter=new FreeTypeFontGenerator.FreeTypeFontParameter();


    }
    public BitmapFont fontInitialize(Color color, float scale)
    {
        BitmapFont font=new BitmapFont();
        fontParameter.color = color;
        fontParameter.size= (int) (Gdx.graphics.getWidth()/25.6);

        font=gen.generateFont(fontParameter);
        font.getData().setScale(scale);
        return font;
    }

    public void displayMessage(SpriteBatch batch,BitmapFont font,String str,float x,float y){


        batch.begin();
        font.draw(batch,str, x,y);
        batch.end();
    }


}

