package com.magine.sample.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.magine.sample.Common;
import com.magine.sample.R;
import com.magine.sample.adapter.CardAdapter;
import com.magine.sample.model.VideosResponse;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private TextView mTxtMessage;
    private RecyclerView mLstVideos;
    private CardAdapter mCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mProgressBar = (ProgressBar) findViewById(R.id.prgIndicator);
        mTxtMessage = (TextView) findViewById(R.id.txtMessage);

        mCardAdapter = new CardAdapter(this);

        mLstVideos = (RecyclerView) findViewById(R.id.lstVideos);
        mLstVideos.setHasFixedSize(true);
        mLstVideos.setLayoutManager(new LinearLayoutManager(this));
        mLstVideos.setAdapter(mCardAdapter);

        if (!Common.isNetworkConnected(this)) {
            Snackbar.make(findViewById(android.R.id.content), R.string.err_network_connection, Snackbar.LENGTH_LONG).show();

            mTxtMessage.setVisibility(View.VISIBLE);
            mTxtMessage.setText(R.string.msg_internet_problems);
        } else {
            fetchVideoList();
        }
    }

    private void fetchVideoList() {
        mProgressBar.setVisibility(View.VISIBLE);

        Common.getApi().getVideoList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        mProgressBar.setVisibility(View.GONE);
                    }
                })
                .subscribe(new Observer<VideosResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onComplete() {}

                    @Override
                    public void onNext(@NonNull VideosResponse response) {
                        if (!response.getCategories().isEmpty()) {
                            mCardAdapter.clear();
                            mCardAdapter.addData(response.getCategories().get(0).getVideoList());
                            mLstVideos.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mTxtMessage.setVisibility(View.VISIBLE);
                        mTxtMessage.setText(R.string.err_fetching_data);
                    }
                });
    }
}
