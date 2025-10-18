package com.example.app_base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import models.Administrador;

public class MainActivity extends AppCompatActivity {

    // Declaracion de variables

    private EditText username, password;

    // Instancia de clase Administrador

    private Administrador admin = new Administrador();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Llamar metodos por ID

        username = findViewById(R.id.editUser);
        password = findViewById(R.id.editPass);

    }

    //Metodo - Iniciar Sesión

    public void IniciarSesion (View view)
    {
        String inputUser = username.getText().toString().trim();
        String inputPass = password.getText().toString().trim();


        if (inputUser.equals(admin.getUsername()) && inputPass.equals(admin.getPassword())) {
            Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText (this,"Usuario o contraseña incorrecta",Toast.LENGTH_LONG).show();

        }


    }
}