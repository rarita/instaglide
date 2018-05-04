package com.raritasolutions.yandex_gallery.di;

import com.raritasolutions.yandex_gallery.ui.LoginActivity;
import com.raritasolutions.yandex_gallery.ui.MainActivity;
import com.raritasolutions.yandex_gallery.ui.image_list.ImageListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by rarita on 21.04.18.
 */
@Component(modules = {AppModule.class, ApiModule.class, UnscopedModule.class})
@Singleton
public interface AppComponent {
    ImageListComponent imageListComponent(ImageListModule imageListModule);
    void inject(MainActivity mainActivity);
    void inject(LoginActivity loginActivity);
}
