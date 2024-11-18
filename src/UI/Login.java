package UI;

import Contenedores.Gestor;
import Excepciones.DatosHabitacionInvalidosExcepcion;
import Modelo.Recepcionista;
import Modelo.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Scanner;

public class Login {
    public Login(){

    }

    public void elegirMenu()throws DatosHabitacionInvalidosExcepcion {
        Gestor<Usuario> listaUsuarios = new Gestor<>();
        try {
            JSONObject dataJSON = new JSONObject(JsonUtils.leer("hotel.json"));

            JSONArray usuarios = dataJSON.getJSONArray("usuarios");

            for(int i=0; i<usuarios.length(); i++){
                JSONObject jsonObject = usuarios.getJSONObject(i);
                listaUsuarios.agregar(Recepcionista.JSONArecepcionista(jsonObject));
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
            MenuAdministrador menuAdministrador = new MenuAdministrador();
            menuAdministrador.menu();
        } else {
            for(Usuario usuario: listaUsuarios.getLista()){
                if(usuario.getNombre().equals(nombre) && usuario.getDni().equals(dni)){
                    MenuRecepcionista menuRecepcionista = new MenuRecepcionista();
                    menuRecepcionista.menu();
                }else{
                    System.out.println("Usuario no existente");
                }
            }
        }
    }









}
