package com.bariagenda.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressWarnings("ALL")
public class RegistroActivity extends AppCompatActivity {
    // INICIALIZAMOS LOS COMPONENTES.
    private EditText usuario, password, Email, Edad;
    private Button registrar;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

    //ASIGNAMOS LOS VALORES DE CADA COMPONENTE DE LA INTERFAZ A LAS VARIABLES
    usuario = findViewById(R.id.etUsuario);
    password = findViewById(R.id.etPassword);
    registrar= findViewById(R.id.btnRegistrarse);
    DB = new DBHelper(this);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user =  usuario.getText().toString();
                String pass = password.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(RegistroActivity.this, "Completa  todo los campos", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkuser = DB.verificarUsuario(user);
                    if (checkuser == false) {
                        Boolean insert = DB.insertarDatos(user, pass);
                        if (insert == true) {
                            Toast.makeText(RegistroActivity.this, "Registro Completado", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegistroActivity.this, "Registro fallido", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegistroActivity.this, "Usuario ya existe", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


}