package com.raritasolutions.yandex_gallery;

/**
 * Created by rarita on 05.05.18.
 */

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.raritasolutions.yandex_gallery.model.InstaData;
import com.raritasolutions.yandex_gallery.model.LoginData;

@Database(entities = {InstaData.class, LoginData.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
    public static final String DB_NAME = "insta_db";
    public abstract LocalDAO localDAO();
}