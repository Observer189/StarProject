package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
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

public class AngarView implements Screen {
    Game game;
    SpriteBatch batch;
    Skin skin;
    MainMenu menu;
    Player player;
    TextureAtlas textureAtlas;
    TextManager textManager;
    BitmapFont font,font1;
    OrthographicCamera camera;
    Stage stage=new Stage();
    StageForButton leftbtn,rightbtn,Back;
    Ship firstShip;
    Image shipIMG;
    TextureRegion background;
    int counter=0;


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
        skin=new Skin();
        textureAtlas=new TextureAtlas(Gdx.files.internal("TexturePack.atlas"));
        skin.addRegions(textureAtlas);
        firstShip=player.getCurrentShip();

        background=skin.getRegion("AngarGround");


        shipIMG=new Image(player.getCurrentShip().getImg());
        shipIMG.setSize(shipIMG.getWidth()*2,shipIMG.getHeight()*2);
        shipIMG.setPosition(0+150+shipIMG.getWidth(),Gdx.graphics.getHeight()/2-shipIMG.getHeight()/2);
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
                 Image img=new Image(skin.getDrawable("Tube"));
                img.setPosition(rightbtn.x-rightbtn.width-img.getWidth(), (float) (Gdx.graphics.getHeight()/7.2*(i+1)));
                  stage.addActor(img);
               }
        stage.addActor(shipIMG);
        InputMultiplexer in =new InputMultiplexer();
        in.addProcessor(rightbtn);
        in.addProcessor(leftbtn);
        in.addProcessor(stage);
        in.addProcessor(Back);
        Gdx.input.setInputProcessor(in);
    }

    @Override
    public void render(float delta) {
      camera.setToOrtho(false, (float) (Gdx.graphics.getWidth()/1.6), (float) (Gdx.graphics.getHeight()/1.5));
      batch.begin();

      batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
      batch.end();
      rightbtn.act(delta);
      rightbtn.draw();
      leftbtn.act(delta);
      leftbtn.draw();
      stage.act(delta);
      stage.draw();
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
