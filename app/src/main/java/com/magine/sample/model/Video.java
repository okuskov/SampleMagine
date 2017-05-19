package com.magine.sample.model;

import com.google.gson.annotations.SerializedName;
import com.magine.sample.service.MagineApi;

/**
 * General model which contains videos data
 * we use it specificlly on videos list
 */
public class Video {
    private String title;
    private String subtitle;
    private String studio;
    private String thumb;
    @SerializedName("image-480x270")
    private String mSmallImageUrl;
    @SerializedName("image-780x1200")
    private String mFullImageUrl;

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subtitle;
    }

    public String getStudio() {
        return studio;
    }

    public String getThumbUrl() {
        return MagineApi.ENDPOINT + thumb;
    }

    public String getSmallImageUrl() {
        return MagineApi.ENDPOINT + mSmallImageUrl;
    }

    public String getFullImageUrl() {
        return MagineApi.ENDPOINT + mFullImageUrl;
    }
}
