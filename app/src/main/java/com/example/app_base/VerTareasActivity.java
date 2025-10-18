package com.example.app_base;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import models.DBHelper;
import models.Tarea;
import com.example.app_base.adapters.TareaAdapter;
import androidx.appcompat.widget.Toolbar;

public class VerTareasActivity extends AppCompatActivity {

    private RecyclerView rvPendientes, rvCompletadas;
    private Button btnToggleCompletadas;
    private DBHelper dbHelper;
    private TareaAdapter adapterPendientes, adapterCompletadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_tareas);

        rvPendientes = findViewById(R.id.rvPendientes);
        rvCompletadas = findViewById(R.id.rvCompletadas);
        btnToggleCompletadas = findViewById(R.id.btnToggleCompletadas);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        dbHelper = new DBHelper(this);

        rvPendientes.setLayoutManager(new LinearLayoutManager(this));
        rvCompletadas.setLayoutManager(new LinearLayoutManager(this));

        cargarTareas();

        btnToggleCompletadas.setOnClickListener(v -> {
            if (rvCompletadas.getVisibility() == View.GONE) {
                rvCompletadas.setVisibility(View.VISIBLE);
                btnToggleCompletadas.setText("Ocultar tareas completadas");
            } else {
                rvCompletadas.setVisibility(View.GONE);
                btnToggleCompletadas.setText("Ver tareas completadas");
            }
        });
    }

    private void cargarTareas() {
        List<Tarea> pendientes = new ArrayList<>();
        List<Tarea> completadas = new ArrayList<>();

        Cursor cursor = dbHelper.obtenerTareas();
        if (cursor.moveToFirst()) {
            do {
                Tarea tarea = new Tarea(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("titulo")),
                        cursor.getString(cursor.getColumnIndexOrThrow("descripcion")),
                        cursor.getString(cursor.getColumnIndexOrThrow("fecha")),
                        cursor.getString(cursor.getColumnIndexOrThrow("prioridad")),
                        cursor.getString(cursor.getColumnIndexOrThrow("estado"))
                );

                if (tarea.getEstado().equals("pendiente")) {
                    pendientes.add(tarea);
                } else {
                    completadas.add(tarea);
                }

            } while (cursor.moveToNext());
        }
        cursor.close();

        adapterPendientes = new TareaAdapter(this, pendientes, tarea -> {
            dbHelper.actualizarEstadoTarea(tarea.getId(), "completada");
            cargarTareas(); // Recarga las listas
        });

        adapterCompletadas = new TareaAdapter(this, completadas, null); // No necesita listener

        rvPendientes.setAdapter(adapterPendientes);
        rvCompletadas.setAdapter(adapterCompletadas);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
