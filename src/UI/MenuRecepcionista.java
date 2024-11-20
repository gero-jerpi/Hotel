package UI;

import ENUMS.Estado;
import Excepciones.FechaInvalidaException;
import Excepciones.HabitacionNoDisponibleException;
import Modelo.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.xml.transform.Source;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class MenuRecepcionista {


    /// Constructor
    public MenuRecepcionista() {

    }


    /// Metodos

    public void menu() {
        /// LECTURA DE JSON

        Hotel hotel = new Hotel();
        Recepcionista recepcionista = new Recepcionista();
        try {
            JSONObject datosJSON = new JSONObject(JsonUtils.leer("hotel.json"));


            JSONArray habitaciones = datosJSON.getJSONArray("habitaciones");

            for (int i = 0; i < habitaciones.length(); i++) {
                Habitacion habitacion = Habitacion.JSONAhabitacion(habitaciones.getJSONObject(i));
                hotel.agregar(habitacion);
            }

            JSONArray usuarios = datosJSON.getJSONArray("usuarios");

            for (int i = 0; i < usuarios.length(); i++) {
                Usuario usuario = Recepcionista.JSONArecepcionista(usuarios.getJSONObject(i));
                hotel.agregar(usuario);
            }

            JSONArray reservas = datosJSON.getJSONArray("reservas");

            for (int i = 0; i < reservas.length(); i++) {
                Reserva reserva = Reserva.JSONAreserva(reservas.getJSONObject(i));
                hotel.agregar(reserva);
            }


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        Scanner scanner = new Scanner(System.in);


        int opcion;

        String ANSI_RESET = "\u001B[0m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_RED = "\u001B[31m";


        do {
            System.out.println("""
                    
                    Elige una de las siguientes opciones:
                    ________
                    """ + ANSI_BLUE + "CHEQUEOS\n" + ANSI_RESET + """
                    --------
                    [1] Check in
                    [2] Check out
                    
                    ________
                    """ + ANSI_GREEN + "RESERVAS\n" + ANSI_RESET + """
                    --------
                    [3] Listar reservas
                    [4] Listar reservas por cliente
                    
                    ____________
                    """ + ANSI_YELLOW + "HABITACIONES\n" + ANSI_RESET + """
                    ------------
                    [5] Listar habitaciones disponibles
                    [6] Listar habitaciones ocupadas
                    [7] Listar habitaciones no disponibles
                    [8] Cambiar estado de una habitación
                    
                    ______
                    """ + ANSI_RED + "EXTRAS\n" + ANSI_RESET + """
                    ------
                    9) Guardar datos
                    0) Finalizar ejecución
                    """);

            opcion = scanner.nextInt();

            switch (opcion) {

                case 1: {

                    Cliente clienteNuevo = new Cliente();


                    System.out.println("Ingrese el DNI del Cliente");
                    String dni = scanner.next();
                    if (!verificarNoContieneLetras(dni) || !verificarLongitudDNI(dni)) {
                        throw new IllegalArgumentException("ERROR:DNI INVALIDO\n");
                    }
                    clienteNuevo.setDni(dni);


                    System.out.println("Ingrese el nombre del Cliente");
                    scanner.nextLine();
                    String nombre = scanner.nextLine();
                    nombre = capitalizarPrimerCaracter(nombre); //Transformo su primer caracter a mayus y el resto a minus
                    if (!verificar3MasCaracteres(nombre) || !verificarNoContieneNumeros(nombre)) { //Verifico q el nombre tenga mas de 3 caracteres y q no contenga numeros
                        throw new IllegalArgumentException("ERROR:NOMBRE INVALIDO");
                    }
                    clienteNuevo.setNombre(nombre);


                    System.out.println("Ingrese el domicilio del Cliente");
                    String domicilio = scanner.nextLine();
                    if (!verificar3MasCaracteres(domicilio)) {///PODRIA TENER VERIFICACIONES PARA Q SEA UNA DIRECCIÓN REAL EJ:DAIREAUX 1827
                        throw new IllegalArgumentException("ERROR:DOMICILIO INVALIDO");
                    }
                    clienteNuevo.setDomicilio(domicilio);

                    System.out.println("Ingrese el origen del Cliente");
                    String origen = scanner.nextLine();
                    if (!verificarNoContieneNumeros(origen) || !verificar3MasCaracteres(origen)) {
                        throw new IllegalArgumentException("ERROR:ORIGEN INVALIDO");
                    }
                    clienteNuevo.setOrigen(origen);

                    System.out.println("\nIngrese el NRO de habitacion a elegir");
                    System.out.println(hotel.listarHabitacionesDisponibles());


                    try {
                        Habitacion habitacionNueva = hotel.buscarHabitacionXnumero(scanner.nextInt());
                        if (habitacionNueva.getEstado() != Estado.DISPONIBLE && habitacionNueva.getEstado() != Estado.RESERVADA) {
                            throw new HabitacionNoDisponibleException();
                        }
                        Reserva nuevaReserva = recepcionista.crearReserva(clienteNuevo, habitacionNueva); //CREO LA RESERVA
                        scanner.nextLine();

                        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                        Date fechaActual = new Date();
                        Date fechaInicio = formatoFecha.parse(nuevaReserva.getFechaInicio());
                        fechaActual = formatoFecha.parse(formatoFecha.format(fechaActual));
                        fechaInicio = formatoFecha.parse(formatoFecha.format(fechaInicio));

                        if(fechaInicio.equals(fechaActual)){
                            nuevaReserva.getHabitacion().setEstado(Estado.OCUPADA);
                        }else if(fechaInicio.compareTo(fechaActual) >= 0){
                            nuevaReserva.getHabitacion().setEstado(Estado.RESERVADA);
                        }else{
                            System.out.println("La fecha de inicio ya ha pasado");
                            break;
                        }

                        hotel.agregar(nuevaReserva); //Agrego la reserva a la lista

                    } catch (FechaInvalidaException | HabitacionNoDisponibleException | ParseException e) {
                        throw new RuntimeException(e);
                    }


                    break;
                }
                case 2: {

                    System.out.println("Ingrese ID de reserva para terminar la reserva.");
                    System.out.println(hotel.listarReservas());
                    ///ACA IRIA LISTAR RESERVAS
                    Reserva reservaAux = new Reserva();
                    reservaAux.setId(scanner.nextInt());
                    scanner.nextLine();

                    if (!hotel.getReservas().getLista().contains(reservaAux)) {
                        throw new IllegalArgumentException("ERROR:ID DE RESERVA NO EXISTE"); /// ESTA VERIFICACION NO LA VEO NECESARIA
                    }

                    Iterator<Reserva> iterator = hotel.getReservas().getLista().iterator();

                    while (iterator.hasNext()) {
                        Reserva reservaActual = iterator.next();
                        Habitacion habitacion = reservaActual.getHabitacion();

                        if (reservaActual.equals(reservaAux)) { //SI LAS RESERVAS COINCIDEN
                            habitacion.setEstado(Estado.DISPONIBLE); //HABITACION AHORA ESTÁ DISPONIBLE
                            iterator.remove();  // REMUEVO LA RESEERVA
                            System.out.println("Check-out realizado con éxito.");
                        }
                    }

                    break;
                }


                case 3: {

                    System.out.println(hotel.listarReservas());

                    break;
                }

                case 4: {
                    System.out.println("Ingrese el DNI del cliente");
                    System.out.println(hotel.listarReservasXCliente(scanner.next()));
                    scanner.nextLine();

                    break;
                }

                case 5: {
                    System.out.println(hotel.listarHabitacionesDisponibles());
                    break;
                }

                case 6: {
                    System.out.println(hotel.listarHabitacionesOcupadas());
                    break;
                }

                case 7: {
                    System.out.println(hotel.listarHabitacionesNOdisponibles());
                    break;
                }

                case 8: {
                    System.out.println(hotel.listarHabitaciones());
                    System.out.println("Ingrese el número de habitación que desea modificar :");
                    Habitacion habitacionAux = hotel.buscarHabitacionXnumero(scanner.nextInt());

                    if (habitacionAux == null) {
                        throw new IllegalArgumentException("ERROR:NUMERO INCORRECTO");
                    }
                    scanner.nextLine();
                    menuEstadosParaModificar();
                    String estadoHabitacion = scanner.nextLine().toUpperCase(); //El cliente ingresa un estado de habitación (ignora minusculas supongo)


                    try {
                        Estado estado = Estado.valueOf(estadoHabitacion);   //creo un estado nuevo y lo igualo al ingresado por teclado (si coincide con algun valor del enum)
                        Iterator<Habitacion> iterador = hotel.getHabitaciones().getLista().iterator(); //iterador para las habitaciones
                        while (iterador.hasNext()) {
                            Habitacion habitacion = iterador.next();
                            if (habitacion.equals(habitacionAux)) { //si las habitacion ingresada existe le cambio el estado al ingresado x teclado
                                habitacion.setEstado(estado);
                                System.out.println("Estado cambiado correctamente!");
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }

                case 9: {
                    JsonUtils.escribir(hotel.toJSON(), "hotel.json");
                    System.out.println("\n\033[32mDatos guardados con exito :)\033[0m\n");
                    break;
                }

                case 0: {
                    System.out.println("╭────── · · ୨୧ · · ──────╮");
                    System.out.println("    Saliendo del menú");
                    System.out.println("╰────── · · ୨୧ · · ──────╯");
                    break;
                }

                default: {

                    System.out.println("Finalizando ejecución");

                    break;
                }
            }


        } while (opcion != 0);

        scanner.close();

    }


    //TRANSFORMA LA PRIMER LETRA DE UN STRING A MAYUSCULA (PARA EL NOMBRE DE LOS CLIENTES);

    public String capitalizarPrimerCaracter(String texto) {

        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException("ERROR: TEXTO VACIO");
        }

        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
    }

    //RETORNA TRUE SI EL STRING TIENE +3 CARACTERES O FALSE SI TIENE MENOS
    public static boolean verificar3MasCaracteres(String mensaje) {
        return mensaje != null && mensaje.length() > 3;
    }

    //RETORNA TRUE SI EL STRING NO CONTIENE NUMEROS, OSEA ESTÁ CHECKEADO / RETORNA FALSE SI EL STRING CONTIENE NUMEROS
    public boolean verificarNoContieneNumeros(String mensaje) {

        for (int i = 0; i < mensaje.length(); i++) {
            if (Character.isDigit(mensaje.charAt(i))) {
                return false;
            }

        }
        return true;
    }


    /**
     * Este metodo verifica que
     * un string NO contenga letras
     */
    public boolean verificarNoContieneLetras(String mensaje) {

        for (int i = 0; i < mensaje.length(); i++) {
            if (Character.isAlphabetic(mensaje.charAt(i))) {
                return false;
            }

        }
        return true;
    }

    /**
     * Este metodo verifica si el string(dni) ingresado
     * contiene entre 7 y 8 digitos.
     *
     * @param dni
     * @return true si dni >= 7 y <= 8
     * false si no cumple con las condiciones
     */

    public boolean verificarLongitudDNI(String dni) {
        return dni != null && dni.length() >= 7 && dni.length() <= 8;
    }


    public void menuEstadosParaModificar() {
        System.out.println("Ingrese un estado para asignar a la habitación");
        System.out.println("1-DISPONIBLE");
        System.out.println("2-OCUPADA");
        System.out.println("3-RESERVADA");
        System.out.println("4-MANTENIMIENTO");
        System.out.println("5-LIMPIEZA");
    }


}