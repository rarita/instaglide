package com.raritasolutions.instaglide.ui.image_list;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.raritasolutions.instaglide.model.LoginData;

import java.util.List;

/**
 * Created by rarita on 21.04.18.
 */

public interface ImageListView extends MvpView {
    void updateList(List<String> images);
    void updateToolbar(LoginData loginData);
}
