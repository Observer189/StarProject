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
    BitmapFont font,font1;
    Player player;
    DrawOneShip dos1,dos2,dos3,dos4,dos5,dos6,dos7,dos8,dos9;

  //  DrawStageForGuns DSFG;
    Button.ButtonStyle BaStyle;
    StageForButton Back;
    int counter=1;
    int counterg=1;
    StationAnim station1;
    Image hp,speed,velocity,dmg,attackSpeed;
    float xX= (float) 1.2;
    float yY= (float) 1.68;







    public Angar(Game game, SpriteBatch batch, MainMenu menu, Player player){
        this.game=game;
        this.batch = batch;
        this.menu=menu;
        this.player=player;


    }

    @Override
    public void show() {
        textManager=new TextManager(0,0);
        font = textManager.fontInitialize(Color.WHITE, (float) 0.65);
        font1=textManager.fontInitialize(Color.WHITE, (float) 0.5);


        camera=new OrthographicCamera();
        batch=new SpriteBatch();
        skin=new Skin();
        textureAtlas=new TextureAtlas(Gdx.files.internal("TexturePack.atlas"));
        skin.addRegions(textureAtlas);
        stage=new Stage();




      //  ShipInfo=new Image(skin.getDrawable("GrayFrame"));
      //  ShipInfo.setSize(190,270);
      //  ShipInfo.setPosition(Gdx.graphics.getWidth()-ShipInfo.getWidth(),Gdx.graphics.getHeight()-ShipInfo.getHeight()-160-10);




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
        station1=new StationAnim(textureAtlas,batch,(float) (Gdx.graphics.getWidth()/2-Gdx.graphics.getWidth()/1.86/2),Gdx.graphics.getHeight()/2-(float) (Gdx.graphics.getWidth()/1.86/2),player);
        ClickListener cl=new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AngarView angarView=new AngarView(game,batch,menu,player);
                game.setScreen(angarView);


            }
        };
        station1.img.addListener(cl);
        station1.img2.addListener(cl);
        stage.addActor(station1.img);
        stage.addActor(station1.img2);
        float widthS= (float) (Gdx.graphics.getWidth()/5.12);
        float heightS= (float) (Gdx.graphics.getHeight()/7.2);
       /* hp=new Image((skin.getDrawable("Tube")));
        hp.setSize(widthS,heightS);
        hp.setPosition(station1.img.getX()+station1.img.getWidth()/2-hp.getWidth()/2, (float) (station1.img.getY()+station1.img.getHeight()*0.95));

        speed=new Image((skin.getDrawable("Tube")));
        speed.setSize(widthS,heightS);
        speed.setPosition(station1.img.getX(), (float) (station1.img.getY()+station1.img.getHeight()*0.78));

        velocity=new Image((skin.getDrawable("Tube")));
        velocity.setSize(widthS,heightS);
        velocity.setPosition(station1.img.getX()+speed.getWidth(), speed.getY());

        attackSpeed=new Image(skin.getDrawable("Tube"));
        attackSpeed.setSize(widthS,heightS);
        attackSpeed.setPosition(speed.getX(),station1.img.getY());

        dmg=new Image(skin.getDrawable("Tube"));
        dmg.setSize(widthS,heightS);
        dmg.setPosition(velocity.getX(),attackSpeed.getY());

     //   Back.addActor(ShipInfo);
        stage.addActor(hp);
        stage.addActor(speed);
        stage.addActor(velocity);
        stage.addActor(attackSpeed);
        stage.addActor(dmg);

*/

        //crating ships image
        if (player.resources.shipList.size()>0){
            String name=player.resources.shipList.get(0).getName();
            if (name.equals("1"))name="Pulsate";
         //   dos1=new DrawOneShip(name,0,station1.img.getX()+station1.img.getWidth()/2,station1.img.getY());

        }
        if (player.resources.shipList.size()>1){
            String name=player.resources.shipList.get(1).getName();
            if (name.equals("1"))name="Pulsate";
            dos2=new DrawOneShip(name,station1.img.getX()+station1.img.getWidth()/2,station1.img.getY());

        }
        if (player.resources.shipList.size()>2){
            String name=player.resources.shipList.get(2).getName();
            if (name.equals("1"))name="Pulsate";
            dos3=new DrawOneShip(name,station1.img.getX()+station1.img.getWidth()/2,station1.img.getY());

        }
        if (player.resources.shipList.size()>3){
            String name=player.resources.shipList.get(3).getName();
            if (name.equals("1"))name="Pulsate";
            dos3=new DrawOneShip(name,station1.img.getX()+station1.img.getWidth()/2,station1.img.getY());

        }
        if (player.resources.shipList.size()>4){
            String name=player.resources.shipList.get(4).getName();
            if (name.equals("1"))name="Pulsate";
            dos4=new DrawOneShip(name,station1.img.getX()+station1.img.getWidth()/2,station1.img.getY());

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
        Back.draw(); station1.draw();
        stage.act(delta);
        stage.draw();
       // textManager.displayMessage(batch,font,"HP: "+player.getCurrentShip().getMaxHp(), (hp.getX()*xX), (float) (hp.getY()+hp.getHeight()/yY));
       // textManager.displayMessage(batch,font,"Speed: "+player.getCurrentShip().getMaxSpeed(), (float) (speed.getX()*xX*2.2),speed.getY()+speed.getHeight()/yY);
       // textManager.displayMessage(batch,font,"Velocity: "+player.getCurrentShip().getVelocity(), (float) (velocity.getX()*xX/1.12),velocity.getY()+speed.getHeight()/yY);
      ///  textManager.displayMessage(batch,font1,"Attack speed:"+player.getCurrentShip().getFixingPoints()[1].getWeapon().getAttackSpeed(), (float) (attackSpeed.getX()*xX*2.2),attackSpeed.getY()+attackSpeed.getHeight()/yY);
      //  textManager.displayMessage(batch,font,"Dmg: "+player.getCurrentShip().getFixingPoints()[0].getWeapon().getAmmo().getDamage(),dmg.getX()*xX,dmg.getY()+dmg.getHeight()/yY);


//        DrawDrawStage(dos1);

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

        public DrawOneShip(String name,float x,float y){
            if (name.equals("1")) name="Pulsate";
            ship=new Image(skin.getDrawable(name));
            ship.setSize((float) (Gdx.graphics.getHeight()/7.6), (float) (Gdx.graphics.getHeight()/7.6));

            ship.setPosition(x-ship.getWidth()/2,y);
            addActor(ship);


        }

    }
    public void DrawDrawStage(DrawOneShip dos){
        dos.act(Gdx.graphics.getDeltaTime());
        dos.draw();

    }



}
