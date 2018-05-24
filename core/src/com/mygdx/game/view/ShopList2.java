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
import com.mygdx.game.model.Weapon;
import com.mygdx.game.model.Weapons.GreenImpulseLaser;
import com.mygdx.game.model.Weapons.Machinegun;
import com.mygdx.game.utils.TextManager;

//This screen is used to show Shop of Guns
public class ShopList2 implements Screen{
    Game game;
    Button.ButtonStyle Gstyle,Sstyle,BaStyle;
    StageForButton Guns,Ships,Back;
    SpriteBatch batch;
    TextureAtlas textureAtlas;
    TextManager textManager;
    Skin skin;
    Screen ShList;
    MainMenu menu;
    Player player;
    CellStage g1,g2;
    Image Money;
    BitmapFont font;
    int counter = 0;
    Boolean NeedToMove = false;
    Boolean NeedToMoveBack = false;
    StageForButton Prev,Go;
    Button.ButtonStyle Gostyle,Prevstyle;
   // Weapon laser,multi;
    GreenImpulseLaser Laser;
    Machinegun multigun;

    GunShow GuShow;
    OrthographicCamera camera = new OrthographicCamera();
    public ShopList2(Game game, SpriteBatch batch, TextureAtlas textureAtlas, MainMenu menu, Player player){
        this.game=game;
        this.batch = batch;
        this.textureAtlas=textureAtlas;
        this.menu=menu;
        this.player=player;


    }
    @Override
    public void show() {
        //ShList=new ShopList(game,batch,textureAtlas);
        textManager=new TextManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        skin=new Skin();
        batch=new SpriteBatch();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("TexturePack.atlas")));
        ShList=new ShopList(game,batch,textureAtlas,menu,player);
        Gstyle = new Button.ButtonStyle();
        Gstyle.up = skin.getDrawable("UnSelectGun");
        Gstyle.down = skin.getDrawable("UnSelectGun");
        Guns=new StageForButton(Gstyle,0,Gdx.graphics.getHeight()-(int) (Gdx.graphics.getHeight()/14.117));
        //change scene when 'guns' clicked
        Guns.btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                //  game.setScreen();

            }
        });
        Sstyle = new Button.ButtonStyle();
        Sstyle.up = skin.getDrawable("UnSelectShip");
        Sstyle.down = skin.getDrawable("UnSelectShip");
        Ships=new StageForButton(Sstyle,Gdx.graphics.getWidth()/2, (int) (Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/14.117));
        //change scene when 'ships' clicked
        Ships.btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //Set Ships-Shop
                game.setScreen(ShList);

            }
        });
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
        Money = new Image(skin.getDrawable("Money"));
        // InformationFrame.setSize((float) (Gdx.graphics.getWidth()/3.5), (float) (Gdx.graphics.getHeight()/3.3));

        //  InformationFrame.setPosition(Gdx.graphics.getWidth()/Gdx.graphics.getWidth(),Gdx.graphics.getHeight()/3);
        Money.setSize((float) (Gdx.graphics.getWidth() / 8.8), (float) (Gdx.graphics.getHeight() / 4.96));
        Money.setPosition(Gdx.graphics.getWidth() / Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 3 + Gdx.graphics.getHeight() / 24);

         Laser=new GreenImpulseLaser(textureAtlas,(int) (Gdx.graphics.getWidth() / 4 * 1.25),(int) (Gdx.graphics.getHeight() - 212-50));
        g1=new CellStage((int) Laser.getX(), (int) Laser.getY(),Laser.getName(),Laser.getCost(), (int) (Gdx.graphics.getWidth()/21.333333), (int) (Gdx.graphics.getHeight()/3.6));
       // laser=new GreenImpulseLaser(textureAtlas,0,0);
        g1.img.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                GuShow = new GunShow(Laser, game, menu, player,(float) (g1.width*1.7),(float) (g1.height*1.7));

                game.setScreen(GuShow);


            }
        });
         multigun=new Machinegun(textureAtlas,g1.x,(int) (g1.y - g1.img.getHeight() - Gdx.graphics.getHeight() / 360));
        g2=new CellStage((int) multigun.getX(),(int)multigun.getY(),multigun.getName(),multigun.getCost(),Gdx.graphics.getWidth()/8,(int) (Gdx.graphics.getHeight()/3.6));
        g2.img.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                GuShow = new GunShow(multigun, game, menu, player, (float) (g2.width*1.7), (float) (g2.height*1.7));

                game.setScreen(GuShow);


            }
        });
        g2.addActor(Money);



        Gostyle = new Button.ButtonStyle();
        Gostyle.up = skin.getDrawable("Go-up");
        Gostyle.down = skin.getDrawable("Go-down");
        Go = new StageForButton(Gostyle, (int) (Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 6.0952381), (int) ((Gdx.graphics.getHeight()) / 1.8), (int) (Gdx.graphics.getHeight() / 3.6), (int) (Gdx.graphics.getHeight() / 3.6));
        Go.btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {


                if (counter == 0)
                    Go.btn.setDisabled(true);
                else {
                    NeedToMove = true;
                    counter--;
                }

            }
        });
        //button for going to the previous stage
         Prevstyle = new Button.ButtonStyle();
        Prevstyle.up = skin.getDrawable("Prev-up");
        Prevstyle.down = skin.getDrawable("Prev-down");
        Prev = new StageForButton(Prevstyle, Go.x, Gdx.graphics.getHeight() / 8, (int) (Gdx.graphics.getHeight() / 3.6), (int) (Gdx.graphics.getHeight() / 3.6));
        Prev.btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (counter == 4)
                    Prev.btn.setDisabled(true);
                else {
                    NeedToMoveBack = true;
                    counter++;
                }

            }
        });

        InputMultiplexer in=new InputMultiplexer();
        in.addProcessor(g1);
        in.addProcessor(g2);
        in.addProcessor(Ships);
        in.addProcessor(Back);
        in.addProcessor(Prev);
        in.addProcessor(Go);
        Gdx.input.setInputProcessor(in);
        font = textManager.fontInitialize(Color.BLACK, 0.8f);
    }

    @Override
    public void render(float delta) {

        camera.setToOrtho(false, (float) (Gdx.graphics.getWidth()/1.6), (float) (Gdx.graphics.getHeight()/1.5));
        LoginView.textrure.draw();
        LoginView.star.draw();
        textManager.displayMessage(batch, font, "" +menu.player.getMoney(), Money.getX() + Money.getWidth(), Money.getY() + Money.getHeight() / 2);
        int x200 = (int) (Gdx.graphics.getWidth() / 6.3);
        int y175 = (int) (Gdx.graphics.getHeight() / 4.11428);
        int y75 = (int) (Gdx.graphics.getHeight() / 9.6);

        Guns.act(delta);
        Guns.draw();
        Ships.act(delta);
        Ships.draw();
        Back.act(delta);
        Back.draw();
        g1.act(delta);
        textManager.displayMessage(batch, font, g1.name, g1.x + x200, g1.y + y175);
        textManager.displayMessage(batch, font, "Price: " +g1.price, g1.x + x200, g1.y + y75);
        g1.draw();

        g2.act(delta);
        textManager.displayMessage(batch, font, g2.name, g2.x + x200, g2.y + y175);
        textManager.displayMessage(batch, font, "Price: " + g2.price, g2.x + x200, g2.y + y75);
        g2.draw();
        Prev.act(delta);
        Prev.draw();
        Go.act(delta);
        Go.draw();
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
        batch.dispose();
        Ships.dispose();
        Guns.dispose();
        textureAtlas.dispose();
        game.dispose();
        skin.dispose();

        System.out.println("DISPOSED");

    }
    public void MoveOld(CellStage ct, int dir) {

        if (dir < 0)
            ct.y -= ct.img.getImageHeight() + Gdx.graphics.getHeight() / 360 + 3;
        else
            ct.y += ct.img.getImageHeight() + Gdx.graphics.getHeight() / 360 + 3;
        ct.gun.setY(ct.y);
        ct.img.setY(ct.y - 15);


    }
     class StageForButton extends Stage {
        Button btn;

         int x;
         int y;
         int width;
         int height;

        public StageForButton(Button.ButtonStyle btnstyle, int x, int y) {

            btn = new Button(btnstyle);
            btn.setBounds(x, y, Gdx.graphics.getWidth()/2, (int) (Gdx.graphics.getHeight()/14.4));




            addActor(btn);
        }

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
    class CellStage extends Stage {
        Image gun;
        int x;
        int y;
        String name;
        Image img;
        int width,height;

        int price;

        public CellStage( int x, int y, String name, int price,int width,int height) {
            this.x = x;
            this.y = y;
            this.name = name;
            this.width=width;
            this.height=height;

            this.price = price;
            gun=new Image(skin.getDrawable(name));
            gun.setSize(width,height);
            gun.setPosition(x, y);
            img = new Image(skin.getDrawable("Frame"));
            img.setPosition(x - 15, y - 10);
            img.setSize((float) (Gdx.graphics.getWidth() / 2.7), (float) (Gdx.graphics.getHeight() / 3.3));


            addActor(gun);
            addActor(img);
        }
    }
}
