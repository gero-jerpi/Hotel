package UI;

import ENUMS.Estado;
import Excepciones.DatosHabitacionInvalidosExcepcion;
import Modelo.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuAdministrador {
    public MenuAdministrador(){

    }

    public void menu() throws DatosHabitacionInvalidosExcepcion{
        Hotel hotel = new Hotel();
        Scanner scanner =new Scanner(System.in);

        try{
            JSONObject datosJSON = new JSONObject(JsonUtils.leer("hotel.json"));
            
            JSONArray habitaciones = datosJSON.getJSONArray("habitaciones");

            for(int i=0; i<habitaciones.length(); i++){
                Habitacion habitacion = Habitacion.JSONAhabitacion(habitaciones.getJSONObject(i));
                hotel.agregar(habitacion);
            }

            JSONArray usuarios = datosJSON.getJSONArray("usuarios");

            for(int i=0; i<usuarios.length(); i++){
                Usuario usuario = Recepcionista.JSONArecepcionista(usuarios.getJSONObject(i));
                hotel.agregar(usuario);
            }

            JSONArray reservas = datosJSON.getJSONArray("reservas");

            for(int i=0; i<reservas.length(); i++){
                Reserva reserva = Reserva.JSONAreserva(reservas.getJSONObject(i));
                hotel.agregar(reserva);
            }


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        int opcion = 0;
        do {

            System.out.println("Elegir una opcion");
            System.out.println("1. Agregar habitacion");
            System.out.println("2. Eliminar habitacion");
            System.out.println("3. Listar habitaciones");
            System.out.println("4. Agregar recepcionista");
            System.out.println("5. Eliminar recepcionista");
            System.out.println("6. Listar recepcionistas");
            System.out.println("7. Guardar datos");
            System.out.println("0. Finalizar ejecucion");

            opcion=scanner.nextInt();


            switch (opcion){
                case 1: {
                    Habitacion aux =new Habitacion();
                    Boolean habitacionValida=false;


                       while(!habitacionValida)
                       {
                            int valido=1;

                                try{
                                    System.out.println("Ingrese numero de habitacion");
                                    int numHabitacion=scanner.nextInt();
                                    scanner.nextLine();
                                    if(numHabitacion<=0)
                                    {
                                        throw new DatosHabitacionInvalidosExcepcion("El numero de habitacion debe ser mayor a 0");
                                    }
                                    aux.setNumeroHabitacion(numHabitacion);

                                    if(hotel.getHabitaciones().getLista().contains(aux))
                                    {
                                        throw new DatosHabitacionInvalidosExcepcion("El numero de habitacion ya existe");
                                    }

                                    System.out.println("Ingrese capacidad de la habitacion");
                                    int capacidadHabitacion= scanner.nextInt();
                                    scanner.nextLine();
                                    if(capacidadHabitacion<=0)
                                    {
                                        throw new DatosHabitacionInvalidosExcepcion("La capacidad debe ser mayor a 0");
                                    }
                                    aux.setCapacidad(capacidadHabitacion);

                                    System.out.println("Ingrese estado de la habitacion");
                                    for(Estado estado:Estado.values())
                                    {
                                        if(!estado.equals(Estado.OCUPADA)){
                                            System.out.println("-"+estado);
                                        }
                                    }
                                    String estadoHabitacion= scanner.nextLine().toUpperCase();
                                    Estado estado=Estado.valueOf(estadoHabitacion);
                                    aux.setEstado(estado);

                                    System.out.println("Ingrese precio de la habitacion");
                                    double precioHabitacion = scanner.nextDouble();
                                    if(precioHabitacion<=0)
                                    {
                                        throw new DatosHabitacionInvalidosExcepcion("La habitacion no puede dar dinero ni ser gratis C:");
                                    }
                                    aux.setprecioNoche(precioHabitacion);

                                    if(hotel.agregar(aux))
                                    {
                                        System.out.println("Habitacion añadida correctamente");
                                        habitacionValida=true;
                                        valido=0;
                                    }else{
                                        System.out.println("Hubo un error al añadir la habitacion");
                                    }

                                }catch(DatosHabitacionInvalidosExcepcion e)
                                {
                                    System.out.println(e.getMessage());
                                }
                                catch(InputMismatchException e)
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

                    Habitacion aux=new Habitacion();

                    try{
                        System.out.println("Ingrese el numero de habitacion que desea eliminar");
                        int numHabitacion=scanner.nextInt();
                        scanner.nextLine();


                        aux=hotel.buscarHabitacionXnumero(numHabitacion);

                        if(aux==null)
                        {
                            System.out.println("La habitacion no existe");
                            break;
                        }

                        if(aux.getEstado()==Estado.OCUPADA)
                        {

                            System.out.println("La habitacion esta ocupada no se puede eliminar");

                        }else
                        {
                            if(hotel.eliminarHabitacion(numHabitacion))
                            {
                                System.out.println("La habitacion se elimino correctamente");
                            }else
                            {
                                System.out.println("Error al eliminar la habitacion");
                            }
                        }
                    }catch(InputMismatchException e)
                    {

                        System.out.println("Debe ingresar un numero valido");
                    }

                    break;
                }
                case 3:{
                    System.out.println(hotel.listarHabitaciones());
                    break;
                }
                case 4:{
                    Recepcionista recepcionista=new Recepcionista();
                    System.out.println("Ingrese nombre del recepcionista");
                    scanner.nextLine();
                    String nombreRecepcionista=scanner.nextLine();
                    recepcionista.setNombre(nombreRecepcionista);
                    recepcionista.setRol("Recepcionista");

                    boolean dniValido=false;

                    while(!dniValido)
                    {
                        System.out.println("Ingrese dni del recepcionista");
                        String dniRecepsionista=scanner.next();
                        if(hotel.verificarDniExistente(dniRecepsionista))
                        {
                            System.out.println("El dni ingresado ya existe");
                        }else
                        {
                            recepcionista.setDni(dniRecepsionista);
                            dniValido=true;
                        }
                    }

                    if(hotel.agregar(recepcionista))
                    {
                        System.out.println("El recepcionista se agrego correctamente");
                    }else {
                        System.out.println("Hubo un error al agregar el recepcionista");
                    }
                    break;
                }
                case 5: {

                    boolean idValido=false;
                    Recepcionista aux= new Recepcionista();
                    while(!idValido)
                    {
                        try{

                            System.out.println("Ingrese la id del recepcionista a elimianr");
                            int idRecepcionista=scanner.nextInt();
                            scanner.nextLine();
                            idValido=true;
                            aux.setId(idRecepcionista);
                            if(hotel.getUsuarios().getLista().remove(aux))
                            {
                                System.out.println("El recepcionista fue eliminado correctamente");
                            }else
                            {
                                System.out.println("El dni ingresado no existe");
                            }


                        }catch(InputMismatchException e)
                        {
                            System.out.println("Debe ingresar un dni valido");
                            scanner.nextLine();
                        }
                    }
                    break;
                }
                case 6:{
                    System.out.println(hotel.listarRecepcionistas());
                    break;
                }
                case 7:{
                        JsonUtils.escribir(hotel.toJSON(),"hotel.json");
                    break;
                }
                case 0:{
                    System.out.println("╭────── · · ୨୧ · · ──────╮");
                    System.out.println("    Saliendo del menú");
                    System.out.println( "╰────── · · ୨୧ · · ──────╯");
                    break;
                }

                default:
                    System.out.println("Opcion equivocada");
            }


        }while(opcion!=0);









     scanner.close();
    }


}
