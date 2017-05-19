package com.magine.sample.model;

import com.google.gson.annotations.SerializedName;

public class Video {
    private String title;
    private String subtitle;
    private String studio;
    private String thumburl;
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
        return thumburl;
    }

    public String getSmallImageUrl() {
        return mSmallImageUrl;
    }

    public String getFullImageUrl() {
        return mFullImageUrl;
    }
}
