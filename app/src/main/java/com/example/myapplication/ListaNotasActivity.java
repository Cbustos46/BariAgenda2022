package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

@SuppressWarnings("ALL")
public class ListaNotasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

        TextView TVTitulo = findViewById(R.id.tvTitulo);
        TextView TVDescripcion = findViewById(R.id.tvDescripcion);
        Button BTNMenu = findViewById(R.id.btnMenu);
        //Ir a Menu desde un bottom
        BTNMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaNotasActivity.this,NotasActivity.class);
                startActivity(intent);
            }
        });
        //Metodo para recibir datos de una activity.
        Bundle bundle = getIntent().getExtras();
        String titulo = bundle.getString("titulo").toString();
        String descripcion = bundle.getString("descripcion").toString();
        TVTitulo.setText(titulo);
        TVDescripcion.setText(descripcion);
    }

}