import ENUMS.Estado;
import Excepciones.DatosHabitacionInvalidosExcepcion;
import Modelo.*;
import UI.Login;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws DatosHabitacionInvalidosExcepcion {

    Login login = new Login();
    login.elegirMenu();

/*
        Hotel hotel = new Hotel();
        Habitacion habitacion = new Habitacion(123, 123, Estado.DISPONIBLE, 123);
        hotel.agregar(habitacion);

        Cliente cliente = new Cliente("1234", "gero", "asd", "asd");
        String fecha1 = "LocalDate.now();";
        String fecha2 = "LocalDate.now();";

        Recepcionista recepcionista = new Recepcionista("gero", "jungla", "1234");

        hotel.setNombre("HOTEL");

        Reserva reserva = new Reserva(habitacion, cliente, fecha1, fecha2);

        hotel.agregar(reserva);
        hotel.agregar(recepcionista);
        JsonUtils.escribir(hotel.hotelAJSON(), "hotel.json");





        /// ADMIN
        // CREAR HABITACION
        // BORRAR HABITACION
        // CREAR RECEPCIONISTA
        // BORRAR RECEPCIONISTA



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