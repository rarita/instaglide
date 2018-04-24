package com.raritasolutions.yandex_gallery.ui.image_list;

import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.raritasolutions.yandex_gallery.RetrofitService;
import com.raritasolutions.yandex_gallery.app.Constants;
import com.raritasolutions.yandex_gallery.app.Utils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rarita on 21.04.18.
 */

public class ImageListPresenterImpl extends MvpBasePresenter<ImageListView> implements ImageListPresenter {

    private static final String TAG = ImageListPresenterImpl.class.getSimpleName();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    // Это все инжектим
    private RetrofitService retrofitService;
    private Constants constants;
    private Utils utils;

    @Inject
    public ImageListPresenterImpl(RetrofitService retrofitService, Constants constants, Utils utils) {
        this.retrofitService = retrofitService;
        this.constants = constants;
        this.utils = utils;
    }

    @Override
    public void attachView(ImageListView view) {
        super.attachView(view);
        requestUpdate();
    }

    @Override
    public void detachView() {
        super.detachView();
        compositeDisposable.dispose();
    }

    @Override
    public void requestUpdate() {
        ifViewAttached(__ ->
        {
            compositeDisposable.add(
            retrofitService
                    .getUserPhotos(constants.ACCESS_TOKEN_PUBLIC_SCOPE)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            data -> getView().updateData(utils.mapInstaDataToURIList(data)),
                            error -> Log.i(TAG,error.toString())));
        }
        );
    }
}
