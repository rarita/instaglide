package com.raritasolutions.yandex_gallery.model;

/**
 * Created by rarita on 05.05.18.
 */

public final class User {

    public final String profile_picture;
    public final String user_name;

    public User(String profile_picture, String user_name) {
        this.profile_picture = profile_picture;
        this.user_name = user_name;
    }
}