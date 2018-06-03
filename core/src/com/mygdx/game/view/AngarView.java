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
     HugeDraw aLotOfGuns;
     Stage stageTubes=new Stage();
     int counterOfCells=1;


    public AngarView(Game game, SpriteBatch batch, MainMenu menu, Player player){
        this.game=game;
        this.batch = batch;
        this.menu=menu;
        this.player=player;



    }

    @Override
    public void show() {
        camera=new OrthographicCamera();
        textManager=new TextManager(0,0);
        font = textManager.fontInitialize(Color.WHITE, (float) 0.65);
        skin=new Skin();
        textureAtlas=new TextureAtlas(Gdx.files.internal("TexturePack.atlas"));
        skin.addRegions(textureAtlas);
        stage=new Stage();

        background=skin.getRegion("AngarGround");
        bachfround2=skin.getRegion("AngarGround2");

        final InputMultiplexer in =new InputMultiplexer();

        shipIMG=new Image(player.getCurrentShip().getImg());
        shipIMG.setSize(shipIMG.getWidth()*2,shipIMG.getHeight()*2);

        shipIMG.setPosition(0+150+shipIMG.getWidth()/2,Gdx.graphics.getHeight()/2-shipIMG.getHeight()/2);
       // player.getCurrentShip().setPosition(shipIMG.getX(),shipIMG.getY());
        String realname=player.getCurrentShip().getFixingPoints()[0].getWeapon().getRealName();
        gunIMG=new Image(skin.getDrawable(realname));
        gunIMG.setPosition(shipIMG.getX()+shipIMG.getWidth()+20,shipIMG.getY());
        gunIMG.setSize(gunIMG.getWidth()/2,gunIMG.getHeight()/2);
        FrameIMG=new Image(skin.getDrawable("GrayFrame"));
        System.out.println("WIDTH:"+gunIMG.getHeight()*2+" Height: "+gunIMG.getHeight()*2);
        FrameIMG.setSize(129,129);
        FrameIMG.setPosition(gunIMG.getX(),gunIMG.getY());
        gunIMG.setPosition(FrameIMG.getX()+FrameIMG.getWidth()/2-gunIMG.getWidth()/2,FrameIMG.getY()+10);
        gunIMG.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            infoShow=false;
            aLotOfGuns=new HugeDraw(player.resources.weaponList);
                in.addProcessor(aLotOfGuns);


            }
        });
       /* НЕ РАБОТАЕТ ОТОБРАЖЕНИЕ ТОЧЕК КРЕПЛЕНИЯ
       Image gun[]=new Image[2];
        gun[0]=new Image(skin.getDrawable(player.getCurrentShip().getFixingPoints()[0].getWeapon().getRealName()));

        gun[0].setPosition(player.getCurrentShip().getFixingPoints()[0].getCenterX(),player.getCurrentShip().getFixingPoints()[0].getCenterY());
        gun[0].setSize(gun[0].getWidth()/2,gun[0].getHeight()/2);

        gun[1]=new Image(skin.getDrawable(player.getCurrentShip().getFixingPoints()[1].getWeapon().getRealName()));

        gun[1].setPosition(player.getCurrentShip().getFixingPoints()[1].getCenterX(),player.getCurrentShip().getFixingPoints()[1].getCenterY());
        gun[1].setSize(gun[1].getWidth()/2,gun[1].getHeight()/2);
        System.out.println("FirstY: "+player.getCurrentShip().getFixingPoints()[0].getY()+" SecondY: "+player.getCurrentShip().getFixingPoints()[1].getY());
System.out.println("FirstY: "+player.getCurrentShip().getFixingPoints()[0].getOffsetY()+" SecondY: "+player.getCurrentShip().getFixingPoints()[1].getOffsetY());
*/
        Button.ButtonStyle right=new Button.ButtonStyle();

        right.up=skin.getDrawable("Right-up");
        right.down=skin.getDrawable("Right-down");
        rightbtn=new StageForButton(right,Gdx.graphics.getWidth()-150,Gdx.graphics.getHeight()/2-150/2,150,150);
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
                    if (name.equals("pulsate")) name="1";
                      shipIMG.setDrawable(skin.getDrawable(name));
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
                    if (name.equals("pulsate")) name="1";
                    shipIMG.setDrawable(skin.getDrawable(name));
                }
                else System.out.println("HAHA");
            }
        });
        Button.ButtonStyle down=new Button.ButtonStyle();
        down.up=skin.getDrawable("Prev-up");
        down.down=skin.getDrawable("Prev-down");
        downbt=new StageForButton(down,850,1,150,150);
        downbt.btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (counterOfCells == player.resources.weaponList.size()-1)
                  downbt.btn.setDisabled(true);
                else {
                    aLotOfGuns.MoveThis(2);
                    counterOfCells++;
                }



            }
        });

        Button.ButtonStyle up=new Button.ButtonStyle();
       up.up=skin.getDrawable("Go-up");
        up.down=skin.getDrawable("Go-down");
        upbt=new StageForButton(up,850,Gdx.graphics.getHeight()-150,150,150);
        upbt.btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
               // /if (counterOfCells)

                if (counterOfCells == 1)
                    upbt.btn.setDisabled(true);
                else {
                    aLotOfGuns.MoveThis(-489310);
                    counterOfCells--;
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


       //Типо получаю координаты
      /* String name=
            player.getCurrentShip().getFixingPoints()[0].getWeapon().getName();
       if (name.equals("BlueLaser")) name="BlueImpulseLaser";
       float x= player.getCurrentShip().getFixingPoints()[0].getOffsetX();

       Image gun=new Image(skin.getDrawable(name));
       player.getCurrentShip().setX(shipIMG.getX());
       player.getCurrentShip().setY(shipIMG.getY());
       gun.setPosition((float) player.getCurrentShip().getFixingPoints()[0].x, (float) player.getCurrentShip().getFixingPoints()[0].y);
       gun.setSize(gun.getWidth()/2,gun.getHeight()/2);
       Image gun2=new Image(skin.getDrawable(name));
        gun2.setSize(gun2.getWidth()/2,gun2.getHeight()/2);
       gun2.setPosition((float) (shipIMG.getX()+shipIMG.getWidth()/1.5),gun.getY());



        stage.addActor(gun);
        stage.addActor(gun2);*/

       // stage.addActor(rightbtn.btn);

        for (int i=0;i<5;i++){
                img[i]=new Image(skin.getDrawable("Tube"));
                img[i].setSize(400,img[i].getHeight());
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
            textManager.displayMessage(batch, font, "HP: " + player.getCurrentShip().getMaxHp(), (float) (img[0].getX() * 1.053), (float) (img[0].getY() + img[0].getHeight() * 4.3));
            textManager.displayMessage(batch, font, "Speed: " + player.getCurrentShip().getMaxSpeed(), (float) (img[3].getX() * 1.053), img[3].getY() - img[3].getHeight() / (2 * yY));
            textManager.displayMessage(batch, font, "Velocity: " + player.getCurrentShip().getVelocity(), (float) (img[2].getX() * 1.053), img[2].getY() - img[2].getHeight() / (2 * yY));
            textManager.displayMessage(batch, font, "DMG: " + player.getCurrentShip().getFixingPoints()[0].getWeapon().getAmmo().getDamage(), (float) (img[1].getX() * 1.053), img[1].getY() - img[1].getHeight() / (2 * yY));
            textManager.displayMessage(batch, font, "Attack Speed: " + player.getCurrentShip().getFixingPoints()[0].getWeapon().getAttackSpeed(), (float) (img[4].getX() * 1.053), img[4].getY() - img[4].getHeight() / (2 * yY));

        } else {
            aLotOfGuns.act(delta);
            aLotOfGuns.draw();
            batch.begin();
            batch.draw(bachfround2,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
            batch.end();

            downbt.act(delta);
            downbt.draw();
            upbt.act(delta);
            upbt.draw();
        }

      rightbtn.act(delta);
      rightbtn.draw();
      leftbtn.act(delta);
      leftbtn.draw();

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
        float y=  (float) (img[0].getY() + img[0].getHeight() * 4.3);
        float deltaY=215;
        ArrayList<Weapon> list;
        float width=100;
        float height=100;
        Image onegun,onegun1,onegun2,onegun3;
        public HugeDraw(final ArrayList<Weapon> list){
            this.x=img[0].getX();
            this.list=list;

            if (list.size()>0){
             onegun=new Image(skin.getDrawable(list.get(0).getRealName()));
                onegun.setSize(list.get(0).getRecomendedw(),list.get(0).getRecomendedh());
                onegun.setPosition(x,y-deltaY);
                onegun.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        for (int i=0;i<player.getCurrentShip().getFixingPoints().length;i++){
                            Weapon wp=(player.resources.weaponList.get(1));
                            player.getCurrentShip().getFixingPoints()[i].setWeapon(wp);}
                        String name=player.getCurrentShip().getFixingPoints()[0].getWeapon().getRealName();
                        gunIMG.setDrawable(skin.getDrawable(name));
                        gunIMG.setSize(player.getCurrentShip().getFixingPoints()[0].getWeapon().getRecomendedw()/2,player.getCurrentShip().getFixingPoints()[0].getWeapon().getRecomendedh()/2);
                        gunIMG.setPosition(FrameIMG.getX()+FrameIMG.getWidth()/2-gunIMG.getWidth()/2,FrameIMG.getY()+10);
                    //    gunIMG.setSize(truewidth,trueheight);
                  //  x=Gdx.graphics.getWidth()*2;
                    infoShow=true;

                }

                });
                addActor(onegun);

            }

            if (list.size()>1){
                onegun1=new Image(skin.getDrawable(list.get(1).getRealName()));
                onegun1.setSize(list.get(1).getRecomendedw(),list.get(1).getRecomendedh());
                onegun1.setPosition(x,onegun.getY()-deltaY);
                onegun1.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        for (int i=0;i<player.getCurrentShip().getFixingPoints().length;i++){
                            Weapon wp=(player.resources.weaponList.get(1));
                            player.getCurrentShip().getFixingPoints()[i].setWeapon(wp);}
                            String name=player.getCurrentShip().getFixingPoints()[0].getWeapon().getRealName();
                        gunIMG.setDrawable(skin.getDrawable(name));
                        gunIMG.setSize(player.getCurrentShip().getFixingPoints()[0].getWeapon().getRecomendedw()/2,player.getCurrentShip().getFixingPoints()[0].getWeapon().getRecomendedh()/2);
                        gunIMG.setPosition(FrameIMG.getX()+FrameIMG.getWidth()/2-gunIMG.getWidth()/2,FrameIMG.getY()+10);
                        //    gunIMG.setSize(truewidth,trueheight);
                        //  x=Gdx.graphics.getWidth()*2;
                        infoShow=true;

                    }

                });
                addActor(onegun1);

            }
            if (list.size()>2){
                onegun2=new Image(skin.getDrawable(list.get(2).getRealName()));
                onegun2.setSize(list.get(2).getRecomendedw(),list.get(2).getRecomendedh());
                onegun2.setPosition(x,onegun1.getY()-deltaY);
                onegun2.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        for (int i=0;i<player.getCurrentShip().getFixingPoints().length;i++){
                            Weapon wp=(player.resources.weaponList.get(1));
                            player.getCurrentShip().getFixingPoints()[i].setWeapon(wp);}
                        String name=player.getCurrentShip().getFixingPoints()[0].getWeapon().getRealName();
                        gunIMG.setDrawable(skin.getDrawable(name));
                        gunIMG.setSize(player.getCurrentShip().getFixingPoints()[0].getWeapon().getRecomendedw()/2,player.getCurrentShip().getFixingPoints()[0].getWeapon().getRecomendedh()/2);
                        gunIMG.setPosition(FrameIMG.getX()+FrameIMG.getWidth()/2-gunIMG.getWidth()/2,FrameIMG.getY()+10);
                        //    gunIMG.setSize(truewidth,trueheight);
                        //  x=Gdx.graphics.getWidth()*2;
                        infoShow=true;

                    }

                });
                addActor(onegun2);

            }
        }
        public void MoveThis(int dir){
            if (dir>0){
                if (list.size()>0){onegun.setY(onegun.getY()+deltaY);}
                if (list.size()>1){onegun1.setY(onegun1.getY()+deltaY);}
                if (list.size()>2){onegun2.setY(onegun2.getY()+deltaY);}
                if (list.size()>3){onegun3.setY(onegun3.getY()+deltaY);}
            }
            else {
                if (list.size()>0){onegun.setY(onegun.getY()-deltaY);}
                if (list.size()>1){onegun1.setY(onegun1.getY()-deltaY);}
                if (list.size()>2){onegun2.setY(onegun2.getY()-deltaY);}
                if (list.size()>3){onegun3.setY(onegun3.getY()-deltaY);}
            }
        }

    }
}
