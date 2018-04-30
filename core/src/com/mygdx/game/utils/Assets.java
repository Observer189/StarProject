package com.mygdx.game.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by Sash on 24.04.2018.
 */

public class Assets
{
    private AssetManager manager;
    public Assets()
    {
        manager=new AssetManager();
        manager.load("TexturePack.atlas",TextureAtlas.class);
        manager.finishLoading();
    }

    public AssetManager getManager() {
        return manager;
    }

    public void dispose()
    {
        manager.dispose();
    }
}
