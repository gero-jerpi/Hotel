package UI;

import ENUMS.Estado;
import Excepciones.FechaInvalidaException;
import Excepciones.HabitacionNoDisponibleException;
import Modelo.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.xml.transform.Source;
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

            hotel.setNombre(datosJSON.getString("nombre")); //HOTEL CREO Q SOLO TIENE NOMBRE, OSEA NO LE PASO EL CONTENIDO // !!!!!CHEQUEAR ESTO !!!!!!

            JSONArray habitaciones = datosJSON.getJSONArray("habitaciones");

            for (int i = 0; i < habitaciones.length(); i++) {
                JSONObject jsonObject = habitaciones.getJSONObject(i);
                hotel.agregar(Habitacion.JSONAhabitacion(jsonObject));
            }

            JSONArray usuarios = datosJSON.getJSONArray("usuarios");

            for (int i = 0; i < usuarios.length(); i++) {
                JSONObject jsonObject = usuarios.getJSONObject(i);
                hotel.agregar(Recepcionista.JSONArecepcionista(jsonObject));
            }

            JSONArray reservas = datosJSON.getJSONArray("reservas");

            for (int i = 0; i < reservas.length(); i++) {
                JSONObject jsonObject = reservas.getJSONObject(i);
                hotel.agregar(Reserva.JSONAreserva(jsonObject));
            }


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        System.out.println(hotel);


        Scanner scanner = new Scanner(System.in);


        int opcion;


        do {


            System.out.println("Elegir una opcion: \n");

            System.out.println("1. CHECK IN");
            System.out.println("2. CHECK OUT");
            System.out.println("3. LISTAR HABITACIONES DISPONIBLES");
            System.out.println("4. LISTAR HABITACIONES OCUPADAS");
            System.out.println("5. LISTAR HABITACIONES NO DISPONIBLE");
            System.out.println("6. CAMBIAR ESTADO HABITACION");
            System.out.println("7. GUARDAR DATOS");
            System.out.println("8. FINALIZAR EJECUCIÓN");

            opcion = scanner.nextInt();

            switch (opcion) {

                case 1: {

                    Cliente clienteNuevo = new Cliente();


                    System.out.println("Ingrese el DNI del Cliente");
                    String dni = scanner.next();
                    if (!verificarNoContieneLetras(dni) || !verificarLongitudDNI(dni)) {
                        throw new IllegalArgumentException("ERROR:DNI INVALIDO");
                    }
                    clienteNuevo.setDni(dni);


                    System.out.println("Ingrese el nombre del Cliente");
                    String nombre = scanner.next();
                    nombre = capitalizarPrimerCaracter(nombre); //Transformo su primer caracter a mayus y el resto a minus
                    if (!verificar3MasCaracteres(nombre) || !verificarNoContieneNumeros(nombre)) { //Verifico q el nombre tenga mas de 3 caracteres y q no contenga numeros
                        throw new IllegalArgumentException("ERROR:NOMBRE INVALIDO");
                    }
                    clienteNuevo.setNombre(nombre);


                    System.out.println("Ingrese el domicilio del Cliente");
                    String domicilio = scanner.next();
                    if (!verificar3MasCaracteres(domicilio)) {///PODRIA TENER VERIFICACIONES PARA Q SEA UNA DIRECCIÓN REAL EJ:DAIREAUX 1827
                        throw new IllegalArgumentException("ERROR:DOMICILIO INVALIDO");
                    }
                    clienteNuevo.setDomicilio(domicilio);

                    System.out.println("Ingrese el origen del Cliente");
                    String origen = scanner.next();
                    if (!verificarNoContieneNumeros(origen) || !verificar3MasCaracteres(origen)) {
                        throw new IllegalArgumentException("ERROR:ORIGEN INVALIDO");
                    }
                    clienteNuevo.setOrigen(origen);

                    System.out.println("Ingrese el NRO de habitacion a elegir.\n");
                    hotel.listarHabitacionesDisponibles(); //ESTO NO LISTA NADA POR LO QUE COMENTÉ ARRIBA


                    try {
                        Habitacion habitacionNueva = hotel.buscarHabitacionXnumero(scanner.nextInt());
                        if (habitacionNueva.getEstado() != Estado.DISPONIBLE) {
                            throw new HabitacionNoDisponibleException();
                        }
                        Reserva nuevaReserva = recepcionista.crearReserva(clienteNuevo, habitacionNueva); //CREO LA RESERVA
                        scanner.nextLine();
                        habitacionNueva.setEstado(Estado.OCUPADA); //LA SETEO EN OCUPADA
                        hotel.getReservas().agregar(nuevaReserva); //Agrego la reserva a la lista

                    } catch (FechaInvalidaException | HabitacionNoDisponibleException e) {
                        throw new RuntimeException(e);
                    }


                    break;
                }
                case 2: {


                    System.out.println("Ingrese el numero de habitación para terminar la reserva."); //PDRIA SSER CON DNI CLIENTE

                    Habitacion habitacionAux = hotel.buscarHabitacionXnumero(scanner.nextInt());
                    if (habitacionAux == null) {
                        throw new IllegalArgumentException("ERROR:NUMERO DE HABITACIÓN INCORRECTO");
                    }

                    Iterator<Reserva> iterator = hotel.getReservas().getLista().iterator();
                    while (iterator.hasNext()) {
                        Reserva reserva = iterator.next();
                        if (reserva.getHabitacion().equals(habitacionAux)) {
                            iterator.remove();
                            reserva.getHabitacion().setEstado(Estado.DISPONIBLE);
                        }

                    }

                    break;
                }
                case 3: {


                    break;
                }

                case 4: {

                    break;
                }

                case 5: {

                    break;
                }

                case 6: {

                    break;
                }

                case 7: {

                    break;
                }

                case 8: {

                    break;
                }

                default: {

                    System.out.println("Finalizando ejecución");

                    break;
                }
            }


        } while (opcion != 8);


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


}
