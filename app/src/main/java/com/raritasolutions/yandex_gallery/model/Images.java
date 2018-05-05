package com.raritasolutions.yandex_gallery.model;

import android.arch.persistence.room.Embedded;

/**
 * Created by rarita on 05.05.18.
 */

public final class Images {

    @Embedded(prefix = "thumbnail")
    public final Resolution thumbnail; //150x150
    @Embedded(prefix = "low_resolution")
    public final Resolution low_resolution; //320x320
    @Embedded(prefix = "standard_resolution")
    public final Resolution standard_resolution; //640x640

    public Images(Resolution thumbnail, Resolution low_resolution, Resolution standard_resolution) {
        this.thumbnail = thumbnail;
        this.low_resolution = low_resolution;
        this.standard_resolution = standard_resolution;
    }

}
