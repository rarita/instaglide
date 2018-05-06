package com.raritasolutions.yandex_gallery.ui.zoomed_activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rarita on 06.05.18.
 */

public class ZoomedPagerAdapter extends FragmentStatePagerAdapter {
    private final List<String> mValues;

    public ZoomedPagerAdapter(FragmentManager fm, List<String> images) {
        super(fm);
        mValues = new ArrayList<>(images);
    }

    @Override
    public Fragment getItem(int position) {
        return ZoomedImageView.newInstance(mValues.get(position));
    }

    @Override
    public int getCount() {
        return mValues.size();
    }

}
