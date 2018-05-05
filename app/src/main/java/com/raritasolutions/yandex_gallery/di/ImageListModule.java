package com.raritasolutions.yandex_gallery.di;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;

import com.raritasolutions.yandex_gallery.RetrofitService;
import com.raritasolutions.yandex_gallery.app.Constants;
import com.raritasolutions.yandex_gallery.app.Repo;
import com.raritasolutions.yandex_gallery.app.Utils;
import com.raritasolutions.yandex_gallery.ui.ToolbarViewHolder;
import com.raritasolutions.yandex_gallery.ui.image_list.GridSpacingItemDecoration;
import com.raritasolutions.yandex_gallery.ui.image_list.ImageListAdapter;
import com.raritasolutions.yandex_gallery.ui.image_list.ImageListPresenterImpl;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by rarita on 21.04.18.
 */

@Module
public class ImageListModule {

    @Provides
    @NonNull
    @ImageListScope
    ImageListPresenterImpl provideImageListPresenter(RetrofitService retrofitService,
                                                     Constants constants,
                                                     Utils utils,
                                                     CompositeDisposable compositeDisposable,
                                                     Repo repo)
    {
        return new ImageListPresenterImpl(retrofitService, constants, utils, compositeDisposable,repo);
    }
    @Provides
    @NonNull
    @ImageListScope
    GridLayoutManager provideGridLayoutManager(Context context, Constants constants)
    {
        return new GridLayoutManager(context, constants.COLUMN_COUNT);
    }
    @Provides
    @NonNull
    @ImageListScope
    ImageListAdapter provideImageListAdapter(LayoutInflater inflater, Context context, Utils utils)
    {
        return new ImageListAdapter(inflater, context, utils);
    }
    @Provides
    @NonNull
    @ImageListScope
    GridSpacingItemDecoration provideGridSpacingItemDecoration(Constants constants)
    {
        return new GridSpacingItemDecoration(constants.COLUMN_COUNT,constants.LIST_SPACING,false,0);
    }
    @Provides
    @NonNull
    @ImageListScope
    ToolbarViewHolder provideToolbarViewHolder(Context context)
    {
        return new ToolbarViewHolder(context);
    }
}
