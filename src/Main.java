import ENUMS.Estado;
import Modelo.Cliente;
import Modelo.Habitacion;
import Modelo.Hotel;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        /// ADMIN
        // CREAR HABITACION
        // BORRAR HABITACION
        // CREAR RECEPCIONISTA
        // BORRAR RECEPCIONISTA

        Hotel hotel = new Hotel();
        Habitacion habitacion = new Habitacion(123, 123, Estado.DISPONIBLE, 123);
        hotel.agregar(habitacion);
        System.out.println(hotel.listarHabitacionesDisponibles());

        Cliente cliente = new Cliente();
        String fecha1 = "";
        String fecha2 = "";

        // Reserva reserva = recepcionista.checkIN(cliente, habitacion, fecha1, fecha2);

        // hotel.agregar(reserva);


        /// RECEPCIONISTA
        /// CHECK IN (CREAR RESERVA);
        /// CHECK OUT (BORRAR RESERVA);

        /*
        Menu
        1. CHECK IN
            DATOS CLIENTE (SCANNER, GUARDAMOS LOS DATOS EN UN OBJETO CREADO DE CLIENTE) EN EL MENU
            LISTA HABITACIONES Y ELIGE LA CORRECTA, CUANDO LA ELIJA LA IGUALA A UN OBJETO CREADO DE HABITACION VACIO => tenemos que tener un metodo que busque habitacion por numero y la retorne
            ListarHabitaciones();
            Scanner
            Habitacion habitacionElegida = hotel.buscarHabitacion(numero);
            Pide fecha con scanner

            RETURN una reserva cargada con todos estos datos

            DESPUES EL HOTEL SE ENCARGA DE CARGARLA CON HOTEL.agregar();
            el metodo check in tiene que recibir (CLIENTE, HABITACION, FECHA)

         */






    }
}