package com.magine.sample.view;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

import com.magine.sample.R;

/**
 * Video player fullscreen activity
 *
 * TODO: we also can add UI controls (play, pause, stop) and pause video while changing phone orientation
 */
public class VideoPlayerActivity extends AppCompatActivity {

    public final static String VIDEO_SOURCE = "video_source";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplayer);

        String source = getIntent().getStringExtra(VIDEO_SOURCE);
        if (source == null) {
            // TODO: we also can send back message through onActivityResult()
            finish();
            return;
        }
        setupVideoPlayer(Uri.parse(source));
    }

    /**
     * Setup and play video on screen
     * @param source Uri
     */
    private void setupVideoPlayer(Uri source) {
        final VideoView videoView = (VideoView) findViewById(R.id.videoView);

        videoView.setVideoURI(source);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                videoView.start();
            }
        });
    }
}
