package com.bariagenda.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class ListaNotasActivity extends AppCompatActivity {

    private ListView lst1;
    private ArrayList<String> arreglo = new ArrayList<String>();
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);


            SQLiteDatabase db = openOrCreateDatabase("BD_NOTAS", Context.MODE_PRIVATE,null);
            lst1 = findViewById(R.id.lst1);
            final Cursor c = db.rawQuery("select * from Notas",null);
            int id = c.getColumnIndex("id");
            int titulo = c.getColumnIndex("titulo");
            int descripcion = c.getColumnIndex("descripcion");
            arreglo.clear();

            arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arreglo);

            lst1.setAdapter(arrayAdapter);

            final  ArrayList<Notas> lista = new ArrayList<Notas>();


            if(c.moveToFirst())
            {
                do{
                    Notas nota = new Notas();
                    nota.id = c.getString(id);
                    nota.titulo= c.getString(titulo);
                    nota.descripcion = c.getString(descripcion);
                    lista.add(nota);

                    arreglo.add(c.getString(id) + " \t " + c.getString(titulo) + " \t "  + c.getString(descripcion) + " \t ");

                } while(c.moveToNext());
                arrayAdapter.notifyDataSetChanged();
                lst1.invalidateViews();
            }

            lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, android.view.View view, int position, long l) {
                    Notas nota = lista.get(position);
                    Intent i = new Intent(getApplicationContext(), EditarNotasActivity.class);
                    i.putExtra("id",nota.id);
                    i.putExtra("titulo",nota.titulo);
                    i.putExtra("descripcion",nota.descripcion);
                    startActivity(i);
                }
            });
        }
}