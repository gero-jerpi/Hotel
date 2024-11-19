package Modelo;

import Contenedores.Gestor;
import ENUMS.Estado;
import Interfaces.IJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;


public class Hotel implements IJson {


    /// ATRIBUTOS

    private Gestor<Habitacion> habitaciones;
    private Gestor<Reserva> reservas;
    private Gestor<Usuario> usuarios;



    /// CONSTRUCTORES

    public Hotel() {
        this.habitaciones = new Gestor<>();
        this.reservas = new Gestor<>();
        this.usuarios = new Gestor<>();
    }



    /// GETTERS && SETTERS


    public Gestor<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Gestor<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Gestor<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Gestor<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Gestor<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(Gestor<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }


    /// METODOS


    public boolean agregar(Object objeto) {
        if (objeto instanceof Habitacion) {
            habitaciones.agregar((Habitacion) objeto);
        } else if (objeto instanceof Reserva) {
            reservas.agregar((Reserva) objeto);

        } else if (objeto instanceof Usuario) {
            usuarios.agregar((Usuario) objeto);
        } else {
            throw new IllegalArgumentException("NO EXISTE");
        }

        return true;
    }



    /// HOTEL A JSON

    @Override
    public JSONObject toJSON() {
        JSONObject hotel = new JSONObject();
        JSONArray habitaciones = new JSONArray();
        JSONArray usuarios = new JSONArray();
        JSONArray reservas = new JSONArray();


        try {
            for (Habitacion habitacion : getHabitaciones().getLista()) {
                habitaciones.put(habitacion.toJSON());
            }

            hotel.put("habitaciones", habitaciones);

            for (Reserva reserva : getReservas().getLista()) {
                reservas.put(reserva.reservaAJSON());
            }

            hotel.put("reservas", reservas);

            for (Usuario usuario : getUsuarios().getLista()) {
                usuarios.put(usuario.toJSON());
            }

            hotel.put("usuarios", usuarios);


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return hotel;
    }


    //metodo  de la clase recepcionista : listar ocupadas con datos de ocupantes , habitaciones disponibles, y habitaciones NO disponibles por algún motivo


    //LISTA LAS HABITACIONES OCUPADAS CON LOS DATOS DE LOS OCUPANTES ^^

    public String listarHabitacionesOcupadas() {
        StringBuilder mensaje = new StringBuilder();

        mensaje.append("Habitaciones ocupadas: \n");
        Iterator<Reserva> iteradorReserva = reservas.getLista().iterator();

        while (iteradorReserva.hasNext()) {
            Reserva reserva = iteradorReserva.next();
            if (reserva.getHabitacion().getEstado() == Estado.OCUPADA) {
                mensaje.append(reserva.getHabitacion().toString()).append("\n").append("Datos del cliente :\n").append(reserva.getCliente().toString());

            }

        }

        return mensaje.toString();

    }



    //LISTA LAS HABITACIONES DISPONIBLES

    public String listarHabitacionesDisponibles() {
        StringBuilder mensaje = new StringBuilder();

        Iterator<Habitacion> iterator = habitaciones.getLista().iterator();

        mensaje.append("Habitaciones Disponibles : \n");
        while ((iterator.hasNext())) {
            Habitacion habitacion = iterator.next();

            if (habitacion.getEstado() == Estado.DISPONIBLE || habitacion.getEstado() == Estado.RESERVADA) {
                mensaje.append(habitacion).append("\n");
            }

        }

        return mensaje.toString();
    }



    /// LISTAR RESERVAS

    public String listarReservas(){
       return reservas.listar();
    }

    //LISTAR TODAS LAS HABITACIONES

    public String listarHabitaciones()
    {
        return habitaciones.listar();
    }


    /////LISTAR RECEPCIONISTAS

    public String listarRecepcionistas()
    {
        return usuarios.listar();
    }



    //LISTA LAS HABITACIONES NO DISPONIBLES Y SU ESTADO (EL POR QUÉ DE SU INDISPONIBILIDAD - OCUPADA - MANTENIMIENTO, ETC)

    public String listarHabitacionesNOdisponibles() {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Habitaciones NO disponibles : \n");
        Iterator<Habitacion> iterator = habitaciones.getLista().iterator();
        while (iterator.hasNext()) {
            Habitacion habitacion = iterator.next();

            if (habitacion.getEstado() != Estado.DISPONIBLE) {
                mensaje.append(habitacion).append("\nMotivo : ").append(habitacion.getEstado());
            }
        }
        return mensaje.toString();
    }



    //LISTA LAS RESERVAS DE UN CLIENTE

    public String listarReservasXCliente(String dni) {
        StringBuilder mensaje = new StringBuilder();
        Iterator<Reserva> iterador = reservas.getLista().iterator();
        mensaje.append("Reservas del cliente :");
        while (iterador.hasNext()) {
            Reserva reserva = iterador.next();

            if (reserva.getCliente().getDni().equals(dni)) {
                mensaje.append(reserva).append("\n");

            }
        }
        return mensaje.toString();
    }



    ///BUSCAR HABITACION POR NUMERO

    public Habitacion buscarHabitacionXnumero(int numeroHabitacion) {

        for (Habitacion habitacion : habitaciones.getLista()) {

            if (habitacion.getNumeroHabitacion() == numeroHabitacion) {
                return habitacion;
            }
        }
        return null;
    }



    //ELIMINAR HABITACION CHEQUEARLA PROQ SE GURO LA HICE COMO EL CULO ASJDHASJKHDJKASHDJKASHDJKASHDIUHAWKJEHAKJSDHKAJSDHKJASHDKJASHDKJHASDJKHASDHKJASHDKJASHD

    public boolean eliminarHabitacion(int num)
    {
        Habitacion aux=new Habitacion();
        aux.setNumeroHabitacion(num);

        if(habitaciones.getLista().remove(aux))
        {
            return true;
        }

        return false;
    }


    /////VERIFICAR DNI USUARIO EXISTE

    public boolean verificarDniExistente(String dni)
    {
        Iterator<Usuario> iterator= usuarios.getLista().iterator();
        while(iterator.hasNext())
        {
            Usuario usuario= iterator.next();

            if(usuario instanceof Recepcionista)
            {
                if(((Recepcionista)usuario).getDni().compareToIgnoreCase(dni)==0)
                {
                    return true;
                }
            }
        }
        return false;
    }



    /// SOBREESCRITURA
    @Override
    public String toString() {
        return "Hotel{" +
                ", Habitaciones:" + habitaciones +
                ", Reservas:" + reservas +
                ", Usuarios:" + usuarios +
                '}';
    }


}
