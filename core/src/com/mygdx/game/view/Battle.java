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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.control.BattleProcessor;
import com.mygdx.game.model.BattleStatus;
import com.mygdx.game.model.Coord;
import com.mygdx.game.model.Map;
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
    public  Player player;
    Player enemy;
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
    Screen endBattle;
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

        player = new Player("unk", new Pulsate(textureAtlas, 0, 0));
        enemy = new Player("unk", new Pulsate(textureAtlas, 0, 0));
        if(battleStatus.getPositionNumber()==1)
        {
            player.getCurrentShip().setPosition(200,300);
            player.getCurrentShip().setRotationPosition(1);
            enemy.getCurrentShip().setPosition(800,300);
            enemy.getCurrentShip().setRotationPosition(2);
        }
        else if (battleStatus.getPositionNumber()==2)
        {
            player.getCurrentShip().setPosition(800,300);
            player.getCurrentShip().setRotationPosition(2);
            enemy.getCurrentShip().setPosition(200,300);
            enemy.getCurrentShip().setRotationPosition(1);
        }
        player.generateName();



        camera=new OrthographicCamera(widthCamera,heightCamera);
        camera.position.set(new Vector3(player.getCurrentShip().getX(),player.getCurrentShip().getX(),0));
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


        endBattle=new EndBattle(player);

    }

    @Override
    public void render(float delta) {


        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.delta=delta;
        //getCoord();
        /*if ((coord.getX() != null) && (coord.getY() != null)) {
            player.getCurrentShip().setPosition(coord.getX(), coord.getY());

        }*/
        //System.out.println(coord.getX() + " " + coord.getY() + " " + "count:" + counter + "number:" + battleStatus.getNumber());

        camera.position.x=player.getCurrentShip().getX();
        camera.position.y=player.getCurrentShip().getY();
        camX =camera.position.x;
        camY =camera.position.y;
        camera.update();
        System.out.println("x="+player.getCurrentShip().getFixingPoints()[0].getWeapon().getX()+" y="+player.getCurrentShip().getFixingPoints()[0].getWeapon().getY()+" shipX:"+player.getCurrentShip().getX()+
                " shipY"+player.getCurrentShip().getY());
        //System.out.println(" SpeedX: "+player.getCurrentShip().getSpeedX()+"SpeedY: "+player.getCurrentShip().getSpeedY());

        batch.setProjectionMatrix(camera.combined);
        classicMap.draw();
        player.getCurrentShip().act(classicMap,joystick.getVector());
        enemy.getCurrentShip().act(classicMap,new Vector2(0,0));

        player.getCurrentShip().draw(batch,textureAtlas);
        enemy.getCurrentShip().draw(batch,textureAtlas);
        if(player.getCurrentShip().getIsShipInRedZone()){
            batch.begin();
            batch.draw(textureAtlas.findRegion("RedZoneAttention"),camX-widthCamera/9,camY-heightCamera/2,70,20);
            batch.end();
        }

        if(player.getCurrentShip().getCurrentHp()>0) {
            joystick.update(BattleProcessor.offsetX, BattleProcessor.offsetY, BattleProcessor.offsetDynamicX, BattleProcessor.offsetDynamicY);//компенсирует смещение камеры смещением джойстика
            joystick.draw();
        }

        if(!player.getCurrentShip().getIsAlive())
        {
            game.setScreen(endBattle);
        }


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
