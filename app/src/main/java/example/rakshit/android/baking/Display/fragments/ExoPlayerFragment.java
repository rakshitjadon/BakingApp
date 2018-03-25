package example.rakshit.android.baking.Display.fragments;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.net.URLConnection;

import example.rakshit.android.baking.R;
import example.rakshit.android.baking.Model.Stones;
/**
 * Created by rakshit on 21/03/18.
 */
public class ExoPlayerFragment extends Fragment implements ExoPlayer.EventListener {

    private static final String TAG = ExoPlayerFragment.class.getSimpleName();
    private SimpleExoPlayer exoPlayer;
    private SimpleExoPlayerView videoPlayer;
    Uri videoUri;
    private MediaSessionCompat mediaSessionCompat;
    private PlaybackStateCompat.Builder playbackBuilder;
    long position;
    boolean videoPlayed;
    public ExoPlayerFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.exo_player, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Stones stones = null;
        if (getArguments()!= null) {
            stones = getArguments().getParcelable("Stones");
        }
        ImageView thumbnailURL = (ImageView) view.findViewById(R.id.thumbnail);
        assert stones != null;
        videoUri = Uri.parse(stones.getVideoURL());
        videoPlayer = (SimpleExoPlayerView) view.findViewById(R.id.video_player);
        if (videoUri.equals("")) {
            Log.d(TAG, "EMPTY URL");
            Toast.makeText(getContext(), "Your toast message.", Toast.LENGTH_SHORT).show();
            videoPlayer.setVisibility(View.INVISIBLE);
            //simpleExoPlayerView.setVisibility(View.GONE);
            //placeHolderImage.setVisibility(View.VISIBLE);
        }
             //videoPlayer.setVisibility(View.INVISIBLE);
        TextView step_desc = (TextView) view.findViewById(R.id.step_description);
        step_desc.setText(stones.getDescription());
        if (savedInstanceState != null) {
                position = (int) savedInstanceState.getLong("position");
            }
        if (stones.getThumbnailURL() != null && !stones.getThumbnailURL().isEmpty()) {
                if (isFile(stones.getThumbnailURL())) {
                    initialisePlayer(Uri.parse(stones.getThumbnailURL()));
                } else {
                    Glide.with(getContext())
                            .load(stones.getThumbnailURL())
                            .into(thumbnailURL);
                    Toast.makeText(getContext(), "NO VIDEO AVAILABLE", Toast.LENGTH_SHORT).show();}
            } else {
        }
        if (stones.getVideoURL() != null && !stones.getVideoURL().isEmpty()) {
                initialisePlayer(videoUri);}

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("position", position);
    }
    public static boolean isFile(String path) {
        String isVideoFile = URLConnection.guessContentTypeFromName(path);
        return isVideoFile != null && isVideoFile.startsWith("video");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (videoUri != null) {
            if (exoPlayer == null) {
                initialisePlayer(videoUri);
            }
            exoPlayer.seekTo(position);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
        if (mediaSessionCompat != null) {
            mediaSessionCompat.setActive(false);
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        if (exoPlayer != null) {
            position = exoPlayer.getCurrentPosition();
        }
        releasePlayer();

    }
    private void initialisePlayer(Uri videoUri) {
        mediaSessionCompat = new MediaSessionCompat(getContext(), this.getClass().getSimpleName());
        mediaSessionCompat.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);
        mediaSessionCompat.setMediaButtonReceiver(null);
        playbackBuilder = new PlaybackStateCompat.Builder()
                .setActions(PlaybackStateCompat.ACTION_PLAY | PlaybackStateCompat.ACTION_PAUSE |
                        PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS |
                        PlaybackStateCompat.ACTION_PLAY_PAUSE);
        mediaSessionCompat.setPlaybackState(playbackBuilder.build());
        mediaSessionCompat.setCallback(new MediaSessionCompat.Callback() {
            @Override
            public void onPlay() {
                exoPlayer.setPlayWhenReady(true);
            }

            @Override
            public void onPause() {
                exoPlayer.setPlayWhenReady(false);
            }

            @Override
            public void onSkipToPrevious() {
                exoPlayer.seekTo(0);
            }
        });
        mediaSessionCompat.setActive(true);
        if (exoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            videoPlayer.setPlayer(exoPlayer);
            exoPlayer.addListener(this);
            String userAgent = Util.getUserAgent(getContext(), "StepVideo");
            MediaSource mediaSource = new ExtractorMediaSource(videoUri,
                    new DefaultDataSourceFactory(getContext(), userAgent),
                    new DefaultExtractorsFactory(),
                    null,
                    null);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(videoPlayed);

        }
    }
    private void releasePlayer() {
        if (exoPlayer != null) {
           videoPlayed = exoPlayer.getPlayWhenReady();
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        }

        if (mediaSessionCompat != null) {
            mediaSessionCompat.setActive(false);
        }
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if ((playbackState == ExoPlayer.STATE_READY) && playWhenReady) {
            playbackBuilder.setState(PlaybackStateCompat.STATE_PLAYING, exoPlayer.getCurrentPosition(), 1f);
        } else if ((playbackState == ExoPlayer.STATE_READY)) {
            playbackBuilder.setState(PlaybackStateCompat.STATE_PAUSED, exoPlayer.getCurrentPosition(), 1f);
        }
        mediaSessionCompat.setPlaybackState(playbackBuilder.build());
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {
    }

    @Override
    public void onPositionDiscontinuity() {

    }
}
