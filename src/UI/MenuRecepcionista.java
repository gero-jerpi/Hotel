package UI;

import Modelo.Habitacion;
import Modelo.Hotel;
import Modelo.Recepcionista;
import Modelo.Reserva;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MenuRecepcionista {


    /// Constructor
    public MenuRecepcionista(){

    }


    /// Metodos

    public void menu(){
        /// LECTURA DE JSON

        Hotel hotel = new Hotel();

        try{
            JSONObject datosJSON = new JSONObject(JsonUtils.leer("hotel.json"));

            hotel.setNombre(datosJSON.getString("nombre"));

            JSONArray habitaciones = datosJSON.getJSONArray("habitaciones");

            for(int i=0; i<habitaciones.length(); i++){
                JSONObject jsonObject = habitaciones.getJSONObject(i);
                hotel.agregar(Habitacion.JSONAhabitacion(jsonObject));
            }

            JSONArray usuarios = datosJSON.getJSONArray("usuarios");

            for(int i=0; i<usuarios.length(); i++){
                JSONObject jsonObject = usuarios.getJSONObject(i);
                hotel.agregar(Recepcionista.JSONArecepcionista(jsonObject));
            }

            JSONArray reservas = datosJSON.getJSONArray("reservas");

            for(int i=0; i<reservas.length(); i++){
                JSONObject jsonObject = reservas.getJSONObject(i);
                hotel.agregar(Reserva.JSONAreserva(jsonObject));
            }


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        System.out.println(hotel);


        System.out.println("Elegir una opcion: ");
        System.out.println("1. CHECK IN");
        System.out.println("2. CHECK OUT");
        System.out.println("3. GUARDAR DATOS");





    }








}
