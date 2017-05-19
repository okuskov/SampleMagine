package com.magine.sample.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.magine.sample.Common;
import com.magine.sample.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ProgressBar mProgressBar;
    private TextView mTxtMessage;
    private RecyclerView mLstVideos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mProgressBar = (ProgressBar) findViewById(R.id.prgIndicator);
        mTxtMessage = (TextView) findViewById(R.id.txtMessage);
        mLstVideos = (RecyclerView) findViewById(R.id.lstVideos);

        if (!Common.isNetworkConnected(this)) {
            Snackbar.make(findViewById(android.R.id.content), R.string.err_network_connection, Snackbar.LENGTH_LONG).show();

            mTxtMessage.setVisibility(View.VISIBLE);
            mTxtMessage.setText(R.string.msg_internet_problems);
        } else {
            // ask list of videos
            Snackbar.make(findViewById(android.R.id.content), "ask list of videos", Snackbar.LENGTH_LONG).show();
        }
    }
}
