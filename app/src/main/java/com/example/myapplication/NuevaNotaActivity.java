package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NuevaNotaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_nota);

        final Button BTNAgregar = findViewById(R.id.btnAgregar);
        BTNAgregar.setOnClickListener(new View.OnClickListener() {
            @Override

            //Metodo para enviar datos de una activity.
            public void onClick(View view) {
                EditText ETTitulo = findViewById(R.id.etTitulo1);
                EditText ETDescripcion = findViewById(R.id.etDescripcion);
                Intent Intent = new Intent(NuevaNotaActivity.this,  ListaNotasActivity.class);
                Intent.putExtra("titulo",ETTitulo.getText().toString());
                Intent.putExtra("descripcion",ETDescripcion.getText().toString());
                startActivity(Intent);
            }
        });
    }
}