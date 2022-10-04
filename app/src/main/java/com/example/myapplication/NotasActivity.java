package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class NotasActivity extends AppCompatActivity {

    private CheckBox seleccionarChk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        seleccionarChk = findViewById(R.id.chkValorar);
    }

    public void btnvotar (View view){
        if (seleccionarChk.isChecked()){
            String mensaje = "Gracias por valorar!";
            Toast toast = Toast.makeText(this,mensaje,Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            String mensaje = "Seleccione por favor";
            Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
        }
    }

    public void onCrear(View view) {
        Intent intent = new Intent(this, NuevaNotaActivity.class );
        startActivity(intent);
    }

    public void onContacto(View view) {
        Intent intent = new Intent(this, ContactosActivity.class );
        startActivity(intent);
    }

    public void onLista(View view)
    {
        Intent intent = new Intent(this, ListaNotasActivity.class );
        startActivity(intent);

    }

    public void onGuardar(View view) {
    }
}