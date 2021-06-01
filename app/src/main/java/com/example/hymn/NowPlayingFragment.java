package com.example.hymn;

import android.media.MediaMetadataRetriever;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.hymn.MainActivity.ARTIST_TO_FRAG;
import static com.example.hymn.MainActivity.PATH_TO_FRAG;
import static com.example.hymn.MainActivity.SHOW_MINI_PLAYER;
import static com.example.hymn.MainActivity.SONG_NAME_TO_FRAG;

public class NowPlayingFragment extends Fragment {

    ImageView nextBtn,albumArt;
    TextView artist,songName;
    FloatingActionButton playPauseBtn;
    View view;

    public NowPlayingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        artist = view.findViewById(R.id.song_artist_mini);
        songName = view.findViewById(R.id.song_name_mini);
        albumArt = view.findViewById(R.id.bottom_album_art);
        nextBtn = view.findViewById(R.id.skip_next_bottom);
        playPauseBtn = view.findViewById(R.id.playpause_mini);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Next",Toast.LENGTH_SHORT).show();
            }
        });
        playPauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"PlayPause",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (SHOW_MINI_PLAYER){
            if (PATH_TO_FRAG!=null)
            {
                byte[] art = getAlbumArt(PATH_TO_FRAG);
                if (art!=null){
                    Glide.with(getContext()).load(art).into(albumArt);
                }
                else {
                    Glide.with(getContext()).load(R.drawable.musicnote).into(albumArt);
                }
                songName.setText(SONG_NAME_TO_FRAG);
                artist.setText(ARTIST_TO_FRAG);
            }
        }
    }

    private byte[] getAlbumArt(String uri)
    {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(uri);
        byte[] art = retriever.getEmbeddedPicture();
        retriever.release();
        return art;
    }
}