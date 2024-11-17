package Modelo;

import Contenedores.Gestor;
import ENUMS.Estado;


public class Hotel{

    /// ATRIBUTOS
    private int id;
    private String nombre;
    private Gestor<Habitacion> habitaciones;
    private Gestor<Reserva> reservas;
    private Gestor<Usuario> usuarios;

    private static int incremento = 0;

    /// CONSTRUCTORES
    public Hotel(String nombre){
        this.id = incremento++;
        this.nombre = nombre;
        this.habitaciones = new Gestor<>();
        this.reservas = new Gestor<>();
        this.usuarios = new Gestor<>();
    }

    public Hotel(){
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
    public boolean agregar(Object objeto){
        if(objeto instanceof Habitacion){
            habitaciones.agregar((Habitacion) objeto);
        }else if(objeto instanceof Reserva){
            reservas.agregar((Reserva) objeto);
            ((Habitacion) objeto).setEstado(Estado.OCUPADA); ///VERIFICAR LUEGO
        }else if(objeto instanceof Usuario){
            usuarios.agregar((Usuario) objeto);
        }else{
            throw new IllegalArgumentException("NO EXISTE");
        }

        return true;
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
