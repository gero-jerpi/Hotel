package Modelo;

import ENUMS.Estado;

import java.util.Objects;

public class Habitacion {

    private int numeroHabitacion;
    private int capacidad;
    private Estado estado;
    private double precioPorNoche;

///CONSTRUCTORES


    public Habitacion(int capacidad, int numeroHabitacion, Estado estado, double precioPorNoche) {
        this.capacidad = capacidad;
        this.numeroHabitacion = numeroHabitacion;
        this.estado = estado;
        this.precioPorNoche = precioPorNoche;
    }


    public  Habitacion(){

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

    public double getPrecioPorNoche() {
        return precioPorNoche;
    }

    public void setPrecioPorNoche(double precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
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
                ", precioPorNoche=" + precioPorNoche +
                '}';
    }



}
