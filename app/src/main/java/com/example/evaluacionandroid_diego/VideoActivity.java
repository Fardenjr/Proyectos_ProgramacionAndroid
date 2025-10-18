package com.example.evaluacionandroid_diego;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {

    private VideoView videoView;
    private Button btnPlay, btnPause, btnForward, btnRewind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = findViewById(R.id.videoView);
        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnForward = findViewById(R.id.btnForward);
        btnRewind = findViewById(R.id.btnRewind);

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.acho_pr); // archivo de video
        videoView.setVideoURI(uri);

        btnPlay.setOnClickListener(v -> videoView.start());

        btnPause.setOnClickListener(v -> videoView.pause());

        btnForward.setOnClickListener(v -> {
            int nuevaPosicion = videoView.getCurrentPosition() + 10000;
            videoView.seekTo(nuevaPosicion);
        });

        btnRewind.setOnClickListener(v -> {
            int nuevaPosicion = videoView.getCurrentPosition() - 10000;
            videoView.seekTo(Math.max(nuevaPosicion, 0));
        });
    }
}