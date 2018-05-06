package com.raritasolutions.yandex_gallery.ui.zoomed_activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.raritasolutions.yandex_gallery.R;
import com.raritasolutions.yandex_gallery.app.App;
import com.raritasolutions.yandex_gallery.app.Repo;
import com.raritasolutions.yandex_gallery.app.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;

public class ZoomedView extends AppCompatActivity {
    public static final String PAGE_TAG = "currentPage";
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Unbinder unbinder;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @Inject
    Repo repo;
    @Inject
    Utils utils;
    // Нужен FragmentManager, поэтому придется оставить hard dependency
    private ZoomedPagerAdapter zoomedPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int page = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoomed_view);
        unbinder = ButterKnife.bind(this);
        App.getInstance().getAppComponent().inject(this);
        // Вспоминаем последнюю/заданную картинку.
        if (savedInstanceState != null) page = savedInstanceState.getInt(PAGE_TAG);
            else if (getIntent().getExtras() != null) page = getIntent().getExtras().getInt(PAGE_TAG);
        // Асинхронщину в отдельный загон
        // Не очень хорошо (совсем не хорошо) ходить в БД/сеть в коде вьюхи, но в данном случае несмертельно.
        // Главное - вовремя высвободить ресурсы.
        setupViewPager(page);

    }

    private void setupViewPager(int currentPage)
    {
        compositeDisposable.add(
            repo.getInstaData()
                .firstElement() // Так как я мерджу источники при запросах в Recycler, на свежесть данных повлиять не должно.
                .subscribe((data) ->
                {
                    zoomedPagerAdapter = new ZoomedPagerAdapter(getSupportFragmentManager(), utils.mapInstaDataToURIList(data));
                    viewPager.setOffscreenPageLimit(2);
                    viewPager.setAdapter(zoomedPagerAdapter);
                    viewPager.setCurrentItem(currentPage);
                }));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PAGE_TAG,viewPager.getCurrentItem());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        compositeDisposable.clear();
    }
}
