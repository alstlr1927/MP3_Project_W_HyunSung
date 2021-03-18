package com.cookandroid.mp3_project_w_hyunsung;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MusicList extends Fragment {

    private static final int SONG = 1, ARTIST = 2, ALBUM =3;

    private MainActivity mainActivity;
    private MusicAdapter adapter;

    private BottomNavigationView btnBottomMenu;
    private FrameLayout musicListFrameLayout;

    private SongList songList;
    private ArtistList artistList;
    private AlbumList albumList;
    private Player player;

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
        View view = inflater.inflate(R.layout.music_list, container, false);

        findViewByIdFunc(view);

        eventHandlerFunc();

        return view;
    }

    private void eventHandlerFunc() {
        songList = new SongList();
        artistList = new ArtistList();
        albumList = new AlbumList();
        player = new Player();
        setFragmentChange(SONG);
        btnBottomMenu.setOnNavigationItemSelectedListener((@NonNull MenuItem item) -> {
            switch (item.getItemId()) {
                case R.id.songList :
                    setFragmentChange(SONG);
                    return true;
                case R.id.artistList :
                    setFragmentChange(ARTIST);
                    return true;
                case R.id.albumList :
                    setFragmentChange(ALBUM);
                    return true;
            }
            return false;
        });
    }

    private void setFragmentChange(int i) {
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        switch (i) {
            case SONG :
                ft.replace(R.id.musicListFrameLayout, songList);
                ft.commit();

                break;
            case ARTIST :
                ft.replace(R.id.musicListFrameLayout, artistList);
                ft.commit();
                break;
            case ALBUM :
                ft.replace(R.id.musicListFrameLayout, albumList);
                ft.commit();
                break;
        }
    }

    private void findViewByIdFunc(View view) {
        musicListFrameLayout = view.findViewById(R.id.musicListFrameLayout);
        btnBottomMenu = view.findViewById(R.id.btnBottomMenu);
    }
}
