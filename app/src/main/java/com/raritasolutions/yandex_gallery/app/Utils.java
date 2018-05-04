package com.raritasolutions.yandex_gallery.app;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import com.raritasolutions.yandex_gallery.model.Dimensions;
import com.raritasolutions.yandex_gallery.model.InstaData;
import com.raritasolutions.yandex_gallery.model.Response;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by rarita on 22.04.18.
 */

public class Utils {
    private final String TAG = Utils.class.getSimpleName();
    DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();

    private Constants constants;
    @Inject
    public Utils(Constants constants)
    {
        this.constants = constants;
    }

    public List<String> mapInstaDataToURIList(Response<InstaData[]> response)
    {
        // стримами это дело организовать не удалось
        // надо обдумать и ещё раз попробовать
        InstaData[] data = response.getData();
        Log.i(TAG, String.valueOf(data.length));
        List<String> result = new ArrayList<>();
        for (InstaData item:data) {
            result.add(item.getImages().getStandard_resolution().getUrl());
        }
        return result;
    }
    // Т.к. фото в Инстаграме квадратные (почти все), возвращает одновременно ширину и высоту одной фотки в списке.
    public int getItemDimensions()
    {
        return displayMetrics.widthPixels / constants.COLUMN_COUNT;
    }
    public Dimensions getScreenDimensions()
    {
        return new Dimensions(displayMetrics.widthPixels,displayMetrics.heightPixels);
    }

}
