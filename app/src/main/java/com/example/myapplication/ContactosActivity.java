package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactosActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview);

        // Inicializar Lista Contactos
        List<Contacto> items = new ArrayList<>();

        items.add(new Contacto(R.drawable.ic_baseline_person_24, "Goku", "2022"));
        items.add(new Contacto(R.drawable.ic_baseline_person_24, "Carlos", "1997"));
        items.add(new Contacto(R.drawable.ic_baseline_person_24, "Anya", "2019"));
        items.add(new Contacto(R.drawable.ic_baseline_person_24, "Roger", "2020"));
        items.add(new Contacto(R.drawable.ic_baseline_person_24, "Sophia", "2001"));

        // Obtener el Recycler
        recycler = findViewById(R.id.myrecyclerview);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        RecyclerView.LayoutManager lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new MyAdapter(items);
        recycler.setAdapter(adapter);
    }
    public void onMap(View view) {
        Intent intent = new Intent(this, MapBoxActivity.class);
        startActivity(intent);

    }
}