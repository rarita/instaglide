package com.raritasolutions.yandex_gallery.ui.image_list;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.raritasolutions.yandex_gallery.R;
import com.raritasolutions.yandex_gallery.app.App;
import com.raritasolutions.yandex_gallery.app.Preferences;
import com.raritasolutions.yandex_gallery.app.Utils;
import com.raritasolutions.yandex_gallery.di.ImageListModule;
import com.raritasolutions.yandex_gallery.model.Counts;
import com.raritasolutions.yandex_gallery.model.LoginData;
import com.raritasolutions.yandex_gallery.ui.ToolbarViewHolder;

import org.w3c.dom.Text;

import java.util.List;

import javax.inject.Inject;


public class ImageListFragment extends MvpFragment<ImageListView,ImageListPresenterImpl> implements ImageListView {

    private final String TAG = ImageListFragment.class.getSimpleName();
    // Injection fields
    @Inject
    ImageListPresenterImpl imageListPresenter;
    @Inject
    ToolbarViewHolder toolbarViewHolder;
    @Inject
    GridLayoutManager gridLayoutManager;
    @Inject
    ImageListAdapter imageListAdapter;
    @Inject
    GridSpacingItemDecoration decoration;
    @Inject
    Preferences preferences;
    @Inject
    Utils utils;

    public ImageListFragment() {
        App.getInstance().getAppComponent().imageListComponent(new ImageListModule()).inject(this);
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
        // Set span count and spacing depending on orientation of the device
        final int itemCount;
        final int listSpacing;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            itemCount = preferences.getColumn_count_portrait();
            listSpacing = preferences.getSpacing_portrait();
        }
        else
        {
            itemCount = preferences.getColumn_count_landscape();
            listSpacing = preferences.getSpacing_landscape();
        }
        gridLayoutManager.setSpanCount(itemCount);
        decoration.setSpanCount(itemCount);
        decoration.setSpacing(listSpacing);
        utils.updateMetrics();
        imageListAdapter.setItemDimensions(utils.getItemDimensions(itemCount));
        // Set the adapter
        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(imageListAdapter);
            recyclerView.addItemDecoration(decoration);
            recyclerView.setHasFixedSize(true);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        toolbarViewHolder.bind(getActivity());
    }

    @Override
    public void updateList(List<String> images) {

        if (imageListAdapter != null)
        {
            imageListAdapter.setItems(images);
            imageListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void updateToolbar(LoginData loginData) {
        if (toolbarViewHolder.isBound())
            toolbarViewHolder.bindData(loginData);
    }

}
