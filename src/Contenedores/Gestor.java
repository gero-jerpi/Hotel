package Contenedores;

import java.util.ArrayList;
import java.util.HashSet;

public class Gestor <T>{

    /// ATRIBUTOS
    private HashSet<T> lista;

    /// CONSTRUCTOR
    public Gestor(){
        this.lista = new HashSet<>();
    }


    /// METODOS
    public boolean agregar(T objeto){
        if(objeto == null){
            throw new IllegalArgumentException("NULO");
        }


        lista.add(objeto);

        return true;
    }

    public String listar(){
        StringBuilder stringBuilder = new StringBuilder();

        for (T objeto: lista){
            stringBuilder = stringBuilder.append(objeto).append("\n");
        }

        return stringBuilder.toString();
    }


    /// GETTERS && SETTERS


    public HashSet<T> getLista() {
        return lista;
    }

    public void setLista(HashSet<T> lista) {
        this.lista = lista;
    }

    /// SOBREESCRITURA
    @Override
    public String toString() {
        return "Gestor{" +
                "Lista:" + lista +
                '}';
    }



}
