package com.magine.sample.model;

import java.util.List;

/**
 * Category model which contains list of video objects
 */
public class Category {
    private String name;
    private List<Video> videos;

    public String getName() {
        return name;
    }

    public List<Video> getVideoList() {
        return videos;
    }

}
