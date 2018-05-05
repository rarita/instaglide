package com.raritasolutions.yandex_gallery.app;

import android.util.Log;

import com.raritasolutions.yandex_gallery.LocalDAO;
import com.raritasolutions.yandex_gallery.RetrofitService;
import com.raritasolutions.yandex_gallery.model.InstaData;
import com.raritasolutions.yandex_gallery.model.LoginData;
import com.raritasolutions.yandex_gallery.model.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
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
    private List<String> images = new ArrayList<>();
    private Response<InstaData> dataResponse;
    private Response<LoginData> loginResponse;
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

    public Flowable<LoginData> getLoginData()
    {
        return Flowable.merge(
                getLoginDataFromDB(),
                getLoginDataFromAPI())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
                //.debounce(400, TimeUnit.MILLISECONDS); // Если API отвечает быстро, дропаем ответ БД во избежание дерганья UI.
    }
    private Flowable<LoginData> getLoginDataFromAPI()
    {
        // Если API вернул православный code == 200, пишем в БД ответ, если нет, ругаемся

        return retrofitService
                .getUserLoginData(constants.ACCESS_TOKEN_PUBLIC_SCOPE)
                .doOnNext(dataFromAPI ->
                {
                    if (dataFromAPI.meta.code == 200) {
                        storeLoginData(dataFromAPI.data);
                        Log.i(TAG, "Login data fetched from API and stored!");
                    }
                    else throw new Exception("Invalid server response");
                })
                .map(dataResponse -> dataResponse.data);
    }

    private Flowable<LoginData> getLoginDataFromDB()
    {
        return localDAO
                .getLoginData()
                .filter(dataResponse -> dataResponse.id != "")
                .doOnNext(__ -> Log.i(TAG,"Login data fetched from DB"));
    }
    public Flowable<InstaData[]> getInstaData()
    {
        return Flowable.merge(
                 getInstaDataFromDB()
                ,getInstaDataFromAPI())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    private Flowable<InstaData[]> getInstaDataFromAPI()
    {
        return retrofitService
                .getUserPhotos(constants.ACCESS_TOKEN_PUBLIC_SCOPE)
                .doOnNext(dataResponse ->
                {
                    if (dataResponse.meta.code == 200) {
                        storeInstaData(dataResponse.data);
                        Log.i(TAG, "Instagram data fetched from API and stored!");
                    }
                    else throw new Exception("Invalid API response");
                })
                .map(dataResponse -> dataResponse.data);
    }
    private Flowable<InstaData[]> getInstaDataFromDB()
    {
        return localDAO
                .getInstaData()
                .filter(dataResponse -> dataResponse.length > 0)
                .doOnNext(dataFromDB -> Log.i(TAG,"Instagram data fetched from DB, its size = " + dataFromDB.length));
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
