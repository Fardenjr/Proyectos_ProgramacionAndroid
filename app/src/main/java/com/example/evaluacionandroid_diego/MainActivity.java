package com.example.evaluacionandroid_diego;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class MainActivity extends AppCompatActivity {

    private static final int CODIGO_SOLICITUD_UBICACION = 100;
    private Button btnIrMapas, btnIrAudio, btnIrVideo, btnIrDescarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIrMapas = findViewById(R.id.btnIrMapas);
        btnIrAudio = findViewById(R.id.btnIrAudio);
        btnIrVideo = findViewById(R.id.btnIrVideo);
        btnIrDescarga = findViewById(R.id.btnIrDescarga);

        guardarClaveGoogleMaps();

        btnIrMapas.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        CODIGO_SOLICITUD_UBICACION);
            } else {
                abrirMapa();
            }
        });

        btnIrAudio.setOnClickListener(v -> startActivity(new Intent(this, AudioActivity.class)));
        btnIrVideo.setOnClickListener(v -> startActivity(new Intent(this, VideoActivity.class)));
        btnIrDescarga.setOnClickListener(v -> startActivity(new Intent(this, DescargaActivity.class)));
    }

    private void abrirMapa() {
        startActivity(new Intent(this, MapaActivity.class));
    }

    private void guardarClaveGoogleMaps() {
        try {
            String masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
            SharedPreferences prefs = EncryptedSharedPreferences.create(
                    "secure_prefs",
                    masterKeyAlias,
                    this,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );

            if (!prefs.contains("google_maps_key")) {
                prefs.edit().putString("google_maps_key", "AIzaSyCH05vxMoJxDjHYDfqVQinzY2cNgu3XddE").apply();
            }

        } catch (GeneralSecurityException | IOException e) {
            Toast.makeText(this, "Error al guardar clave segura", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CODIGO_SOLICITUD_UBICACION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                abrirMapa();
            } else {
                Toast.makeText(this, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

