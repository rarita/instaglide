package com.raritasolutions.instaglide.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by rarita on 21.04.18.
 */
@Entity(tableName = "table_data")
public final class InstaData {

    @PrimaryKey
    @NonNull
    public final String id;
    @Embedded
    public final Images images;
    @Embedded
    public final User user;

    public InstaData(Images images, User user, String id) {
        this.images = images;
        this.user = user;
        this.id = id;
    }

}

