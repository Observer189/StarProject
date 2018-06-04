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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.Weapons.BlueImpulseLaser;
import com.mygdx.game.model.Weapons.Machinegun;
import com.mygdx.game.model.Weapons.RocketLauncher;
import com.mygdx.game.model.Weapons.Shotgun;
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
    CellStage g1,g2,g3,g4;
    Image Money,InformationTube;
    BitmapFont font,font1;
    int counter = 0;
    Boolean NeedToMove = false;
    Boolean NeedToMoveBack = false;
    StageForButton Prev,Go;
    Button.ButtonStyle Gostyle,Prevstyle;
   // Weapon laser,multi;
    BlueImpulseLaser Laser;
    Machinegun multigun;
    Shotgun shotgun;
    RocketLauncher rocketLauncher;

    int digits[];
    String MoneyStr="";

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
        Gdx.input.setCatchBackKey(true);

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



        Money.setSize((float) (Gdx.graphics.getWidth() / 8.8), (float) (Gdx.graphics.getHeight() / 4.96));
        Money.setPosition(Gdx.graphics.getWidth() / Gdx.graphics.getWidth(), (float) (Gdx.graphics.getHeight() -Money.getHeight()-(int) (Gdx.graphics.getHeight() / 14.4)));

        Integer mon=menu.player.getMoney();
        int heightOfTube=0;
        digits=new int[mon.toString().length()];
        int restDigits=mon;
        for (int i=mon.toString().length()-1;i>=0;i--) {
            heightOfTube += (int) (Gdx.graphics.getWidth() / 25.6);
            digits[i] = restDigits % 10;
            restDigits = restDigits / 10;
        }

        for (int i=0;i<mon.toString().length();i++){
            MoneyStr+=digits[i]+"\n";

        }

        InformationTube=new Image(skin.getDrawable("Tube2"));
        InformationTube.setSize((float) (Gdx.graphics.getWidth() / 8.8),heightOfTube);
        InformationTube.setPosition(Money.getX(), (float) (Money.getY()-InformationTube.getHeight()));


        Laser=new BlueImpulseLaser(textureAtlas,(int) (Gdx.graphics.getWidth() / 4 * 1.25),(int) (Gdx.graphics.getHeight() -Gdx.graphics.getHeight()/2.7480916));
        g1=new CellStage((int) Laser.getX(), (int) Laser.getY(),Laser.getName(),Laser.getCost(), Laser.getRecomendedw(), Laser.getRecomendedh());


        g1.gun.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                GuShow = new GunShow(Laser, game, menu, player,(float) (g1.width*1.7),(float) (g1.height*1.7));

                game.setScreen(GuShow);


            }
        });//
         multigun=new Machinegun(textureAtlas,g1.x,(int) (g1.y - g1.img.getHeight() - Gdx.graphics.getHeight() / 360));
        g2=new CellStage((int) ((int) multigun.getX()-multigun.getRecomendedw()+g1.width),(int)multigun.getY(),multigun.getName(),multigun.getCost(),multigun.getRecomendedw(),multigun.getRecomendedh());
        g2.gun.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                GuShow = new GunShow(multigun, game, menu, player, (float) (g2.width*1.7), (float) (g2.height*1.7));

                game.setScreen(GuShow);


            }
        });
        shotgun=new Shotgun(textureAtlas,g1.x,(int) (g2.y - g1.img.getHeight() - Gdx.graphics.getHeight() / 360));
        g3=new CellStage((int) ((int) shotgun.getX()-shotgun.getRecomendedw()+g1.width), (int) shotgun.getY(),shotgun.getName(),shotgun.getCost(),shotgun.getRecomendedw(),shotgun.getRecomendedh());
        g3.gun.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                GuShow = new GunShow(shotgun, game, menu, player, (float) (g3.width*1.7), (float) (g3.height*1.7));

                game.setScreen(GuShow);


            }
        });
        rocketLauncher=new RocketLauncher(textureAtlas,g1.x,g3.y-g1.img.getHeight()-Gdx.graphics.getHeight() / 360);
        g4=new CellStage((int) ((int) rocketLauncher.getX()-rocketLauncher.getRecomendedw()+g1.width), (int) rocketLauncher.getY(),rocketLauncher.getName(),rocketLauncher.getCost(),rocketLauncher.getRecomendedw(),rocketLauncher.getRecomendedh());
        g4.gun.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                GuShow = new GunShow(rocketLauncher, game, menu, player, (float) (g4.width*1.7), (float) (g4.height*1.7));

                game.setScreen(GuShow);


            }
        });



        g2.addActor(Money);
        g2.addActor(InformationTube);



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
                if (counter == 3)
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
        in.addProcessor(g3);
        in.addProcessor(g4);
        in.addProcessor(Ships);
        in.addProcessor(Back);
        in.addProcessor(Prev);
        in.addProcessor(Go);
        Gdx.input.setInputProcessor(in);
        font = textManager.fontInitialize(Color.WHITE, 0.8f);
        font1 = textManager.fontInitialize(Color.WHITE, 0.6f);
    }

    @Override
    public void render(float delta) {

        camera.setToOrtho(false, (float) (Gdx.graphics.getWidth()/1.6), (float) (Gdx.graphics.getHeight()/1.5));
        LoginView.textrure.draw();
        LoginView.star.draw();

        int x200 = (int) (Gdx.graphics.getWidth() / 6.3);
        int y175 = (int) (Gdx.graphics.getHeight() / 4.11428);
        int y75 = (int) (Gdx.graphics.getHeight() / 9.6);


        Back.act(delta);
        Back.draw();
        g1.act(delta);
        g1.draw();
        textManager.displayMessage(batch, font1, g1.name, (float) (g1.x + x200), (float) (g1.y + y175));
        textManager.displayMessage(batch, font, "Price: " +g1.price, g1.x + x200, (float) (g1.y + y75*0.915));


        g2.act(delta);
        g2.draw();
        textManager.displayMessage(batch, font, g2.name, (float) (g1.x + x200), (float) (g2.y + y175));
        textManager.displayMessage(batch, font, "Price: " + g2.price, g1.x + x200, (float) (g2.y + y75*0.915));
        textManager.displayMessage(batch, font, MoneyStr, (float) (Money.getX()+Gdx.graphics.getWidth()/21.33333), (float) (InformationTube.getY()+InformationTube.getHeight()-Gdx.graphics.getHeight()/28.8));

        g3.act(delta);
        g3.draw();
        textManager.displayMessage(batch, font, g3.name, (float) (g1.x + x200), (float) (g3.y + y175));
        textManager.displayMessage(batch, font, "Price: " + g3.price, g1.x + x200, (float) (g3.y + y75*0.915));

        g4.act(delta);
        g4.draw();
        textManager.displayMessage(batch, font1, g4.name, (float) (g1.x + x200), (float) (g4.y + y175));
        textManager.displayMessage(batch, font, "Price: " + g4.price, g1.x + x200, (float) (g4.y + y75*0.915));

        Prev.act(delta);
        Prev.draw();
        Go.act(delta);
        Go.draw();
        if (NeedToMove == true) {


            MoveOld(g1, -1);
            MoveOld(g2, -1);
            MoveOld(g3, -1);
            MoveOld(g4, -1);
           // MoveOld(ct5, -1);


        }
        NeedToMove = false;
        if (NeedToMoveBack == true) {

            MoveOld(g1, 1);
            MoveOld(g2, 1);
            MoveOld(g3, 1);
            MoveOld(g4, 1);
        //    MoveOld(ct5, 1);


        }

        NeedToMoveBack = false;
        Guns.act(delta);
        Guns.draw();
        Ships.act(delta);
        Ships.draw();
        batch.begin();
        batch.end();
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            game.setScreen(menu);}

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
            ct.y -= ct.img.getImageHeight() + Gdx.graphics.getHeight() / 360 + Gdx.graphics.getHeight()/240;
        else
            ct.y += ct.img.getImageHeight() + Gdx.graphics.getHeight() / 360 + Gdx.graphics.getHeight()/240;
        ct.gun.setY(ct.y);
        ct.img.setY(ct.y - Gdx.graphics.getHeight()/48);


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
        float width,height;

        int price;

        public CellStage(int x, int y, String name, int price, float width, float height) {
            this.x = x;
            this.y = y;
            this.name = name;
            if (name.equals("BlueLaser"))name="BlueImpulseLaser";
            this.width=width;
            this.height=height;

            this.price = price;
            gun=new Image(skin.getDrawable(name));
            gun.setSize(width,height);
            gun.setPosition(x, y);
            img = new Image(skin.getDrawable("InfoFrame"));
            img.setPosition((float) (x +gun.getWidth()+Gdx.graphics.getWidth()/85.333333), y - Gdx.graphics.getHeight()/72);
            img.setSize((float) (Gdx.graphics.getWidth() / 2.7), (float) (Gdx.graphics.getHeight() / 3.3));


            addActor(gun);
            addActor(img);
        }
    }
}
