package com.raritasolutions.instaglide;

/**
 * Created by rarita on 05.05.18.
 */

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.raritasolutions.instaglide.app.Preferences;
import com.raritasolutions.instaglide.model.InstaData;
import com.raritasolutions.instaglide.model.LoginData;

@Database(entities = {InstaData.class, LoginData.class, Preferences.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
    public static final String DB_NAME = "insta_db";
    public abstract LocalDAO localDAO();
}