package com.bariagenda.myapplication;
// Clase contacto para listaContactos
public class Contacto {
    private final int imagen;
    private final String nombre;
    private final String anio;

    public Contacto(int imagen, String nombre, String anio) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.anio = anio;
    }


    public String getNombre() {
        return nombre;
    }

    public String getAnio() {
        return anio;
    }

    public int getImagen() {
        return imagen;
    }
}
