package com.raritasolutions.instaglide.di;

import com.raritasolutions.instaglide.app.App;
import com.raritasolutions.instaglide.ui.LoginActivity;
import com.raritasolutions.instaglide.ui.main_activity.MainActivity;
import com.raritasolutions.instaglide.ui.SettingsActivity;
import com.raritasolutions.instaglide.ui.zoomed_activity.ZoomedView;

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
    void inject(SettingsActivity settingsActivity);
    void inject(ZoomedView zoomedView);
    void inject(App app);
}
