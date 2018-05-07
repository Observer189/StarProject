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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.model.Player;
import com.mygdx.game.utils.TextManager;




//This screen is used to show Shop of Ships
public class ShopList implements Screen {
    OrthographicCamera camera;
    Game game;
    Screen ShList2;
    StageForButton Guns, Ships,Go,Prev;
    Button.ButtonStyle Gstyle, Sstyle,Gostyle,Prevstyle;
    SpriteBatch batch;
    TextureAtlas textureAtlas;
    TextManager textManager;
    Button.ButtonStyle st1, st2, st3, st4, st5; // Styles for 5 ships
    CellStage ct1, ct2, ct3, ct4, ct5; // 5 Stages for 5 ships
    public static Skin skin;
    Image InformationFrame,Money;
    BitmapFont font;
    Boolean NeedToMove=false;
    Boolean NeedToMoveBack=false;


    public ShopList(Game game, SpriteBatch batch, TextureAtlas textureAtlas) {
        this.game = game;
        this.batch = batch;
        this.textureAtlas = textureAtlas;
    }

    @Override
    public void dispose() {
        ShList2.dispose();
        Go.dispose();
        Prev.dispose();
        font.dispose();
        batch.dispose();
        skin.dispose();
        Ships.dispose();
        Guns.dispose();
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

        textManager.displayMessage(batch,font, ct1.name, ct1.x + 200, ct1.y + 175);
        textManager.displayMessage(batch,font, "Price: " + ct1.price, ct1.x + 200, ct1.y + 75);
        ct1.act(delta);
        ct1.draw();

        //'Bat' ship
        textManager.displayMessage(batch,font, ct2.name, ct2.x + 200, ct2.y + 175);
        textManager.displayMessage(batch, font,"Price: " + ct2.price, ct2.x + 200, ct2.y + 75);
        ct2.act(delta);
        ct2.draw();
        //'Dakkar' ship
        textManager.displayMessage(batch,font, ct3.name,  ct3.x + 200, ct3.y + 175);
        textManager.displayMessage(batch,font, "Price: " + ct3.price, ct3.x + 200, ct3.y + 75);
        ct3.act(delta);
        ct3.draw();
        //'Mite' ship
        textManager.displayMessage(batch,font, ct4.name,  ct4.x + 200, ct4.y + 175);
        textManager.displayMessage(batch,font, "Price: " + ct4.price, ct4.x + 200, ct4.y + 75);
        ct4.act(delta);
        ct4.draw();
        //'Hunter' ship
        textManager.displayMessage(batch,font, ct5.name, ct5.x + 200, ct5.y + 175);
                                //textManager.displayMessage(batch,font, "Price: " + ct5.price,  ct5.x + 200, ct5.y + 75);
        textManager.displayMessage(batch,font, "Price: " + InformationFrame.getImageX(),  ct5.x + 200, ct5.y + 75);
        ct5.act(delta);
        ct5.draw();
        //Information bar
        //extManager.displayMessage(batch,font, ""+camera.position.x/10+" "+camera.position.y+" " +Gdx.graphics.getWidth(), (int) camera.position.x*2-785,  (int) camera.position.y*3 - 293+125);


        //Top buutons for swithing
        Guns.act(delta);
        Guns.draw();
        Ships.act(delta);
        Ships.draw();
        Go.act(delta);
        Go.draw();
        Prev.act(delta);
        Prev.draw();


        if (NeedToMove==true) {

            MoveButton(Go,-1);
            MoveOld(ct1,-1);
            MoveOld(ct2,-1);
            MoveOld(ct3,-1);
            MoveOld(ct4,-1);
            MoveOld(ct5,-1);
            MoveButton(Prev,-1);
            //MoveImage(Money, (int) (InformationFrame.getImageX()+5),-1);
           // MoveImage(InformationFrame,(int) camera.position.x/400,-1);
            InformationFrame.setPosition((int) camera.position.x/400,camera.position.y*3 - 293-10);
            Money.setPosition(InformationFrame.getImageX()+5, (float) (((camera.position.y*3) - 293-10)+(InformationFrame.getHeight()/2.7)));

        }
        if (NeedToMoveBack==true) {
            MoveButton(Go,1);
            MoveButton(Prev,1);
            MoveOld(ct1,1);
            MoveOld(ct2,1);
            MoveOld(ct3,1);
            MoveOld(ct4,1);
            MoveOld(ct5,1);
           // MoveImage(Money, (int) (InformationFrame.getImageX()+5),1);
          //  MoveImage(InformationFrame,(int) camera.position.x/400,1);
            InformationFrame.setPosition((int) camera.position.x/400,camera.position.y*3 - 293-10);
            Money.setPosition(InformationFrame.getImageX()+5, (float) (((camera.position.y*3) - 293-10)+(InformationFrame.getHeight()/2.7)));

        }
        NeedToMove=false;
        NeedToMoveBack=false;
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

    }

    @Override
    public void hide() {

    }



    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.position.set(new Vector3(400,240,0));
        ShList2=new ShopList2(game,batch,textureAtlas);
        batch = new SpriteBatch();    //Battle.player.getMoney();
        textManager = new TextManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        skin = new Skin();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("TexturePack.atlas")));
        //Creating frame, where player can see his money (Information Bar)
        InformationFrame=new Image(skin.getDrawable("Frame"));
        Money=new Image(skin.getDrawable("Money"));
        InformationFrame.setSize(480,220);
        InformationFrame.setPosition((int) camera.position.x/400,camera.position.y*3 - 293-10);
        Money.setSize(75,75);
        Money.setPosition(InformationFrame.getImageX()+5, (float) (((camera.position.y*3) - 293-10)+(InformationFrame.getHeight()/2.7)));

        //--------------------drawing buttons again
        //button for going to the next page
        Gostyle=new Button.ButtonStyle();
        Gostyle.up=skin.getDrawable("Go-up");
        Gostyle.down=skin.getDrawable("Go-down");
        Go=new StageForButton(Gostyle,(int) (camera.position.x*3.2-210), (int)(camera.position.y*3)/4,200,200);
        Go.btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                NeedToMove=true;


            }
        });
        //button for going to the previous stage
        Prevstyle=new Button.ButtonStyle();
        Prevstyle.up=skin.getDrawable("Prev-up");
        Prevstyle.down=skin.getDrawable("Prev-down");
        Prev=new StageForButton(Prevstyle,(int) (camera.position.x*3.2+20), (int)(camera.position.y*3)/4,200,200);
        Prev.btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                NeedToMoveBack=true;


            }
        });

        //creating button with guns image
        Gstyle = new Button.ButtonStyle();
        Gstyle.up = skin.getDrawable("UnSelectGun");
        Gstyle.down = skin.getDrawable("UnSelectGun");
        Guns = new StageForButton(Gstyle, (int) camera.position.x/400, (int) (camera.position.y*3 - 51),Gdx.graphics.getWidth() / 2,50);
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
        Ships = new StageForButton(Sstyle, (int) (camera.position.x*3.2 / 2), (int) (camera.position.y*3 - 51),Gdx.graphics.getWidth() / 2,50);
        //change scene when 'ships' clicked
        Ships.btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                // game.setScreen(ShList);

            }
        });
        //-----------------------end


        //place for '1' ship
        st1 = new Button.ButtonStyle();
        st1.up = skin.getDrawable("1");
        st1.down = skin.getDrawable("1");
        ct1 = new CellStage(st1, (int) camera.position.x/400+15, (int) (camera.position.y*3 - 513.6), "Pulsate",  40);
        //place for 'Bat' ship
        st2 = new Button.ButtonStyle();
        st2.up = skin.getDrawable("Bat");
        st2.down = skin.getDrawable("Bat");
        ct2 = new CellStage(st2, (int) camera.position.x/400+15, (int) (camera.position.y*3 - 735), "Bat",  200);
        //place for 'Dakkar' ship
        st3 = new Button.ButtonStyle();
        st3.up = skin.getDrawable("Dakkar");
        st3.down = skin.getDrawable("Dakkar");
        ct3 = new CellStage(st3, (int) (ct1.x + ct1.img.getWidth())+1, ct2.y , "Dakkar",  280);
        //place for 'Mite' ship
        st4 = new Button.ButtonStyle();
        st4.up = skin.getDrawable("Mite");
        st4.down = skin.getDrawable("Mite");
        ct4 = new CellStage(st4, (int) (ct1.x + ct1.img.getWidth())+1, (int) (camera.position.y*3 - 293), "Mite",  145);
        //place for 'Hunter' ship
        st5 = new Button.ButtonStyle();
        st5.up = skin.getDrawable("Hunter");
        st5.down = skin.getDrawable("Hunter");
        ct5 = new CellStage(st5, ct3.x, ct1.y, "Hunter",  190);
        ct5.addActor(InformationFrame);
        ct5.addActor(Money);


        // join
        InputMultiplexer in=new InputMultiplexer();
        in.addProcessor(Go);
        in.addProcessor(Prev);
        in.addProcessor(Guns);
        //  in.addProcessor(ct1);
        // in.addProcessor(ct2);
        // in.addProcessor(ct3);
        // in.addProcessor(ct4);
        // in.addProcessor(ct5);

         Gdx.input.setInputProcessor(in);
        font=textManager.fontInitialize(Color.BLACK,40);
    }
    public void MoveOld(CellStage ct,int dir){
        for (int i=0;i<650;i++){
             if (dir<0)
                ct.x-=2;
            else
                ct.x+=2;
            ct.btn.setX(ct.x);
            ct.img.setX(ct.x-15);




        }


    }
    public void MoveButton(StageForButton ct,int dir){
        for (int i=0;i<650;i++){
            if (dir<0)
            ct.x-=2;
            else
                ct.x+=2;
            ct.btn.setX(ct.x);




        }

    }

    public void MoveImage(Image img,int Starterx,int dir){
        img.setX(Starterx);
        for (int i=0;i<650;i++){


            if (dir<0)
            Starterx-=2;
            else
                Starterx+=2;
            img.setX(Starterx);

        }

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
            btn.setBounds(x, y, 200, 200);
            img = new Image(skin.getDrawable("Frame"));
            img.setPosition(x - 15, y - 10);
            img.setSize(480, 220);



            addActor(btn);
            addActor(img);
        }
    }

    static class StageForButton extends Stage {
        Button btn;
        int x;

        public StageForButton(Button.ButtonStyle btnstyle, int x, int y,int width,int height) {
            this.x=x;
            btn = new Button(btnstyle);
            btn.setBounds(x, y, width, height);


            addActor(btn);
        }
    }

}



