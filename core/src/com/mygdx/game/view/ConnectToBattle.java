package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.model.BattleStatus;
import com.mygdx.game.model.Player;
import com.mygdx.game.requests.servApi;

import java.io.IOException;

import retrofit2.Call;
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
    Player player;

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
        battleStatus=new BattleStatus(null,"add");
        player.generateName();
        getBattleNumber();

        if(battleStatus.getNumber()!=null) {
            game.setScreen(new Battle(batch, game, textureAtlas, battleStatus));
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        System.out.println(battleStatus.getNumber()+" "+battleStatus.getStatus());
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
    private void getBattleNumber() {

        Call<BattleStatus> call = request.getBattleNumber(player.getName(),battleStatus.getStatus());
        try {
            battleStatus.setBattleStatus(call.execute().body());


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
