package com.raritasolutions.yandex_gallery.di;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;

import com.raritasolutions.yandex_gallery.RetrofitService;
import com.raritasolutions.yandex_gallery.app.Constants;
import com.raritasolutions.yandex_gallery.app.Utils;
import com.raritasolutions.yandex_gallery.ui.image_list.ImageListAdapter;
import com.raritasolutions.yandex_gallery.ui.image_list.ImageListPresenterImpl;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rarita on 21.04.18.
 */

@Module
public class ImageListModule {

    @Provides
    @NonNull
    @ImageListScope
    ImageListPresenterImpl provideImageListPresenter(RetrofitService retrofitService, Constants constants, Utils utils)
    {
        return new ImageListPresenterImpl(retrofitService, constants, utils);
    }
    @Provides
    @NonNull
    @ImageListScope
    GridLayoutManager provideGridLayoutManager(Context context, Constants constants)
    {
        return new GridLayoutManager(context, constants.columnCount);
    }
    @Provides
    @NonNull
    @ImageListScope
    ImageListAdapter provideImageListAdapter(LayoutInflater inflater, Context context, Utils utils)
    {
        return new ImageListAdapter(inflater, context, utils);
    }
}
