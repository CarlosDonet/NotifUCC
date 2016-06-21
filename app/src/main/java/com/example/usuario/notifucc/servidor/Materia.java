package com.example.usuario.notifucc.servidor;

/**
 * Created by Carlos on May 2016.
 */
public class Materia {
    private String nombre;
    private String catedra;
    private String profesor;

    public Materia(String nombre, String catedra, String profesor) {
        this.nombre = nombre;
        this.catedra = catedra;
        this.profesor = profesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCatedra() {
        return catedra;
    }

    public void setCatedra(String catedra) {
        this.catedra = catedra;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }
}
