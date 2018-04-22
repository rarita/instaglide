package com.raritasolutions.yandex_gallery.di;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rarita on 21.04.18.
 */
@Module
public class UnscopedModule {
    @NonNull
    @Provides
    LayoutInflater provideLayoutInflater(Context context)
    {
        return LayoutInflater.from(context);
    }
}
