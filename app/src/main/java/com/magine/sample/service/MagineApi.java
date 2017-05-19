package com.magine.sample.service;

import com.magine.sample.model.VideosResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MagineApi {

    String ENDPOINT = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/";

    @GET("videos-enhanced-c.json")
    Observable<VideosResponse> getVideoList();
}
