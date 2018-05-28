package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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
import com.mygdx.game.model.Player;
import com.mygdx.game.utils.TextManager;

public class Angar implements Screen {
    Stage stage;
    OrthographicCamera camera;
    Game game;
    SpriteBatch batch;
    Skin skin;
    TextureAtlas textureAtlas;
    Image CurrentShipImg,ShipInfo,CurrentGunImg,GunInfo;
    TextManager textManager;
    Image[] guns;
    MainMenu menu;
    BitmapFont font;
    Player player;
    DrawOneShip dos1,dos2,dos3,dos4,dos5,dos6,dos7,dos8,dos9;

  //  DrawStageForGuns DSFG;
    Button.ButtonStyle BaStyle;
    StageForButton Back;
    int counter=1;
    int counterg=1;
    StationAnim station1,station2;







    public Angar(Game game, SpriteBatch batch, MainMenu menu, Player player){
        this.game=game;
        this.batch = batch;
       this.menu=menu;
       this.player=player;


    }

    @Override
    public void show() {
        textManager=new TextManager(0,0);
        font = textManager.fontInitialize(Color.WHITE, (float) 0.7);


        camera=new OrthographicCamera();
        batch=new SpriteBatch();
        skin=new Skin();
        textureAtlas=new TextureAtlas(Gdx.files.internal("TexturePack.atlas"));
        skin.addRegions(textureAtlas);
        stage=new Stage();
        station1=new StationAnim(textureAtlas,batch,(float) (Gdx.graphics.getWidth()/3-550/1.63),Gdx.graphics.getHeight()/2-550/2);
        station2=new StationAnim(textureAtlas,batch,(float)(station1.img.getX()+station1.img.getWidth()*0.88467),station1.img.getY());
        String shname=player.getCurrentShip().getName();
        if (shname.equals("1"))shname="Pulsate";
        CurrentShipImg=new Image(skin.getDrawable(shname));
        CurrentShipImg.setSize(160,160);
        CurrentShipImg.setPosition((float) (Gdx.graphics.getWidth()-CurrentShipImg.getWidth()*1.06), (float) (Gdx.graphics.getHeight()-CurrentShipImg.getWidth()*1.06));
        ShipInfo=new Image(skin.getDrawable("GrayFrame"));
        ShipInfo.setSize(190,270);
        ShipInfo.setPosition(Gdx.graphics.getWidth()-ShipInfo.getWidth(),Gdx.graphics.getHeight()-ShipInfo.getHeight()-CurrentShipImg.getHeight()-10);

        BaStyle = new Button.ButtonStyle();
        BaStyle.up = skin.getDrawable("Back-up");
        BaStyle.down = skin.getDrawable("Back-down");
        Back = new StageForButton(BaStyle, Gdx.graphics.getHeight()/Gdx.graphics.getHeight(), Gdx.graphics.getWidth()/Gdx.graphics.getWidth(), (int) (Gdx.graphics.getWidth()/8.8), (int) (Gdx.graphics.getHeight()/4.96));
        Back.btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(menu);

            }
        });
        Back.addActor(ShipInfo);
        Back.addActor(CurrentShipImg);



        //crating ships image
        if (player.resources.shipList.size()>0){
            String name=player.resources.shipList.get(0).getName();
            if (name.equals("1"))name="Pulsate";
            dos1=new DrawOneShip(name,0,station1.img.getX()+station1.img.getWidth()/2,station1.img.getY());

        }
        if (player.resources.shipList.size()>1){
            String name=player.resources.shipList.get(1).getName();
            if (name.equals("1"))name="Pulsate";
            dos2=new DrawOneShip(name,72,station1.img.getX()+station1.img.getWidth()/2,station1.img.getY());

        }
        if (player.resources.shipList.size()>2){
            String name=player.resources.shipList.get(2).getName();
            if (name.equals("1"))name="Pulsate";
            dos3=new DrawOneShip(name,144,station1.img.getX()+station1.img.getWidth()/2,station1.img.getY());

        }
        if (player.resources.shipList.size()>3){
            String name=player.resources.shipList.get(3).getName();
            if (name.equals("1"))name="Pulsate";
            dos3=new DrawOneShip(name,216,station1.img.getX()+station1.img.getWidth()/2,station1.img.getY());

        }
        if (player.resources.shipList.size()>4){
            String name=player.resources.shipList.get(4).getName();
            if (name.equals("1"))name="Pulsate";
            dos4=new DrawOneShip(name,-72,station1.img.getX()+station1.img.getWidth()/2,station1.img.getY());

        }




        InputMultiplexer in=new InputMultiplexer();
        in.addProcessor(stage);
        in.addProcessor(Back);

        Gdx.input.setInputProcessor(in);


    }

    @Override
    public void render(float delta) {
        camera.setToOrtho(false, (float) (Gdx.graphics.getWidth()/1.6), (float) (Gdx.graphics.getHeight()/1.5));
        LoginView.textrure.draw();
        LoginView.star.draw();

        Back.act(delta);
        Back.draw();
        textManager.displayMessage(batch,font,"HP: \n"+player.getCurrentShip().getMaxHp(),CurrentShipImg.getX(),CurrentShipImg.getY()-25);
        textManager.displayMessage(batch,font,"Speed: \n"+player.getCurrentShip().getMaxSpeed(),CurrentShipImg.getX(),CurrentShipImg.getY()-26*2*2);
        textManager.displayMessage(batch,font,"Velocity: \n"+player.getCurrentShip().getVelocity(),CurrentShipImg.getX(),CurrentShipImg.getY()-29*3*2);
        station1.draw();
        station2.draw();
        stage.act(delta);
        stage.draw();
        DrawDrawStage(dos1);

      //  batch.begin();
        //batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        menu.music.pause();


    }

    @Override
    public void resume()
    {
        menu.music.play();

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

    class DrawOneShip extends Stage{
       public Image ship;

        public DrawOneShip(String name,int Rotation,float x,float y){
            if (name.equals("1")) name="Pulsate";
            ship=new Image(skin.getDrawable(name));
            ship.setSize((float) (Gdx.graphics.getHeight()/7.6), (float) (Gdx.graphics.getHeight()/7.6));
            ship.setOrigin(ship.getWidth()/2, ship.getHeight()/2);
            ship.rotateBy(Rotation);
            ship.setPosition(x-ship.getWidth()/2,y);
            addActor(ship);


        }

    }
    public void DrawDrawStage(DrawOneShip dos){
        dos.act(Gdx.graphics.getDeltaTime());
        dos.draw();

    }



}
