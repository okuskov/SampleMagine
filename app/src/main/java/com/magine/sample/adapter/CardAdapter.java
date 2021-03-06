package com.magine.sample.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.magine.sample.R;
import com.magine.sample.model.Video;
import com.magine.sample.view.VideoPlayerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * List adapter with view holder class
 * Binding raw data to particular UI controls by rows
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private List<Video> mItems;

    public CardAdapter() {
        super();
        mItems = new ArrayList<>();
    }

    public void addData(List<Video> items) {
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_video, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final Context context = viewHolder.itemView.getContext();
        final Video item = mItems.get(i);

        Glide.with(context)
                .load(item.getThumbUrl())
                .placeholder(R.drawable.default_placeholder)
                .error(R.drawable.default_placeholder)
                .crossFade()
                .into(viewHolder.thumb);
        viewHolder.title.setText(item.getTitle());
        viewHolder.studio.setText(item.getStudio());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!item.getSources().isEmpty()) {
                    Intent intent = new Intent(context, VideoPlayerActivity.class);
                    intent.putExtra(VideoPlayerActivity.VIDEO_SOURCE, item.getSources().get(0));
                    context.startActivity(intent);
                } else {
                    // list of source is empty
                    // do nothing here or show notification message to user
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView thumb;
        public TextView title;
        public TextView studio;

        public ViewHolder(View itemView) {
            super(itemView);
            thumb = (ImageView) itemView.findViewById(R.id.imgThumb);
            title = (TextView) itemView.findViewById(R.id.txtTitle);
            studio = (TextView) itemView.findViewById(R.id.txtStudio);
        }
    }
}