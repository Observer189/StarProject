package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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
import com.mygdx.game.model.Weapon;
import com.mygdx.game.utils.TextManager;

import java.util.ArrayList;

public class AngarView implements Screen {
    Game game;
    SpriteBatch batch;
    Skin skin;
    MainMenu menu;
    Player player;
    TextureAtlas textureAtlas;
    TextManager textManager;
    BitmapFont font;
    OrthographicCamera camera;
    Stage stage;
    StageForButton leftbtn,rightbtn,Back,downbt,upbt;

    Image shipIMG,gunIMG,FrameIMG;
    TextureRegion background,bachfround2;
    int counter=0;
    Image img[]=new Image[5];
    float xX= (float) 2.3;
    float yY= (float) 1.73;
     Boolean infoShow=true;
     HugeDraw Gun1,Gun2,Gun3,Gun4;
     Stage stageTubes=new Stage();
     int counterOfCells=0;
     Boolean MoveUp=false;
     Boolean MoveDown=false;
    InputMultiplexer in;


    public AngarView(Game game, SpriteBatch batch, MainMenu menu, Player player){
        this.game=game;
        this.batch = batch;
        this.menu=menu;
        this.player=player;



    }

    @Override
    public void show() {
        Gdx.input.setCatchBackKey(true);
        camera=new OrthographicCamera();
        textManager=new TextManager(0,0);
        font = textManager.fontInitialize(Color.WHITE, (float) 0.65);
        skin=new Skin();
        textureAtlas=new TextureAtlas(Gdx.files.internal("TexturePack.atlas"));
        skin.addRegions(textureAtlas);
        stage=new Stage();

        background=skin.getRegion("AngarGround");
        bachfround2=skin.getRegion("AngarGround2");

         in =new InputMultiplexer();

        shipIMG=new Image(player.getCurrentShip().getImg());
        shipIMG.setSize(player.getCurrentShip().getRealw(),player.getCurrentShip().getRealh());

        shipIMG.setPosition((float) (Gdx.graphics.getWidth()/4.26666-shipIMG.getWidth()/2),Gdx.graphics.getHeight()/2-shipIMG.getHeight()/2);
       // player.getCurrentShip().setPosition(shipIMG.getX(),shipIMG.getY());
        String realname=player.getCurrentShip().getFixingPoints()[0].getWeapon().getRealName();
        gunIMG=new Image(skin.getDrawable(realname));
        gunIMG.setPosition(shipIMG.getX()+shipIMG.getWidth()+Gdx.graphics.getHeight()/36,shipIMG.getY());
        gunIMG.setSize(gunIMG.getWidth()/2,gunIMG.getHeight()/2);
        FrameIMG=new Image(skin.getDrawable("GrayFrame"));
        System.out.println("WIDTH:"+gunIMG.getHeight()*2+" Height: "+gunIMG.getHeight()*2);
        FrameIMG.setSize((float) (Gdx.graphics.getWidth()/9.92248062), (float) (Gdx.graphics.getHeight()/5.58139535));
        FrameIMG.setPosition((float) (Gdx.graphics.getWidth()/2.56), (float) (Gdx.graphics.getHeight()/2.4));
        gunIMG.setPosition(FrameIMG.getX()+FrameIMG.getWidth()/2-gunIMG.getWidth()/2,FrameIMG.getY()+Gdx.graphics.getHeight()/72);

        System.out.println(shipIMG.getWidth()+" !!!! "+shipIMG.getHeight());
        Gun1=new HugeDraw(player.resources.weaponList,0,Gdx.graphics.getHeight()/2,Gdx.graphics.getWidth()*2);
        if (player.resources.weaponList.size()>1) Gun2=new HugeDraw(player.resources.weaponList,1, (int) (Gdx.graphics.getHeight()/4.96551724),Gdx.graphics.getWidth()*2);
        if (player.resources.weaponList.size()>2) Gun3=new HugeDraw(player.resources.weaponList,2, (int) (Gdx.graphics.getHeight()/-10.28571429),Gdx.graphics.getWidth()*2);
        if (player.resources.weaponList.size()>3) Gun4=new HugeDraw(player.resources.weaponList,3, (int) (Gdx.graphics.getHeight()/-2.52631579),Gdx.graphics.getWidth()*2);
        gunIMG.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            infoShow=false;
                if (player.resources.weaponList.size()>0){
                    Gun1.x=img[0].getX();
                    Gun1.onegun.setX(Gun1.x);
                    Gun1.onegun.addListener(new ClickListener(){
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            for (int i=0;i<player.getCurrentShip().getFixingPoints().length;i++){

                                player.getCurrentShip().getFixingPoints()[i].setWeapon(player.resources.weaponList.get(0).weaponByName());}
                            String name=player.getCurrentShip().getFixingPoints()[0].getWeapon().getRealName();
                            gunIMG.setDrawable(skin.getDrawable(name));
                            gunIMG.setSize(player.getCurrentShip().getFixingPoints()[0].getWeapon().getRecomendedw()/2,player.getCurrentShip().getFixingPoints()[0].getWeapon().getRecomendedh()/2);
                            gunIMG.setPosition(FrameIMG.getX()+FrameIMG.getWidth()/2-gunIMG.getWidth()/2,FrameIMG.getY()+10);
                            //    gunIMG.setSize(truewidth,trueheight);
                            //  x=Gdx.graphics.getWidth()*2;
                            infoShow=true;
                          //  counterOfCells=0;
                           Gun1.x=Gdx.graphics.getWidth()*2;
                           if (player.resources.weaponList.size()>1)Gun2.x=Gun1.x;
                            if (player.resources.weaponList.size()>2)Gun3.x=Gun1.x;
                            if (player.resources.weaponList.size()>3){Gun4.x=Gun1.x; Gun4.onegun.setX(Gun1.x);}

                        }

                    });


                    in.addProcessor(Gun1);
                }
                if (player.resources.weaponList.size()>1){
                    Gun2.x=Gun1.x;
                    Gun2.onegun.setX(Gun1.x);
                    Gun2.onegun.addListener(new ClickListener(){
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            for (int i=0;i<player.getCurrentShip().getFixingPoints().length;i++){
                                player.getCurrentShip().getFixingPoints()[i].setWeapon(player.resources.weaponList.get(1).weaponByName());}
                            String name=player.getCurrentShip().getFixingPoints()[0].getWeapon().getRealName();
                            gunIMG.setDrawable(skin.getDrawable(name));
                            gunIMG.setSize(player.getCurrentShip().getFixingPoints()[0].getWeapon().getRecomendedw()/2,player.getCurrentShip().getFixingPoints()[0].getWeapon().getRecomendedh()/2);
                            gunIMG.setPosition(FrameIMG.getX()+FrameIMG.getWidth()/2-gunIMG.getWidth()/2,FrameIMG.getY()+10);
                            //    gunIMG.setSize(truewidth,trueheight);
                            //  x=Gdx.graphics.getWidth()*2;
                            infoShow=true;
                          //  counterOfCells=0;
                            Gun1.x=Gdx.graphics.getWidth()*2;
                            Gun1.onegun.setX(Gun1.x);
                            if (player.resources.weaponList.size()>1){Gun2.x=Gun1.x; Gun2.onegun.setX(Gun1.x);}
                            if (player.resources.weaponList.size()>2){Gun3.x=Gun1.x; Gun3.onegun.setX(Gun1.x);}
                            if (player.resources.weaponList.size()>3){Gun4.x=Gun1.x; Gun4.onegun.setX(Gun1.x);}

                        }

                    });


                    in.addProcessor(Gun2);
                }
                if (player.resources.weaponList.size()>2){
                    Gun3.x=Gun1.x;
                    Gun3.onegun.setX(Gun1.x);
                    Gun3.onegun.addListener(new ClickListener(){
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            for (int i=0;i<player.getCurrentShip().getFixingPoints().length;i++){
                                player.getCurrentShip().getFixingPoints()[i].setWeapon(player.resources.weaponList.get(2).weaponByName());}
                            String name=player.getCurrentShip().getFixingPoints()[0].getWeapon().getRealName();
                            gunIMG.setDrawable(skin.getDrawable(name));
                            gunIMG.setSize(player.getCurrentShip().getFixingPoints()[0].getWeapon().getRecomendedw()/2,player.getCurrentShip().getFixingPoints()[0].getWeapon().getRecomendedh()/2);
                            gunIMG.setPosition(FrameIMG.getX()+FrameIMG.getWidth()/2-gunIMG.getWidth()/2,FrameIMG.getY()+10);
                            //    gunIMG.setSize(truewidth,trueheight);
                            //  x=Gdx.graphics.getWidth()*2;
                            infoShow=true;
                            //counterOfCells=0;
                            Gun1.x=Gdx.graphics.getWidth()*2;
                            Gun1.onegun.setX(Gun1.x);
                            if (player.resources.weaponList.size()>1){Gun2.x=Gun1.x; Gun2.onegun.setX(Gun1.x);}
                            if (player.resources.weaponList.size()>2){Gun3.x=Gun1.x; Gun3.onegun.setX(Gun1.x);}
                            if (player.resources.weaponList.size()>3){Gun4.x=Gun1.x; Gun4.onegun.setX(Gun1.x);}


                        }

                    });
                    System.out.println(Gun1.y+" "+Gun2.y+" "+Gun3.y);

                    in.addProcessor(Gun3);
                }
                if (player.resources.weaponList.size()>3){
                    Gun4.x=Gun1.x;
                    Gun4.onegun.setX(Gun1.x);
                    Gun4.onegun.addListener(new ClickListener(){
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            for (int i=0;i<player.getCurrentShip().getFixingPoints().length;i++){
                                player.getCurrentShip().getFixingPoints()[i].setWeapon(player.resources.weaponList.get(3).weaponByName());}
                            String name=player.getCurrentShip().getFixingPoints()[0].getWeapon().getRealName();
                            gunIMG.setDrawable(skin.getDrawable(name));
                            gunIMG.setSize(player.getCurrentShip().getFixingPoints()[0].getWeapon().getRecomendedw()/2,player.getCurrentShip().getFixingPoints()[0].getWeapon().getRecomendedh()/2);
                            gunIMG.setPosition(FrameIMG.getX()+FrameIMG.getWidth()/2-gunIMG.getWidth()/2,FrameIMG.getY()+10);
                            //    gunIMG.setSize(truewidth,trueheight);
                            //  x=Gdx.graphics.getWidth()*2;
                            infoShow=true;
                            //counterOfCells=0;
                            Gun1.x=Gdx.graphics.getWidth()*2;
                            Gun1.onegun.setX(Gun1.x);
                            if (player.resources.weaponList.size()>1){Gun2.x=Gun1.x; Gun2.onegun.setX(Gun1.x);}
                            if (player.resources.weaponList.size()>2){Gun3.x=Gun1.x; Gun3.onegun.setX(Gun1.x);}
                            if (player.resources.weaponList.size()>3){Gun4.x=Gun1.x; Gun4.onegun.setX(Gun1.x);}


                        }

                    });
                    System.out.println(Gun1.y+" "+Gun2.y+" "+Gun3.y);

                    in.addProcessor(Gun4);
                }




            }
        });

        Button.ButtonStyle right=new Button.ButtonStyle();

        right.up=skin.getDrawable("Right-up");
        right.down=skin.getDrawable("Right-down");
        rightbtn=new StageForButton(right, (int) (Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()/8.533333)),Gdx.graphics.getHeight()/2-150/2,(int) (Gdx.graphics.getWidth()/8.533333), (int) (Gdx.graphics.getHeight()/4.8));
        rightbtn.btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (counter<player.resources.shipList.size()-1){
                    counter++;
                    System.out.println("Counter:"+ counter);
                    player.setCurrentShip(player.resources.shipList.get(counter));
                    System.out.println("Ship:"+ player.getCurrentShip());
                  //  shipIMG.setDrawable(player.getCurrentShip().getImg());
                    String name=player.getCurrentShip().getName();
                    if (name.equals("Pulsate")) name="1";
                      shipIMG.setDrawable(skin.getDrawable(name));
                    String realname=player.getCurrentShip().getFixingPoints()[0].getWeapon().getRealName();
                    gunIMG.setDrawable(skin.getDrawable(realname));
                    gunIMG.setSize(player.getCurrentShip().getFixingPoints()[0].getWeapon().getRecomendedw()/2,player.getCurrentShip().getFixingPoints()[0].getWeapon().getRecomendedh()/2);
                    gunIMG.setPosition(FrameIMG.getX()+FrameIMG.getWidth()/2-gunIMG.getWidth()/2,FrameIMG.getY()+Gdx.graphics.getHeight()/72);
                    shipIMG.setSize(player.getCurrentShip().getRealw(),player.getCurrentShip().getRealh());
                    shipIMG.setPosition((float) (Gdx.graphics.getWidth()/4.26666-shipIMG.getWidth()/2),Gdx.graphics.getHeight()/2-shipIMG.getHeight()/2);
                }
                else System.out.println("HAHA");
            }
        });
        Button.ButtonStyle left=new Button.ButtonStyle();
        left.up=skin.getDrawable("Left-up");
        left.down=skin.getDrawable("Left-down");
        leftbtn=new StageForButton(left,0,rightbtn.y,rightbtn.width,rightbtn.height);
        leftbtn.btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (counter>0){
                    counter--;
                    System.out.println("Counter1:"+ counter);
                    player.setCurrentShip(player.resources.shipList.get(counter));
                    System.out.println("Ship1:"+ player.getCurrentShip());
                   // shipIMG.setDrawable(player.getCurrentShip().getImg());
                    String name=player.getCurrentShip().getName();
                    if (name.equals("Pulsate")) name="1";
                    shipIMG.setDrawable(skin.getDrawable(name));
                    String realname=player.getCurrentShip().getFixingPoints()[0].getWeapon().getRealName();
                    gunIMG.setDrawable(skin.getDrawable(realname));
                    gunIMG.setSize(player.getCurrentShip().getFixingPoints()[0].getWeapon().getRecomendedw()/2,player.getCurrentShip().getFixingPoints()[0].getWeapon().getRecomendedh()/2);
                    gunIMG.setPosition(FrameIMG.getX()+FrameIMG.getWidth()/2-gunIMG.getWidth()/2,FrameIMG.getY()+Gdx.graphics.getHeight()/72);
                    shipIMG.setSize(player.getCurrentShip().getRealw(),player.getCurrentShip().getRealh());
                    shipIMG.setPosition((float) (Gdx.graphics.getWidth()/4.26666-shipIMG.getWidth()/2),Gdx.graphics.getHeight()/2-shipIMG.getHeight()/2);
                }
                else System.out.println("HAHA");
            }
        });
        Button.ButtonStyle down=new Button.ButtonStyle();
        down.up=skin.getDrawable("Prev-up");
        down.down=skin.getDrawable("Prev-down");
        downbt=new StageForButton(down,(int) (Gdx.graphics.getWidth()/1.50588235),1,(int) (Gdx.graphics.getWidth()/8.533333), (int) (Gdx.graphics.getHeight()/4.8));
        downbt.btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (counterOfCells!= player.resources.weaponList.size()-1) {
                    MoveDown=true;
                    counterOfCells++;
                }
                else {//  downbt.btn.setDisabled(true);

                }



            }
        });

        Button.ButtonStyle up=new Button.ButtonStyle();
       up.up=skin.getDrawable("Go-up");
        up.down=skin.getDrawable("Go-down");
        upbt=new StageForButton(up, downbt.x,Gdx.graphics.getHeight()-150, (int) (Gdx.graphics.getWidth()/8.533333), (int) (Gdx.graphics.getHeight()/4.8));
        upbt.btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
               // /if (counterOfCells)

                if (counterOfCells !=0){
                    MoveUp=true;
                counterOfCells--;  }
                else {
                   // upbt.btn.setDisabled(true);
                }


            }
        });

        Button.ButtonStyle BaStyle = new Button.ButtonStyle();
        BaStyle.up = skin.getDrawable("Back-up");
        BaStyle.down = skin.getDrawable("Back-down");
        Back = new StageForButton(BaStyle, 0, (int) (Gdx.graphics.getHeight()/Gdx.graphics.getHeight()), (int) (Gdx.graphics.getWidth() / 8.8), (int) (Gdx.graphics.getHeight() / 4.96));
        System.out.println("Clicker");
        Back.btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(menu);
                System.out.println( player.resources.shipList);

            }
        });



        for (int i=0;i<5;i++){
                img[i]=new Image(skin.getDrawable("Tube"));
                img[i].setSize((float) (Gdx.graphics.getWidth()/3.2), (float) (Gdx.graphics.getHeight()/6.72897196));
                img[i].setPosition(rightbtn.x-rightbtn.width/5-img[i].getWidth(), (float) (Gdx.graphics.getHeight()/7.2*(i+1)));

                  stageTubes.addActor(img[i]);
               }

        stage.addActor(shipIMG);
        stage.addActor(FrameIMG);
        stage.addActor(gunIMG);


        in.addProcessor(rightbtn);
        in.addProcessor(leftbtn);
        in.addProcessor(stage);
        in.addProcessor(Back);
        in.addProcessor(upbt);
        in.addProcessor(downbt);




        Gdx.input.setInputProcessor(in);
    }

    @Override
    public void render(float delta) {
      camera.setToOrtho(false, (float) (Gdx.graphics.getWidth()/1.6), (float) (Gdx.graphics.getHeight()/1.5));
      batch.begin();

      batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
      batch.end();
      stage.act(delta);
        stage.draw();
        if (infoShow) {
            stageTubes.act(delta);
            stageTubes.draw();
            textManager.displayMessage(batch, font, "HP: " + player.getCurrentShip().getMaxHp(), (float) (img[4].getX() * 1.053), (float) (img[4].getY()+Gdx.graphics.getWidth()/18));
            textManager.displayMessage(batch, font, "Speed: " + player.getCurrentShip().getMaxSpeed(), (float) (img[3].getX() * 1.053), (float) (img[3].getY()+Gdx.graphics.getWidth()/18));
            textManager.displayMessage(batch, font, "Velocity: " + player.getCurrentShip().getVelocity(), (float) (img[2].getX() * 1.053), (float) (img[2].getY() +Gdx.graphics.getWidth()/18));
            textManager.displayMessage(batch, font, "DMG: " + player.getCurrentShip().getFixingPoints()[0].getWeapon().getAmmo().getDamage(), (float) (img[1].getX() * 1.053), (float) (img[1].getY()+Gdx.graphics.getWidth()/18));
            textManager.displayMessage(batch, font, "Attack Speed: " + player.getCurrentShip().getFixingPoints()[0].getWeapon().getAttackSpeed(), (float) (img[0].getX() * 1.053), (float) (img[0].getY()+Gdx.graphics.getWidth()/18));

        } else {
            batch.begin();
            batch.draw(bachfround2,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
            batch.end();
           // aLotOfGuns.act(delta);
           // aLotOfGuns.draw();
            Gun1.act(delta);
            Gun1.draw(); //System.out.println("Need "+Gun1.y);
            if (player.resources.weaponList.size()>1){
            Gun2.act(delta);
            Gun2.draw();}
            if (player.resources.weaponList.size()>2){
            Gun3.act(delta);
            Gun3.draw();}
            if (player.resources.weaponList.size()>3){
                Gun4.act(delta);
                Gun4.draw();}
            batch.begin();
            batch.draw(bachfround2,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
            batch.end();

            downbt.act(delta);
            downbt.draw();
            upbt.act(delta);
            upbt.draw();
        }

        if (MoveUp){
         MoveUpS();

        }
        if (MoveDown) {

          MoveDownS();



        }
        System.out.println(shipIMG.getWidth()+" "+shipIMG.getHeight());
      rightbtn.act(delta);
      rightbtn.draw();
      leftbtn.act(delta);
      leftbtn.draw();

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            game.setScreen(menu);}
      Back.act(delta);
      Back.draw();


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
        stage.dispose();
        stageTubes.dispose();
        batch.dispose();
        game.dispose();


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
    class HugeDraw extends Stage{
        float truewidth=shipIMG.getWidth();
        float trueheight=shipIMG.getHeight();
        float x;
        float y;
        float deltaY= (float) (Gdx.graphics.getHeight()/3.34883721);
        ArrayList<Weapon> list;
        float width= (float) (Gdx.graphics.getWidth()/12.8);
        float height= (float) (Gdx.graphics.getHeight()/7.2);
        Button.ButtonStyle s;
        Button onegun;
        public HugeDraw(final ArrayList<Weapon> list,int index,int y,int x){
            this.x=x;
            this.list=list;
            this.y=y;
        //    y=  (float) (img[0].getY() + img[0].getHeight() * 4.3);

                s=new Button.ButtonStyle();
                s.up=skin.getDrawable(list.get(index).getRealName());
                s.down=skin.getDrawable(list.get(index).getRealName());
             onegun=new Button(s);
                onegun.setSize(list.get(index).getRecomendedw(),list.get(index).getRecomendedh());
                onegun.setPosition(x,y);


                addActor(onegun);



        }


    }
    public void MoveDownS(){
        //change
        Gun1.y+=Gun1.deltaY;
        Gun1.onegun.setY(Gun1.y);

        if (player.resources.weaponList.size()>1){

            Gun2.y+=Gun2.deltaY;
            Gun2.onegun.setY(Gun2.y);
        }
        if (player.resources.weaponList.size()>2){

            Gun3.y+=Gun3.deltaY;
            Gun3.onegun.setY(Gun3.y);

        }
        if (player.resources.weaponList.size()>3){

            Gun4.y+=Gun4.deltaY;
            Gun4.onegun.setY(Gun4.y);

        }


        //act
    /*    Gun1.act(Gdx.graphics.getDeltaTime());
        Gun1.draw(); //System.out.println("Need "+Gun1.y);
        if (player.resources.weaponList.size()>1){
            Gun2.act(Gdx.graphics.getDeltaTime());
            Gun2.draw();}
        if (player.resources.weaponList.size()>2){
            Gun3.act(Gdx.graphics.getDeltaTime());
            Gun3.draw();}
        if (player.resources.weaponList.size()>3){
            Gun4.act(Gdx.graphics.getDeltaTime());
            Gun4.draw();}*/

        System.out.println("What: "+Gun2.deltaY+" "+Gun2.y);


        MoveDown=false;



    }
    public void MoveUpS(){
        //change
        Gun1.y-=Gun1.deltaY;
        Gun1.onegun.setY(Gun1.y);

        if (player.resources.weaponList.size()>1){

            Gun2.y-=Gun2.deltaY;
            Gun2.onegun.setY(Gun2.y);

        }
        if (player.resources.weaponList.size()>2){
            Gun3.y-=Gun3.deltaY;
            Gun3.onegun.setY(Gun3.y);


        }
        if (player.resources.weaponList.size()>3){
            Gun4.y-=Gun4.deltaY;
            Gun4.onegun.setY(Gun4.y);


        }


        //act
     /*   Gun1.act(Gdx.graphics.getDeltaTime());
        Gun1.draw(); //System.out.println("Need "+Gun1.y);
        if (player.resources.weaponList.size()>1){
            Gun2.act(Gdx.graphics.getDeltaTime());
            Gun2.draw();}
        if (player.resources.weaponList.size()>2){
            Gun3.act(Gdx.graphics.getDeltaTime());
            Gun3.draw();}
        if (player.resources.weaponList.size()>3){
            Gun4.act(Gdx.graphics.getDeltaTime());
            Gun4.draw();}*/
        MoveUp=false;

    }

}
