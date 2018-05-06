package com.raritasolutions.instaglide.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxSeekBar;
import com.raritasolutions.instaglide.R;
import com.raritasolutions.instaglide.app.App;
import com.raritasolutions.instaglide.app.Preferences;
import com.raritasolutions.instaglide.app.Repo;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SettingsActivity extends AppCompatActivity {

    // Данная вьюха также отлично справляется без презентера, храня все нужное в Preferences.
    private final String TAG = SettingsActivity.class.getSimpleName();
    private enum Reference {
        PORTRAIT_SPAN,
        PORTRAIT_SPACING,
        LANDSCAPE_SPAN,
        LANDSCAPE_SPACING
    };
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Unbinder unbinder;

    // Toolbar
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    // Seek bars
    @BindView(R.id.seekBar_portrait_span)
    SeekBar portraitSpan;
    @BindView(R.id.seekBar_portrait_spacing)
    SeekBar portraitSpacing;
    @BindView(R.id.seekBar_landscape_span)
    SeekBar landscapeSpan;
    @BindView(R.id.seekBar_landscape_spacing)
    SeekBar landscapeSpacing;
    // Value views
    @BindView(R.id.portrait_span_value)
    TextView portraitSpanValue;
    @BindView(R.id.landscape_span_value)
    TextView landscapeSpanValue;
    @BindView(R.id.portrait_spacing_value)
    TextView portraitSpacingValue;
    @BindView(R.id.landscape_spacing_value)
    TextView landscapeSpacingValue;
    // Injection
    @Inject
    Preferences preferences;
    @Inject
    Repo repo;

    // Не самая красивая реализация настроек.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        App.getInstance().getAppComponent().inject(this);
        unbinder = ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        loadInitialData();
        subscribeToUIEvents();
    }
    private void loadInitialData()
    {
        portraitSpan.setProgress(preferences.getColumn_count_portrait() - 1);
        portraitSpacing.setProgress(preferences.getSpacing_portrait() - 1);
        landscapeSpan.setProgress(preferences.getColumn_count_landscape() - 1);
        landscapeSpacing.setProgress(preferences.getSpacing_landscape() - 1);
    }
    private void subscribeToUIEvents()
    {
        // Portrait Span
        compositeDisposable.add(RxSeekBar.userChanges(portraitSpan)
                .subscribe(
                        pos -> applyChanges(pos,Reference.PORTRAIT_SPAN),
                        throwable -> Log.i(TAG,throwable.toString())));
        // Landscape Span
        compositeDisposable.add(RxSeekBar.userChanges(landscapeSpan)
                .subscribe(
                        pos -> applyChanges(pos,Reference.LANDSCAPE_SPAN),
                        throwable -> Log.i(TAG,throwable.toString())));
        // Portrait Spacing
        compositeDisposable.add(RxSeekBar.userChanges(portraitSpacing)
                .subscribe(
                        pos -> applyChanges(pos,Reference.PORTRAIT_SPACING),
                        throwable -> Log.i(TAG,throwable.toString())));
        // Landscape Spacing
        compositeDisposable.add(RxSeekBar.userChanges(landscapeSpacing)
                .subscribe(
                        pos -> applyChanges(pos,Reference.LANDSCAPE_SPACING),
                        throwable -> Log.i(TAG,throwable.toString())));
    }
    private void applyChanges(int pos, Reference ref)
    {
        pos++;
        switch (ref) {
            case PORTRAIT_SPAN:
                portraitSpanValue.setText(String.valueOf(pos));
                preferences.setColumn_count_portrait(pos);
                break;
            case PORTRAIT_SPACING:
                portraitSpacingValue.setText(String.valueOf(pos));
                preferences.setSpacing_portrait(pos);
                break;
            case LANDSCAPE_SPAN:
                landscapeSpanValue.setText(String.valueOf(pos));
                preferences.setColumn_count_landscape(pos);
                break;
            case LANDSCAPE_SPACING:
                landscapeSpacingValue.setText(String.valueOf(pos));
                preferences.setSpacing_landscape(pos);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        compositeDisposable.clear();
        // После того, как все отвязали, сохраняем то, что ввел пользователь
        repo.storePreferences(preferences);
    }
}
