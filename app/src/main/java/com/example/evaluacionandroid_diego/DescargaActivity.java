package com.example.evaluacionandroid_diego;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import java.io.File;
import java.io.FileOutputStream;

public class DescargaActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button btnDescargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descarga);

        imageView = findViewById(R.id.imageDescarga);
        btnDescargar = findViewById(R.id.btnDescargar);

        // Mostrar imagen desde drawable
        imageView.setImageResource(R.drawable.achopr_app); // reemplaza con el nombre real

        // Compartir imagen
        btnDescargar.setOnClickListener(v -> {
            Drawable drawable = getResources().getDrawable(R.drawable.achopr_app);
            File file = new File(getCacheDir(), "imagen_compartida.png");

            try (FileOutputStream out = new FileOutputStream(file)) {
                android.graphics.Bitmap bitmap = android.graphics.drawable.BitmapDrawable.class.cast(drawable).getBitmap();
                bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, out);
                out.flush();

                Uri uri = FileProvider.getUriForFile(this, getPackageName() + ".provider", file);
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("image/png");
                intent.putExtra(Intent.EXTRA_STREAM, uri);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(Intent.createChooser(intent, "Compartir imagen"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}