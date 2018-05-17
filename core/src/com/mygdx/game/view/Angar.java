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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.Ship;
import com.mygdx.game.model.Ships.Pulsate;
import com.mygdx.game.model.Weapon;
import com.mygdx.game.model.Weapons.GreenImpulseLaser;
import com.mygdx.game.utils.TextManager;

import java.util.ArrayList;

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
    DrawStageForShips DSFS;
    DrawStageForGuns DSFG;
    Button.ButtonStyle BaStyle;
    StageForButton Back;



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
        String shipname=player.getCurrentShip().getName();
        if (shipname.equals("Pulsate")  )shipname="1";
        Shimg=new Image(skin.getDrawable(shipname));
        Shimg.setSize((float) (Gdx.graphics.getHeight()/3.6), (float) (Gdx.graphics.getHeight()/3.6));
        Shimg.setPosition((float) (Gdx.graphics.getWidth()/Gdx.graphics.getWidth()+Gdx.graphics.getWidth()/85.3), (float) (Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/3.44));

        Frameimg=new Image(skin.getDrawable("Frame"));
        Frameimg.setSize((float) (Gdx.graphics.getWidth()/2.7), (float) (Gdx.graphics.getHeight()/3.3));
        Frameimg.setPosition((float) (Shimg.getX()-Gdx.graphics.getWidth()/85.3),Shimg.getY()-Gdx.graphics.getHeight()/72);
        DSFS=new DrawStageForShips(Gdx.graphics.getWidth()+100, 400,player.resources.shipList);
        player.resources.weaponList.add(new GreenImpulseLaser(textureAtlas,400,400));
     //   DSFG=new DrawStageForGuns(Gdx.graphics.getWidth()+100, 400,player.resources.getWeaponList());

        Frameimg.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            DSFS.ViewMove((int) (Frameimg.getX()+Frameimg.getWidth()+Frameimg.getWidth()/10), (int) Shimg.getY());
            System.out.println("I CLICKED");


            }
        });
        for (int i=0;i<player.resources.shipList.size();i++){

        }

        //frames for guns

        guns=new Image[player.getCurrentShip().getFixingPointsDigit()];
        float x=Frameimg.getX();
        float y=Frameimg.getY()-Gdx.graphics.getHeight()/5;
        for (int i=0;i<guns.length;i++){

            Image img= new Image(skin.getDrawable("Frame"));
            img.setSize(120,130);
            img.setPosition(x,y);

            guns[i]=img;
            guns[i].addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {






                }
            });
            Image gun=new Image(skin.getDrawable("GreenLaser"));
            gun.setSize(34,128);
            gun.setPosition(guns[i].getX()+guns[i].getWidth()/2-gun.getWidth()/2,guns[i].getY());

            x+=img.getWidth()+img.getWidth()/2;
            stage.addActor(guns[i]);
            stage.addActor(gun);

        }

        BaStyle = new Button.ButtonStyle();
        BaStyle.up = skin.getDrawable("Back-up");
        BaStyle.down = skin.getDrawable("Back-down");
        Back = new StageForButton(BaStyle, Gdx.graphics.getHeight()/Gdx.graphics.getHeight(), Gdx.graphics.getWidth()/Gdx.graphics.getWidth(), (int) (Gdx.graphics.getWidth()/8.8), (int) (Gdx.graphics.getHeight()/4.96));
        System.out.println("Clicker");
        Back.btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(menu);

            }
        });

        // stage.addActor(guns[1]);

        stage.addActor(Shimg);
        stage.addActor(Frameimg);
        InputMultiplexer in=new InputMultiplexer();
        in.addProcessor(stage);
        in.addProcessor(Back);
        Gdx.input.setInputProcessor(in);

    }

    @Override
    public void render(float delta) {
        camera.setToOrtho(false, 800, 480);
        Gdx.gl.glClearColor(0, 64, 247, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        textManager.displayMessage(batch,font," "+player.resources.shipList,200,200);
        for (int i=0;i<DSFS.list.size();i++) {
            textManager.displayMessage(batch, font, player.getCurrentShip().getName(), DSFS.sh.get(i).getX()+DSFS.sh.get(i).getWidth(),DSFS.sh.get(i).getY()+DSFS.frame.get(i).getHeight()/2);
        }
        stage.act(delta);
        stage.draw();
        DSFS.act(delta);
        DSFS.draw();
        Back.act(delta);
        Back.draw();
        batch.begin();
        batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        menu.music.stop();

    }

    @Override
    public void resume() {
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
    class DrawStageForShips extends  Stage{
        int x;
        int y;
        ArrayList<Ship> list;
        ArrayList<Image> sh=new ArrayList<Image>();
        ArrayList<Image> frame=new ArrayList<Image>();
        public DrawStageForShips(int x,int y,ArrayList<Ship> list){
            this.x=x;
            this.y=y;
            this.list=list;
            int deltay;
            deltay = Gdx.graphics.getHeight() / 9;
            for (int i=0;i<list.size();i++){
                String drawname= list.get(i).getName();
                if (drawname=="Pulsate") drawname="1";
                sh.add(new Image(skin.getDrawable(drawname)));
                sh.get(i).setSize((float) (Gdx.graphics.getHeight()/3.6), (float) (Gdx.graphics.getHeight()/3.6));
                sh.get(i).setPosition((float) (x), (float) (y-deltay*(i+1)));

                frame.add( new Image(skin.getDrawable("Frame")));
                frame.get(i).setSize((float) (Gdx.graphics.getWidth()/2.7), (float) (Gdx.graphics.getHeight()/3.3));
                frame.get(i).setPosition((float) (sh.get(i).getX()-Gdx.graphics.getWidth()/85.3),sh.get(i).getY()-Gdx.graphics.getHeight()/72);
                addActor(sh.get(i));
                addActor(frame.get(i));
            }


        }
        public  void ViewMove(int x,int y){
            for (int i=0;i<list.size();i++){
                sh.get(i).setPosition(x,y);
                frame.get(i).setPosition((float) (sh.get(i).getX()-Gdx.graphics.getWidth()/85.3),sh.get(i).getY()-Gdx.graphics.getHeight()/72);

            }
        }

    }
    class DrawStageForGuns extends Stage{
        int x;
        int y;
        ArrayList<Weapon> list;
        ArrayList<Image> gu=new ArrayList<Image>();
        ArrayList<Image> frame=new ArrayList<Image>();
        public DrawStageForGuns(int x,int y,ArrayList<Weapon> list){
            this.x=x;
            this.y=y;
            this.list=list;
            int deltay;
            deltay = Gdx.graphics.getHeight() / 9;
            for (int i=0;i<list.size();i++){
                String drawname= list.get(i).getName();

                gu.add(new Image(skin.getDrawable(drawname)));
                gu.get(i).setSize((float) (Gdx.graphics.getHeight()/3.6), (float) (Gdx.graphics.getHeight()/3.6));
                gu.get(i).setPosition((float) (x), (float) (y-deltay*(i+1)));

                frame.add( new Image(skin.getDrawable("Frame")));
                frame.get(i).setSize((float) (Gdx.graphics.getWidth()/2.7), (float) (Gdx.graphics.getHeight()/3.3));
                frame.get(i).setPosition((float) (gu.get(i).getX()-Gdx.graphics.getWidth()/85.3),gu.get(i).getY()-Gdx.graphics.getHeight()/72);
                addActor(gu.get(i));
                addActor(frame.get(i));
            }


        }
        public  void ViewMove(int x,int y){
            for (int i=0;i<list.size();i++){
                gu.get(i).setPosition(x,y);
                frame.get(i).setPosition((float) (gu.get(i).getX()-Gdx.graphics.getWidth()/85.3),gu.get(i).getY()-Gdx.graphics.getHeight()/72);

            }
        }


    }

}
