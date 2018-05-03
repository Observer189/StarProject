package com.mygdx.game.requests;

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
    @POST("sash")
    Call<Coord> getCoord(@Query("x") String x, @Query("y") String y);

    @GET("battle")
    Call<Coord> getBattleNumber(@Query("name")String name);

    @POST("battle/{battleNumber}")
    Call<Coord> get(@Path("battleNumber") Integer number,@Query("x") String x, @Query("y") String y);

}
