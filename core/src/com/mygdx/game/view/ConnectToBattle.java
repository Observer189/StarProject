package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.control.ConnectToBattleProcessor;
import com.mygdx.game.model.BattleStatus;
import com.mygdx.game.model.Player;
import com.mygdx.game.requests.servApi;
import com.mygdx.game.utils.TextManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sash on 03.05.2018.
 */

public class ConnectToBattle implements Screen {
    SpriteBatch batch;
    Game game;
    TextureAtlas textureAtlas;
    servApi request;
    BattleStatus battleStatus;
    public static Player player;
    TextManager textManager;
    int counter;
    boolean getBattleIsFinished;
    InputProcessor processor;
    BitmapFont blueFont;
    public final String baseURL = "https://star-project-serv.herokuapp.com/";
    public ConnectToBattle(SpriteBatch batch, Game game, TextureAtlas textureAtlas,Player player) {
        this.batch = batch;
        this.game = game;
        this.textureAtlas = textureAtlas;
        this.player=new Player();
    }
    @Override
    public void show() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        request = retrofit.create(servApi.class);
        processor=new ConnectToBattleProcessor();
        Gdx.input.setInputProcessor(processor);
        textManager=new TextManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        blueFont=textManager.fontInitialize(Color.BLUE,30);
        battleStatus=new BattleStatus(null,null,"add");
        player.generateName();
        counter=0;
        getBattleIsFinished=false;
        //getBattleNumber();

        //if((battleStatus.getNumber()!=null)&&(battleStatus.getStatus().equals("ready"))) {
            battleStatus.setNumber(1);//убрать
            game.setScreen(new Battle(batch, game, textureAtlas, battleStatus));
        //}
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(counter==100)counter=0;
        counter++;
        if((battleStatus.getStatus().equals("wait"))&&(getBattleIsFinished))
        {
            getBattleIsFinished=false;
            getBattleNumber();
        }
        System.out.println(battleStatus.getNumber()+" "+battleStatus.getStatus()+"QueueSize:"+battleStatus.getQueueSize());
        if(battleStatus.getStatus().equals("add"))
        {
            textManager.displayMessage(batch,blueFont,"Connection to server...",300,300);
        }
        if(battleStatus.getStatus().equals("wait"))
        {
            textManager.displayMessage(batch,blueFont,"Search enemy...",300,300);
            textManager.displayMessage(batch,blueFont,"Players in queue:"+" "+battleStatus.getQueueSize(),300,350);
        }
        if(battleStatus.getNumber()!=null) {
            game.setScreen(new Battle(batch, game, textureAtlas, battleStatus));
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
        batch.dispose();
        textureAtlas.dispose();
        game.dispose();
    }
    private void getBattleNumber() {

        /*Call<BattleStatus> call = request.getBattleNumber(player.getName(),battleStatus.getStatus());
        try {
            battleStatus.setBattleStatus(call.execute().body());


        } catch (IOException e) {
            e.printStackTrace();
        }*/
        Call<BattleStatus> call = request.getBattleNumber(player.getName(),battleStatus.getStatus());
        call.enqueue(new Callback<BattleStatus>() {
            @Override
            public void onResponse(Call<BattleStatus> call, Response<BattleStatus> response) {
                battleStatus.setBattleStatus(response.body());
                getBattleIsFinished=true;
            }

            @Override
            public void onFailure(Call<BattleStatus> call, Throwable t) {
                getBattleIsFinished=true;
            }
        });

    }
}
