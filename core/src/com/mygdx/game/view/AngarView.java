package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
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
    StageForButton leftbtn,rightbtn,backbtn;
    Ship firstShip;
    Image shipIMG;
    TextureRegion background;


    public AngarView(Game game, SpriteBatch batch, MainMenu menu, Player player){
        this.game=game;
        this.batch = batch;
        this.menu=menu;
        this.player=player;

        camera=new OrthographicCamera();
        textManager=new TextManager(0,0);
        skin=new Skin();
        textureAtlas=new TextureAtlas(Gdx.files.internal("TexturePack.atlas"));
        skin.addRegions(textureAtlas);

    }

    @Override
    public void show() {
        firstShip=player.getCurrentShip();

        background=skin.getRegion("AngarGround");
        Button.ButtonStyle right=new Button.ButtonStyle();
        right.up=skin.getDrawable("Right-up");
        right.down=skin.getDrawable("Right-down");
        rightbtn=new StageForButton(right,Gdx.graphics.getWidth()-150,Gdx.graphics.getHeight()/2-150/2,150,150);
        Button.ButtonStyle left=new Button.ButtonStyle();
        left.up=skin.getDrawable("Left-up");
        left.down=skin.getDrawable("Left-down");
        leftbtn=new StageForButton(left,0,rightbtn.y,rightbtn.width,rightbtn.height);
        shipIMG=new Image(player.getCurrentShip().getImg());
       shipIMG.setSize(shipIMG.getWidth()*2,shipIMG.getHeight()*2);
       System.out.println("WIDTH: "+shipIMG.getWidth());
       shipIMG.setPosition(leftbtn.x+leftbtn.width+shipIMG.getWidth(),Gdx.graphics.getHeight()/2-shipIMG.getHeight()/2);

       //Типо получаю координаты
       String name=
            player.getCurrentShip().getFixingPoints()[0].getWeapon().getName();
       if (name.equals("BlueLaser")) name="BlueImpulseLaser";
       float x= player.getCurrentShip().getFixingPoints()[0].getOffsetX();
       for (int i=0;i<5;i++){
           Image img=new Image(skin.getDrawable("Tube"));
           img.setPosition(rightbtn.x-rightbtn.width-img.getWidth(), (float) (Gdx.graphics.getHeight()/7.2*(i+1)));
           stage.addActor(img);
       }

       Image gun=new Image(skin.getDrawable(name));
       gun.setPosition(shipIMG.getX()-shipIMG.getWidth()/6,shipIMG.getY()+shipIMG.getHeight()/2);
       gun.setSize(gun.getWidth()/2,gun.getHeight()/2);
       Image gun2=new Image(skin.getDrawable(name));
        gun2.setSize(gun2.getWidth()/2,gun2.getHeight()/2);
       gun2.setPosition((float) (shipIMG.getX()+shipIMG.getWidth()/1.5),gun.getY());


        stage.addActor(shipIMG);
        stage.addActor(gun);
        stage.addActor(gun2);


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
