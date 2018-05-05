package com.raritasolutions.yandex_gallery;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.raritasolutions.yandex_gallery.model.InstaData;
import com.raritasolutions.yandex_gallery.model.LoginData;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by rarita on 05.05.18.
 */

@Dao
public interface LocalDAO {
    @Query("SELECT * FROM table_login")
    Flowable<LoginData> getLoginData();

    @Query("SELECT * FROM table_data")
    Flowable<InstaData[]> getInstaData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLoginData(LoginData loginData);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertInstaData(InstaData[] instaData);
}
