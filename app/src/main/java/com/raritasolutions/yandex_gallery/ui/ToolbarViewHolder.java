package com.raritasolutions.yandex_gallery.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.raritasolutions.yandex_gallery.R;
import com.raritasolutions.yandex_gallery.model.Counts;
import com.raritasolutions.yandex_gallery.model.LoginData;
import com.raritasolutions.yandex_gallery.ui.image_list.GridSpacingItemDecoration;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by rarita on 03.05.18.
 */

public class ToolbarViewHolder {
    // Контекст для Glide (инъекция)
    private final Context context;
    private Unbinder unbinder;
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
    }

    public boolean isBound()
    {
        return (unbinder != null);
    }

    public void bindData(LoginData loginData)
    {
        username.setText(loginData.getUsername());
        fullname.setText(loginData.getFull_name());
        final Counts counts = loginData.getCounts();
        media_count.setText(counts.getMedia());
        followers_count.setText(counts.getFollowed_by());
        following_count.setText(counts.getFollows());
        Glide.with(context)
                .load(loginData.getProfile_picture())
                .into(profile_pic);
    }
}
