package com.mygdx.game.view;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.model.Player;
import com.mygdx.game.utils.TextManager;




public class ShopList  implements Screen{
    Game game;
    StageForButton Guns,Ships;
    Button.ButtonStyle Gstyle,Sstyle;
    SpriteBatch batch;
    TextureAtlas textureAtlas;
    TextManager textManager;
    Button.ButtonStyle st1,st2,st3,st4,st5;
    CellStage ct1,ct2,ct3,ct4,ct5;

    public ShopList(Game game, SpriteBatch batch,TextureAtlas textureAtlas) {
        this.game = game;
        this.batch = batch;
        this.textureAtlas=textureAtlas;
    }


    @Override
    public void render(float delta) {
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        Gdx.gl.glClearColor(0, 64, 247, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       // textManager.displayMessage(batch,"Your money: " /*+ Player.getMoney()*/ ,Color.BLACK,ct1.size,20 , Gdx.graphics.getHeight()-20);
        //'1' ship
        textManager.displayMessage(batch,ct1.name ,Color.BLACK,ct1.size, ct1.x+200, ct1.y+175);
        textManager.displayMessage(batch,"Price: "+ct1.price ,Color.BLACK,ct1.size, ct1.x+200, ct1.y+75);
        ct1.act(delta);
        ct1.draw();
        textManager.displayMessage(batch,"_____________________________" ,Color.BLACK,ct1.size, 0, ct2.y+234);
        //'Bat' ship
        textManager.displayMessage(batch,ct2.name ,Color.BLACK,ct2.size, ct2.x+200, ct2.y+175);
        textManager.displayMessage(batch,"Price: "+ct2.price ,Color.BLACK,ct2.size, ct2.x+200, ct2.y+75);

        Guns.act(delta);
        Guns.draw();
        Ships.act(delta);
        Ships.draw();
        ct2.act(delta);
        ct2.draw();
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

    @Override
    public void show() {
        batch=new SpriteBatch();
       textManager=new TextManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Skin skin = new Skin();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("TexturePack.atlas")));
        //drawing buttons again

        //creating button with guns image
        Gstyle = new Button.ButtonStyle();
        Gstyle.up = skin.getDrawable("UnSelectGun");
        Gstyle.down = skin.getDrawable("UnSelectGun");
         Guns=new StageForButton(Gstyle,0,Gdx.graphics.getHeight()-51);
        //change scene when 'guns' clicked
        Guns.btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                //  game.setScreen();

            }
        });



        //creating button with ships image
        Sstyle = new Button.ButtonStyle();
        Sstyle.up = skin.getDrawable("SelectShip");
        Sstyle.down = skin.getDrawable("SelectShip");
        Ships=new StageForButton(Sstyle,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()-51);
        //change scene when 'ships' clicked
        Ships.btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

               // game.setScreen(ShList);

            }
        });

        //place for '1' ship
        st1=new Button.ButtonStyle();
        st1.up=skin.getDrawable("1");
        st1.down=skin.getDrawable("1");
        ct1=new CellStage(st1,20,Gdx.graphics.getHeight()-520,"Pulsate",40,40);
        //place for 'Bat' ship
        st2=new Button.ButtonStyle();
        st2.up=skin.getDrawable("Bat");
        st2.down=skin.getDrawable("Bat");
        ct2=new CellStage(st2,20,Gdx.graphics.getHeight()-735,"Bat",40,200);




        // join
       // InputMultiplexer in=new InputMultiplexer();

      //  in.addProcessor(ct1);
       // in.addProcessor(ct2);
       // in.addProcessor(ct3);
       // in.addProcessor(ct4);
       // in.addProcessor(ct5);

      //  Gdx.input.setInputProcessor(in);
    }

    class CellStage extends Stage {
        Button btn;
        int x;
        int y;
        String name;
        int size;
        int price;

        public CellStage(Button.ButtonStyle btnstyle, int x, int y,String name,int size,int price) {
            btn = new Button(btnstyle);
            btn.setBounds(x, y, 200, 200);
            this.x=x;
            this.y=y;
            this.name=name;
            this.size=size;
            this.price=price;

            addActor(btn);
        }
    }
    class StageForButton extends Stage {
        Button btn;

        public StageForButton(Button.ButtonStyle btnstyle, int x, int y) {

            btn = new Button(btnstyle);
            btn.setBounds(x, y, Gdx.graphics.getWidth()/2, 50);




            addActor(btn);
        }
    }

}


