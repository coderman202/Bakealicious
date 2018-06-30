package com.coderman202.bakealicious.fragments;

import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coderman202.bakealicious.R;
import com.coderman202.bakealicious.model.StepsItem;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class StepFragment extends Fragment{

    private static final String LOG_TAG = StepFragment.class.getSimpleName();

    private StepsItem stepsItem;
    private static final String STEP_ITEM = "step_item";

    // All media player variables.
    private static MediaSessionCompat mediaSession;
    private SimpleExoPlayer exoPlayer;
    private PlaybackStateCompat.Builder playbackBuilder;
    private NotificationManager notificationManager;
    private MediaSource mediaSource;
    DefaultDataSourceFactory dataSourceFactory;
    ExtractorMediaSource.Factory mediaSourceFactory;
    DefaultRenderersFactory renderersFactory;
    DefaultTrackSelector trackSelector;
    DefaultLoadControl loadControl;

    private boolean playReady;
    private long playPosition;
    private int currentWindow;
    private static final String PLAY_READY = "play_ready";
    private static final String PLAYBACK_POSITION = "play_position";
    private static final String CURRENT_WINDOW = "current_window";

    private Context context;

    @BindView(R.id.step_description) TextView stepDescView;
    @BindView(R.id.video_player) PlayerView stepVideoPlayer;


    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public StepFragment() {
    }

    public void setStepItem(StepsItem stepItem){
        this.stepsItem = stepItem;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_steps, container, false);

        ButterKnife.bind(this, rootView);

        if(savedInstanceState != null){
            stepsItem = savedInstanceState.getParcelable(STEP_ITEM);
            playReady = savedInstanceState.getBoolean(PLAY_READY);
            playPosition = savedInstanceState.getLong(PLAYBACK_POSITION);
            currentWindow = savedInstanceState.getInt(CURRENT_WINDOW);
        }
        else{
            stepsItem = getArguments().getParcelable(STEP_ITEM);
            currentWindow = 0;
            playPosition = 0L;
        }

        context = getContext();

        stepDescView.setText(stepsItem.getDescription());

        return rootView;
    }

    private void initPlayer(){
        renderersFactory = new DefaultRenderersFactory(context);
        trackSelector = new DefaultTrackSelector();
        loadControl = new DefaultLoadControl();
        exoPlayer = ExoPlayerFactory.newSimpleInstance(renderersFactory, trackSelector, loadControl);
        stepVideoPlayer.setPlayer(exoPlayer);
        exoPlayer.setPlayWhenReady(playReady);
        exoPlayer.seekTo(playPosition);
        String videoUrl = stepsItem.getVideoURL();
        Log.e(LOG_TAG, videoUrl);
        Uri videoUri = Uri.parse(videoUrl).buildUpon().build();

        if ((videoUri != null) && (!videoUrl.equals(""))) {
            buildMediaSource(videoUri);
            Log.e(LOG_TAG, "Uri: " + videoUri.toString());
            exoPlayer.prepare(mediaSource, true, false);
        }
        else {
            Log.e(LOG_TAG, "No URL :(");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedState){
        super.onSaveInstanceState(savedState);
        savedState.putInt(CURRENT_WINDOW, currentWindow);
        savedState.putBoolean(PLAY_READY, playReady);
        savedState.putLong(PLAYBACK_POSITION, playPosition);
        savedState.putParcelable(STEP_ITEM, stepsItem);
    }

    @Override
    public void onDestroy(){
        Log.e(LOG_TAG, "Destroy");
        super.onDestroy();
        releasePlayer();
    }

    @Override
    public void onStop(){
        Log.e(LOG_TAG, "Stop");
        super.onStop();
        if(Build.VERSION.SDK_INT > 23){
            releasePlayer();
        }
    }

    @Override
    public void onPause(){
        Log.e(LOG_TAG, "Pause");
        super.onPause();
        if(Build.VERSION.SDK_INT > 23){
            releasePlayer();
        }
    }

    @Override
    public void onStart(){
        Log.e(LOG_TAG, "Start");
        super.onStart();
        if(Build.VERSION.SDK_INT > 23){
            initPlayer();
        }
    }

    @Override
    public void onResume(){
        Log.e(LOG_TAG, "Resume");
        super.onResume();
        if(Build.VERSION.SDK_INT <= 23 || exoPlayer == null){
            initPlayer();
        }
    }

    private void releasePlayer(){
        if(exoPlayer != null){
            playPosition = exoPlayer.getCurrentPosition();
            playReady = exoPlayer.getPlayWhenReady();
            currentWindow = exoPlayer.getCurrentWindowIndex();
            exoPlayer.release();
            exoPlayer = null;
        }
    }

    private void buildMediaSource(Uri uri) {
        String appName = context.getString(R.string.app_name);
        String userAgent = Util.getUserAgent(context, appName);
        dataSourceFactory = new DefaultDataSourceFactory(getContext(), userAgent);
        mediaSourceFactory = new ExtractorMediaSource.Factory(dataSourceFactory);
        mediaSource = mediaSourceFactory.createMediaSource(uri);
    }
}
