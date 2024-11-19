package Modelo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.Objects;

public class Reserva {


    /// ATRIBUTOS

    private int id;

    private Habitacion habitacion;
    private Cliente cliente; //CLIENTE O ID CLIENTE?
    private String fechaInicio;
    private String fechaFin;
    private int cantidadPersonas;

    private static int idIncremental; //VERIFICAR LUEGO//



    //CONSTRUCTORES


    public Reserva(Habitacion habitacion, Cliente cliente, String fechaInicio, String fechaFin) {
        this.habitacion = habitacion;
        this.id = idIncremental++;
        this.cliente = cliente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Reserva() {
        this.id = idIncremental++;
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



    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getIdIncremental() {
        return idIncremental;
    }

    public static void setIdIncremental(int idIncremental) {
        Reserva.idIncremental = idIncremental;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }




    ///METODOS DE LA CLASE


    public  boolean verificarFecha(String fecha){
        return fecha.matches("\\d{4}-\\d{2}-\\d{2}");
    }


    /// JSON A RESERVA

    public static Reserva JSONAreserva(JSONObject jsonObject){
        Reserva reserva = new Reserva();

        try{
            Cliente cliente = Cliente.JSONAcliente(jsonObject.getJSONObject("cliente"));
            Habitacion habitacion = Habitacion.JSONAhabitacion(jsonObject.getJSONObject("habitacion"));


            reserva.setId(jsonObject.getInt("id"));
            reserva.setCliente(cliente);
            reserva.setFechaInicio(jsonObject.getString("fechaInicio"));
            reserva.setFechaFin(jsonObject.getString("fechaFin"));
            reserva.setHabitacion(habitacion);


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return reserva;
    }



    /// RESERVA A JSON

    public JSONObject reservaAJSON(){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("id", this.id);
            jsonObject.put("habitacion", this.habitacion.toJSON());
            jsonObject.put("cliente", this.cliente.toJSON());
            jsonObject.put("fechaInicio", this.fechaInicio);
            jsonObject.put("fechaFin", this.fechaFin);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        return jsonObject;
    }



    /// SOBREESCRITURA


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


    @Override
    public String toString() {
        return "Reserva{" +
                "Id:" + id +
                ", Habitacion:" + habitacion +
                ", Clientes:" + cliente +
                ", FechaInicio:" + fechaInicio +
                ", FechaFin:" + fechaFin +
                '}';
    }


}



