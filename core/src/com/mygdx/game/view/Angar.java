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
import com.mygdx.game.model.Player;
import com.mygdx.game.model.Ship;
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
    int counter=1;
    int counterg=1;
    Image gun[];




    public Angar(Game game, SpriteBatch batch, MainMenu menu, Player player){
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
        DSFG=new DrawStageForGuns(Gdx.graphics.getWidth()+100, 400,menu.player.resources.weaponList,menu.player.getCurrentShip().getFixingPointsDigit());


        Frameimg.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (counter%2==0)
                    DSFS.ViewMove(Gdx.graphics.getWidth()*2, (int) Shimg.getY());
                    else {
                    DSFS.ViewMove((int) (Frameimg.getX() + Frameimg.getWidth() + Frameimg.getWidth() / 10), (int) Shimg.getY());
                    DSFG.ViewMove(Gdx.graphics.getWidth()*2, (int) Shimg.getY());
                }
            System.out.println("I CLICKED");
            counter++;


            }
        });
        for (int i=0;i<player.resources.shipList.size();i++){

        }

        //frames for guns

        guns=new Image[player.getCurrentShip().getFixingPointsDigit()];
        gun=new Image[player.getCurrentShip().getFixingPointsDigit()];
        float x=Frameimg.getX();
        float y=Frameimg.getY()-Gdx.graphics.getHeight()/5;
        for (int i=0;i<guns.length;i++){

            Image img= new Image(skin.getDrawable("Frame"));
            img.setSize(120,130);
            img.setPosition(x,y);

           guns[i]=img;

            gun[i]=new Image(skin.getDrawable(menu.player.getCurrentShip().getFixingPoints()[i].getWeapon().getName()));
            if (menu.player.getCurrentShip().getFixingPoints()[i].getWeapon().getName().equals("GreenLaser"))
            gun[i].setSize(34,128);
            else gun[i].setSize(78,126);
            gun[i].setPosition(guns[i].getX()+guns[i].getWidth()/2-gun[i].getWidth()/2,guns[i].getY());

            x+=img.getWidth()+img.getWidth()/2;

            stage.addActor(gun[i]);

        }

        if (guns.length>0)stage.addActor(guns[0]);
        if (guns.length>1)stage.addActor(guns[1]);
        if (menu.player.resources.weaponList.size()>0){

            guns[0].addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (counterg%2==0)
                        DSFG.ViewMove(Gdx.graphics.getWidth()*2, (int) Shimg.getY());
                   else {
                        DSFG.ViewMove((int) (Frameimg.getX() + Frameimg.getWidth() + Frameimg.getWidth() / 10), (int) Shimg.getY());
                        DSFS.ViewMove(Gdx.graphics.getWidth()*2, (int) Shimg.getY());
                       // DSFG.setNewGun(0);
                   }
                    System.out.println("I CLICKED");
                    System.out.println(menu.player.resources.weaponList);
                    counterg++;

                }
            });
        }
        if (menu.player.resources.weaponList.size()>1){
            stage.addActor(guns[1]);
            guns[1].addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (counterg%2==0)
                        DSFG.ViewMove(Gdx.graphics.getWidth()*2, (int) Shimg.getY());
                    else{
                        DSFG.ViewMove((int) (Frameimg.getX()+Frameimg.getWidth()+Frameimg.getWidth()/10), (int) Shimg.getY());
                        DSFS.ViewMove(Gdx.graphics.getWidth()*2, (int) Shimg.getY());
                    }
                    System.out.println("I CLICKED");
                    counterg++;

                }
            });
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
        in.addProcessor(DSFS);
        in.addProcessor(DSFG);
        Gdx.input.setInputProcessor(in);

    }

    @Override
    public void render(float delta) {
        camera.setToOrtho(false, (float) (Gdx.graphics.getWidth()/1.6), (float) (Gdx.graphics.getHeight()/1.5));
        Gdx.gl.glClearColor(0, 64, 247, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        textManager.displayMessage(batch,font,player.getCurrentShip().getName(), (float) (Shimg.getX()+Shimg.getWidth()*1.2),Shimg.getY()+Shimg.getHeight()/2);
       // textManager.displayMessage(batch,font," "+player.resources.shipList,200,200);
        for (int i=0;i<DSFS.list.size();i++) {
            textManager.displayMessage(batch, font, player.resources.shipList.get(i).getName(), DSFS.sh.get(i).getX()+DSFS.sh.get(i).getWidth(),DSFS.sh.get(i).getY()+DSFS.frame.get(i).getHeight()/2);

        }
        for (int i=0;i<DSFG.list.size();i++) {
            textManager.displayMessage(batch, font, player.resources.weaponList.get(i).getName(), DSFG.gu.get(i).getX()+DSFG.gu.get(i).getWidth(),DSFG.gu.get(i).getY()+DSFG.frame.get(i).getHeight()/2);

        }
        stage.act(delta);
        stage.draw();
        DSFS.act(delta);
        DSFS.draw();
        Back.act(delta);
        Back.draw();
        DSFG.act(delta);
        DSFG.draw();
        //System.out.println("SHIP: "+player.getCurrentShip());
        batch.begin();
        batch.end();

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
    class DrawStageForShips extends  Stage{
        int x;
        int y;
        ArrayList<Ship> list;
        ArrayList<Image> sh=new ArrayList<Image>();
        ArrayList<Image> frame=new ArrayList<Image>();
        public DrawStageForShips(int x, int y, final ArrayList<Ship> list){
            this.x=x;
            this.y=y;
            this.list=list;
            int deltay;
            deltay = Gdx.graphics.getHeight() / 9;
           // System.out.println(list.size());
            for (int i=0;i<list.size();i++){

                String drawname= list.get(i).getName();

                if (drawname=="Pulsate") drawname="1";
                sh.add(new Image(skin.getDrawable(drawname)));
                sh.get(i).setSize((float) (Gdx.graphics.getHeight()/3.6), (float) (Gdx.graphics.getHeight()/3.6));
                sh.get(i).setPosition((float) (x), (float) (y-deltay*(i+1)));

                frame.add( new Image(skin.getDrawable("Frame")));
                frame.get(i).setSize((float) (Gdx.graphics.getWidth()/2.7), (float) (Gdx.graphics.getHeight()/3.3));
                frame.get(i).setPosition((float) (sh.get(i).getX()-Gdx.graphics.getWidth()/85.3),(float) (y-deltay*(i+1)));//sh.get(i).getY()-Gdx.graphics.getHeight()/72


                addActor(sh.get(i));
                addActor(frame.get(i));
            }
            if (list.size()>0)
                frame.get(0).addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {

                        player.setCurrentShip(list.get(0));
                        String ImgName=player.getCurrentShip().getName();
                        if (ImgName.equals("Pulsate")) ImgName="1";
                        Shimg.setDrawable(skin.getDrawable(ImgName));
                    }
                });
            if (list.size()>1)
                frame.get(1).addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {

                        player.setCurrentShip(list.get(1));
                        String ImgName=player.getCurrentShip().getName();
                        if (ImgName.equals("Pulsate")) ImgName="1";
                        Shimg.setDrawable(skin.getDrawable(ImgName));

                    }
                });
            if (list.size()>2)
                frame.get(2).addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {

                        player.setCurrentShip(list.get(2));
                        String ImgName=player.getCurrentShip().getName();
                        if (ImgName.equals("Pulsate")) ImgName="1";
                        Shimg.setDrawable(skin.getDrawable(ImgName));
                    }
                });


        }
        public  void ViewMove(int x,int y){

            for (int i=0;i<list.size();i++){
                sh.get(i).setPosition(x, (float) (Shimg.getY()-sh.get(i).getHeight()*i*1.13));
                frame.get(i).setPosition((float) (sh.get(i).getX()-Gdx.graphics.getWidth()/85.3),sh.get(i).getY()-Gdx.graphics.getHeight()/72);

            }
        }

    }
    class DrawStageForGuns extends Stage{
        int x;
        int y;
        int FrameDigit;
        ArrayList<Weapon> list;
        ArrayList<Image> gu=new ArrayList<Image>();
        ArrayList<Image> frame=new ArrayList<Image>();
        public DrawStageForGuns(int x, int y, final ArrayList<Weapon> list,int FramesDidgit){
            this.x=x;
            this.y=y;
            this.list=list;
            int deltay;
            this.FrameDigit=FramesDidgit;
            deltay = Gdx.graphics.getHeight() / 9;

            System.out.println("SIZE "+list.size());

            for (int i=0;i<list.size();i++){
                String drawname= list.get(i).getName();

                gu.add(new Image(skin.getDrawable(drawname)));
             //   gu.get(i).setSize((float) (Gdx.graphics.getHeight()/3.6), (float) (Gdx.graphics.getHeight()/3.6));
                gu.get(i).setSize(list.get(i).getWidth(),list.get(i).getHeight());
                gu.get(i).setPosition((float) (x), (float) (y-deltay*(i+1)));
                System.out.println("This Y: "+gu.get(i).getY());
                frame.add( new Image(skin.getDrawable("Frame")));
                frame.get(i).setSize((float) (Gdx.graphics.getWidth()/2.7), (float) (Gdx.graphics.getHeight()/3.3));
                frame.get(i).setPosition((float) (gu.get(i).getX()-Gdx.graphics.getWidth()/85.3),(float) (y-deltay*(i+1)));
                addActor(gu.get(i));
                addActor(frame.get(i));
            }
            if (FramesDidgit>0) {
            if (list.size() > 0)
                frame.get(0).addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {

                        player.getCurrentShip().getFixingPoints()[0].setWeapon(list.get(0));
                        String ImgName = player.getCurrentShip().getFixingPoints()[0].getWeapon().getName();
                        if (ImgName.equals("GreenLaser")) gun[0].setSize(34,128);
                        else gun[0].setSize(78,126);
                        gun[0].setDrawable(skin.getDrawable(ImgName));
                        
                    }
                });
            if (list.size() > 1)
                frame.get(1).addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {

                        player.getCurrentShip().getFixingPoints()[0].setWeapon(list.get(1));
                        String ImgName = player.getCurrentShip().getFixingPoints()[0].getWeapon().getName();
                        if (ImgName.equals("GreenLaser")) gun[0].setSize(34,128);
                        else gun[0].setSize(78,126);
                        gun[0].setDrawable(skin.getDrawable(ImgName));

                    }
                });}



            if (FramesDidgit>1) {
                if (list.size() > 0)
                    frame.get(0).addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {

                            player.getCurrentShip().getFixingPoints()[1].setWeapon(list.get(0));
                            String ImgName = player.getCurrentShip().getFixingPoints()[1].getWeapon().getName();
                            if (ImgName.equals("GreenLaser")) gun[1].setSize(34,128);
                            else gun[1].setSize(78,126);
                            gun[1].setDrawable(skin.getDrawable(ImgName));
                        }
                    });
                if (list.size() > 1)
                    frame.get(1).addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {

                            player.getCurrentShip().getFixingPoints()[1].setWeapon(list.get(1));
                            String ImgName = player.getCurrentShip().getFixingPoints()[1].getWeapon().getName();
                            if (ImgName.equals("GreenLaser")) gun[1].setSize(34,128);
                            else gun[1].setSize(78,126);

                            gun[1].setDrawable(skin.getDrawable(ImgName));

                        }
                    });

            }

        }
        public void setNewGun(final int pos){

                if (list.size() > 0)
                    frame.get(0).addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {

                            player.getCurrentShip().getFixingPoints()[0].setWeapon(list.get(0));
                            String ImgName = player.getCurrentShip().getFixingPoints()[0].getWeapon().getName();
                            gun[pos].setDrawable(skin.getDrawable(ImgName));
                        }
                    });
                if (list.size() > 1)
                    frame.get(1).addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {

                            player.getCurrentShip().getFixingPoints()[0].setWeapon(list.get(1));
                            String ImgName = player.getCurrentShip().getFixingPoints()[0].getWeapon().getName();

                            gun[pos].setDrawable(skin.getDrawable(ImgName));

                        }
                    });

            }




        public  void ViewMove(int x,int y){
            for (int i=0;i<list.size();i++){
                gu.get(i).setPosition(x, (float) (Shimg.getY()-gu.get(i).getHeight()*i*1.13));
                frame.get(i).setPosition((float) (gu.get(i).getX()-Gdx.graphics.getWidth()/85.3),gu.get(i).getY()-Gdx.graphics.getHeight()/72);

            }
        }


    }

}
