package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by Sash on 24.04.2018.
 */

public class Assets
{

    private AssetManager manager;
    public static Preferences preferences;
    public static String Log;
    public  static String Pass;
    public Assets()
    {
        manager=new AssetManager();
        manager.load("TexturePack.atlas",TextureAtlas.class);
        manager.finishLoading();
    }
    public static void  load(){
        preferences= Gdx.app.getPreferences("DB1");
      //  preferences.putString("Log","");
      // preferences.putString("Pass","");
        Log=preferences.getString("Log","");
        Pass=preferences.getString("Pass","");
    }

    public AssetManager getManager() {
        return manager;
    }

    public void dispose()
    {
        manager.dispose();
    }
}
