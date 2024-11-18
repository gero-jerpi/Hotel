package UI;

import ENUMS.Estado;
import Excepciones.DatosHabitacionInvalidosExcepcion;
import Modelo.Habitacion;
import Modelo.Hotel;
import Modelo.Recepcionista;
import Modelo.Reserva;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuAdministrador {
    public MenuAdministrador(){

    }



    public void menu()throws DatosHabitacionInvalidosExcepcion{
        Hotel hotel = new Hotel();
        Scanner scanner =new Scanner(System.in);
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



        System.out.println("Elegir una opcion");
        System.out.println("1. Agregar habitacion");
        System.out.println("2. Eliminar habitacion");
        System.out.println("3. Listar habitaciones");
        System.out.println("4. Agregar recepcionista");
        System.out.println("5. Eliminar recepcionista");
        System.out.println("6. Listar recepcionistas");
        System.out.println("7. Guardar datos");
        System.out.println("0. Finalizar ejecucion");

        int opcion = 0;
        opcion=scanner.nextInt();


        do {
            switch (opcion){
                case 1: {
                    Habitacion aux =new Habitacion();
                    Boolean habitacionValida=false;


                       while(!habitacionValida)
                       {

                            try{

                                System.out.println("Ingrese numero de habitacion");
                                aux.setNumeroHabitacion(scanner.nextInt());






                                System.out.println("Ingrese capacidad de la habitacion");
                                aux.setCapacidad(scanner.nextInt());
                                System.out.println("Ingrese estado de la habitacion");
                                for(Estado estado:Estado.values())
                                {
                                    System.out.println("- "+estado);
                                }


                                ///verificar
                                hotel.agregar(aux);

                            }catch(InputMismatchException e)
                            {
                                System.out.println("Debe ingresar los datos pedidos");
                                scanner.nextLine();
                            }catch(IllegalArgumentException e)
                            {
                                System.out.println("Estado ingresado no valido");
                            }

                       }



                    break;
                }
                case 2:{

                    break;
                }
                case 3:{

                    break;
                }
                case 4:{

                    break;
                }
                case 5: {

                    break;
                }
                case 6:{

                    break;
                }
                case 7:{

                    break;
                }

                default:
                    System.out.println("Opcion equivocada");
            }




        }while(opcion!=0);









     scanner.close();
    }


}
