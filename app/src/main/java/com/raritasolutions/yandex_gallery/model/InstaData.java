package com.raritasolutions.yandex_gallery.model;

/**
 * Created by rarita on 21.04.18.
 */

public class InstaData {

    public final Images images;
    public final User user;

    public InstaData(Images images, User user) {
        this.images = images;
        this.user = user;
    }

    public class User {

        public final String profile_picture;
        public final String user_name;

        public User(String profile_picture, String user_name) {
            this.profile_picture = profile_picture;
            this.user_name = user_name;
        }
    }

    public class Images {

        public final resolution thumbnail; //150x150
        public final resolution low_resolution; //320x320
        public final resolution standard_resolution; //640x640

        public Images(resolution thumbnail, resolution low_resolution, resolution standard_resolution) {
            this.thumbnail = thumbnail;
            this.low_resolution = low_resolution;
            this.standard_resolution = standard_resolution;
        }

        public class resolution {
            public final String url;

            public resolution(String url) {
                this.url = url;
            }
        }

    }
}

