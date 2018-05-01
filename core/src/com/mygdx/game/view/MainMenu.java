package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.utils.TextManager;

/**
 * Created by Sash on 24.04.2018.
 */

public class MainMenu implements Screen {
    public float y=20/(float)(Gdx.graphics.getWidth()/Gdx.graphics.getHeight());
    StageForButton sfb;
    SpriteBatch batch;
    Game game;
    TextureRegion img;
    private TextureAtlas textureAtlas;
   TextManager textManager;
   public Button.ButtonStyle p_button;





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

        Skin skin=new Skin();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("TexturePack.atlas")));
        p_button=new Button.ButtonStyle();
        p_button.up= skin.getDrawable("Hunter");
        p_button.down= skin.getDrawable("Hunter");
        sfb=new StageForButton();

    }

    @Override
    public void render(float delta) {
        System.out.println("render");
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        textManager.displayMessage(batch,"Hello, Dan",Color.BLUE,50,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());


        batch.begin();
        batch.draw(img,100,100);

        sfb.act(delta);
        sfb.draw();

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
        sfb.dispose();
        textureAtlas.dispose();

    }

    public void setTextureAtlas(TextureAtlas textureAtlas) {
        this.textureAtlas = textureAtlas;
    }
    class StageForButton extends Stage{
        public StageForButton() {
            super(new FitViewport(Gdx.graphics.getWidth(),  Gdx.graphics.getHeight()));
            Button play=new Button(p_button);
            play.setBounds(250,250,200,200);
            addActor(play);
        }
    }
}
