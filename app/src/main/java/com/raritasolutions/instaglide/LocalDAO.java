package com.raritasolutions.instaglide;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.raritasolutions.instaglide.app.Preferences;
import com.raritasolutions.instaglide.model.InstaData;
import com.raritasolutions.instaglide.model.LoginData;

import dagger.multibindings.IntoSet;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by rarita on 05.05.18.
 */

@Dao
public interface LocalDAO {
    @Query("SELECT * FROM table_login")
    Maybe<LoginData> getLoginData();

    @Query("SELECT * FROM table_data")
    Maybe<InstaData[]> getInstaData();

    @Query("SELECT * FROM table_prefs")
    Single<Preferences> getPreferences();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPreferences(Preferences preferences);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLoginData(LoginData loginData);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertInstaData(InstaData[] instaData);
}
