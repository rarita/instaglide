package com.raritasolutions.instaglide.app;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import com.annimon.stream.Stream;
import com.raritasolutions.instaglide.model.Dimensions;
import com.raritasolutions.instaglide.model.InstaData;
import com.raritasolutions.instaglide.model.Response;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by rarita on 22.04.18.
 */

public class Utils {
    private final String TAG = Utils.class.getSimpleName();
    private DisplayMetrics displayMetrics;

    @Inject
    public Utils(Constants constants)
    {
        updateMetrics();
    }
    public void updateMetrics()
    {
        displayMetrics = Resources.getSystem().getDisplayMetrics();
    }

    public List<String> mapInstaDataToURIList(InstaData[] data)
    {
        return Stream.of(data).map(instaData -> instaData.images.standard_resolution.url).toList();
    }
    public String getRandomPhotoFromUriList(List<String> provider) throws Exception {
        int size = provider.size();
        if (size == 0) throw new Exception("Provided list can't be empty!");
        int randomPosition = (int) Math.round(size * Math.random());
        return provider.get(randomPosition);
    }
    // Т.к. фото в Инстаграме квадратные (почти все), возвращает одновременно ширину и высоту одной фотки в списке.
    public int getItemDimensions(int itemCount)
    {
        return displayMetrics.widthPixels / itemCount;
    }
    // Получаем ширину-высоту для адекватного отображения фона на LoginActivity
    public Dimensions getScreenDimensions()
    {
        return new Dimensions(displayMetrics.widthPixels,displayMetrics.heightPixels);
    }

}
