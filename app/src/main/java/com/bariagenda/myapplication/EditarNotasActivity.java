package com.bariagenda.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressWarnings("ALL")
public class EditarNotasActivity extends AppCompatActivity {

    private EditText ed_titulo,ed_descripcion,ed_id;
    private Button b_editar,b_eliminar,b_volver,b_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_notas);

        ed_titulo = findViewById(R.id.et_titulo);
        ed_descripcion = findViewById(R.id.et_descripcion);
        ed_id = findViewById(R.id.id);

        b_editar = findViewById(R.id.btn_editar);
        b_eliminar = findViewById(R.id.btn_eliminar);
        b_volver = findViewById(R.id.btn_volver);
        b_menu = findViewById(R.id.btn_menu);

        Intent i = getIntent();

        String et_id = i.getStringExtra("id").toString();
        String et_titulo = i.getStringExtra("titulo").toString();
        String et_descripcion = i.getStringExtra("descripcion").toString();

        ed_id.setText(et_id);
        ed_titulo.setText(et_titulo);
        ed_descripcion.setText(et_descripcion);

        b_editar.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                editar();
            }
        });

        b_eliminar.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                eliminar();
            }
        });

        b_volver.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent i = new Intent(EditarNotasActivity.this,ListaNotasActivity.class);
                startActivity(i);
            }
        });

        b_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EditarNotasActivity.this, NotasActivity.class);
                startActivity(i);
            }
        });
    }

    public void eliminar()
    {
        try
        {
            String id = ed_id.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("BD_NOTAS", Context.MODE_PRIVATE,null);


            String sql = "delete from Notas where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1,id);
            statement.execute();
            Toast.makeText(this,"Nota eliminada.",Toast.LENGTH_LONG).show();

            ed_titulo.setText("");
            ed_descripcion.setText("");
            ed_titulo.requestFocus();

        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Error no se pudieron realizar los cambios.",Toast.LENGTH_LONG).show();
        }
    }
    public void editar()
    {
        try
        {
            String titulo = ed_titulo.getText().toString();
            String descripcion = ed_descripcion.getText().toString();
            String id = ed_id.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("BD_NOTAS",Context.MODE_PRIVATE,null);

            String sql = "update Notas set titulo = ?,descripcion=? where id= ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,titulo);
            statement.bindString(2,descripcion);
            statement.bindString(3,id);
            statement.execute();
            Toast.makeText(this,"Nota Actualizada",Toast.LENGTH_LONG).show();

            ed_titulo.setText("");
            ed_descripcion.setText("");
            ed_titulo.requestFocus();
        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Error no se realizar los cambios.",Toast.LENGTH_LONG).show();
        }
    }
}