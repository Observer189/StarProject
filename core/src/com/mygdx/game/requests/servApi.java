package com.mygdx.game.requests;

import com.mygdx.game.model.BattleStatus;
import com.mygdx.game.model.Coord;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Sash on 01.05.2018.
 */

public interface servApi {

    @GET("battle")
    Call<BattleStatus> getBattleNumber(@Query("name")String name,@Query("status") String status);

    @POST("battle/{battleNumber}")
    Call<Coord> get(@Path("battleNumber") Integer number,@Query("name")String name,@Query("enemyName")String enemyName,@Query("x") Float x, @Query("y") Float y);

}
