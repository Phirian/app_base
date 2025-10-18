package com.example.app_base;

import static android.graphics.Color.WHITE;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import models.DBHelper;

public class EstadisticasActivity extends AppCompatActivity {

    TextView txtProgreso, txtPorcentaje, txtMensaje;
    ProgressBar progresoTareas;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        txtProgreso = findViewById(R.id.txtProgreso);
        txtPorcentaje = findViewById(R.id.txtPorcentaje);
        txtMensaje = findViewById(R.id.txtMensaje);
        progresoTareas = findViewById(R.id.progresoTareas);

        dbHelper = new DBHelper(this);

        // Obtener datos
        int total = dbHelper.contarTareas();
        int completadas = dbHelper.contarTareasPorEstado("completada");

        if (total == 0) {
            txtProgreso.setText("Aún no has agregado tareas");
            txtPorcentaje.setText("0% completado");
            progresoTareas.setProgress(0);
            progresoTareas.setProgressTintList(ColorStateList.valueOf(Color.GRAY));
            txtMensaje.setText("¡Agrega tu primera tarea para comenzar!");
            txtMensaje.setTextColor(Color.GRAY);
        } else {
            int porcentaje = (int) ((completadas * 100.0f) / total);
            txtProgreso.setText("Progreso: " + completadas + " de " + total + " tareas completadas (" + porcentaje + "%)");
            txtPorcentaje.setText(porcentaje + "% completado");
            progresoTareas.setProgress(porcentaje);

            //Colores para la barra de progreso
            int color = Color.WHITE;
            String mensaje;

            if (porcentaje < 40) {
                mensaje = "¡Vamos que se puede!";
            } else if (porcentaje < 70) {
                mensaje = "¡Llevas buen ritmo!! Sigue así!!";
            } else {
                mensaje = "¡Excelente trabajo!";
            }

            progresoTareas.setProgressTintList(ColorStateList.valueOf(color));
            txtPorcentaje.setTextColor(color);
            txtMensaje.setText(mensaje);
            txtMensaje.setTextColor(color);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
