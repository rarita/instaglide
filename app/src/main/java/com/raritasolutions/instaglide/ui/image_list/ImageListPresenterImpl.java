package com.raritasolutions.instaglide.ui.image_list;

import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.raritasolutions.instaglide.RetrofitService;
import com.raritasolutions.instaglide.app.Constants;
import com.raritasolutions.instaglide.app.Repo;
import com.raritasolutions.instaglide.app.Utils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rarita on 21.04.18.
 */

public class ImageListPresenterImpl extends MvpBasePresenter<ImageListView> implements ImageListPresenter {

    private static final String TAG = ImageListPresenterImpl.class.getSimpleName();
    // Это все инжектим
    private final CompositeDisposable compositeDisposable;
    private final Utils utils;
    private final Repo repo;

    @Inject
    public ImageListPresenterImpl(Utils utils,
                                  CompositeDisposable compositeDisposable,
                                  Repo repo)
    {
        this.utils = utils;
        this.compositeDisposable = compositeDisposable;
        this.repo = repo;
    }

    @Override
    public void attachView(ImageListView view) {
        super.attachView(view);
        requestListUpdate();
        requestHeaderUpdate();
    }

    @Override
    public void detachView() {
        super.detachView();
        compositeDisposable.clear(); // 3 часа убито на понимание того, что dispose != clear
    }

    @Override
    public void requestListUpdate() {
        ifViewAttached(view ->
                {
                    compositeDisposable.add(repo.getInstaData()
                                    .subscribe(
                                            data ->
                                            {
                                                List<String> converted = utils.mapInstaDataToURIList(data);
                                                view.updateList(converted);
                                            },
                                            error -> Log.i(TAG,error.toString())));
                }
        );
    }
    @Override
    public void requestHeaderUpdate() {
        ifViewAttached(view -> {
            compositeDisposable.add(repo.getLoginData()
                    .subscribe(
                            loginDataResponse -> view.updateToolbar(loginDataResponse),
                            throwable -> Log.i(TAG,throwable.toString())));
        });
    }
}
