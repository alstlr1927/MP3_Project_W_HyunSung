package com.cookandroid.mp3_project_w_hyunsung;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Player extends Fragment {

    private ImageView ivPlayerAlbumCover;
    private TextView tvPlayerTitle, tvPlayerArtist, tvCurrentTime, tvDuration;
    private SeekBar seekBar;
    private Button btnPrev, btnPlayPause, btnNext;

    private MediaPlayer mPlayer = new MediaPlayer();


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.player, container, false);

        findViewByIDFunc(view);
        return view;
    }

    private void findViewByIDFunc(View view) {
        ivPlayerAlbumCover = view.findViewById(R.id.ivPlayerAlbumCover);
        tvPlayerTitle = view.findViewById(R.id.tvPlayerTitle);
        tvPlayerArtist = view.findViewById(R.id.tvPlayerArtist);
        tvCurrentTime = view.findViewById(R.id.tvCurrentTime);
        tvDuration = view.findViewById(R.id.tvDuration);
        seekBar = view.findViewById(R.id.seekBar);
        btnNext = view.findViewById(R.id.btnNext);
        btnPlayPause = view.findViewById(R.id.btnPlayPause);
        btnPrev = view.findViewById(R.id.btnPrev);
    }
}
