package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.mygdx.game.utils.TextManager;

/**
 * Created by Sash on 24.04.2018.
 */

public class MainMenu implements Screen {
    SpriteBatch batch;
    TextureRegion img;
    private TextureAtlas textureAtlas;
   TextManager textManager;
    private Screen battle;
    Game game;


    public MainMenu(SpriteBatch batch, Game game)
    {
        this.batch=batch;
        this.game=game;
    }




    @Override
    public void show() {
        batch = new SpriteBatch();


       textManager = new TextManager(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        img = new TextureRegion(textureAtlas.findRegion("Dakkar"));
        battle = new Battle(batch,game,textureAtlas);
       game.setScreen(battle);

        System.out.println("show");

    }

    @Override
    public void render(float delta) {
        System.out.println("render");
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        textManager.displayMessage(batch,"Hello, Dan",Color.BLUE,50,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.begin();
        batch.draw(img,100,100);


        batch.end();


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
        batch.dispose();

    }

    public void setTextureAtlas(TextureAtlas textureAtlas) {
        this.textureAtlas = textureAtlas;
    }
}
