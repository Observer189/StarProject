package com.mygdx.game.view;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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

import com.mygdx.game.model.Ships.Pulsate;
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
    public Player player;
    public TextureAtlas textureAtlas;
    TextManager textManager;
    public Button.ButtonStyle p_button, sh_button, ang_button;
    public Screen CTB;//ConnectToBattle
    Screen PreShop,angar;
    BitmapFont font;
    Input.TextInputListener LogIn;

    public LogListener Log;
    PassListener Pass;
    int ForLogCounter=0;
    InputMultiplexer in;
    String first;
    public Music music;



    public MainMenu(SpriteBatch batch, Game game,Player player) {
        this.batch = batch;
        this.game = game;
        this.player=player;
    }


    @Override
    public void show() {
        music = Gdx.audio.newMusic(Gdx.files.internal("MenuMusic.mp3"));

       /* LogIn = new Input.TextInputListener() {


            @Override
            public void input(String text) {
                MainMenu.text=text;

            }

            @Override
            public void canceled() {


            }
        };*/
        first="Your password";
        Log=new LogListener();
        Pass=new PassListener();
        if (ForLogCounter==0) {
            Gdx.input.getTextInput(Log, "Log", "", "log-in");
            music.setLooping(true);
            music.play();
        }



        //PreShop=new PreShop(game,batch,textureAtlas);
        batch = new SpriteBatch();
        CTB=new ConnectToBattle(batch,game,textureAtlas,player,this);

        PreShop=new PreShop(game,batch,textureAtlas,this,player);

        angar=new Angar(game,batch,this,player);
        textManager = new TextManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font=textManager.fontInitialize(Color.BLACK,1f);
        //game.setScreen(battle);
        Skin skin = new Skin();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("TexturePack.atlas")));

                p_button = new Button.ButtonStyle();
        p_button.up = skin.getDrawable("Start-up");
        p_button.down = skin.getDrawable("Start-down");



        sfplaybutton = new StageForButton(p_button, (int) (Gdx.graphics.getWidth()/4.3), (int) (Gdx.graphics.getHeight()/4.8));
        sfplaybutton.btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ForLogCounter++;

                music.stop();
                game.setScreen(CTB);

            }
        });

        //кнопка магаз
        sh_button = new Button.ButtonStyle();
        sh_button.up = skin.getDrawable("Shop-up");
        sh_button.down = skin.getDrawable("Shop-down");
        sfshop = new StageForButton(sh_button, (int) (Gdx.graphics.getWidth()/2.13), (int) (Gdx.graphics.getHeight()/4.8));
        sfshop.btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ForLogCounter++;
                DisableBtn(true);
                game.setScreen( PreShop);



            }
        });

        //кнопка ангар

        ang_button = new Button.ButtonStyle();
        ang_button.up = skin.getDrawable("Angar-up");
        ang_button.down = skin.getDrawable("Angar-down");
        sfangar = new StageForButton(ang_button, (int) (Gdx.graphics.getWidth()/1.42), (int) (Gdx.graphics.getHeight()/4.8));
        sfangar.btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ForLogCounter++;
                DisableBtn(true);
                 game.setScreen(angar);



            }
        });


       in=new InputMultiplexer();

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

            if (Log.Show) {
                Gdx.input.getTextInput(Log, "Log", "", "Log-in(must be filled)");
                System.out.println(Log.Show+"1");
                Log.Show = false;
                System.out.println(Log.Show+"2");
            }
        }
        if (ForLogCounter==0) {
            if (Log.ShowPass&&Pass.Show) {

                Gdx.input.getTextInput(Pass, "Pass", "", first);
                Pass.Show=false;
                if (Pass.Success)
                    Pass.canceled();
                else first="Something wrong. Check your password";

            }




        }



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
        System.out.println("Resumed");
        DisableBtn(false);

    }

    @Override
    public void pause() {
        music.stop();



    }

    @Override
    public void resume() {
    music.play();

    }

    @Override
    public void hide() {
        System.out.println("Hided");
        DisableBtn(true);


    }
    public void DisableBtn(Boolean b){
        if (b==true){
        in.removeProcessor(sfangar);
        in.removeProcessor(sfshop);
        in.removeProcessor(sfplaybutton);}
        else{
            in.addProcessor(sfplaybutton);
        in.addProcessor(sfshop);
        in.addProcessor(sfangar);}

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
