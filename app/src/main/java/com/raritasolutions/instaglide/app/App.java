package com.raritasolutions.instaglide.app;

import android.app.Application;
import android.util.Log;

import com.raritasolutions.instaglide.di.AppComponent;
import com.raritasolutions.instaglide.di.AppModule;
import com.raritasolutions.instaglide.di.DaggerAppComponent;
import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * Created by rarita on 21.04.18.
 */

public class App extends Application
{
    private static final String TAG = App.class.getSimpleName();
    private Disposable disposable;
    // Держим инстанс этого класса для доступа к Dagger Components
    private static App instance = null;
    // Инжектим это
    @Inject
    Repo repo;
    @Inject
    Preferences preferences;
    @Inject
    Constants constants;
    // Сами компоненты
    private AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        // Включаем канарейку
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        // Инициализируем модуль, содержащий 3/4 зависимостей приложения.
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this.getApplicationContext()))
                .build();
        instance = this;
        // Получаем отсюда репозитории и prefs и предзагружаем настройки
        this.appComponent.inject(this);
        // Если получается, восстанавиливаем настройки из БД, если нет - остается дефолтная реализация из констант.
        disposable = repo.getPreferences()
                .subscribe(
                        preferences1 -> preferences.fromPreferences(preferences1),
                        error -> Log.i(TAG, error.toString()));
    }

    // Отдаем инстанс
    public static App getInstance() {
        return instance;
    }

    // Getter-ы компонентов
    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        disposable.dispose();
    }
}
