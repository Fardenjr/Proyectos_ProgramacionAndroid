package com.example.evaluacionandroid_diego;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final int CODIGO_SOLICITUD_UBICACION = 100;
    private GoogleMap mapa;
    private FusedLocationProviderClient proveedorUbicacion;
    private boolean modoAgregarMarcador = false;

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
        verificarPermisosUbicacion();
    }

    private void verificarPermisosUbicacion() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    CODIGO_SOLICITUD_UBICACION);
        } else {
            activarUbicacionUsuario();
        }
    }

    private void activarUbicacionUsuario() {
        if (mapa != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mapa.setMyLocationEnabled(true);
                centrarEnUbicacionActual();
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CODIGO_SOLICITUD_UBICACION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                activarUbicacionUsuario();
            } else {
                Toast.makeText(this, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}