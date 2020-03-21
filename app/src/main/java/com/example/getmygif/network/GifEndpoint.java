package com.example.getmygif.network;

import com.example.getmygif.models.GifResponse;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface GifEndpoint {
    @GET("/v1/gifs/search")
    Single<GifResponse> search(@Query("api_key") String apiKey, @Query("q") String search, @Query("limit") int num);

    @GET("user/get")
    Single<GifResponse> get(@QueryMap Map<String, String> query);

}
