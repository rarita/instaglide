package com.raritasolutions.yandex_gallery.ui.image_list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.raritasolutions.yandex_gallery.R;
import com.raritasolutions.yandex_gallery.app.App;

import javax.inject.Inject;


public class ImageListFragment extends MvpFragment<ImageListView,ImageListPresenterImpl> implements ImageListView {

    // Injection fields
    @Inject
    ImageListPresenterImpl imageListPresenter;
    @Inject
    GridLayoutManager gridLayoutManager;
    @Inject
    ImageListAdapter imageListAdapter;

    public ImageListFragment() {
        App.getInstance().getImageListComponent().inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public ImageListPresenterImpl createPresenter() {
        return imageListPresenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_list, container, false);
        // Set the adapter
        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(imageListAdapter);
        }
        return view;
    }

    @Override
    public void updateData() {

    }

}