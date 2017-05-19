package com.magine.sample;

import android.content.Context;
import android.net.ConnectivityManager;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.magine.sample.service.MagineApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Common used methods which could not be related to specific class
 */
public class Common {

    /**
     * Check network connection and before doing any network requests
     * TODO: we only check network connection but we also can check is internet available or not
     * @param context application context
     * @return boolean, return true if we are connected, false in other case
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    /**
     * Give us link to api manager which we should use for any API requests
     * @return MagineApi
     */
    public static MagineApi getApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MagineApi.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(MagineApi.class);
    }
}
