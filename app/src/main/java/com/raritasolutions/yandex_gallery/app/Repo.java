package com.raritasolutions.yandex_gallery.app;

import android.util.Log;

import com.raritasolutions.yandex_gallery.LocalDAO;
import com.raritasolutions.yandex_gallery.RetrofitService;
import com.raritasolutions.yandex_gallery.model.InstaData;
import com.raritasolutions.yandex_gallery.model.LoginData;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rarita on 03.05.18.
 */

public class Repo {
    private final String TAG = Repo.class.getSimpleName();
    // Injectable
    private final RetrofitService retrofitService;
    private final LocalDAO localDAO;
    private final Constants constants;

    @Inject
    public Repo(RetrofitService retrofitService, LocalDAO localDAO, Constants constants) {
        this.retrofitService = retrofitService;
        this.localDAO = localDAO;
        this.constants = constants;
    }
    public Maybe<Preferences> getPreferences()
    {
        return localDAO
                .getPreferences();
    }
    public Observable<LoginData> getLoginData()
    {
        // Установка внутри concat subscribeOn спасает от выбивания Observable ошибками API
        return Observable
                .mergeDelayError(
                        getLoginDataFromDB().toObservable().subscribeOn(Schedulers.io()),
                        getLoginDataFromAPI().toObservable().subscribeOn(Schedulers.io()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(),true);
    }
    private Single<LoginData> getLoginDataFromAPI()
    {
        // Если API вернул православный code == 200, пишем в БД ответ, если нет, ругаемся
        return retrofitService
                .getUserLoginData(constants.ACCESS_TOKEN_PUBLIC_SCOPE)
                .doOnSuccess(dataFromAPI ->
                {
                    if (dataFromAPI.meta.code == 200) {
                        storeLoginData(dataFromAPI.data);
                        Log.i(TAG, "Login data fetched from API and stored!");
                    }
                    else throw new Exception("Invalid server response");
                })
                .map(dataResponse -> dataResponse.data);
    }

    private Maybe<LoginData> getLoginDataFromDB()
    {
        return localDAO
                .getLoginData()
                .filter(dataResponse -> dataResponse.id != "")
                .doOnSuccess(data -> Log.i(TAG,"Login data fetched from DB, id = " + data.username));
    }
    public Observable<InstaData[]> getInstaData()
    {
        return Observable.mergeDelayError(
                getInstaDataFromDB().toObservable().subscribeOn(Schedulers.io()),
                getInstaDataFromAPI().toObservable().subscribeOn(Schedulers.io()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(),true);
    }
    private Single<InstaData[]> getInstaDataFromAPI()
    {
        return retrofitService
                .getUserPhotos(constants.ACCESS_TOKEN_PUBLIC_SCOPE)
                .doOnSuccess(dataResponse ->
                {
                    if (dataResponse.meta.code == 200) {
                        storeInstaData(dataResponse.data);
                        Log.i(TAG, "Instagram data fetched from API and stored!");
                    }
                    else throw new Exception("Invalid API response");
                })
                .map(dataResponse -> dataResponse.data);
    }
    private Maybe<InstaData[]> getInstaDataFromDB()
    {
        return localDAO
                .getInstaData()
                .filter(dataResponse -> dataResponse.length > 0)
                .doOnSuccess(dataFromDB -> Log.i(TAG,"Instagram data fetched from DB, its size = " + dataFromDB.length));
    }
    private void storeLoginData(LoginData loginData)
    {
       Completable.fromAction(() -> localDAO.insertLoginData(loginData))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> Log.i(TAG,"Login data stored"));
    }
    private void storeInstaData(InstaData[] instaData)
    {
        Completable.fromAction(() -> localDAO.insertInstaData(instaData))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> Log.i(TAG,"Insta data stored"));
    }
}
