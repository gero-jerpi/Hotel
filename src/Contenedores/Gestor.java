package Contenedores;

import java.util.ArrayList;

public class Gestor <T>{

    /// ATRIBUTOS
    private ArrayList<T> lista;

    /// CONSTRUCTOR
    public Gestor(){
        this.lista = new ArrayList<>();
    }


    /// METODOS
    public boolean agregar(T objeto){
        if(objeto == null){
            throw new IllegalArgumentException("NULO");
        }

        if(lista.contains(objeto)){
            throw new IllegalArgumentException("CONTIENE");
        }

        lista.add(objeto);

        return true;
    }



    /// GETTERS && SETTERS
    public ArrayList<T> getLista() {
        return lista;
    }

    public void setLista(ArrayList<T> lista) {
        this.lista = lista;
    }


    /// SOBREESCRITURA
    @Override
    public String toString() {
        return "Gestor{" +
                "lista=" + lista +
                '}';
    }





}
