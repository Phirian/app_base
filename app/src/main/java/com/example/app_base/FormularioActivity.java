package com.example.app_base;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import models.DBHelper;
import java.util.Calendar;
import androidx.appcompat.widget.Toolbar;


public class FormularioActivity extends AppCompatActivity {

    EditText etTitulo, etDescripcion, etFecha;
    Spinner spinnerPrioridad;
    Button btnGuardar;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        // Referencias UI
        etTitulo = findViewById(R.id.etTitulo);
        etDescripcion = findViewById(R.id.etDescripcion);
        etFecha = findViewById(R.id.etFecha);
        spinnerPrioridad = findViewById(R.id.spinnerPrioridad);
        btnGuardar = findViewById(R.id.btnGuardar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        dbHelper = new DBHelper(this);

        // Spinner para seleccionar prioridad
        String[] prioridades = {"Alta", "Media", "Baja"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, prioridades);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPrioridad.setAdapter(adapter);

        // Calendario para seleccionar fecha
        etFecha.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            int año = c.get(Calendar.YEAR);
            int mes = c.get(Calendar.MONTH);
            int dia = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dpd = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                String fecha = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year);
                etFecha.setText(fecha);
            }, año, mes, dia);
            dpd.show();
        });

        // Guardar tarea
        btnGuardar.setOnClickListener(v -> {
            String titulo = etTitulo.getText().toString().trim();
            String descripcion = etDescripcion.getText().toString().trim();
            String fecha = etFecha.getText().toString().trim();
            String prioridad = spinnerPrioridad.getSelectedItem().toString();

            if (titulo.isEmpty() || fecha.isEmpty()) {
                Toast.makeText(this, "Completa título y fecha", Toast.LENGTH_SHORT).show();
                return;
            }

            dbHelper.insertarTarea(titulo, descripcion, fecha, prioridad);
            Toast.makeText(this, "Tarea guardada", Toast.LENGTH_SHORT).show();
            finish(); // Volver atrás
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
