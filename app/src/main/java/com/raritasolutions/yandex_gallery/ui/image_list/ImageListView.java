package com.raritasolutions.yandex_gallery.ui.image_list;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.raritasolutions.yandex_gallery.model.LoginData;

import java.util.List;

/**
 * Created by rarita on 21.04.18.
 */

public interface ImageListView extends MvpView {
    void updateList(List<String> images);
    void updateToolbar(LoginData loginData);
}
