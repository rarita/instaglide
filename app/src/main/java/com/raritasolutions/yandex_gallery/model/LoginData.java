package com.raritasolutions.yandex_gallery.model;

/**
 * Created by rarita on 03.05.18.
 */

public class LoginData {
    public final String username;
    public final String profile_picture;
    public final String full_name;
    public final String bio;
    public final String website;
    public final Counts counts;

    public LoginData(String username, String profile_picture, String full_name, String bio, String website, Counts counts) {
        this.username = username;
        this.profile_picture = profile_picture;
        this.full_name = full_name;
        this.bio = bio;
        this.website = website;
        this.counts = counts;
    }
}
