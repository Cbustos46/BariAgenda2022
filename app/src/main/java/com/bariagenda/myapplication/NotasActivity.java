package com.bariagenda.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

@SuppressWarnings("ALL")
public class NotasActivity extends AppCompatActivity {

    private CheckBox seleccionarChk;
    private Button btn_lnotas,btn_CerrarSesión;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        final Button BTNcrear = findViewById(R.id.btnCrear);
        seleccionarChk = findViewById(R.id.chkValorar);
        btn_lnotas = findViewById(R.id.btn_lnotas);
        btn_CerrarSesión = findViewById(R.id.btnCerrarSesion);

        BTNcrear.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotasActivity.this, NuevaNotaActivity.class );
                startActivity(intent);
            }
        });

        btn_CerrarSesión.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(NotasActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btn_lnotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotasActivity.this,ListaNotasActivity.class);
                startActivity(intent);
            }
        });

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



    public void onContacto(View view) {
        Intent intent = new Intent(this, ContactosActivity.class );
        startActivity(intent);
    }




}