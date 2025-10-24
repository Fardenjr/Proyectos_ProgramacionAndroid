package com.example.evaluacionandroid_diego;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class MapaActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mapa;
    private FusedLocationProviderClient proveedorUbicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        SupportMapFragment fragmentoMapa = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);

        if (fragmentoMapa != null) {
            fragmentoMapa.getMapAsync(this);
        } else {
            Toast.makeText(this, "No se pudo cargar el mapa", Toast.LENGTH_SHORT).show();
        }

        proveedorUbicacion = LocationServices.getFusedLocationProviderClient(this);

        String apiKey = obtenerClaveGoogleMaps();
        if (apiKey != null) {
            // Aquí podrías usar la clave en APIs que la requieran directamente
            // Por ejemplo: MapsInitializer.initialize(this, MapsInitializer.Renderer.LATEST, null);
        } else {
            Toast.makeText(this, "No se pudo obtener la clave de Google Maps", Toast.LENGTH_SHORT).show();
        }
    }

    private String obtenerClaveGoogleMaps() {
        try {
            String masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
            SharedPreferences prefs = EncryptedSharedPreferences.create(
                    "secure_prefs",
                    masterKeyAlias,
                    this,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
            return prefs.getString("google_maps_key", null);
        } catch (GeneralSecurityException | IOException e) {
            return null;
        }
    }

    private void activarUbicacionUsuario() {
        if (mapa != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mapa.setMyLocationEnabled(true);
                centrarEnUbicacionActual();
            } else {
                Toast.makeText(this, "Permisos insuficientes para mostrar ubicación", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void centrarEnUbicacionActual() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        proveedorUbicacion.getLastLocation()
                .addOnSuccessListener(this, ubicacion -> {
                    if (ubicacion != null) {
                        LatLng coordenadas = new LatLng(ubicacion.getLatitude(), ubicacion.getLongitude());
                        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(coordenadas, 15));
                        mapa.addMarker(new MarkerOptions().position(coordenadas).title("Tu ubicación"));
                    } else {
                        Toast.makeText(this, "No se pudo obtener la ubicación actual", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mapa = googleMap;
        activarUbicacionUsuario();
        mapa.setOnMapClickListener(posicion -> {
            mapa.addMarker(new MarkerOptions()
                    .position(posicion)
                    .title("Marcador personalizado"));
        });
    }
}
