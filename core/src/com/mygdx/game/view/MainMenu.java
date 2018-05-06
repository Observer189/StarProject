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
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.async.AsyncTask;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.model.Player;
import com.mygdx.game.utils.TextManager;

import java.awt.Font;

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
    Skin skin;
    private TextManager textManager1;


    public MainMenu(SpriteBatch batch, Game game) {
        this.batch = batch;
        this.game = game;
    }


    @Override
    public void show() {
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        PreShop=new PreShop(game,batch,textureAtlas);
        batch = new SpriteBatch();
        CTB=new ConnectToBattle(batch,game,textureAtlas,player);
        player=new Player();

        textManager = new TextManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        skin = new Skin();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("TexturePack.atlas")));

                p_button = new Button.ButtonStyle();
        p_button.up = skin.getDrawable("Start-up");
        p_button.down = skin.getDrawable("Start-down");



        sfplaybutton = new StageForButton(p_button, 300, 150);
        sfplaybutton.btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

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


        System.out.println("Here(Reset)");
        Gdx.gl.glClearColor(0, 64, 247, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        //textManager.displayMessage(batch,"x= "+Gdx.graphics.getWidth()+" y= "+Gdx.graphics.getHeight() ,Color.BLACK,50, (int) (Gdx.graphics.getWidth()/3.5), (int) (Gdx.graphics.getHeight()/1.3+90));


        //batch.draw(img,100,100);

        sfplaybutton.act(delta);
        sfplaybutton.draw();


        sfshop.act(delta);
        sfshop.draw();

        sfangar.act(delta);
        sfangar.draw();

        batch.begin();
        textManager.displayMessage(batch, "Welcome to Star game!", Color.BLACK, 50, (int) (Gdx.graphics.getWidth() / 3.5), (int) (Gdx.graphics.getHeight() / 1.3 + 20));
       // textManager.gen.dispose();
        //textManager.font.dispose();


        batch.end();

    }



    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
    dispose();

    }

    @Override
    public void resume() {
        show();

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
        skin.dispose();
        PreShop.dispose();
        //CTB.dispose();
       textureAtlas.dispose();
        game.dispose();
        textManager.gen.dispose();
        textManager.font.dispose();
        System.out.println("DISPOSED");



    }

    public void setTextureAtlas(TextureAtlas textureAtlas) {
        this.textureAtlas = textureAtlas;
    }

   static  class StageForButton extends Stage {
        Button btn;

        public StageForButton(Button.ButtonStyle btnstyle, int x, int y) {

            btn = new Button(btnstyle);
            btn.setBounds(x, y, 200, 200);




            addActor(btn);
        }
    }




}
