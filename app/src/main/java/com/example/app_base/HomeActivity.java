package com.example.app_base;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.ShapeAppearanceModel;


public class HomeActivity extends AppCompatActivity {

    Button agregar, ver, estadisticas;
    ViewFlipper flipper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        flipper = findViewById(R.id.editflip);

        int[] banners = {
                R.drawable.banner1,
                R.drawable.banner2,
                R.drawable.banner3
        };

        for (int banner : banners) {
            ShapeableImageView img = new ShapeableImageView(this);
            img.setImageResource(banner);
            img.setScaleType(ImageView.ScaleType.FIT_CENTER);
            img.setPadding(8, 8, 8, 8);
            img.setLayoutParams(new ViewFlipper.LayoutParams(
                    ViewFlipper.LayoutParams.MATCH_PARENT,
                    ViewFlipper.LayoutParams.MATCH_PARENT
            ));

            ShapeAppearanceModel shapeModel = new ShapeAppearanceModel()
                    .toBuilder()
                    .setAllCorners(CornerFamily.ROUNDED, 32f)
                    .build();
            img.setShapeAppearanceModel(shapeModel);

            flipper.addView(img);
        }

        flipper.setFlipInterval(5000);
        flipper.setAutoStart(true);

        agregar = findViewById(R.id.btnAgregar);
        ver = findViewById(R.id.btnVer);
        estadisticas = findViewById(R.id.btnEstadisticas);

        agregar.setOnClickListener(v -> startActivity(new Intent(this, FormularioActivity.class)));
        ver.setOnClickListener(v -> startActivity(new Intent(this, VerTareasActivity.class)));
        estadisticas.setOnClickListener(v -> startActivity(new Intent(this, EstadisticasActivity.class)));

        }
}
