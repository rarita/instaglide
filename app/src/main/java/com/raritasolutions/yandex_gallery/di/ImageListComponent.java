package com.raritasolutions.yandex_gallery.di;

import com.raritasolutions.yandex_gallery.ui.image_list.ImageListFragment;

import dagger.Subcomponent;

/**
 * Created by rarita on 21.04.18.
 */
@Subcomponent(modules = {ImageListModule.class})
@ImageListScope
public interface ImageListComponent {
    void inject(ImageListFragment imageListFragment);
}