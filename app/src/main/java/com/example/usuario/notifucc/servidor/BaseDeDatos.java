package com.example.usuario.notifucc.servidor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Carlos on May 2016.
 */
public class BaseDeDatos implements Serializable{

    private ArrayList<Usuario> tabla_usuarios = new ArrayList<>(Arrays.asList(
            new Usuario("Carolina","Ponce",0123456,"0123456"),
            new Usuario("Jose","Diaz",1234567,"1234567"),
            new Usuario("Juan","Ramirez",2345678,"2345678"),
            new Usuario("Luis","Campos",3456789,"3456789")
    ));

    private ArrayList<Materia> tabla_materias = new ArrayList<>(Arrays.asList(
            new Materia("Analisis Matematico I", "A", "Jose Diaz"),
            new Materia("Fisica II", "A", "Jorge Castillo"),
            new Materia("Seminario Teologico", "B", "Cesar Silupu"),
            new Materia("Logica de Programación", "C", "Jose Diaz")
    ));

    public Usuario buscarUsuario(int clave, String password){
        for (int i = 0; i < tabla_usuarios.size(); i++) {
            if((tabla_usuarios.get(i).getClave() == clave) && (tabla_usuarios.get(i).getPassword().equals(password))){
                return tabla_usuarios.get(i);
            }
        }
        return null;
    }

    public ArrayList<String> buscarMaterias(String nombreProfesor){
        ArrayList<String> materias = new ArrayList<>();
        String profesor;
        for (int i = 0; i < tabla_materias.size(); i++) {
            profesor = tabla_materias.get(i).getProfesor();
            if(profesor.equals(nombreProfesor)){
                materias.add(tabla_materias.get(i).getNombre());
            }
        }
        return materias;
    }

    public void addUsuario(Usuario user){
        tabla_usuarios.add(user);
    }
}
