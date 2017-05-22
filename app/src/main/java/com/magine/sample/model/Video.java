package com.magine.sample.model;

import com.magine.sample.service.MagineApi;

/**
 * General model which contains videos data
 * we use it specificlly on videos list
 */
public class Video {
    private String title;
    private String studio;
    private String thumb;

    public String getTitle() {
        return title;
    }

    public String getStudio() {
        return studio;
    }

    public String getThumbUrl() {
        return MagineApi.ENDPOINT + thumb;
    }
}
