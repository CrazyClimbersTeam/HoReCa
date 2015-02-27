package com.crazyclimbersteam.horeca.fragment.detail;

import com.crazyclimbersteam.horeca.net.pojo.DetailItemModel;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Марковка on 21.02.2015.
 */
public interface IRequest {

    @GET("/places")
    List<DetailItemModel> listPlaces();

    @GET("/places/")
    void listPlaces(Callback <List<DetailItemModel>> callback);

   /* @GET("/places/{id}")
    String getId(@Path("id") String id);*/
}
