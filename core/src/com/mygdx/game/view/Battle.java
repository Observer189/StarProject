package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.model.Coord;
import com.mygdx.game.model.Player;
import com.mygdx.game.requests.servApi;
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
    Coord coord;
    servApi request;
    Coord c;
    int counter;
    Integer battleNumber;
    static Integer number;
    public final String baseURL = "https://star-project-serv.herokuapp.com/";
    public Battle(SpriteBatch batch, Game game,TextureAtlas textureAtlas)
    {
        this.batch=batch;
        this.game=game;
        this.textureAtlas=textureAtlas;
    }
    @Override
    public void show() {
        player = new Player();
        player.generateName();
        coord = new Coord(20, 30);
        counter = 0;

        textManager = new TextManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        request = retrofit.create(servApi.class);

            getBattleNumber();


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        getCoord();
        if((coord.getX()!=null)&&(coord.getY()!=null))
        {
            counter++;
            textManager.displayMessage(batch, player.getName() + " " + coord.getX().toString() + " " + coord.getY().toString(), Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        }
        System.out.println(coord.getX()+" "+coord.getY()+" "+"count:"+counter+"number:"+battleNumber);
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

    public void setBattleNumber(Integer battleNumber) {
        this.battleNumber = battleNumber;
    }

    private void getCoord()
    {

        c=new Coord();

        Call<Coord> call=request.getCoord("10","150");

        call.enqueue(new Callback<Coord>()
        {

            @Override
            public void onResponse(Call<Coord> call, Response<Coord> response) {
                //coord=response.body();
                coord.setX(Integer.valueOf(response.body().getX()));
                coord.setY(Integer.valueOf(response.body().getY()));
            }

            @Override
            public void onFailure(Call<Coord> call, Throwable t) {

            }
        });

    }
    private Integer getBattleNumber()
    {

        Call<Coord> call=request.getBattleNumber(player.getName());
        call.enqueue(new Callback<Coord>() {
            @Override
            public void onResponse(Call<Coord> call, Response<Coord> response) {

                setBattleNumber(number=response.body().getX());

            }

            @Override
            public void onFailure(Call<Coord> call, Throwable t) {
               setBattleNumber(-3);
               getBattleNumber();

            }
        });
        return number;
    }
}
