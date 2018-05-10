package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.control.BattleProcessor;
import com.mygdx.game.model.BattleStatus;
import com.mygdx.game.model.Coord;
import com.mygdx.game.model.Map;
import com.mygdx.game.model.Ships.Bat;
import com.mygdx.game.model.Ships.Dakkar;
import com.mygdx.game.model.Ships.Hunter;
import com.mygdx.game.model.Ships.Mite;
import com.mygdx.game.model.Player;

import com.mygdx.game.model.Ships.Pulsate;
import com.mygdx.game.requests.servApi;
import com.mygdx.game.utils.Joystick;
import com.mygdx.game.utils.TextManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.Thread.sleep;

/**
 * Created by Sash on 27.04.2018.
 */

public class Battle implements Screen {
    TextManager textManager;
    SpriteBatch batch;
    Game game;
    TextureAtlas textureAtlas;
    public Player player;
    Coord coord;
    servApi request;
    OrthographicCamera camera;
    int counter;
    BitmapFont redFont;
    BattleStatus battleStatus;
    InputProcessor processor;

    public static float camX;
    public static float camY;
    public static float delta;
    public static float widthCamera;
    public static float heightCamera;
    int mapWidth;
    int mapHeight;
    public Joystick joystick;
    final public float AspectRatio;
    public final String baseURL = "https://star-project-serv.herokuapp.com/";
    Map classicMap;
    public Battle(SpriteBatch batch, Game game, TextureAtlas textureAtlas,BattleStatus battleStatus) {
        this.batch = batch;
        this.game = game;
        this.textureAtlas = textureAtlas;
        this.battleStatus=battleStatus;
        AspectRatio=(float)Gdx.graphics.getWidth()/Gdx.graphics.getHeight();
        mapWidth=1000;
        mapHeight=600;
        widthCamera=220;
        heightCamera=220/AspectRatio;
    }

    @Override
    public void show() {

        classicMap=new Map(batch,textureAtlas.findRegion("ClassicSpace"),mapWidth,mapHeight);
        player = new Player("unk", 1000, new Hunter(textureAtlas, 150, 300));
        player.generateName();

        camera=new OrthographicCamera(widthCamera,heightCamera);
        camera.position.set(new Vector3(player.getShip().getX(),player.getShip().getX(),0));
        coord = new Coord(20, 30);
        counter = 0;
        joystick=new Joystick(batch,0,10,textureAtlas.findRegion("Dj1p1"),textureAtlas.findRegion("Dj1p2"));

        textManager = new TextManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        redFont=textManager.fontInitialize(Color.RED,0.1f);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        request = retrofit.create(servApi.class);
        processor=new BattleProcessor(joystick);
        Gdx.input.setInputProcessor(processor);
        player.getShip().setRotation(270);


    }

    @Override
    public void render(float delta) {


        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.delta=delta;
        //getCoord();
        /*if ((coord.getX() != null) && (coord.getY() != null)) {
            player.getShip().setPosition(coord.getX(), coord.getY());

        }*/
        //System.out.println(coord.getX() + " " + coord.getY() + " " + "count:" + counter + "number:" + battleStatus.getNumber());

        camera.position.x=player.getShip().getX();
        camera.position.y=player.getShip().getY();
        camX =camera.position.x;
        camY =camera.position.y;
        camera.update();
        System.out.println("x="+joystick.getVector().x+" y="+joystick.getVector().y+" shipX:"+player.getShip().getX()+
                " shipY"+player.getShip().getY());
        //System.out.println(" SpeedX: "+player.getShip().getSpeedX()+"SpeedY: "+player.getShip().getSpeedY());

        batch.setProjectionMatrix(camera.combined);
        classicMap.draw();
        player.getShip().setMovementVector(joystick.getVector());
        player.getShip().move(classicMap);
        player.getShip().draw(batch);
        if(player.getShip().getIsShipInRedZone()){
            batch.begin();
            batch.draw(textureAtlas.findRegion("RedZoneAttention"),camX-widthCamera/9,camY-heightCamera/2,70,20);
            batch.end();
        }
        joystick.update(BattleProcessor.offsetX,BattleProcessor.offsetY,BattleProcessor.offsetDynamicX,BattleProcessor.offsetDynamicY);//компенсирует смещение камеры смещением джойстика

        joystick.draw();


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



    private void getCoord() {


        Call<Coord> call = request.get(battleStatus.getNumber(), "10", "150");

        call.enqueue(new Callback<Coord>() {

            @Override
            public void onResponse(Call<Coord> call, Response<Coord> response) {
                //coord=response.body();
                counter++;
                coord.setX(Integer.valueOf(response.body().getX()));
                coord.setY(Integer.valueOf(response.body().getY()));
            }

            @Override
            public void onFailure(Call<Coord> call, Throwable t) {

            }
        });

    }


}
