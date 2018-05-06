package com.raritasolutions.instaglide.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.raritasolutions.instaglide.R;
import com.raritasolutions.instaglide.app.App;
import com.raritasolutions.instaglide.app.GlideApp;
import com.raritasolutions.instaglide.app.Utils;
import com.raritasolutions.instaglide.model.Dimensions;
import com.raritasolutions.instaglide.ui.main_activity.MainActivity;
import com.raritasolutions.instaglide.ui.zoomed_activity.ZoomedView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginActivity extends AppCompatActivity {

    // Данная вьюха не нуждается в презентере, потому что ничего сложного не делает и, тем более, не хранит.
    private Unbinder unbinder;
    @BindView(R.id.login_container)
    ConstraintLayout loginContainer;
    @Inject
    Context context;
    @Inject
    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        App.getInstance().getAppComponent().inject(this);
        unbinder = ButterKnife.bind(this);
        setup_background();
    }

    private void setup_background()
    {
        Dimensions dim = utils.getScreenDimensions();
        GlideApp.with(context)
                .load(R.drawable.stars2)
                .centerCrop()
                .into(new SimpleTarget<Drawable>(dim.width,dim.height) {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        loginContainer.setBackground(resource);
                    }
                });
    }


    // Бинды Butterknife красивее, чем RxBinding.
    // Цены ему не было, если бы он биндил SeekBar-ы.
    @OnClick(R.id.button_demo)
    void login()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.button_settings)
    void openSettings()
    {
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
