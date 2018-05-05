package com.raritasolutions.yandex_gallery.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by rarita on 03.05.18.
 */

@Entity(tableName = "table_login")
public class LoginData {
    @PrimaryKey
    @NonNull
    public final String id;
    public final String username;
    public final String profile_picture;
    public final String full_name;
    public final String bio;
    public final String website;
    @Embedded
    public final Counts counts;

    public LoginData(String username, String profile_picture, String full_name, String bio, String website, Counts counts, String id) {
        this.id = id;
        this.username = username;
        this.profile_picture = profile_picture;
        this.full_name = full_name;
        this.bio = bio;
        this.website = website;
        this.counts = counts;
    }
}
