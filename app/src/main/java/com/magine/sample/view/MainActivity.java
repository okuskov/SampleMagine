package com.magine.sample.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.magine.sample.Common;
import com.magine.sample.R;
import com.magine.sample.adapter.CardAdapter;
import com.magine.sample.model.Category;
import com.magine.sample.model.Video;
import com.magine.sample.model.VideosResponse;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

/**
 * Main application activity
 *
 * TODO: we also can manage savedInstancestate and avoid reloading screen whili changing orientation
 * TODO: also we can create some king of presenter and fetchVideoList() method move to it (MVP architecture)
 */
public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private ImageView mImgError;
    private RecyclerView mLstVideos;
    private CardAdapter mCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mProgressBar = (ProgressBar) findViewById(R.id.prgIndicator);
        mImgError = (ImageView) findViewById(R.id.imgError);

        mCardAdapter = new CardAdapter();

        mLstVideos = (RecyclerView) findViewById(R.id.lstVideos);
        mLstVideos.setHasFixedSize(true);
        mLstVideos.setLayoutManager(new LinearLayoutManager(this));
        mLstVideos.setAdapter(mCardAdapter);

        fetchVideoList();
    }

    /**
     * Show error message on the bottom of the screen with ability of reload last action (fetching video list)
     * @param message link to string message in resource file
     */
    private void showMessage(@StringRes int message) {
        mImgError.setVisibility(View.VISIBLE);

        final Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(R.string.reload, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
                fetchVideoList();
            }
        });
        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
    }

    /**
     * Trying to do network connection and fetch list of video
     * If list was received it pass them to list adapter otherwise show the error message
     */
    private void fetchVideoList() {
        if (!Common.isNetworkConnected(this)) {
            showMessage(R.string.msg_internet_problems);
            return ;
        }

        mImgError.setVisibility(View.GONE);
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
                        List<Category> categoryList = response.getCategories();
                        if (categoryList.isEmpty()) {
                            showMessage(R.string.err_empty_list);
                            return;
                        }

                        List<Video> videoList = categoryList.get(0).getVideoList();
                        if (videoList.isEmpty()) {
                            showMessage(R.string.err_empty_list);
                            return;
                        }

                        mCardAdapter.clear();
                        mCardAdapter.addData(videoList);
                        mLstVideos.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        showMessage(R.string.err_fetching_data);
                    }
                });
    }
}
