package com.raritasolutions.instaglide.di;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;

import com.raritasolutions.instaglide.app.Constants;
import com.raritasolutions.instaglide.app.Repo;
import com.raritasolutions.instaglide.app.Utils;
import com.raritasolutions.instaglide.ui.main_activity.ToolbarViewHolder;
import com.raritasolutions.instaglide.ui.image_list.GridSpacingItemDecoration;
import com.raritasolutions.instaglide.ui.image_list.ImageListAdapter;
import com.raritasolutions.instaglide.ui.image_list.ImageListPresenterImpl;

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
    ImageListPresenterImpl provideImageListPresenter(Utils utils,
                                                     CompositeDisposable compositeDisposable,
                                                     Repo repo)
    {
        return new ImageListPresenterImpl(utils, compositeDisposable,repo);
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
    GridSpacingItemDecoration provideGridSpacingItemDecoration()
    {
        return new GridSpacingItemDecoration(false,0);
    }
    @Provides
    @NonNull
    @ImageListScope
    ToolbarViewHolder provideToolbarViewHolder(Context context)
    {
        return new ToolbarViewHolder(context);
    }
}
