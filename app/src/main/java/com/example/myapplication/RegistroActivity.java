package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

@SuppressWarnings("ALL")
public class RegistroActivity extends AppCompatActivity {
    // INICIALIZAMOS LOS COMPONENTES.
    private EditText Usuario, Password, Email, Edad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    //ASIGNAMOS LOS VALORES DE CADA COMPONENTE DE LA INTERFAZ A LAS VARIABLES
    Usuario = findViewById(R.id.etUsuario);
    Password = findViewById(R.id.etPassword);
    Email = findViewById(R.id.etEmail);
    Edad = findViewById(R.id.etEdad);
    }

    public void onRegister(View view)
    {
        if (Usuario.getText().toString().isEmpty()) {
            Toast.makeText(this, "Campo Usuario Vacio", Toast.LENGTH_SHORT).show();
        }else {
            if (Password.getText().toString().isEmpty()) {
                Toast.makeText(this, "Campo Password Vacio", Toast.LENGTH_LONG).show();
            } else {
                if (Email.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Campo Email Vacio", Toast.LENGTH_LONG).show();
                } else {
                    if (Edad.getText().toString().isEmpty()) {
                        Toast.makeText(this, "Campo Edad Vacio", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "¡Formulario Completo!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        }
    }
}