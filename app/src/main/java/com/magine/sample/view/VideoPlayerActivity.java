package com.magine.sample.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Video player fullscreen activity
 */
public class VideoPlayerActivity extends AppCompatActivity {

    public final static String VIDEO_SOURCE = "video_source";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_videoplayer);

        String source = getIntent().getStringExtra(VIDEO_SOURCE);
        Log.i("foo", "source: " + source);
    }
}
