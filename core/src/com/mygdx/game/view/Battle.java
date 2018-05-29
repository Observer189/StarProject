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
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.control.BattleProcessor;
import com.mygdx.game.model.BattleStatus;
import com.mygdx.game.model.Coord;
import com.mygdx.game.model.Map;
import com.mygdx.game.model.Maps.ClassicSpace;
import com.mygdx.game.model.Player;

import com.mygdx.game.model.Ships.Pulsate;
import com.mygdx.game.requests.servApi;
import com.mygdx.game.utils.ButtonForProcessor;
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
    Player player;
    Player enemy;
    Coord coord;
    servApi request;
    OrthographicCamera camera;
    int counter;
    BitmapFont redFont;
    BattleStatus battleStatus;
    InputProcessor processor;
    MainMenu mainMenu;
    public static float camX;
    public static float camY;
    public static float delta;
    public static float widthCamera;
    public static float heightCamera;
    long ping;
    long startTime;
    long estimatedTime;

    int mapWidth;
    int mapHeight;
    public Joystick joystick;
    ButtonForProcessor turnLeft;
    ButtonForProcessor turnRight;
    boolean getCoordIsFinished;
    final public float AspectRatio;
    public final String baseURL = "https://star-project-serv.herokuapp.com/";
    Map classicMap;
    Screen endBattle;

    public Battle(SpriteBatch batch, Game game, TextureAtlas textureAtlas,BattleStatus battleStatus,Player player,Player enemy,MainMenu mainMenu) {
        this.mainMenu=mainMenu;
        this.batch = batch;
        this.game = game;
        this.textureAtlas = textureAtlas;
        this.battleStatus=battleStatus;
        this.player = player;
        this.enemy=enemy;
        AspectRatio=(float)Gdx.graphics.getWidth()/Gdx.graphics.getHeight();
        mapWidth=1000;
        mapHeight=600;
        widthCamera=220;
        heightCamera=220/AspectRatio;
        getCoordIsFinished=false;
        ping =0;

    }

    @Override
    public void show() {



        classicMap=Map.generateMap(batch,textureAtlas);

        //enemy = new Player(battleStatus.getName(), new Pulsate(textureAtlas, 0, 0));
        if(battleStatus.getPositionNumber()==1)
        {
            player.getCurrentShip().setPosition(200,400);
            player.getCurrentShip().setRotation(270);
            enemy.getCurrentShip().setPosition(800,200);
            enemy.getCurrentShip().setRotation(90);
        }
        else if (battleStatus.getPositionNumber()==2)
        {
            player.getCurrentShip().setPosition(800,200);
            player.getCurrentShip().setRotation(90);
            enemy.getCurrentShip().setPosition(200,400);
            enemy.getCurrentShip().setRotation(270);
        }




        camera=new OrthographicCamera(widthCamera,heightCamera);
        camera.position.set(new Vector3(player.getCurrentShip().getX(),player.getCurrentShip().getX(),0));
        camX =camera.position.x;
        camY =camera.position.y;
        coord = new Coord(0f,0f,0f);
        counter = 0;
        joystick=new Joystick(batch,0,10,textureAtlas.findRegion("Dj1p1"),textureAtlas.findRegion("Dj1p2"));
        turnLeft=new ButtonForProcessor(batch,camX+widthCamera/5,camY,20,20,textureAtlas.findRegion("TurnLeft"));
        turnRight=new ButtonForProcessor(batch,camX+widthCamera/5+30,camY,20,20,textureAtlas.findRegion("TurnRight"));

        textManager = new TextManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        redFont=textManager.fontInitialize(Color.RED,0.1f);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        request = retrofit.create(servApi.class);
        processor=new BattleProcessor(joystick,player.getCurrentShip());
        Gdx.input.setInputProcessor(processor);



        getCoord();
    }

    @Override
    public void render(float delta) {


        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.delta=delta;
        if(getCoordIsFinished) {
            getCoord();
        }
        /*if ((coord.getX() != null) && (coord.getY() != null)) {
            player.getCurrentShip().setPosition(coord.getX(), coord.getY());

        }*/
        //System.out.println(coord.getX() + " " + coord.getY() + " " + "count:" + counter + "number:" + battleStatus.getNumber());

        camera.position.x=player.getCurrentShip().getX();
        camera.position.y=player.getCurrentShip().getY();
        camX =camera.position.x;
        camY =camera.position.y;
        turnLeft.setX(camX+widthCamera/5);
        turnLeft.setY(camY-heightCamera/3);
        turnRight.setX(camX+widthCamera/5+30);
        turnRight.setY(camY-heightCamera/3);
        camera.update();
        //System.out.println("x="+coord.getX()+"y="+coord.getY());

        //System.out.println(" Player: "+player.getName()+"Enemy: "+enemy.getName());

        batch.setProjectionMatrix(camera.combined);
        classicMap.draw();
        turnLeft.draw();
        turnRight.draw();
        player.getCurrentShip().act(enemy.getCurrentShip(),classicMap,joystick.getVector());
        enemy.getCurrentShip().act(player.getCurrentShip(),classicMap,new Vector2(0,0));
        enemy.getCurrentShip().setRotation(coord.getRotation());

        player.getCurrentShip().draw(batch,textureAtlas);
        enemy.getCurrentShip().draw(batch,textureAtlas);



        if(Intersector.overlapConvexPolygons(player.getCurrentShip().getBounds(), enemy.getCurrentShip().getBounds()))
        {
            player.getCurrentShip().setCurrentHp(0);
            enemy.getCurrentShip().setCurrentHp(0);
        }
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
            joystick.setActive(false);
            player.getCurrentShip().nullify();
            endBattle=new EndBattle(player,batch,game,mainMenu,classicMap,"Failure");
            game.setScreen(endBattle);
        }
        if(!enemy.getCurrentShip().getIsAlive())
        {
            joystick.setActive(false);
            player.getCurrentShip().nullify();
            endBattle=new EndBattle(player,batch,game,mainMenu,classicMap,"Victory");
            game.setScreen(endBattle);
        }

        //System.out.println(player.getCurrentShip().getFixingPoints()[0].getWeapon().getX()+"!"+player.getCurrentShip().getFixingPoints()[0].getWeapon().getY());
       // System.out.println("Player:"+player.getCurrentShip().getCurrentHp()+"Enemy:"+enemy.getCurrentShip().getCurrentHp());

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

        startTime = System.currentTimeMillis();
        Call<Coord> call = request.get(battleStatus.getNumber(),player.getName(),enemy.getName(), player.getCurrentShip().getX(),player.getCurrentShip().getY(),player.getCurrentShip().getRotation());
        //System.out.println(player.getCurrentShip().getX()+" "+player.getCurrentShip().getY());
        //System.out.println(enemy.getName()+" "+player.getName()+" c="+counter);
        call.enqueue(new Callback<Coord>() {

            @Override
            public void onResponse(Call<Coord> call, Response<Coord> response) {
                //coord=response.body();
                counter++;
                coord.setX(Float.valueOf(response.body().getX()));
                coord.setY(Float.valueOf(response.body().getY()));
                coord.setRotation(Float.valueOf(response.body().getRotation()));
                //System.out.println(coord.getX()+" "+coord.getY());
                if(coord.getX()!=null)
                enemy.getCurrentShip().setPosition(coord.getX(),coord.getY());

                //System.out.println(enemy.getCurrentShip().getX()+" "+enemy.getCurrentShip().getY());
               while (!getCoordIsFinished)
               {
                   if(System.currentTimeMillis() - startTime>=20)
                   {
                       getCoordIsFinished=true;
                   }
               }
                //System.out.println("getCoord succes!!!");
                //getCoordIsFinished=true;
                estimatedTime = System.currentTimeMillis() - startTime;
                ping=estimatedTime;

                //System.out.println("Ping:"+ping);

            }

            @Override
            public void onFailure(Call<Coord> call, Throwable t) {
                getCoordIsFinished=true;
                //System.out.println("getCoord failure");
            }
        });

    }






}
