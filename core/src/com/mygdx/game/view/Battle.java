package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.utils.TextManager;

import retrofit2.Retrofit;

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
        batch=new SpriteBatch();


        textManager=new TextManager(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

    }

    @Override
    public void render(float delta) {
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        Gdx.gl.glClearColor(40, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        textManager.displayMessage(batch, "FIGHT!", Color.BLACK, 50, (int) (Gdx.graphics.getWidth() / 3.5), (int) (Gdx.graphics.getHeight() / 1.3 + 20));
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
