package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


import com.mygdx.game.model.Player;
import com.mygdx.game.model.Ship;
import com.mygdx.game.utils.TextManager;
import com.mygdx.game.utils.Toast;

import java.awt.Menu;

public class ShipShow implements Screen {
    //for drawing
    OrthographicCamera camera;
    SpriteBatch batch;
    Game game;
    TextManager textManager;
    BitmapFont font,font1;
    public TextureAtlas textureAtlas;
    Skin skin;
    Stage stage;
    int xt;
    int yt;
    int dyt;
    Boolean MakeToast=false;
    Ship ship;
    //Buttons
    StageForButton Back, Buy;
    Button.ButtonStyle BaStyle, BuStyle;
    //Sreens
    Screen ShList;
    Toast toast;
    MainMenu menu;
    Player player;
    //for ship's params
    Image Shipimg;
    String name;
    int cost;
    int maxHp;


    float velocity;
    float maxSpeed;



    public ShipShow(Ship ship, Game game,MainMenu menu,Player player) {

        this.ship=ship;
        this.game=game;
        this.menu=menu;
        this.player=player;

    }


    @Override
    public void show() {


        textureAtlas=new TextureAtlas(Gdx.files.internal("TexturePack.atlas"));
        stage = new Stage();
        skin = new Skin();
        skin.addRegions(textureAtlas);

        Shipimg = new Image(ship.getImg());
        Shipimg.setSize((float) (Gdx.graphics.getWidth() / 4.3), (float) (Gdx.graphics.getHeight() / 2.4));
        Shipimg.setPosition(Gdx.graphics.getWidth() / Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 2 - Shipimg.getHeight() / 2);
        //used for textManager params

        xt = (int) (Shipimg.getX() + Shipimg.getWidth() * 1.2);
        yt = (int) ((Shipimg.getY() + Shipimg.getHeight()) * 1.1);
        stage.addActor(Shipimg);
        dyt = Gdx.graphics.getHeight() / 9;

        batch = new SpriteBatch();
        camera = new OrthographicCamera();

        textManager = new TextManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        ShList=new ShopList(game,batch,textureAtlas,menu,player);
        font = textManager.fontInitialize(Color.BLACK, 1);
        font1 = textManager.fontInitialize(Color.WHITE, 1);
        Toast.ToastFactory toastFactory = new Toast.ToastFactory.Builder()
                .font(font1)
                .build();
        toast = toastFactory.create("Successfully bought" , Toast.Length.LONG);



        //Buy button
        BuStyle = new Button.ButtonStyle();
        BuStyle.up = skin.getDrawable("Buy-up");
        BuStyle.down = skin.getDrawable("Buy-down");
        Buy = new StageForButton(BuStyle,(int) ( Gdx.graphics.getWidth()-Shipimg.getHeight()), (int) ((Gdx.graphics.getHeight()) /2-Shipimg.getHeight()/2) , 200, 200);

        Buy.btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MakeToast=true;
                ShList=new ShopList(game,batch,textureAtlas,menu,player);
                player.resources.shipList.add(ship);
             

            }
        });
        //Back button
        BaStyle = new Button.ButtonStyle();
        BaStyle.up = skin.getDrawable("Back-up");
        BaStyle.down = skin.getDrawable("Back-down");
        Back = new StageForButton(BaStyle, (int) Shipimg.getX(), (int) (Gdx.graphics.getHeight()/Gdx.graphics.getHeight()), 200, 200);
        System.out.println("Clicker");
        Back.btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(ShList);
                System.out.println( player.resources.shipList);

            }
        });


        System.out.println("Clicker2");
        InputMultiplexer in=new InputMultiplexer();
        in.addProcessor(Buy);
        in.addProcessor(Back);
        Gdx.input.setInputProcessor(in);




    }

    @Override
    public void render(float delta) {
        camera.setToOrtho(false, 800, 480);
        Gdx.gl.glClearColor(0, 64, 247, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (name == "1") name = "Pulsate";

        textManager.displayMessage(batch, font, "" + ship.getName(), xt, yt);
        textManager.displayMessage(batch, font, "Price: " + ship.getCost(), xt, yt - dyt);
        textManager.displayMessage(batch, font, "HP: " + ship.getMaxHp(), xt, yt - dyt * 2);
        textManager.displayMessage(batch, font, "Speed: " + ship.getMaxHp(), xt, yt - dyt * 3);
        textManager.displayMessage(batch, font, "Velocity: " + ship.getVelocity(), xt, yt - dyt * 4);
        textManager.displayMessage(batch, font, "Weapons: "+ship.getFixingPointsDigit(), xt, yt - dyt * 5);

        if (MakeToast==true){
            toast.render(Gdx.graphics.getDeltaTime());

        }
        Buy.act(delta);
        Buy.draw();
        Back.act();
        Back.draw();
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
        game.dispose();
        Buy.dispose();
        Back.dispose();
        batch.dispose();
        textureAtlas.dispose();
        skin.dispose();
        stage.dispose();

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