package com.example.evaluacionandroid_diego;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private boolean modoAgregarMarcador = false;
    private FloatingActionButton botonMarcador;
    private static final int CODIGO_SOLICITUD_UBICACION = 100;

    private GoogleMap mapa;
    private FusedLocationProviderClient proveedorUbicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonMarcador = findViewById(R.id.btnAgregarMarcador);

        SupportMapFragment fragmentoMapa = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);

        if (fragmentoMapa != null) {
            fragmentoMapa.getMapAsync(this);
        } else {
            Toast.makeText(this, "No se pudo cargar el mapa", Toast.LENGTH_SHORT).show();
        }

        proveedorUbicacion = LocationServices.getFusedLocationProviderClient(this);

        verificarPermisosUbicacion();

        botonMarcador.setOnClickListener(view -> {
            modoAgregarMarcador = !modoAgregarMarcador;

            if (modoAgregarMarcador) {
                Toast.makeText(this, "Toca el mapa para agregar un marcador", Toast.LENGTH_SHORT).show();
                botonMarcador.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
            } else {
                Toast.makeText(this, "Modo de marcador desactivado", Toast.LENGTH_SHORT).show();
                botonMarcador.setImageResource(android.R.drawable.ic_input_add);
            }
        });
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
            if (modoAgregarMarcador) {
                mapa.addMarker(new MarkerOptions()
                        .position(posicion)
                        .title("Marcador personalizado"));
            }
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
