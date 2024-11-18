package Modelo;

import org.json.JSONException;
import org.json.JSONObject;

public class Recepcionista extends Usuario {

    public Recepcionista(String nombre, String rol, String dni){
        super(nombre, rol, dni);
    }

    public  Recepcionista(){

    }


    /// JSON A RECEPCIONISTA

    public static Usuario JSONArecepcionista(JSONObject jsonObject){
        Recepcionista recepcionista = new Recepcionista();
        try{
            recepcionista.setId(jsonObject.getInt("id"));
            recepcionista.setNombre(jsonObject.getString("nombre"));
            recepcionista.setRol(jsonObject.getString("rol"));
            recepcionista.setDni(jsonObject.getString("dni"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return recepcionista;
    }


/// CHECK IN

    public Reserva crearReserva(Cliente cliente){
        Reserva nuevaReserva = new Reserva();

        if(cliente == null){
            throw new IllegalArgumentException("NULO");
        }






        return nuevaReserva;
    }




}
