package com.raritasolutions.instaglide.di;

import android.content.Context;
import android.support.annotation.NonNull;

import com.raritasolutions.instaglide.LocalDAO;
import com.raritasolutions.instaglide.RetrofitService;
import com.raritasolutions.instaglide.app.Constants;
import com.raritasolutions.instaglide.app.Preferences;
import com.raritasolutions.instaglide.app.Repo;
import com.raritasolutions.instaglide.app.Utils;

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
    Preferences providePreferences(Constants constants) {
        Preferences preferences = new Preferences();
        preferences.fromConstants(constants);
        return preferences;
    }

}
