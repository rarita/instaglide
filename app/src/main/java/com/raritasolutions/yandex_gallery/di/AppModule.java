package com.raritasolutions.yandex_gallery.di;

import android.content.Context;
import android.support.annotation.NonNull;

import com.raritasolutions.yandex_gallery.LocalDAO;
import com.raritasolutions.yandex_gallery.RetrofitService;
import com.raritasolutions.yandex_gallery.app.Constants;
import com.raritasolutions.yandex_gallery.app.Preferences;
import com.raritasolutions.yandex_gallery.app.Repo;
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
    Constants provideConstants() {return new Constants();}
    @NonNull
    @Provides
    @Singleton
    Utils provideUtils(Constants constants) {return new Utils(constants); }
    @NonNull
    @Provides
    @Singleton
    Repo provideRepo(RetrofitService retrofitService, Constants constants, LocalDAO localDAO){
        return new Repo(retrofitService,localDAO,constants);
    }
    @NonNull
    @Provides
    @Singleton
    Preferences providePreferences(Constants constants) {return new Preferences(constants);}

}
