package Modelo;

import Contenedores.Gestor;
import ENUMS.Estado;

import java.util.Iterator;


public class Hotel {

    /// ATRIBUTOS
    private int id;
    private String nombre;
    private Gestor<Habitacion> habitaciones;
    private Gestor<Reserva> reservas;
    private Gestor<Usuario> usuarios;

    private static int incremento = 0;

    /// CONSTRUCTORES
    public Hotel(String nombre) {
        this.id = incremento++;
        this.nombre = nombre;
        this.habitaciones = new Gestor<>();
        this.reservas = new Gestor<>();
        this.usuarios = new Gestor<>();
    }

    public Hotel() {
        this.id = incremento++;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    /// METODOS
    public boolean agregar(Object objeto) {
        if (objeto instanceof Habitacion) {
            habitaciones.agregar((Habitacion) objeto);
        } else if (objeto instanceof Reserva) {
            reservas.agregar((Reserva) objeto);
            ((Habitacion) objeto).setEstado(Estado.OCUPADA); ///VERIFICAR LUEGO
        } else if (objeto instanceof Usuario) {
            usuarios.agregar((Usuario) objeto);
        } else {
            throw new IllegalArgumentException("NO EXISTE");
        }

        return true;
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
                mensaje.append("Habitación NRO ").append(reserva.getHabitacion().getNumeroHabitacion()).append("\n").append(reserva.getHabitacion().toString()).append("\n").append("Datos del cliente :\n").append(reserva.getCliente().toString());

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

            if (habitacion.getEstado() == Estado.DISPONIBLE) {
                mensaje.append("Habitacion NRO ").append(habitacion.getNumeroHabitacion()).append("\n").append(habitacion.toString());


            }

        }

        return mensaje.toString();
    }

    //LISTA LAS HABITACIONES NO DISPONIBLES Y SU ESTADO (EL POR QUÉ DE SU INDISPONIBILIDAD - OCUPADA - MANTENIMIENTO, ETC)
    public String listarHabitacionesNOdisponibles() {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Habitaciones NO disponibles : \n");
        Iterator<Habitacion> iterator = habitaciones.getLista().iterator();
        while (iterator.hasNext()) {
            Habitacion habitacion = iterator.next();

            if (habitacion.getEstado() != Estado.DISPONIBLE) {
                mensaje.append("Habitacion NRO ").append(habitacion.getNumeroHabitacion()).append("\n").append(habitacion.toString()).append("\nMotivo : ").append(habitacion.getEstado());
            }
        }
        return mensaje.toString();
    }

    //LISTA LAS RESERVAS DE UN CLIENTE

    public String listarReservasXCliente(int id) {
        StringBuilder mensaje = new StringBuilder();
        Iterator<Reserva> iterador = reservas.getLista().iterator();
        mensaje.append("Reservas del cliente :");
        while (iterador.hasNext()) {
            Reserva reserva = iterador.next();

            if (reserva.getCliente().getId() == id) {
                mensaje.append(reserva.toString()).append("\n");

            }
        }
        return mensaje.toString();
    }


    /// SOBREESCRITURA
    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", habitaciones=" + habitaciones +
                ", reservas=" + reservas +
                ", usuarios=" + usuarios +
                '}';
    }


}
