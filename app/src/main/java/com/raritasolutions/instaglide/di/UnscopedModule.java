package com.raritasolutions.instaglide.di;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

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
    @NonNull
    @Provides
    CompositeDisposable provideCompositeDisposable(){return new CompositeDisposable(); }
}
