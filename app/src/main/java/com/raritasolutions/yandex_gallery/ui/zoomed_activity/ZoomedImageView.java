package com.raritasolutions.yandex_gallery.ui.zoomed_activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.request.target.Target;
import com.raritasolutions.yandex_gallery.R;
import com.raritasolutions.yandex_gallery.app.GlideApp;



import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ZoomedImageView extends Fragment {

    private static final String URL_TAG = "imageURL";
    private String imageURL;
    private Unbinder unbinder;

    @BindView(R.id.image_zoomed)
    ImageView image;

    public ZoomedImageView() {
        // Required empty public constructor
    }


    public static ZoomedImageView newInstance(String imageURL) {
        ZoomedImageView fragment = new ZoomedImageView();
        Bundle args = new Bundle();
        args.putString(URL_TAG,imageURL);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imageURL = getArguments().getString(URL_TAG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_zoomed_image_view, container, false);
        unbinder = ButterKnife.bind(this,view);
        GlideApp.with(view)
                .load(imageURL)
                .fitCenter()
                .into(image);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
