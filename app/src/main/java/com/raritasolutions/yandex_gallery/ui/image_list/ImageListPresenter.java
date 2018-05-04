package com.raritasolutions.yandex_gallery.ui.image_list;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

/**
 * Created by rarita on 21.04.18.
 */

public interface ImageListPresenter extends MvpPresenter<ImageListView> {
    void requestListUpdate();
    void requestHeaderUpdate();
}
