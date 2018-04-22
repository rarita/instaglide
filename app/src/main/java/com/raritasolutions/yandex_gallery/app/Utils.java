package com.raritasolutions.yandex_gallery.app;

import com.annimon.stream.Stream;
import com.annimon.stream.function.UnaryOperator;
import com.annimon.stream.iterator.*;
import com.raritasolutions.yandex_gallery.model.InstaData;
import com.raritasolutions.yandex_gallery.model.InstaResponse;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by rarita on 22.04.18.
 */

public class Utils {

    public List<String> mapInstaDataToURIList(InstaResponse response)
    {
        // стримами это дело организовать не удалось
        // надо обдумать и ещё раз попробовать
        InstaData[] data = response.getData();
        List<String> result = new ArrayList<>();
        for (InstaData item:data) {
            result.add(item.getImages().getStandard_resolution().getUrl());
        }
        return result;
    }

}
