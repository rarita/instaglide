package com.raritasolutions.yandex_gallery.ui.image_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.raritasolutions.yandex_gallery.R;
import com.raritasolutions.yandex_gallery.app.Utils;

import java.util.ArrayList;
import java.util.List;


public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder> {

    private List<String> mValues;
    private Context context;
    private LayoutInflater inflater;
    private int imageDimensions;

    public ImageListAdapter(LayoutInflater inflater, Context context, Utils utils) {
        mValues = new ArrayList<>();
        this.inflater = inflater;
        this.context = context;
        this.imageDimensions = utils.getItemDimensions();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater
                .inflate(R.layout.fragment_instaimage, parent, false);
        return new ViewHolder(view);
    }
    public void setItems(List<String> images)
    {
        mValues = images;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String mItem = mValues.get(position);
        Glide.with(context).load(mItem).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;

        public ViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.imageView);
            image.getLayoutParams().height = imageDimensions;
            image.getLayoutParams().width = imageDimensions;
        }
    }
}
