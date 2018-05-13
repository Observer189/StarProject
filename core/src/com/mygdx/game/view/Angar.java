package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.Ships.Pulsate;
import com.mygdx.game.utils.TextManager;

public class Angar implements Screen {
    Stage stage;
    OrthographicCamera camera;
    Game game;
    SpriteBatch batch;
    Skin skin;
    TextureAtlas textureAtlas;
    Image Frameimg,Shimg;
    TextManager textManager;
    Image[] guns;
    MainMenu menu;
    BitmapFont font;
    Player player;



    public Angar(Game game, SpriteBatch batch,MainMenu menu,Player player){
        this.game=game;
        this.batch = batch;
       this.menu=menu;
       this.player=player;


    }

    @Override
    public void show() {
        textManager=new TextManager(0,0);
        font = textManager.fontInitialize(Color.BLACK, 1);


        camera=new OrthographicCamera();
        batch=new SpriteBatch();
        skin=new Skin();
        textureAtlas=new TextureAtlas(Gdx.files.internal("TexturePack.atlas"));
        skin.addRegions(textureAtlas);
        stage=new Stage();

        Shimg=new Image(skin.getDrawable("1"));
        Shimg.setSize((float) (Gdx.graphics.getHeight()/3.6), (float) (Gdx.graphics.getHeight()/3.6));
        Shimg.setPosition((float) (Gdx.graphics.getWidth()/Gdx.graphics.getWidth()+Gdx.graphics.getWidth()/85.3), (float) (Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/3.44));

        Frameimg=new Image(skin.getDrawable("Frame"));
        Frameimg.setSize((float) (Gdx.graphics.getWidth()/2.7), (float) (Gdx.graphics.getHeight()/3.3));
        Frameimg.setPosition((float) (Shimg.getX()-Gdx.graphics.getWidth()/85.3),Shimg.getY()-Gdx.graphics.getHeight()/72);
        Frameimg.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {



            }
        });

        //frames for guns

        guns=new Image[player.getCurrentShip().getFixingPointsDigit()];
        float x=Frameimg.getX()+Gdx.graphics.getHeight()/8/2;
        float y=Frameimg.getY()-Gdx.graphics.getHeight()/6;
        for (int i=0;i<guns.length;i++){

            Image img= new Image(skin.getDrawable("Frame"));
            img.setSize(85,85);
            img.setPosition(x,y);
            guns[i]=img;
            guns[i].addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {



                }
            });


            x+=img.getWidth()+img.getWidth()/2;
            stage.addActor(guns[i]);
        }

        // stage.addActor(guns[1]);
        stage.addActor(Frameimg);
        stage.addActor(Shimg);

    }

    @Override
    public void render(float delta) {
        camera.setToOrtho(false, 800, 480);
        Gdx.gl.glClearColor(0, 64, 247, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        textManager.displayMessage(batch,font," "+player.resources.shipList,200,200);

        stage.act(delta);
        stage.draw();
        batch.begin();
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

    }
    class StageForButton extends Stage {
        Button btn;
        int x;
        int y;
        int width;
        int height;

        public StageForButton(Button.ButtonStyle btnstyle, int x, int y, int width, int height) {
            this.height = height;
            this.width = width;
            this.y = y;
            this.x = x;
            btn = new Button(btnstyle);
            btn.setBounds(x, y, width, height);


            addActor(btn);
        }
    }

}
