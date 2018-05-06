package com.raritasolutions.instaglide.app;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import javax.annotation.Nullable;

/**
 * Created by rarita on 06.05.18.
 */
@Entity(tableName = "table_prefs")
public class Preferences {
    @PrimaryKey
    private int id = 0; // по дефолту для БД
    private int column_count_portrait;
    private int column_count_landscape;
    private int spacing_portrait;
    private int spacing_landscape;


    public Preferences() {
    }

    // Используется, когда восстанавливаем preferences с локального хранилища.
    public void fromPreferences(Preferences preferences)
    {
        column_count_portrait = preferences.getColumn_count_portrait();
        column_count_landscape = preferences.getColumn_count_landscape();
        spacing_portrait = preferences.getSpacing_portrait();
        spacing_landscape = preferences.getSpacing_landscape();
    }
    // Если запуск происходит первый раз.
    public void fromConstants(Constants constants)
    {
        column_count_portrait = constants.COLUMN_COUNT;
        column_count_landscape = constants.COLUMN_COUNT * 2;
        spacing_portrait = constants.LIST_SPACING;
        spacing_landscape = constants.LIST_SPACING * 2;
    }
    // Геттеры - сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getColumn_count_portrait() {
        return column_count_portrait;
    }

    public void setColumn_count_portrait(int column_count_portrait) {
        this.column_count_portrait = column_count_portrait;
    }
    public int getColumn_count_landscape() {
        return column_count_landscape;
    }

    public void setColumn_count_landscape(int column_count_landscape) {
        this.column_count_landscape = column_count_landscape;
    }
    public int getSpacing_portrait() {
        return spacing_portrait;
    }

    public void setSpacing_portrait(int spacing_portrait) {
        this.spacing_portrait = spacing_portrait;
    }
    public int getSpacing_landscape() {
        return spacing_landscape;
    }

    public void setSpacing_landscape(int spacing_landscape) {
        this.spacing_landscape = spacing_landscape;
    }
}
