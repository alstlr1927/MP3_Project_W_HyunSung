package com.cookandroid.mp3_project_w_hyunsung;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    private ImageView imgDrawerMenuAlbumCover;
    private ListView menuListView;

    private DrawerLayout drawerLayout;
    private FrameLayout mainFrameLayout;

    private MusicList musicList = new MusicList();
    private Player player = new Player();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermissionsFunc();

        findViewByIdFunc();

        replaceFrag();

        eventHandlerFunc();
    }

    private void requestPermissionsFunc() {
        ActivityCompat.requestPermissions(this,
                new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, MODE_PRIVATE);
    }

    private void eventHandlerFunc() {
        imgDrawerMenuAlbumCover.setOnClickListener((View v) -> {
            drawerLayout.closeDrawer(Gravity.LEFT);

        });

        //드로워블 메뉴에 리스트뷰 만들기
        makeMenuListView();

        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0 :
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        replaceFrag();
                        break;
                    case 1 :
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
                        ft.addToBackStack(null);
                        ft.replace(R.id.mainFrameLayout, player);
                        ft.commit();
                        break;
                    case 2 :
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        break;
                    case 3 :
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        break;
                }
            }
        });
    }

    private void makeMenuListView() {
        final String[] menuList = new String[] {"재생 목록", "재생 중", "설정", "소개"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.memolist_type, menuList);
        menuListView.setAdapter(adapter);
    }

    private void replaceFrag() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        ft.replace(R.id.mainFrameLayout, musicList);
        ft.commit();
    }

    private void findViewByIdFunc() {
        menuListView = (ListView) findViewById(R.id.menuListView);
        mainFrameLayout = (FrameLayout) findViewById(R.id.mainFrameLayout);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        imgDrawerMenuAlbumCover = (ImageView) findViewById(R.id.imgDrawerMenuAlbumCover);
    }
}