package com.raritasolutions.yandex_gallery.model;

/**
 * Created by rarita on 03.05.18.
 */

public class LoginData {
    private String username;
    private String profile_picture;
    private String full_name;
    private String bio;
    private String website;
    private Counts counts;

    public String getUsername() {return username;}

    public String getProfile_picture() {return profile_picture;}

    public String getFull_name() {return full_name;}

    public String getBio() {return bio;}

    public String getWebsite() {return website;}

    public Counts getCounts() {return counts;}
}
