package com.cookandroid.mp3_project_w_hyunsung;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ArtistList extends Fragment {

    private ListView listView2;
    private final String[] artistArr = new String[]{"박효신", "가호", "10센치", "김범수", "레드애플", "스탠딩 에그", "데이식스",
            "방탄소년단", "인피니트", "이예준", "에이치코드", "#안녕", "머쉬베놈", "래원", "스윙스", "허각", "슈퍼비", "윤딴딴",
            "그냥", "창모", "저스디스"};

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
        View view = inflater.inflate(R.layout.artist_list, container, false);

        findViewByIDFunc(view);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, artistArr);
        listView2.setAdapter(adapter);

        return view;
    }

    private void findViewByIDFunc(View view) {
        listView2 = view.findViewById(R.id.listView2);
    }
}
