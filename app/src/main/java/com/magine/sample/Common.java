package com.magine.sample;

import android.content.Context;
import android.net.ConnectivityManager;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.magine.sample.service.MagineApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Common {

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public static MagineApi getApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MagineApi.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(MagineApi.class);
    }
}
