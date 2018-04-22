package com.raritasolutions.yandex_gallery.ui.image_list;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.raritasolutions.yandex_gallery.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder> {

    private List<String> mValues;
    private Context context;
    private LayoutInflater inflater;

    public ImageListAdapter(LayoutInflater inflater, Context context) {
        mValues = new ArrayList<>();
        for (int i = 0; i < 25; i++) mValues.add("http://s.mediasole.ru/images/75/75506/d89e2a91d9932fef64ac532f756cbd06.jpg");
        this.inflater = inflater;
        this.context = context;
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
        }
    }
}
