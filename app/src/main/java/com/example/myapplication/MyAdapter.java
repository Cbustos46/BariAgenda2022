package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.ContactoViewHolder> {
    private final List<Contacto> items;

    public static class ContactoViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagen;
        public TextView nombre;
        public TextView anio;

        public ContactoViewHolder(View v) {
            super(v);
            imagen = v.findViewById(R.id.imagen);
            nombre = v.findViewById(R.id.nombre);
            anio = v.findViewById(R.id.anio);
        }
    }

    public MyAdapter(List<Contacto> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ContactoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_contactos, viewGroup, false);
        return new ContactoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ContactoViewHolder viewHolder, int i) {
        viewHolder.imagen.setImageResource(items.get(i).getImagen());
        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.anio.setText(items.get(i).getAnio());
    }
}
