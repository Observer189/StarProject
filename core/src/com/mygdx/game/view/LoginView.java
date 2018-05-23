package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.model.Map;
import com.mygdx.game.model.Player;
import com.mygdx.game.utils.StarGen;
import com.mygdx.game.utils.TextManager;
import com.mygdx.game.utils.Toast;

public class LoginView implements Screen {
    public static Music music;
    Game game;
    SpriteBatch batch;
    Player player;
    BitmapFont font,font1;
    TextManager textManager;
    TextField.TextFieldStyle txtStyle;
    TextField textFieldLog,textFieldPass,textFieldConfirm;
    Stage stage;
    Boolean KBisActive=false;
    static Map textrure;
    TextButton.TextButtonStyle btnstyle,btnstyle1;
    TextButton button,button1;
    Toast toast;
    Boolean MakeToast=false;
    OrthographicCamera camera = new OrthographicCamera();
    Boolean ShowConfirm=false;

    Array<TextureAtlas.AtlasRegion> array;
    StarGen star;


    TextureAtlas textureAtlas;
    public LoginView(SpriteBatch batch, Game game, Player player){
        this.batch=batch;
        this.game=game;
        this.player=player;


    }
    @Override
    public void show() {
        music = Gdx.audio.newMusic(Gdx.files.internal("MenuMusic.mp3"));
        music.setLooping(true);
        music.play();
        batch=new SpriteBatch();
        textureAtlas=new TextureAtlas(Gdx.files.internal("TexturePack.atlas"));
        textrure= Map.generateMap(batch,textureAtlas);
        textrure.setHeight(Gdx.graphics.getHeight()); textrure.setWidth(Gdx.graphics.getWidth());

        stage=new Stage();
        Skin skin = new Skin();
        skin.addRegions(textureAtlas);
        textManager = new TextManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        array=new Array<TextureAtlas.AtlasRegion>();
       for (int i=0;i<17;i++)
           array.add(textureAtlas.findRegion("Explosion"+(i+1)));



        font=textManager.fontInitialize(Color.BLACK,1f);
        font1=textManager.fontInitialize(Color.WHITE,1f);

        Toast.ToastFactory toastFactory = new Toast.ToastFactory.Builder()
                .font(font1)
                .build();
        toast = toastFactory.create("Can't be empty/more than 3 letter" , Toast.Length.LONG);


        txtStyle=new TextField.TextFieldStyle();
        txtStyle.font=font;
        txtStyle.fontColor=Color.BLACK;
        txtStyle.background=skin.getDrawable("FrameInput");
        txtStyle.cursor=skin.getDrawable("GreenLaserAmmo");

        textFieldLog =new TextField("",txtStyle);
        textFieldLog.setMessageText("Your log-in");
        textFieldLog.setSize(Gdx.graphics.getWidth()/2,100);
        textFieldLog.setPosition(Gdx.graphics.getWidth()/2-textFieldLog.getWidth()/2,540);
        textFieldLog.getStyle().background.setLeftWidth(20);
        stage.addActor(textFieldLog);
        textFieldLog.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                KBisActive=true;
                textFieldLog.getOnscreenKeyboard().show(true);
            }
        });

        textFieldPass =new TextField("",txtStyle);
        textFieldPass.setMessageText("Your password");
        textFieldPass.setSize(Gdx.graphics.getWidth()/2,100);
        textFieldPass.setPosition(Gdx.graphics.getWidth()/2-textFieldLog.getWidth()/2,420);

        textFieldPass.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                KBisActive=true;
                textFieldPass.getOnscreenKeyboard().show(true);

            }
        });
        stage.addActor(textFieldPass);
        textFieldConfirm=new TextField("",txtStyle);
        textFieldConfirm.setMessageText("Confirm your password");
        textFieldConfirm.setSize(textFieldPass.getWidth(),textFieldPass.getHeight());
        textFieldConfirm.setPosition(textFieldPass.getX(),textFieldPass.getY()-textFieldConfirm.getHeight()-20);
        textFieldConfirm.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                KBisActive=true;
                textFieldConfirm.getOnscreenKeyboard().show(true);

            }
        });

        btnstyle=new TextButton.TextButtonStyle();
        btnstyle.font=font1;

        button=new TextButton("Sign in",btnstyle);
        button.setPosition(textFieldLog.getX()-20,200);
        button.setSize(300,100);
        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!textFieldLog.getText().equals(null)&& !textFieldLog.getText().contains(" ")&& !(textFieldLog.getText().length()<3)&&
                !textFieldPass.getText().equals(null)&& !textFieldPass.getText().contains(" ")&& !(textFieldPass.getText().length()<3)
                        ){

                game.setScreen(new MainMenu(batch,game,player));}
                else {MakeToast=true;
                }
            }
        });
        stage.addActor(button);
        btnstyle1=new TextButton.TextButtonStyle();
        btnstyle1.font=font1;
        button1=new TextButton("Sign up",btnstyle1);
        button1.setSize(300,100);
        button1.setPosition(textFieldLog.getX()+textFieldLog.getWidth()-button1.getWidth(),200);
        button1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
               ShowConfirm=true;
            }
        });


        stage.addActor(button1);









        Gdx.input.setInputProcessor(stage);
        star=new StarGen(textureAtlas,batch);


    }

    @Override
    public void render(float delta) {

        camera.setToOrtho(false, 800, 480);
        if (ShowConfirm) {
            stage.addActor(textFieldConfirm);
            ShowConfirm=false;
        }
        if (Gdx.input.isTouched()&&KBisActive)
        {
            textFieldLog.getOnscreenKeyboard().show(false);
            textFieldPass.getOnscreenKeyboard().show(false);
            textFieldConfirm.getOnscreenKeyboard().show(false);
            KBisActive=false;
        }


        textrure.draw();
        star.draw();

        stage.act(delta);
        stage.draw();
        if (MakeToast){

            toast.render(Gdx.graphics.getDeltaTime());
            if (toast.timeToLive<=0) {
                MakeToast = false;
                toast.timeToLive= Toast.Length.LONG.duration;
                toast.opacity=1f;
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        music.pause();



    }

   

    @Override
    public void resume() {
        music.play();

    }


    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
