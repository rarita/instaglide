package com.raritasolutions.instaglide.model;

/**
 * Created by rarita on 03.05.18.
 */

public class Counts {
    public final String media;
    public final String follows;
    public final String followed_by;

    public Counts(String media, String follows, String followed_by) {
        this.media = media;
        this.follows = follows;
        this.followed_by = followed_by;
    }
}
