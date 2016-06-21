package com.example.usuario.notifucc.servidor;

import java.io.Serializable;

/**
 * Created by Carlos on May 2016.
 */
public class Usuario implements Serializable{
    private String nombre;
    private String apellido;
    private int clave;
    private String password;

    public Usuario(int clave, String password) {
        this.clave = clave;
        this.password = password;
    }

    public Usuario(String nombre, String apellido, int clave, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.clave = clave;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
