package com.raritasolutions.yandex_gallery.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.raritasolutions.yandex_gallery.R;
import com.raritasolutions.yandex_gallery.app.App;
import com.raritasolutions.yandex_gallery.app.GlideApp;
import com.raritasolutions.yandex_gallery.app.Utils;
import com.raritasolutions.yandex_gallery.model.Dimensions;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class LoginActivity extends AppCompatActivity {

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
                .into(new SimpleTarget<Drawable>(dim.getWidth(),dim.getHeight()) {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        loginContainer.setBackground(resource);
                    }
                });
    }


    @OnClick(R.id.button_demo)
    void login()
    {
        // Подождем сеть перед тем как показать MainActivity.
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
