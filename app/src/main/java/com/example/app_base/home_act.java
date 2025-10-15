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


public class home_act extends AppCompatActivity {

    Button btnAgregar, btnVer, btnEstadisticas;
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


        btnAgregar = findViewById(R.id.btnAgregar);
        btnVer = findViewById(R.id.btnVer);
        btnEstadisticas = findViewById(R.id.btnEstadisticas);


    }
}
