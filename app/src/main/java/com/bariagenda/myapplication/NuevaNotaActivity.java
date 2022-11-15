package com.bariagenda.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

@SuppressWarnings("ALL")
public class NuevaNotaActivity extends AppCompatActivity {

    private EditText ed_titulo,ed_descripcion;
    private Button b_agregar,b_ver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_nota);

        ed_titulo = findViewById(R.id.et_titulo);
        ed_descripcion = findViewById(R.id.et_descripcion);

        b_agregar = findViewById(R.id.btn_agregar);
        b_ver = findViewById(R.id.btn_ver);

        b_ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), ListaNotasActivity.class);
                startActivity(i);
            }
        });
        b_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertar();
            }
        });
    }

    public void insertar()
    {
        try
        {
            String titulo = ed_titulo.getText().toString();
            String descripcion = ed_descripcion.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("BD_NOTAS", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS Notas(id INTEGER PRIMARY KEY AUTOINCREMENT,titulo VARCHAR,descripcion VARCHAR)");

            String sql = "insert into Notas(titulo,descripcion)values(?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,titulo);
            statement.bindString(2,descripcion);
            statement.execute();
            Toast.makeText(this,"Nota creada exitosamente",Toast.LENGTH_LONG).show();

            ed_titulo.setText("");
            ed_descripcion.setText("");
            ed_titulo.requestFocus();
        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Error no se pudieron realizar los cambios",Toast.LENGTH_LONG).show();
        }
    }
}