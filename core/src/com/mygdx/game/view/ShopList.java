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
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.Ship;
import com.mygdx.game.model.Ships.Bat;
import com.mygdx.game.model.Ships.Pulsate;
import com.mygdx.game.utils.TextManager;


//This screen is used to show Shop of Ships
public class ShopList implements Screen {
    OrthographicCamera camera;
    Game game;
    Screen ShList2, ShShow;
    MainMenu menu;
    private StageForButton Guns, Ships, Go, Prev, Back;
    Button.ButtonStyle Gstyle, Sstyle, Gostyle, Prevstyle, BaStyle;
    SpriteBatch batch;
    TextureAtlas textureAtlas;
    TextManager textManager;
    Button.ButtonStyle st1, st2, st3, st4, st5; // Styles for 5 ships
    CellStage ct1, ct2, ct3, ct4, ct5; // 5 Stages for 5 ships
    public static Skin skin;
    Image InformationFrame, Money;
    BitmapFont font;
    Boolean NeedToMove = false;
    Boolean NeedToMoveBack = false;
    Viewport VP;
    int counter = 0;

    //ships
   Ship pulsate,bat,dakkar,hunter,mite;
    Player player;



    public ShopList(Game game, SpriteBatch batch, TextureAtlas textureAtlas,MainMenu menu,Player player) {
        this.game = game;
        this.batch = batch;
        this.textureAtlas = textureAtlas;
        this.menu=menu;
        this.player=player;
    }

    @Override
    public void dispose() {
        game.dispose();
        Guns.dispose();
        ShList2.dispose();
        Go.dispose();
        Prev.dispose();
        font.dispose();
        batch.dispose();
        skin.dispose();
        Ships.dispose();


        ct1.dispose();
        ct2.dispose();
        ct3.dispose();
        ct5.dispose();
        ct4.dispose();

        textureAtlas.dispose();
        System.out.println("DISPOSED");


    }

    @Override
    public void render(float delta) {


        Gdx.gl.glClearColor(0, 64, 247, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        // batch.setProjectionMatrix(camera.combined);
        // camera.setToOrtho(false, 800, 480);

        // textManager.displayMessage(batch,"Your money: " /*+ Player.getMoney()*/ ,Color.BLACK,ct1.size,20 , Gdx.graphics.getHeight()-20);
        //'1' ship
        // textManager.displayMessage(batch,font, ct1.name, ct1.x + 200, 300);

        textManager.displayMessage(batch, font, ct1.name, ct1.x + 200, ct1.y + 175);
        textManager.displayMessage(batch, font, "Price: " + ct1.price, ct1.x + 200, ct1.y + 75);
        ct1.act(delta);
        ct1.draw();

        //'Bat' ship
        textManager.displayMessage(batch, font, ct2.name, ct2.x + 200, ct2.y + 175);
        textManager.displayMessage(batch, font, "Price: " + ct2.price, ct2.x + 200, ct2.y + 75);
        ct2.act(delta);
        ct2.draw();
        //'Dakkar' ship
        textManager.displayMessage(batch, font, ct3.name, ct3.x + 200, ct3.y + 175);
        textManager.displayMessage(batch, font, "Price: " + ct3.price, ct3.x + 200, ct3.y + 75);
        ct3.act(delta);
        ct3.draw();
        //'Mite' ship
        textManager.displayMessage(batch, font, ct4.name, ct4.x + 200, ct4.y + 175);
        textManager.displayMessage(batch, font, "Price: " + ct4.price, ct4.x + 200, ct4.y + 75);
        ct4.act(delta);
        ct4.draw();
        //'Hunter' ship
        textManager.displayMessage(batch, font, ct5.name, ct5.x + 200, ct5.y + 175);
        //textManager.displayMessage(batch,font, "Price: " + ct5.price,  ct5.x + 200, ct5.y + 75);
        textManager.displayMessage(batch, font, "Price: " + ct5.price, ct5.x + 200, ct5.y + 75);
        ct5.act(delta);
        ct5.draw();
        //Information bar
        //int a=menu.
        textManager.displayMessage(batch,font,""+new Player("Hah",pulsate).getMoney(),Money.getX()+Money.getWidth(),Money.getY()+Money.getHeight()/2);


        //Top buutons for swithing
        Guns.act(delta);
        Guns.draw();
        Ships.act(delta);
        Ships.draw();
        Go.act(delta);
        Go.draw();
        Prev.act(delta);
        Prev.draw();
        Back.act(delta);
        Back.draw();


        if (NeedToMove == true) {


            MoveOld(ct1, -1);
            MoveOld(ct2, -1);
            MoveOld(ct3, -1);
            MoveOld(ct4, -1);
            MoveOld(ct5, -1);


        }
        NeedToMove = false;
        if (NeedToMoveBack == true) {

            MoveOld(ct1, 1);
            MoveOld(ct2, 1);
            MoveOld(ct3, 1);
            MoveOld(ct4, 1);
            MoveOld(ct5, 1);


        }

        NeedToMoveBack = false;
        batch.begin();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {


    }

    @Override
    public void resume() {
        System.out.println( player.resources.shipList);
    }

    @Override
    public void hide() {

    }


    @Override
    public void show() {

        camera = new OrthographicCamera();
        camera.position.set(new Vector3(400, 240, 0));
        //  VP=new FillViewport((float)(camera.position.x*3.2),camera.position.y*3,camera) ;

        ShList2 = new ShopList2(game, batch, textureAtlas,menu,player);
        batch = new SpriteBatch();    //Battle.player.getMoney();
        textManager = new TextManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        System.out.println( player.resources.shipList);

        skin = new Skin();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("TexturePack.atlas")));
        Window.WindowStyle ws = new Window.WindowStyle();

        //Creating frame, where player can see his money (Information Bar)
        // InformationFrame=new Image(skin.getDrawable("Frame"));
        Money = new Image(skin.getDrawable("Money"));
        // InformationFrame.setSize((float) (Gdx.graphics.getWidth()/3.5), (float) (Gdx.graphics.getHeight()/3.3));

        //  InformationFrame.setPosition(Gdx.graphics.getWidth()/Gdx.graphics.getWidth(),Gdx.graphics.getHeight()/3);
        Money.setSize((float) (Gdx.graphics.getWidth()/8.8), (float) (Gdx.graphics.getHeight()/4.96));
        Money.setPosition(Gdx.graphics.getWidth() / Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 3 + Gdx.graphics.getHeight() / 24);


        //--------------------drawing buttons again
        //button for going to the next page
        Gostyle = new Button.ButtonStyle();
        Gostyle.up = skin.getDrawable("Go-up");
        Gostyle.down = skin.getDrawable("Go-down");
        Go = new StageForButton(Gostyle, (int) (Gdx.graphics.getWidth() - Gdx.graphics.getWidth()/6.0952381), (int) ((Gdx.graphics.getHeight()) / 1.8), (int) (Gdx.graphics.getHeight()/3.6), (int) (Gdx.graphics.getHeight()/3.6));
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
        Prev = new StageForButton(Prevstyle, Go.x, Gdx.graphics.getHeight() / 8, (int) (Gdx.graphics.getHeight()/3.6), (int) (Gdx.graphics.getHeight()/3.6));
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
        //creating button with guns image
        Gstyle = new Button.ButtonStyle();
        Gstyle.up = skin.getDrawable("UnSelectGun");
        Gstyle.down = skin.getDrawable("UnSelectGun");

        Guns = new StageForButton(Gstyle, Gdx.graphics.getWidth() / Gdx.graphics.getWidth(), (int) (Gdx.graphics.getHeight() - 50), Gdx.graphics.getWidth() / 2, (int) (Gdx.graphics.getHeight()/14.4));
        //change scene when 'guns' clicked
        Guns.btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(ShList2);

            }
        });


        //creating button with ships image
        Sstyle = new Button.ButtonStyle();
        Sstyle.up = skin.getDrawable("SelectShip");
        Sstyle.down = skin.getDrawable("SelectShip");
        Ships = new StageForButton(Sstyle, (int) (Gdx.graphics.getWidth() / 2), (int) (Gdx.graphics.getHeight() - 50), Gdx.graphics.getWidth() / 2, (int) (Gdx.graphics.getHeight()/14.4));
        //change scene when 'ships' clicked
        Ships.btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                // game.setScreen(ShList);

            }
        });
        //-----------------------end


        //place for '1' ship
        pulsate=new Pulsate(textureAtlas,0,0);
        st1 = new Button.ButtonStyle();
        st1.up = skin.getDrawable("1");
        st1.down = skin.getDrawable("1");
        ct1 = new CellStage(st1, (int) (Gdx.graphics.getWidth() / 4 * 1.25), Ships.y - 212, pulsate.getName(), pulsate.getCost());
        ct1.img.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                ShShow = new ShipShow(pulsate,game,menu,player);

                game.setScreen(ShShow);

                //  Gdx.input.setInputProcessor(null);


            }
        });
        //place for 'Bat' ship
       // Ship bat=new Bat(textureAtlas,0,0);
        st2 = new Button.ButtonStyle();
        st2.up = skin.getDrawable("Bat");
        st2.down = skin.getDrawable("Bat");
        ct2 = new CellStage(st2, ct1.x, (int) (ct1.y - ct1.img.getHeight() - Gdx.graphics.getHeight() / 360), "Bat", 200);
        ct2.img.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               // ShShow = new ShipShow(skin.getRegion("Bat"), 0, 0, 0, 0, ct2.name, ct2.price, 400, 20, 100, game);
             //   game.setScreen(ShShow);
                // Gdx.input.setInputProcessor(null);

            }
        });
        //place for 'Dakkar' ship
        st3 = new Button.ButtonStyle();
        st3.up = skin.getDrawable("Dakkar");
        st3.down = skin.getDrawable("Dakkar");
        ct3 = new CellStage(st3, ct1.x, (int) (ct2.y - ct1.img.getHeight() - Gdx.graphics.getHeight() / 360), "Dakkar", 280);
        ct3.img.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               // ShShow = new ShipShow(skin.getRegion("Dakkar"), 0, 0, 0, 0, ct3.name, ct3.price, 400, 20, 100, game);
               // game.setScreen(ShShow);
                // Gdx.input.setInputProcessor(null);

            }
        });
        //place for 'Mite' ship
        st4 = new Button.ButtonStyle();
        st4.up = skin.getDrawable("Mite");
        st4.down = skin.getDrawable("Mite");
        ct4 = new CellStage(st4, ct1.x, (int) (ct3.y - ct1.img.getHeight() - Gdx.graphics.getHeight() / 360), "Mite", 145);
        ct4.img.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
              //  ShShow = new ShipShow(skin.getRegion("Mite"), 0, 0, 0, 0, ct4.name, ct4.price, 400, 20, 100, game);
              //  game.setScreen(ShShow);
                // Gdx.input.setInputProcessor(null);

            }
        });
        //place for 'Hunter' ship
        st5 = new Button.ButtonStyle();
        st5.up = skin.getDrawable("Hunter");
        st5.down = skin.getDrawable("Hunter");
        ct5 = new CellStage(st5, ct1.x, (int) (ct4.y - ct1.img.getHeight() - Gdx.graphics.getHeight() / 360), "Hunter", 190);
        ct5.addActor(Money);
        ct5.img.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
              //  ShShow = new ShipShow(skin.getRegion("Hunter"), 0, 0, 0, 0, ct5.name, ct5.price, 400, 20, 100, game);
              //  game.setScreen(ShShow);
                // Gdx.input.setInputProcessor(null);

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


        // join
        InputMultiplexer in = new InputMultiplexer();
        in.addProcessor(Go);
        in.addProcessor(Prev);
        in.addProcessor(Guns);
        in.addProcessor(ct1);
        in.addProcessor(ct2);
        in.addProcessor(ct3);
        in.addProcessor(ct4);
        in.addProcessor(ct5);
        in.addProcessor(Back);

        Gdx.input.setInputProcessor(in);
        font = textManager.fontInitialize(Color.BLACK, (float) 0.8);
    }

    public void MoveOld(CellStage ct, int dir) {

        if (dir < 0)
            ct.y -= ct.img.getImageHeight() + Gdx.graphics.getHeight() / 360 + 3;
        else
            ct.y += ct.img.getImageHeight() + Gdx.graphics.getHeight() / 360 + 3;
        ct.btn.setY(ct.y);
        ct.img.setY(ct.y - 15);


    }


    static class CellStage extends Stage {
        Button btn;
        int x;
        int y;
        String name;
        Image img;

        int price;

        public CellStage(Button.ButtonStyle btnstyle, int x, int y, String name, int price) {
            this.x = x;
            this.y = y;
            this.name = name;

            this.price = price;
            btn = new Button(btnstyle);
            btn.setBounds(x, y, (float) (Gdx.graphics.getHeight()/3.6), (float) (Gdx.graphics.getHeight()/3.6));
            img = new Image(skin.getDrawable("Frame"));
            img.setPosition(x - 15, y - 10);
            img.setSize((float) (Gdx.graphics.getWidth()/2.7), (float) (Gdx.graphics.getHeight()/3.3));


            addActor(btn);
            addActor(img);
        }
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



