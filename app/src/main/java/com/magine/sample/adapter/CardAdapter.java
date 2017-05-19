package com.magine.sample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.magine.sample.R;
import com.magine.sample.model.Video;

import java.util.ArrayList;
import java.util.List;

/**
 * List adapter with view holder class
 * Binding raw data to particular UI controls by rows
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private List<Video> mItems;
    private Context mContext;

    public CardAdapter(Context context) {
        super();
        mContext = context;
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
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Video item = mItems.get(i);
        Glide.with(mContext)
                .load(item.getThumbUrl())
                .placeholder(R.drawable.default_placeholder)
                .error(R.drawable.default_placeholder)
                .crossFade()
                .into(viewHolder.thumb);
        viewHolder.title.setText(item.getTitle());
        viewHolder.studio.setText(item.getStudio());
        viewHolder.subtitle.setText(item.getSubTitle());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView thumb;
        public TextView title;
        public TextView studio;
        public TextView subtitle;

        public ViewHolder(View itemView) {
            super(itemView);
            thumb = (ImageView) itemView.findViewById(R.id.imgThumb);
            title = (TextView) itemView.findViewById(R.id.txtTitle);
            studio = (TextView) itemView.findViewById(R.id.txtStudio);
            subtitle = (TextView) itemView.findViewById(R.id.txtSubtitle);
        }
    }
}