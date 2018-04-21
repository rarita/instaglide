package com.raritasolutions.yandex_gallery.di;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;

import com.raritasolutions.yandex_gallery.app.Constants;
import com.raritasolutions.yandex_gallery.ui.image_list.ImageListAdapter;
import com.raritasolutions.yandex_gallery.ui.image_list.ImageListPresenterImpl;

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
    ImageListPresenterImpl provideImageListPresenter()
    {
        return new ImageListPresenterImpl();
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
    ImageListAdapter provideImageListAdapter()
    {
        return new ImageListAdapter();
    }
}
