package UI;

import Modelo.Habitacion;
import Modelo.Hotel;
import Modelo.Recepcionista;
import Modelo.Reserva;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Scanner;

public class MenuRecepcionista {


    /// Constructor
    public MenuRecepcionista() {

    }


    /// Metodos

    public void menu() {
        /// LECTURA DE JSON

        Hotel hotel = new Hotel();

        try {
            JSONObject datosJSON = new JSONObject(JsonUtils.leer("hotel.json"));

            hotel.setNombre(datosJSON.getString("nombre"));

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




                    break;
                }
                case 2: {

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


}
