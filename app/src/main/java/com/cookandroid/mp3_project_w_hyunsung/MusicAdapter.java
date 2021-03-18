package com.cookandroid.mp3_project_w_hyunsung;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.CustomViewHolder> {
    private Context context;
    private ArrayList<MusicData> musicData;
    //2. 멤버 변수를 만든다.
    private OnItemClickListener mListener;

    public MusicAdapter(Context context, ArrayList<MusicData> musicData) {
        this.context = context;
        this.musicData = musicData;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        //앨범 이미지 비트맵으로 만들기
        Bitmap albumImg = getAlbumIng(context, Integer.parseInt(musicData.get(position).getAlbumCover()), 200);
        if(albumImg != null) {
            holder.albumCover.setImageBitmap(albumImg);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        holder.artist.setText(musicData.get(position).getArtist());
        holder.title.setText(musicData.get(position).getTitle());
        holder.duration.setText(sdf.format(Integer.parseInt(musicData.get(position).getDuration())));
        holder.itemView.setTag(position);

    }

    // 앨범아트 가져오는 함수
    private Bitmap getAlbumIng(Context context, int albumArt, int imgMaxSize) {
        BitmapFactory.Options options = new BitmapFactory.Options();

        /*컨텐트 프로바이더(Content Provider)는 앱 간의 데이터 공유를 위해 사용됨.
        특정 앱이 다른 앱의 데이터를 직접 접근해서 사용할 수 없기 때문에
        무조건 컨텐트 프로바이더를 통해 다른 앱의 데이터를 사용해야만 한다.
        다른 앱의 데이터를 사용하고자 하는 앱에서는 URI를 이용하여 컨텐트 리졸버(Content Resolver)를 통해
        다른 앱의 컨텐트 프로바이더에게 데이터를 요청하게 되는데
        요청받은 컨텐트 프로바이더는 URI를 확인하고 내부에서 데이터를 꺼내어 컨텐트 리졸버에게 전달한다.
        */

        ContentResolver contentResolver = context.getContentResolver();

        // 앨범 커버는 별도로 생성 (uri 제공 안함)
        Uri uri = Uri.parse("content://media/external/audio/albumart/" + albumArt);
        if(uri != null) {
            ParcelFileDescriptor fd = null;
            try {
                fd = contentResolver.openFileDescriptor(uri, "r");

                //메모리할당을 하지 않으면서 해댱된 정볼르 읽올수 있음.
                options.inJustDecodeBounds =true;

                int scale = 0;
                if(options.outHeight > imgMaxSize || options.outWidth > imgMaxSize){
                    scale = (int)Math.pow(2,(int) Math.round(Math.log(imgMaxSize /
                            (double) Math.max(options.outHeight, options.outWidth)) / Math.log(0.5)));
                }
                //비트맵을 위해 메모리를 할당하겠다.
                options.inJustDecodeBounds = false;
                options.inSampleSize = scale;

                Bitmap bitmap = BitmapFactory.decodeFileDescriptor(fd.getFileDescriptor(), null, options);

                if(bitmap != null) {
                    if(options.outWidth != imgMaxSize || options.outHeight != imgMaxSize) {
                        Bitmap map = Bitmap.createScaledBitmap(bitmap, imgMaxSize, imgMaxSize, true);
                        bitmap.recycle();
                        bitmap = map;
                    }
                }
                return bitmap;
            } catch (FileNotFoundException e) {
                Log.d("MainAdapter", "Content Resolver Error!");
            } finally {
                try {
                    if(fd != null)
                        fd.close();
                } catch (IOException e){

                }
            }
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return (musicData != null) ? musicData.size() : 0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView albumCover;
        TextView title;
        TextView artist;
        TextView duration;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.albumCover = itemView.findViewById(R.id.item_ivAlbumCover);
            this.title = itemView.findViewById(R.id.item_tvTitle);
            this.artist = itemView.findViewById(R.id.item_tvArtist);
            this.duration = itemView.findViewById(R.id.item_tvDuration);

            //4. 구현한다.
            itemView.setOnClickListener((View v) -> {
                int pos = getAdapterPosition();
                if(pos != RecyclerView.NO_POSITION) {

                    mListener.onItemClick(v, pos);
                }
            });
        }
    }

    public void setMusicData(ArrayList<MusicData> musicData) {
        this.musicData = musicData;
    }

    //1. 내부 인터페이스 정의
    public interface OnItemClickListener {
        //1. 추상화 메소드
        void onItemClick(View v, int pos);
    }
    //3.내부 인터페이스 멤버변수에 대한 setters만든다.

    public void setOnItemClickListener(OnItemClickListener Listener) {
        this.mListener = Listener;
    }
}
