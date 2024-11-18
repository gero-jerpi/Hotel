package Modelo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.Objects;

public class Reserva {

    private int id;
    private static int idIncremental; //VERIFICAR LUEGO//
    private Habitacion habitacion;
    private Cliente cliente; //CLIENTE O ID CLIENTE?
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    private int cantidadPersonas;


    //CONSTRUCTORES

    public Reserva(Habitacion habitacion,Cliente cliente, LocalDate fechaInicio, LocalDate fechaFin) {
        this.habitacion = habitacion;
        this.id = idIncremental++;
        this.cliente = cliente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Reserva() {

    }

    /// RESERVA A JSON
    public JSONObject reservaAJSON(){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("id", this.id);
            jsonObject.put("habitacion", this.habitacion.habitacionAJSON());
            jsonObject.put("cliente", this.cliente.clienteAJSON());
            jsonObject.put("fechaInicio", this.fechaInicio);
            jsonObject.put("fechaFin", this.fechaFin);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        return jsonObject;
    }



    ///GETTERS Y SETTERS


    public int getId() {
        return id;
    }


    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }



    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Cliente getCliente() {
        return cliente;
    }



    ///METODO EQUALS


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reserva reserva)) return false;
        return id == reserva.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

///METODO TOSTRING


    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", habitacion=" + habitacion +
                ", clientes=" + cliente +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                '}';
    }




}



