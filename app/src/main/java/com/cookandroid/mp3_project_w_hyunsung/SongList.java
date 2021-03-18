package com.cookandroid.mp3_project_w_hyunsung;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SongList extends Fragment implements MusicAdapter.OnItemClickListener {

    private RecyclerView recyclerViewTitle;

    private MusicDBHelper dbHelper;
    private MusicAdapter adapter;
    private Player player;

    private ArrayList<MusicData> musicDataList;

    private int selectPosition;

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
        View view = inflater.inflate(R.layout.song_list, container, false);

        findViewByIDFunc(view);

        conectAdapterToRecyclerView();

        eventHandlerFunc();

        return view;
    }

    private void eventHandlerFunc() {
        adapter.setOnItemClickListener(new MusicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
//                FragmentManager fm = getChildFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
//                ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
//                ft.addToBackStack(null);
//                ft.replace(R.id.musicListFrameLayout ,new Player());
//                ft.commit();
            }
        });
    }

    private void conectAdapterToRecyclerView() {
        dbHelper = MusicDBHelper.getInstance(getActivity());
        musicDataList = dbHelper.findMusicFile();

        adapter = new MusicAdapter(getActivity(), musicDataList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerViewTitle.setLayoutManager(linearLayoutManager);
        recyclerViewTitle.setAdapter(adapter);
    }

    private void findViewByIDFunc(View view) {
        recyclerViewTitle = (RecyclerView) view.findViewById(R.id.recyclerViewTitle);
    }

    @Override
    public void onItemClick(View v, int pos) {

    }
}
