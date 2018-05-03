package com.raritasolutions.yandex_gallery.app;

import android.app.Application;

import com.raritasolutions.yandex_gallery.di.AppComponent;
import com.raritasolutions.yandex_gallery.di.AppModule;
import com.raritasolutions.yandex_gallery.di.DaggerAppComponent;


/**
 * Created by rarita on 21.04.18.
 */

public class App extends Application
{
    // Держим инстанс этого класса для доступа к Dagger Components
    private static App instance = null;
    // Сами компоненты
    private AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this.getApplicationContext()))
                .build();
        instance = this;
    }

    // Отдаем инстанс
    public static App getInstance() {
        return instance;
    }

    // Getter-ы компонентов
    public AppComponent getAppComponent() {
        return appComponent;
    }
}
