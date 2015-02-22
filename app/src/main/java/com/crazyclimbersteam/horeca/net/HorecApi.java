package com.crazyclimbersteam.horeca.net;

import com.crazyclimbersteam.horeca.net.pojo.DetailItemModel;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created with Android Studio.
 * User: Vitalii Morozov
 * Date: 22.02.15
 * Time: 03:05
 */
public interface HorecApi {

    @GET("/places/")
    public void listAllPlaces(Callback<List<DetailItemModel>> callback);

    @GET("/places/placebycategorynames/{query}")
    public void filterPlaces(@Path("query") String query, Callback<List<DetailItemModel>> callback);

    @GET("/places/menubyplaceid/{id}")
    public void getMenu(@Path("id") String id, Callback<List<DetailItemModel>> callback);

}
