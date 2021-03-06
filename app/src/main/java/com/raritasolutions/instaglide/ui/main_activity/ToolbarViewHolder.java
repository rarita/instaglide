package com.raritasolutions.instaglide.ui.main_activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.raritasolutions.instaglide.R;
import com.raritasolutions.instaglide.app.GlideApp;
import com.raritasolutions.instaglide.model.Counts;
import com.raritasolutions.instaglide.model.LoginData;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation;

/**
 * Created by rarita on 03.05.18.
 */
// Вытащено в отдельный класс, чтобы не мозолить глаза в Activity.
public class ToolbarViewHolder {
    // Контекст для Glide (инъекция)
    private final Context context;
    private String user = "Not Logged In";
    private Unbinder unbinder;
    // Физические тулбары
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    // Аватар
    @BindView(R.id.profile_pic)
    ImageView profile_pic;
    // Данные о юзере
    @BindView(R.id.username_view)
    TextView username;
    @BindView(R.id.fullname_view)
    TextView fullname;
    // "Числовые" поля
    @BindView(R.id.postsq_view)
    TextView media_count;
    @BindView(R.id.followersq_view)
    TextView followers_count;
    @BindView(R.id.followingq_view)
    TextView following_count;

    @Inject
    public ToolbarViewHolder(Context context) {
        this.context = context;
    }
    public void bind(Activity activity)
    {
        unbinder = ButterKnife.bind(this, activity);
        // Как только получили заветные биндинги избавляемся от катающегося по всему тулбару тайтла.
        disableTitleScaling();
    }

    public void setUser(String user) {
        this.user = user;
    }

    private void disableTitleScaling()
    {
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("Instaglide: " + user);
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    public boolean isBound()
    {
        return (unbinder != null);
    }

    public void bindData(LoginData loginData)
    {
        username.setText(loginData.username);
        fullname.setText(loginData.full_name);
        final Counts counts = loginData.counts;
        media_count.setText(counts.media);
        followers_count.setText(counts.followed_by);
        following_count.setText(counts.follows);
        Glide.with(context)
                .load(loginData.profile_picture)
                .into(profile_pic);
    }


}
