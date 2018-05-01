package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.utils.TextManager;

/**
 * Created by Sash on 27.04.2018.
 */

public class Battle implements Screen {
    TextManager textManager;
    SpriteBatch batch;
    Game game;
    TextureAtlas textureAtlas;
    public Battle(SpriteBatch batch, Game game,TextureAtlas textureAtlas)
    {
        this.batch=batch;
        this.game=game;
        this.textureAtlas=textureAtlas;
    }
    @Override
    public void show() {

        textManager=new TextManager(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    }

    @Override
    public void render(float delta) {
       textManager.displayMessage(batch,"Fight!!!",Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
