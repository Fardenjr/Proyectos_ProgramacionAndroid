package com.example.evaluacionandroid_diego;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AudioActivity extends AppCompatActivity {

    private MediaPlayer reproductor;
    private Button btnReproducir, btnDetener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        btnReproducir = findViewById(R.id.btnReproducirAudio);
        btnDetener = findViewById(R.id.btnDetenerAudio);

        // Reproduce achopr.mp3 desde res/raw
        reproductor = MediaPlayer.create(this, R.raw.achopr);

        btnReproducir.setOnClickListener(v -> {
            if (!reproductor.isPlaying()) {
                reproductor.start();
                Toast.makeText(this, "Reproduciendo achopr.mp3", Toast.LENGTH_SHORT).show();
            }
        });

        btnDetener.setOnClickListener(v -> {
            if (reproductor.isPlaying()) {
                reproductor.pause();
                reproductor.seekTo(0);
                Toast.makeText(this, "Audio detenido", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (reproductor != null) {
            reproductor.release();
            reproductor = null;
        }
    }
}