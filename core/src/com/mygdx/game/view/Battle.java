package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.model.BattleStatus;
import com.mygdx.game.model.Coord;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.Ship;
import com.mygdx.game.model.Ships.Mite;
import com.mygdx.game.requests.servApi;
import com.mygdx.game.utils.TextManager;

import java.io.IOException;

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
    BitmapFont blueFont;
    BattleStatus battleStatus;
    final public  float AspectRatio;
    public final String baseURL = "https://star-project-serv.herokuapp.com/";

    public Battle(SpriteBatch batch, Game game, TextureAtlas textureAtlas,BattleStatus battleStatus) {
        this.batch = batch;
        this.game = game;
        this.textureAtlas = textureAtlas;
        this.battleStatus=battleStatus;
        AspectRatio=Gdx.graphics.getWidth()/Gdx.graphics.getHeight();
    }

    @Override
    public void show() {


        player = new Player("unk", 1000, new Mite(textureAtlas.findRegion("Mite"), 200, 300,5,5/AspectRatio));
        player.generateName();
        camera=new OrthographicCamera(30,30/AspectRatio);
        camera.position.set(new Vector3(player.getShip().getX(),player.getShip().getX(),0));
        coord = new Coord(20, 30);
        counter = 0;

        textManager = new TextManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        blueFont=textManager.fontInitialize(Color.BLUE,30);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        request = retrofit.create(servApi.class);




    }

    @Override
    public void render(float delta) {


        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //getCoord();
        /*if ((coord.getX() != null) && (coord.getY() != null)) {
            player.getShip().setPosition(coord.getX(), coord.getY());

        }*/
        System.out.println(coord.getX() + " " + coord.getY() + " " + "count:" + counter + "number:" + battleStatus.getNumber());

       camera.position.x=player.getShip().getX();
        camera.position.y=player.getShip().getY();
        camera.update();
        System.out.println("x="+camera.position.x+" y="+camera.position.y);
        batch.setProjectionMatrix(camera.combined);
        player.getShip().draw(batch);

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
