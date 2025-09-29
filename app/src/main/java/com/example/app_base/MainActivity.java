package com.example.app_base;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declaracion de variables

    private EditText username, password;
    private TextView titulo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Llamar metodos por ID

        username = findViewById(R.id.editUser);
        password = findViewById(R.id.editPass);
        titulo = findViewById(R.id.txtDesc);
    }

    //Metodo - Iniciar Sesión

    public void IniciarSesion (View view)
    {
        String usuario = username.getText().toString();
        String pass = password.getText().toString();


    }
}