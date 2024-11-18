package Modelo;

import ENUMS.Estado;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Habitacion {

    private int numeroHabitacion;
    private int capacidad;
    private Estado estado;
    private double precioNoche;

///CONSTRUCTORES


    public Habitacion(int capacidad, int numeroHabitacion, Estado estado, double precioNoche) {
        this.capacidad = capacidad;
        this.numeroHabitacion = numeroHabitacion;
        this.estado = estado;
        this.precioNoche = precioNoche;
    }


    public  Habitacion(){

    }

/// HABITACION A JSON
    public JSONObject habitacionAJSON(){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("numeroHabitacion", this.numeroHabitacion);
            jsonObject.put("capacidad", this.capacidad);
            jsonObject.put("estado", this.estado);
            jsonObject.put("precioNoche", this.precioNoche);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        return jsonObject;
    }


    //GETTERS Y SETTERS


    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public double getprecioNoche() {
        return precioNoche;
    }

    public void setprecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
    }

    ///METODO EQUALS


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Habitacion that)) return false;
        return numeroHabitacion == that.numeroHabitacion;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numeroHabitacion);
    }




    ///METODO TOSTRING


    @Override
    public String toString() {
        return "Habitacion{" +
                "numeroHabitacion=" + numeroHabitacion +
                ", capacidad=" + capacidad +
                ", estado=" + estado +
                ", precioPorNoche=" + precioNoche +
                '}';
    }



}
