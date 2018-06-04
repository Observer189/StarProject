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
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.Ship;
import com.mygdx.game.model.Ships.Axe;
import com.mygdx.game.model.Ships.Dakkar;
import com.mygdx.game.model.Ships.Dashing;
import com.mygdx.game.model.Ships.Mite;
import com.mygdx.game.model.Ships.Pulsate;
import com.mygdx.game.model.Ships.Rock;
import com.mygdx.game.model.Ships.Sudden;
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
    Button.ButtonStyle st1, st2, st3, st4, st5, st6, st7; // Styles for 5 ships
    CellStage ct1, ct2, ct3, ct4, ct5, ct6, ct7; // 5 Stages for 5 ships
    public static Skin skin;
    Image InformationTube, Money;
    BitmapFont font;
    Boolean NeedToMove = false;
    Boolean NeedToMoveBack = false;
    Viewport VP;
    int counter = 0;
    int digits[];
    String MoneyStr="";

    //ships
    Ship pulsate, bat, rock, hunter, mite,axe,dashing,sudden;
    Player player;


    public ShopList(Game game, SpriteBatch batch, TextureAtlas textureAtlas, MainMenu menu, Player player) {
        this.game = game;
        this.batch = batch;
        this.textureAtlas = textureAtlas;
        this.menu = menu;
        this.player = player;
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



    }

    @Override
    public void render(float delta) {


        camera.setToOrtho(false, (float) (Gdx.graphics.getWidth()/1.6), (float) (Gdx.graphics.getHeight()/1.5));
        LoginView.textrure.draw();
        LoginView.star.draw();
        camera.update();

        int x200 = (int) (Gdx.graphics.getWidth() / 6.3);
        int y175 = (int) (Gdx.graphics.getHeight() / 4.11428);
        int y75 = (int) (Gdx.graphics.getHeight() / 9.6);

        ct1.act(delta);
        ct1.draw();
        textManager.displayMessage(batch, font, ct1.name, (float) (ct1.x + x200*1.5),  (ct1.y + y175));
        textManager.displayMessage(batch, font, "Price: " + ct1.price, (float) (ct1.x + x200*1.5), (float) (ct1.y + y75*0.915));
        //'Bat' ship

        ct2.act(delta);
        ct2.draw();
        textManager.displayMessage(batch, font, ct2.name, (float) (ct1.x + x200*1.5), ct2.y + y175);
        textManager.displayMessage(batch, font, "Price: " + ct2.price, (float) (ct1.x + x200*1.5), (float) (ct2.y + y75*0.915));
        //'Dakkar' ship
        ct3.act(delta);
        ct3.draw();
        textManager.displayMessage(batch, font, ct3.name, (float) (ct1.x + x200*1.5), ct3.y + y175);
        textManager.displayMessage(batch, font, "Price: " + ct3.price, (float) (ct1.x + x200*1.5), (float) (ct3.y + y75*0.915));

        //'Mite' ship

        ct4.act(delta);
        ct4.draw();
        textManager.displayMessage(batch, font, ct4.name, (float) (ct1.x + x200*1.5), ct4.y + y175);
        textManager.displayMessage(batch, font, "Price: " + ct4.price, (float) (ct1.x + x200*1.5), (float) (ct4.y + y75*0.915));
        //'Hunter' ship

        ct5.act(delta);
        ct5.draw();
        textManager.displayMessage(batch, font, ct5.name, (float) (ct1.x + x200*1.5), ct5.y + y175);
        textManager.displayMessage(batch, font, "Price: " + ct5.price, (float) (ct1.x + x200*1.5), (float) (ct5.y + y75*0.915));
        //Information bar
        //int a=menu.
        textManager.displayMessage(batch, font, MoneyStr,  Money.getX()+60 , InformationTube.getY()+InformationTube.getHeight()-25);


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
    public void resume() {
        menu.music.play();

    }

    @Override
    public void hide() {

    }


    @Override
    public void show() {
        Gdx.input.setCatchBackKey(true);
        camera = new OrthographicCamera();

        //  VP=new FillViewport((float)(camera.position.x*3.2),camera.position.y*3,camera) ;

        ShList2 = new ShopList2(game, batch, textureAtlas, menu, player);
        batch = new SpriteBatch();    //Battle.player.getMoney();
        textManager = new TextManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        System.out.println(player.resources.shipList);

        skin = new Skin();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("TexturePack.atlas")));




        //--------------------drawing buttons again
        //button for going to the next page
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
        //creating button with guns image
        Gstyle = new Button.ButtonStyle();
        Gstyle.up = skin.getDrawable("UnSelectGun");
        Gstyle.down = skin.getDrawable("UnSelectGun");

        Guns = new StageForButton(Gstyle, Gdx.graphics.getWidth() / Gdx.graphics.getWidth(), (int) (Gdx.graphics.getHeight() - 50), Gdx.graphics.getWidth() / 2, (int) (Gdx.graphics.getHeight() / 14.4));
        //change scene when 'guns' clicked
        Guns.btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(ShList2);

            }
        });


        //creating button with ships image
        Sstyle = new Button.ButtonStyle();
        Sstyle.up = skin.getDrawable("UnSelectShip");
        Sstyle.down = skin.getDrawable("UnSelectShip");
        Ships = new StageForButton(Sstyle, (int) (Gdx.graphics.getWidth() / 2), (int) (Gdx.graphics.getHeight() - 50), Gdx.graphics.getWidth() / 2, (int) (Gdx.graphics.getHeight() / 14.4));
        //change scene when 'ships' clicked
        Ships.btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                // game.setScreen(ShList);

            }
        });
        //-----------------------end
        //Creating frame, where player can see his money (Information Bar)

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


        //place for '1' ship
     /*   pulsate = new Pulsate(textureAtlas, 0, 0);
        st1 = new Button.ButtonStyle();
        st1.up = skin.getDrawable("1");
        st1.down = skin.getDrawable("1");
        ct1 = new CellStage(st1, (float) (Gdx.graphics.getWidth() / 5 * 1), (float) (Ships.y - Gdx.graphics.getHeight()/3.39622642), pulsate.getName(), pulsate.getCost(),Width.SHORTWIDTH,Height.DEFAULTHEIGHT);
        ct1.btn.setPosition(ct1.x+(Width.DEFAULTWIDTH.getWidth()-Width.SHORTWIDTH.getWidth()),ct1.y);
        ct1.img.setPosition(ct1.btn.getX() +ct1.btn.getWidth()+15, ct1.y - 10);*/
        sudden=new Sudden(textureAtlas,0,0);
        st1 = new Button.ButtonStyle();
        st1.up = skin.getDrawable("Sudden");
        st1.down = skin.getDrawable("Sudden");
        ct1 = new CellStage(st1, (float) (Gdx.graphics.getWidth() / 5 * 1), (float) (Ships.y - Gdx.graphics.getHeight()/3.39622642), sudden.getName(), sudden.getCost(),Width.SHORTWIDTH,Height.DEFAULTHEIGHT);
        ct1.btn.setPosition(ct1.x+(Width.DEFAULTWIDTH.getWidth()-Width.SHORTWIDTH.getWidth()),ct1.y);
        ct1.img.setPosition(ct1.btn.getX() +ct1.btn.getWidth()+15, ct1.y - 10);
        ct1.btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                ShShow = new ShipShow(sudden, game, menu, player,ct1.width,ct1.height);

                game.setScreen(ShShow);


            }
        });
        //place for 'Dashing' ship
        // Ship bat=new Bat(textureAtlas,0,0);
        dashing=new Dashing(textureAtlas,0,0);
        st2 = new Button.ButtonStyle();
        st2.up = skin.getDrawable("Dashing");
        st2.down = skin.getDrawable("Dashing");
        ct2 = new CellStage(st2, ct1.x+(Width.DEFAULTWIDTH.getWidth()-Width.SHORTWIDTH.getWidth()),  (ct1.y - ct1.img.getHeight() - Gdx.graphics.getHeight() / 360), "Dashing", dashing.getCost(),Width.SHORTWIDTH,Height.DEFAULTHEIGHT);
        ct2.btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ShShow = new ShipShow(dashing, game, menu, player,ct2.width,ct2.height);
                game.setScreen(ShShow);

            }
        });
        //place for 'Dakkar' ship
        rock = new Rock(textureAtlas, 0, 0);
        st3 = new Button.ButtonStyle();
        st3.up = skin.getDrawable("Rock");
        st3.down = skin.getDrawable("Rock");
        ct3 = new CellStage(st3, ct1.x+(Width.DEFAULTWIDTH.getWidth()-Width.SHORTWIDTH.getWidth()),  (ct2.y - ct1.img.getHeight() - Gdx.graphics.getHeight() / 360),rock.getName(),rock.getCost(),Width.SHORTWIDTH,Height.DEFAULTHEIGHT);
        ct3.btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ShShow = new ShipShow(rock, game, menu, player,ct3.width,ct3.height);

                game.setScreen(ShShow);

            }
        });
        //place for 'Mite' ship

        st4 = new Button.ButtonStyle();
        st4.up = skin.getDrawable("Mite");
        st4.down = skin.getDrawable("Mite");
        ct4 = new CellStage(st4, ct1.x+(Width.DEFAULTWIDTH.getWidth()-Width.SHORTWIDTH.getWidth()),  (ct3.y - ct1.img.getHeight() - Gdx.graphics.getHeight() / 360), "Mite", 30000,Width.SHORTWIDTH,Height.DEFAULTHEIGHT);
        ct4.btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               // ShShow = new ShipShow(mite, game, menu, player,ct4.width,ct4.height);
                //  game.setScreen(ShShow);
                // Gdx.input.setInputProcessor(null);

            }
        });
        //place for 'Axe' ship
        axe=new Axe(textureAtlas,0,0);
        st5 = new Button.ButtonStyle();
        st5.up = skin.getDrawable("Axe");
        st5.down = skin.getDrawable("Axe");
        ct5 = new CellStage(st5, ct1.x+(Width.DEFAULTWIDTH.getWidth()-Width.SHORTWIDTH.getWidth()),  (ct4.y - ct1.img.getHeight() - Gdx.graphics.getHeight() / 360), axe.getName(), axe.getCost(),Width.SHORTWIDTH,Height.DEFAULTHEIGHT);
        ct5.addActor(Money);
        ct4.addActor(InformationTube);

        ct5.btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ShShow = new ShipShow(axe, game, menu, player,ct5.width,ct5.height);

                game.setScreen(ShShow);

            }
        });
        BaStyle = new Button.ButtonStyle();
        BaStyle.up = skin.getDrawable("Back-up");
        BaStyle.down = skin.getDrawable("Back-down");
        Back = new StageForButton(BaStyle, Gdx.graphics.getHeight() / Gdx.graphics.getHeight(), Gdx.graphics.getWidth() / Gdx.graphics.getWidth(), (int) (Gdx.graphics.getWidth() / 8.8), (int) (Gdx.graphics.getHeight() / 4.96));
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
        font = textManager.fontInitialize(Color.WHITE, 0.8f);
    }

    public void MoveOld(CellStage ct, int dir) {

        if (dir < 0)
            ct.y -= (ct.img.getImageHeight() + Gdx.graphics.getHeight() / 360 + 3);
        else
            ct.y +=( ct.img.getImageHeight() + Gdx.graphics.getHeight() / 360 + 3);
        ct.btn.setY(ct.y);
        ct.img.setY(ct.y - 10);


    }


     class CellStage extends Stage {
        Button btn;
        float x;
        float y;
        String name;
        Image img;
        float width;
        float height;
        int price;

        public CellStage(Button.ButtonStyle btnstyle, float x, float y, String name, int price,Width width,Height height) {
            this.x = x;
            this.y = y;
            this.name = name;
            this.width=width.i;
            this.height=height.v;
            this.price = price;
            btn = new Button(btnstyle);
            btn.setBounds(x, y, width.i, height.v);
            img = new Image(skin.getDrawable("InfoFrame"));
            img.setPosition(x +btn.getWidth()+15, y - 10);
            img.setSize((float) (Gdx.graphics.getWidth() / 2.7), (float) (Gdx.graphics.getHeight() / 3.3));



            addActor(img);
            addActor(btn);
        }
    }


        public enum Width {
            SHORTWIDTH((float)(Gdx.graphics.getHeight()/5.14285714)),
            DEFAULTWIDTH((float) (Gdx.graphics.getHeight() / 3.8)),;
            public float i;
            Width(float i) {
                this.i=i;
            }

            public float getWidth() {
                return i;
            }
        }
       public enum Height{
            //SHORTHEIGHT( (float) (Gdx.graphics.getHeight() / 3.8)),
            DEFAULTHEIGHT((float) (Gdx.graphics.getHeight() / 3.8));
            float v;

            Height(float v) {
                this.v=v;
            }

            public float getHeight() {
                return v;
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



