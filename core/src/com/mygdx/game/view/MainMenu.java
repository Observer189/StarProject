package com.mygdx.game.view;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.model.Player;

import com.mygdx.game.utils.LogListener;
import com.mygdx.game.utils.PassListener;
import com.mygdx.game.utils.TextManager;

/**
 * Created by Sash on 24.04.2018.
 */

public class MainMenu implements Screen {

    public float y = 20 / (float) (Gdx.graphics.getWidth() / Gdx.graphics.getHeight());
    StageForButton sfplaybutton, sfshop, sfangar;
    SpriteBatch batch;
    Game game;
    Player player;
    public TextureAtlas textureAtlas;
    TextManager textManager;
    public Button.ButtonStyle p_button, sh_button, ang_button;
    Screen CTB;//ConnectToBattle
    Screen PreShop;
    BitmapFont font;
    Input.TextInputListener LogIn;
    public static String text;
    LogListener Log;
    PassListener Pass;
    int ForLogCounter=0;



    public MainMenu(SpriteBatch batch, Game game) {
        this.batch = batch;
        this.game = game;
    }


    @Override
    public void show() {
       /* LogIn = new Input.TextInputListener() {


            @Override
            public void input(String text) {
                MainMenu.text=text;

            }

            @Override
            public void canceled() {


            }
        };*/
        Log=new LogListener();
        Pass=new PassListener();
        if (ForLogCounter==0)
        Gdx.input.getTextInput(Log,"Log","","log-in");




        //PreShop=new PreShop(game,batch,textureAtlas);
        batch = new SpriteBatch();
        CTB=new ConnectToBattle(batch,game,textureAtlas,player);
        player=new Player();
        PreShop=new PreShop(game,batch,textureAtlas,this);

        textManager = new TextManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font=textManager.fontInitialize(Color.BLACK,1f);
        //game.setScreen(battle);
        Skin skin = new Skin();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("TexturePack.atlas")));

                p_button = new Button.ButtonStyle();
        p_button.up = skin.getDrawable("Start-up");
        p_button.down = skin.getDrawable("Start-down");



        sfplaybutton = new StageForButton(p_button, 300, 150);
        sfplaybutton.btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ForLogCounter++;
                game.setScreen(CTB);

            }
        });

        //кнопка магаз
        sh_button = new Button.ButtonStyle();
        sh_button.up = skin.getDrawable("Shop-up");
        sh_button.down = skin.getDrawable("Shop-down");
        sfshop = new StageForButton(sh_button, 600, 150);
        sfshop.btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ForLogCounter++;
                game.setScreen( PreShop);


            }
        });

        //кнопка ангар

        ang_button = new Button.ButtonStyle();
        ang_button.up = skin.getDrawable("Angar-up");
        ang_button.down = skin.getDrawable("Angar-down");
        sfangar = new StageForButton(ang_button, 900, 150);
        sfangar.btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ForLogCounter++;
                // game.setScreen();

            }
        });


        InputMultiplexer in=new InputMultiplexer();

        in.addProcessor(sfplaybutton);
        in.addProcessor(sfshop);
        in.addProcessor(sfangar);

            Gdx.input.setInputProcessor(in);
    }


    @Override
    public void render(float delta) {

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        Gdx.gl.glClearColor(0, 64, 247, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (ForLogCounter==0) {

            if (Log.Show == true) {
                Gdx.input.getTextInput(Log, "Log", "", "Log-in(must be filled)");
                Log.Show = false;

            }
            if (Log.ShowPass==true)

            if (Pass.Show == true) {
                Gdx.input.getTextInput(Pass, "Pass", "", "Your password");
                Pass.Show = false;
            }
        }
        if (Log.Show==false && Pass.Show==false)
            ForLogCounter++;


        textManager.displayMessage(batch,font, "Welcome to Star game!",  (int) (Gdx.graphics.getWidth() / 3.5), (int) (Gdx.graphics.getHeight() / 1.3 + 20));
        //textManager.displayMessage(batch,"x= "+Gdx.graphics.getWidth()+" y= "+Gdx.graphics.getHeight() ,Color.BLACK,50, (int) (Gdx.graphics.getWidth()/3.5), (int) (Gdx.graphics.getHeight()/1.3+90));


        //batch.draw(img,100,100);

        sfplaybutton.act(delta);
        sfplaybutton.draw();


        sfshop.act(delta);
        sfshop.draw();

        sfangar.act(delta);
        sfangar.draw();

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
        sfplaybutton.dispose();
        sfangar.dispose();
        sfshop.dispose();
        textureAtlas.dispose();
        game.dispose();



    }

    public void setTextureAtlas(TextureAtlas textureAtlas) {
        this.textureAtlas = textureAtlas;
    }

    class StageForButton extends Stage {
        Button btn;

        public StageForButton(Button.ButtonStyle btnstyle, int x, int y) {

            btn = new Button(btnstyle);
            btn.setBounds(x, y, 200, 200);




            addActor(btn);
        }
    }




}
