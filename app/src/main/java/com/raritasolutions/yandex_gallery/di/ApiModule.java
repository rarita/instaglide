package com.raritasolutions.yandex_gallery.di;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.raritasolutions.yandex_gallery.RetrofitService;
import com.raritasolutions.yandex_gallery.app.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rarita on 21.04.18.
 */
@Module
public class ApiModule {
    @Provides
    @NonNull
    @Singleton
    RetrofitService provideRetrofitService(Constants constants)
    {
        return new Retrofit.Builder()
            .baseUrl(constants.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(RetrofitService.class);
    }

}
