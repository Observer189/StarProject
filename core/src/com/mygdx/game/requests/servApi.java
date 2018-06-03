package com.mygdx.game.requests;

import com.mygdx.game.ServModels.ServPlayer;
import com.mygdx.game.ServModels.ServShip;
import com.mygdx.game.model.BattleStatus;
import com.mygdx.game.model.Coord;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Sash on 01.05.2018.
 */

public interface servApi {

    /*@GET("battle")
    Call<BattleStatus> getBattleNumber(@Query("name")String name,@Query("shipName") String shipName,@Query("status") String status,@Query("requestTime") long requestTime);*/
    @POST("battle")
    Call<BattleStatus> getBattleNumber(@Query("name")String name, @Query("ship") ServShip ship, @Query("status") String status);

    @POST("battle/{battleNumber}")
    Call<Coord> get(@Path("battleNumber") Integer number,@Query("name")String name,@Query("enemyName")String enemyName,@Query("x") Float x, @Query("y") Float y,@Query("rotation") Float rotation);

    @PUT("players/create")
    Call<Integer> createPlayer(@Query("name") String name,@Query("password") String password,@Query("money")int money);

    @POST("players/update")
    Call<Integer> updateMoney(@Query("name")String name,@Query("money") int money);

    @GET("players/getPlayer")
    Call<ServPlayer> getPlayer(@Query("name") String name);

}
