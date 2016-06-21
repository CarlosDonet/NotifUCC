package com.example.usuario.notifucc.servidor;

/**
 * Created by Carlos on May 2016.
 */
public class Notificacion {

    private String emisor;
    private String texto;
    private String grupo;
    private String titulo;

    public Notificacion(String emisor, String texto, String grupo, String titulo) {
        this.emisor = emisor;
        this.texto = texto;
        this.grupo = grupo;
        this.titulo = titulo;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
