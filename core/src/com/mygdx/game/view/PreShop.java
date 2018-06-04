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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.model.Player;
import com.mygdx.game.utils.TextManager;

public class PreShop implements Screen {
    StageForButton Guns,Ships;
    Button.ButtonStyle Gstyle,Sstyle;
    Skin skin;
    TextureAtlas textureAtlas;
    SpriteBatch batch;
    Game game;
    Screen ShList,ShList2;
    OrthographicCamera camera;
    TextManager textManager;
    BitmapFont font;
    MainMenu menu;
    Player player;
    public PreShop(Game game, SpriteBatch batch, TextureAtlas textureAtlas, MainMenu menu, Player player){
        this.game=game;
        this.batch = batch;
        this.textureAtlas=textureAtlas;
        this.menu=menu;
        this.player=player;



    }

    @Override
    public void show() {
        textManager=new TextManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        skin=new Skin();
        batch=new SpriteBatch();
        font=textManager.fontInitialize(Color.BLACK,1f);

        skin.addRegions(new TextureAtlas(Gdx.files.internal("TexturePack.atlas")));
        ShList=new ShopList(game,batch,textureAtlas,menu,player);
        ShList2=new ShopList2(game,batch,textureAtlas,menu,player);
        //creating button with guns image
        Gstyle = new Button.ButtonStyle();
        Gstyle.up = skin.getDrawable("UnSelectGun");
        Gstyle.down = skin.getDrawable("UnSelectGun");
        Guns=new StageForButton(Gstyle,0,(int) (Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/14.11764706));
        //change scene when 'guns' clicked
        Guns.btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(ShList2);

            }
        });



        //creating button with ships image
        Sstyle = new Button.ButtonStyle();
        Sstyle.up = skin.getDrawable("UnSelectShip");
        Sstyle.down = skin.getDrawable("UnSelectShip");
        Ships=new StageForButton(Sstyle,Gdx.graphics.getWidth()/2, (int) (Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/14.11764706));
        //change scene when 'ships' clicked
        Ships.btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(ShList);

            }
        });
        InputMultiplexer in=new InputMultiplexer();

        in.addProcessor(Ships);
        in.addProcessor(Guns);


        Gdx.input.setInputProcessor(in);
        camera = new OrthographicCamera();
    }

    @Override
    public void render(float delta) {

        camera.setToOrtho(false, (float) (Gdx.graphics.getWidth()/1.6), (float) (Gdx.graphics.getHeight()/1.5));
        LoginView.textrure.draw();
        LoginView.star.draw();
        textManager.displayMessage(batch,font,"Guns " , (int) (Gdx.graphics.getWidth()/4.6), Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/12);
        textManager.displayMessage(batch,font,"Ships" , (Gdx.graphics.getWidth())-(int) (Gdx.graphics.getWidth()/3.4), Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/12);
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
        Guns.dispose();
        Ships.dispose();
        batch.dispose();
        game.dispose();
        textureAtlas.dispose();



    }
   class StageForButton extends Stage {
        Button btn;

        public StageForButton(Button.ButtonStyle btnstyle, int x, int y) {

            btn = new Button(btnstyle);
            btn.setBounds(x, y, Gdx.graphics.getWidth()/2, 50);




            addActor(btn);
        }
    }
}
