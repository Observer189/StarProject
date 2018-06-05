package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.mygdx.game.model.Weapons.BlueImpulseLaser;
import com.mygdx.game.requests.servApi;
import com.mygdx.game.utils.TextManager;
import com.mygdx.game.utils.Toast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GunShow implements Screen {
    //for drawing
    OrthographicCamera camera;
    SpriteBatch batch;
    Game game;
    TextManager textManager;
    BitmapFont font,font1;
    public TextureAtlas textureAtlas;
    Skin skin;
    Stage stage;
    int xt;
    int yt;
    int dyt;
    Boolean MakeToast=false;
    Boolean MakeToast1=false;
    Boolean MakeToast2=false;
    Weapon weapon;
    //Buttons
    StageForButton Back, Buy;
    Button.ButtonStyle BaStyle, BuStyle;
    //Sreens
    Screen ShList;
    Toast toast,toast1,toast2;
    MainMenu menu;
    Player player;
    //for ship's params
    Image Gunimg;
    String name;
    int cost;
    int maxHp;
    float width,height;

    float velocity;
    float maxSpeed;

    servApi request;
    public final String baseURL = "https://star-project-serv.herokuapp.com/";

    public GunShow(Weapon weapon, Game game, MainMenu menu, Player player, float width, float height) {
        this.width=width;
        this.height=height;
        this.weapon=weapon;
        this.game=game;
        this.menu=menu;
        this.player=player;

    }


    @Override
    public void show() {
        Gdx.input.setCatchBackKey(true);

        textureAtlas=new TextureAtlas(Gdx.files.internal("TexturePack.atlas"));
        stage = new Stage();
        skin = new Skin();
        skin.addRegions(textureAtlas);
       // (float) (Gdx.graphics.getWidth() / 14.3), (float) (Gdx.graphics.getHeight() / 1.96669)
        String name=weapon.getName();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        request = retrofit.create(servApi.class);
        if (name.equals("BlueLaser"))name= "BlueImpulseLaser";
        Gunimg = new Image(skin.getDrawable(name));
        Gunimg.setSize(width,height);
        Gunimg.setPosition((float) ((Gdx.graphics.getWidth() / Gdx.graphics.getWidth())*20), Gdx.graphics.getHeight() / 2 - Gunimg.getHeight() / 2);
        //used for textManager params

        xt = (int) (Gdx.graphics.getWidth()/3.2);
        yt = (int) ((Gunimg.getY() + Gunimg.getHeight()) * 1.1);
        stage.addActor(Gunimg);
        dyt = Gdx.graphics.getHeight() / 9;

        batch = new SpriteBatch();
        camera = new OrthographicCamera();

        textManager = new TextManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        ShList=new ShopList2(game,batch,textureAtlas,menu,player);
        if (weapon.getName().equals("BlueImpulseLaser")||weapon.getName().equals("RocketLauncher"))
        font = textManager.fontInitialize(Color.WHITE, (float) 0.7);
        else
            font = textManager.fontInitialize(Color.WHITE, 1);
        font1 = textManager.fontInitialize(Color.WHITE, 1);
        Toast.ToastFactory toastFactory = new Toast.ToastFactory.Builder()
                .font(font1)
                .build();
        toast = toastFactory.create("Successfully bought" , Toast.Length.LONG);
        toast1 = toastFactory.create("Already bought" , Toast.Length.LONG);
        toast2=toastFactory.create("Not enough money!" , Toast.Length.LONG);


        //Buy button
        BuStyle = new Button.ButtonStyle();
        BuStyle.up = skin.getDrawable("Buy-up");
        BuStyle.down = skin.getDrawable("Buy-down");
        Buy = new StageForButton(BuStyle,(int) ( Gdx.graphics.getWidth()- Gunimg.getHeight()), (int) ((Gdx.graphics.getHeight()) /2- Gunimg.getHeight()/2) , (int) (Gdx.graphics.getHeight()/3.6), (int) (Gdx.graphics.getHeight()/3.6));

        Buy.btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                // ShList=new ShopList(game,batch,textureAtlas,menu,player);
                if (menu.player.resources.weaponList.contains(weapon)) {
                    MakeToast1=true;
                } else {
                    if (!menu.player.resources.weaponList.contains(weapon)&& (menu.player.resources.getMoney()>=weapon.getCost())){
                        MakeToast=true;
                        int mon=menu.player.getMoney()-weapon.getCost();
                        menu.player.setMoney(mon);
                        updatePlayerMoney();
                        player.resources.weaponList.add(weapon);

                    }else MakeToast2=true;

                }


            }
        });
        //Back button
        BaStyle = new Button.ButtonStyle();
        BaStyle.up = skin.getDrawable("Back-up");
        BaStyle.down = skin.getDrawable("Back-down");
        Back = new StageForButton(BaStyle, (int) Gunimg.getX(), (int) (Gdx.graphics.getHeight()/Gdx.graphics.getHeight()), (int) (Gdx.graphics.getWidth() / 8.8), (int) (Gdx.graphics.getHeight() / 4.96));
        System.out.println("Clicker");
        Back.btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(ShList);
                System.out.println( player.resources.shipList);

            }
        });
        Image[]tube;
        tube=new Image[6];
        for (int i=0;i<6;i++){
            if (i%2!=0){
            tube[i]=new Image(skin.getDrawable("Tube"));
            tube[i].setSize((float) (Gdx.graphics.getWidth()/3.2),Gdx.graphics.getHeight()/6);
            tube[i].setPosition((float) (xt*0.9), (float)  ((yt - dyt * (i+1))));
            stage.addActor(tube[i]);}



        }



        InputMultiplexer in=new InputMultiplexer();
        in.addProcessor(Buy);
        in.addProcessor(Back);
        in.addProcessor(stage);
        Gdx.input.setInputProcessor(in);




    }

    @Override
    public void render(float delta) {
        camera.setToOrtho(false, (float) (Gdx.graphics.getWidth()/1.6), (float) (Gdx.graphics.getHeight()/1.5));
        LoginView.textrure.draw();
        LoginView.star.draw();

        stage.act(delta);
        stage.draw();
        textManager.displayMessage(batch, font, "" + weapon.getName(), xt, yt-dyt*1);
        textManager.displayMessage(batch, font, "Price: " + weapon.getCost(), xt, yt - dyt*3);
        textManager.displayMessage(batch, font, "Speed: " + weapon.getAttackSpeed(), xt, yt - dyt * 5);


        if (MakeToast==true){
            toast.render(Gdx.graphics.getDeltaTime());

        }
        if (MakeToast1==true){
            toast1.render(Gdx.graphics.getDeltaTime());


        }
        if (MakeToast2==true){
            toast2.render(Gdx.graphics.getDeltaTime());


        }
        Buy.act(delta);
        Buy.draw();
        Back.act();
        Back.draw();
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            game.setScreen(ShList);  }
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
        game.dispose();
        Buy.dispose();
        Back.dispose();
        batch.dispose();
        textureAtlas.dispose();
        skin.dispose();
        stage.dispose();
        game.dispose();
        font.dispose();
        batch.dispose();


        textureAtlas.dispose();

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
    private void updatePlayerMoney()
    {
        Call<Integer> call=request.updateMoney(player.getName(),player.getMoney());
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
