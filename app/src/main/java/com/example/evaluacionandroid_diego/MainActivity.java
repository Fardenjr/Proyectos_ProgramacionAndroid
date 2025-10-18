package com.example.evaluacionandroid_diego;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnIrMapas, btnIrAudio, btnIrVideo, btnIrDescarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vincular botones del menú
        btnIrMapas = findViewById(R.id.btnIrMapas);
        btnIrAudio = findViewById(R.id.btnIrAudio);
        btnIrVideo = findViewById(R.id.btnIrVideo);
        btnIrDescarga = findViewById(R.id.btnIrDescarga);

        // Navegación a cada actividad
        btnIrMapas.setOnClickListener(v -> {
            startActivity(new Intent(this, MapaActivity.class));
        });

        btnIrAudio.setOnClickListener(v -> {
            startActivity(new Intent(this, AudioActivity.class));
        });

        btnIrVideo.setOnClickListener(v -> {
            startActivity(new Intent(this, VideoActivity.class));
        });

        btnIrDescarga.setOnClickListener(v -> {
            startActivity(new Intent(this, DescargaActivity.class));
        });
    }
}