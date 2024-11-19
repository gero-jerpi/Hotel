package UI;

import Contenedores.Gestor;
import Excepciones.DatosHabitacionInvalidosExcepcion;
import Modelo.Recepcionista;
import Modelo.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Scanner;

public class Login {

    /// CONSTRUCTOR

    public Login(){

    }



    /// METODOS

    public void elegirMenu()throws DatosHabitacionInvalidosExcepcion {
        MenuAdministrador menuAdministrador = new MenuAdministrador();
        MenuRecepcionista menuRecepcionista = new MenuRecepcionista();
        Gestor<Usuario> listaUsuarios = new Gestor<>();


        File archivoJSON = new File("hotel.json");
        if(!archivoJSON.exists()){
            System.out.println("Creando archivo....");
            JSONObject hotel = new JSONObject();
            JSONArray habitaciones = new JSONArray();
            JSONArray usuarios = new JSONArray();
            JSONArray reservas = new JSONArray();

            try {
                hotel.put("usuarios", usuarios);
                hotel.put("habitaciones", habitaciones);
                hotel.put("reservas", reservas);
                JsonUtils.escribir(hotel, "hotel.json");
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }


        try {
            JSONObject dataJSON = new JSONObject(JsonUtils.leer("hotel.json"));

            JSONArray usuarios = dataJSON.getJSONArray("usuarios");

            for(int i=0; i<usuarios.length(); i++){
                Usuario usuario = Recepcionista.JSONArecepcionista(usuarios.getJSONObject(i));
                Usuario.setIdIncremental(Usuario.getIdIncremental() - 1);
                listaUsuarios.agregar(usuario);
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        Scanner scanner = new Scanner(System.in);


        System.out.println("Ingrese su nombre");
        String nombre = scanner.next();

        System.out.println("Ingrese su dni");
        String dni = scanner.next();

        if(nombre.equals("admin") && dni.equals("admin")){

            menuAdministrador.menu();
        } else {
            for(Usuario usuario: listaUsuarios.getLista()){
                if(usuario.getNombre().equals(nombre) && usuario.getDni().equals(dni)){
                    menuRecepcionista.menu();
                }else{
                    System.out.println("Usuario no existente");
                }
            }
        }
    }









}
