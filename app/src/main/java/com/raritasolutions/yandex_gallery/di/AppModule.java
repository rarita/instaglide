package com.raritasolutions.yandex_gallery.di;

import android.content.Context;
import android.support.annotation.NonNull;

import com.raritasolutions.yandex_gallery.app.Constants;
import com.raritasolutions.yandex_gallery.app.Utils;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rarita on 21.04.18.
 */

@Module
public class AppModule {
    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @NonNull
    @Provides
    @Singleton
    Context provideContext()
    {
        return context;
    }
    @NonNull
    @Provides
    @Singleton
    Constants provideConstants() {return new Constants(); }
    @NonNull
    @Provides
    @Singleton
    Utils provideUtils() {return new Utils(); }

}
