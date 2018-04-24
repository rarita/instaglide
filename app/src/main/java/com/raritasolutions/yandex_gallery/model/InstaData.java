package com.raritasolutions.yandex_gallery.model;

/**
 * Created by rarita on 21.04.18.
 */

public class InstaData {

    private Images images;
    private User user;

    public Images getImages() {
        return images;
    }

    public User getUser() {
        return user;
    }

    public class User {

        private String profile_picture;
        private String user_name;

        public String getProfile_picture() {
            return profile_picture;
        }

        public String getUser_name() {
            return user_name;
        }
    }

    public class Images {

        private resolution thumbnail; //150x150
        private resolution low_resolution; //320x320
        private resolution standard_resolution; //640x640

        public resolution getStandard_resolution() {
            return standard_resolution;
        }

        public class resolution {
            private String url;
            public String getUrl() {
                return url;
            }
        }

    }
}

