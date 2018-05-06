package com.mygdx.game.view;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.model.Player;
import com.mygdx.game.utils.TextManager;


//This screen is used to show Shop of Ships
public class ShopList implements Screen {
    OrthographicCamera camera;
    Game game;
    StageForButton Guns, Ships;
    Button.ButtonStyle Gstyle, Sstyle;
    SpriteBatch batch;
    TextureAtlas textureAtlas;
    TextManager textManager;
    Button.ButtonStyle st1, st2, st3, st4, st5; // Styles for 5 ships
    CellStage ct1, ct2, ct3, ct4, ct5; // 5 Stages for 5 ships
   public static Skin skin;
    Image InformationFrame,Money;


    public ShopList(Game game, SpriteBatch batch, TextureAtlas textureAtlas) {
        this.game = game;
        this.batch = batch;
        this.textureAtlas = textureAtlas;
    }


    @Override
    public void render(float delta) {

        camera.setToOrtho(false, 800, 480);
        Gdx.gl.glClearColor(0, 64, 247, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // textManager.displayMessage(batch,"Your money: " /*+ Player.getMoney()*/ ,Color.BLACK,ct1.size,20 , Gdx.graphics.getHeight()-20);
        //'1' ship
        textManager.displayMessage(batch, ct1.name, Color.BLACK, ct1.size, ct1.x + 200, ct1.y + 175);
        textManager.displayMessage(batch, "Price: " + ct1.price, Color.BLACK, ct1.size, ct1.x + 200, ct1.y + 75);
        ct1.act(delta);
        ct1.draw();

        //'Bat' ship
        textManager.displayMessage(batch, ct2.name, Color.BLACK, ct2.size, ct2.x + 200, ct2.y + 175);
        textManager.displayMessage(batch, "Price: " + ct2.price, Color.BLACK, ct2.size, ct2.x + 200, ct2.y + 75);
        ct2.act(delta);
        ct2.draw();
        //'Dakkar' ship
        textManager.displayMessage(batch, ct3.name, Color.BLACK, ct3.size, ct3.x + 200, ct3.y + 175);
        textManager.displayMessage(batch, "Price: " + ct3.price, Color.BLACK, ct3.size, ct3.x + 200, ct3.y + 75);
        ct3.act(delta);
        ct3.draw();
        //'Mite' ship
        textManager.displayMessage(batch, ct4.name, Color.BLACK, ct4.size, ct4.x + 200, ct4.y + 175);
        textManager.displayMessage(batch, "Price: " + ct4.price, Color.BLACK, ct4.size, ct4.x + 200, ct4.y + 75);
        ct4.act(delta);
        ct4.draw();
        //'Hunter' ship
        textManager.displayMessage(batch, ct5.name, Color.BLACK, ct5.size, ct5.x + 200, ct5.y + 175);
        textManager.displayMessage(batch, "Price: " + ct5.price, Color.BLACK, ct5.size, ct5.x + 200, ct5.y + 75);
        ct5.act(delta);
        ct5.draw();
        //Information bar
        textManager.displayMessage(batch, "Battle.player.getMoney()", Color.BLACK, 30, 50, Gdx.graphics.getHeight() - 293+125);

        //Top buutons for swithing
        Guns.act(delta);
        Guns.draw();
        Ships.act(delta);
        Ships.draw();

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

        batch.dispose();
        skin.dispose();
        Ships.dispose();
        Guns.dispose();
        ct1.dispose();
        ct2.dispose();
        ct3.dispose();
        ct5.dispose();
        ct4.dispose();
        textureAtlas.dispose();


    }

    @Override
    public void show() {
        camera = new OrthographicCamera();

        batch = new SpriteBatch();    //Battle.player.getMoney();
        textManager = new TextManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        skin = new Skin();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("TexturePack.atlas")));
        //Creating frame, where player can see his money (Information Bar)
        InformationFrame=new Image(skin.getDrawable("Frame"));
        Money=new Image(skin.getDrawable("Money"));
        InformationFrame.setSize(480,220);
        InformationFrame.setPosition(0,Gdx.graphics.getHeight() - 293-10);
        Money.setSize(75,75);
        Money.setPosition(InformationFrame.getImageX()+5, (float) (((Gdx.graphics.getHeight()) - 293-10)+(InformationFrame.getHeight()/2.7)));

        //--------------------drawing buttons again

        //creating button with guns image
        Gstyle = new Button.ButtonStyle();
        Gstyle.up = skin.getDrawable("UnSelectGun");
        Gstyle.down = skin.getDrawable("UnSelectGun");
        Guns = new StageForButton(Gstyle, 0, Gdx.graphics.getHeight() - 51);
        //change scene when 'guns' clicked
        Guns.btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                //  game.setScreen();

            }
        });


        //creating button with ships image
        Sstyle = new Button.ButtonStyle();
        Sstyle.up = skin.getDrawable("SelectShip");
        Sstyle.down = skin.getDrawable("SelectShip");
        Ships = new StageForButton(Sstyle, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 51);
        //change scene when 'ships' clicked
        Ships.btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                // game.setScreen(ShList);

            }
        });
        //-----------------------end


        //place for '1' ship
        st1 = new Button.ButtonStyle();
        st1.up = skin.getDrawable("1");
        st1.down = skin.getDrawable("1");
        ct1 = new CellStage(st1, 15, (int) (Gdx.graphics.getHeight() - 513.6), "Pulsate", 40, 40);
        //place for 'Bat' ship
        st2 = new Button.ButtonStyle();
        st2.up = skin.getDrawable("Bat");
        st2.down = skin.getDrawable("Bat");
        ct2 = new CellStage(st2, 15, Gdx.graphics.getHeight() - 735, "Bat", 40, 200);
        //place for 'Dakkar' ship
        st3 = new Button.ButtonStyle();
        st3.up = skin.getDrawable("Dakkar");
        st3.down = skin.getDrawable("Dakkar");
        ct3 = new CellStage(st3, (int) (ct1.x + ct1.img.getWidth())+1, ct2.y , "Dakkar", 40, 280);
        //place for 'Mite' ship
        st4 = new Button.ButtonStyle();
        st4.up = skin.getDrawable("Mite");
        st4.down = skin.getDrawable("Mite");
        ct4 = new CellStage(st4, (int) (ct1.x + ct1.img.getWidth())+1, Gdx.graphics.getHeight() - 293, "Mite", 40, 145);
        //place for 'Hunter' ship
        st5 = new Button.ButtonStyle();
        st5.up = skin.getDrawable("Hunter");
        st5.down = skin.getDrawable("Hunter");
        ct5 = new CellStage(st5, ct3.x, ct1.y, "Hunter", 40, 190);
        ct5.addActor(InformationFrame);
        ct5.addActor(Money);


        // join
        // InputMultiplexer in=new InputMultiplexer();

        //  in.addProcessor(ct1);
        // in.addProcessor(ct2);
        // in.addProcessor(ct3);
        // in.addProcessor(ct4);
        // in.addProcessor(ct5);

        //  Gdx.input.setInputProcessor(in);
    }

    static class CellStage extends Stage {
        Button btn;
        int x;
        int y;
        String name;
        Image img;
        int size;
        int price;

        public CellStage(Button.ButtonStyle btnstyle, int x, int y, String name, int size, int price) {
            btn = new Button(btnstyle);
            btn.setBounds(x, y, 200, 200);
            img = new Image(skin.getDrawable("Frame"));
            img.setPosition(x - 15, y - 10);
            img.setSize(480, 220);

            this.x = x;
            this.y = y;
            this.name = name;
            this.size = size;
            this.price = price;

            addActor(btn);
            addActor(img);
        }
    }

    static class StageForButton extends Stage {
        Button btn;

        public StageForButton(Button.ButtonStyle btnstyle, int x, int y) {

            btn = new Button(btnstyle);
            btn.setBounds(x, y, Gdx.graphics.getWidth() / 2, 50);


            addActor(btn);
        }
    }

}


