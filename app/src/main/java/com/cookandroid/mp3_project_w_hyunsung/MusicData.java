package com.cookandroid.mp3_project_w_hyunsung;

import java.util.Objects;

public class MusicData {
    private String id;
    private String artist;
    private String title;
    private String albumCover;
    private String duration;
    private int liked;

    public MusicData() {
        this(null, null, null, null, null, 0);
    }

    public MusicData(String id, String artist, String title, String albumCover, String duration, int liked) {
        super();
        this.id = id;
        this.artist = artist;
        this.title = title;
        this.albumCover = albumCover;
        this.duration = duration;
        this.liked = liked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbumCover() {
        return albumCover;
    }

    public void setAlbumCover(String albumCover) {
        this.albumCover = albumCover;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

}
