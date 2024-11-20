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

        String ANSI_RESET = "\u001B[0m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_RED = "\u001B[31m";


        do {
            System.out.println("""
                    
                     Elige una de las siguientes opciones:
                     __________
                     """ + ANSI_BLUE + "HABITACIONES\n" + ANSI_RESET + """
                     ----------
                     [1] Agregar
                     [2] Eliminar
                     [3] Listar
          
                     ______________
                     """ + ANSI_YELLOW + "RECEPCIONISTAS\n" + ANSI_RESET + """
                     --------------
                     [4] Agregar
                     [5] Eliminar
                     [6] Listar
                    
                     ______
                     """ + ANSI_RED + "EXTRAS\n" + ANSI_RESET + """
                     ------
                     [7] Guardar datos
                     [0] Finalizar ejecución
                     """);
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
                                        throw new DatosHabitacionInvalidosExcepcion("El numero de habitacion debe ser mayor a 0\n");
                                    }
                                    aux.setNumeroHabitacion(numHabitacion);

                                    if(hotel.getHabitaciones().getLista().contains(aux))
                                    {
                                        throw new DatosHabitacionInvalidosExcepcion("El numero de habitacion ya existe\n");
                                    }

                                    System.out.println("Ingrese capacidad de la habitacion");
                                    int capacidadHabitacion= scanner.nextInt();
                                    scanner.nextLine();
                                    if(capacidadHabitacion<=0)
                                    {
                                        throw new DatosHabitacionInvalidosExcepcion("La capacidad debe ser mayor a 0\n");
                                    }
                                    aux.setCapacidad(capacidadHabitacion);

                                    System.out.println("Ingrese estado de la habitacion");
                                    for(Estado estado:Estado.values())
                                    {
                                        if(!estado.equals(Estado.OCUPADA) && !estado.equals(Estado.RESERVADA)){
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
                                        throw new DatosHabitacionInvalidosExcepcion("La habitacion no puede dar dinero ni ser gratis C:\n");
                                    }
                                    aux.setprecioNoche(precioHabitacion);

                                    if(hotel.agregar(aux))
                                    {
                                        System.out.println("Habitacion añadida correctamente\n");
                                        habitacionValida=true;
                                        valido=0;
                                    }else{
                                        System.out.println("Hubo un error al añadir la habitacion\n");
                                    }

                                }catch(DatosHabitacionInvalidosExcepcion e)
                                {
                                    System.out.println(e.getMessage());
                                }
                                catch(InputMismatchException e)
                                {
                                    System.out.println("Debe ingresar los datos pedidos\n");
                                    scanner.nextLine();
                                }catch(IllegalArgumentException e)
                                {
                                    System.out.println("Estado ingresado no valido\n");
                                }

                       }

                    break;
                }
                case 2:{

                    Habitacion aux=new Habitacion();

                    try{
                        System.out.println(hotel.listarHabitaciones());
                        System.out.println("Ingrese el numero de habitacion que desea eliminar");
                        int numHabitacion=scanner.nextInt();
                        scanner.nextLine();


                        aux=hotel.buscarHabitacionXnumero(numHabitacion);

                        if(aux==null)
                        {
                            System.out.println("La habitacion no existe\n");
                            break;
                        }

                        if(aux.getEstado()==Estado.OCUPADA)
                        {

                            System.out.println("La habitacion esta ocupada no se puede eliminar\n");

                        }else
                        {
                            if(hotel.eliminarHabitacion(numHabitacion))
                            {
                                System.out.println("La habitacion se elimino correctamente\n");
                            }else
                            {
                                System.out.println("Error al eliminar la habitacion\n");
                            }
                        }
                    }catch(InputMismatchException e)
                    {

                        System.out.println("Debe ingresar un numero valido\n");
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
                    recepcionista.setRol("Recepcionista\n");

                    boolean dniValido=false;

                    while(!dniValido)
                    {
                        System.out.println("Ingrese dni del recepcionista");
                        String dniRecepsionista=scanner.next();
                        if(hotel.verificarDniExistente(dniRecepsionista))
                        {
                            System.out.println("El dni ingresado ya existe\n");
                        }else
                        {
                            recepcionista.setDni(dniRecepsionista);
                            dniValido=true;
                        }
                    }

                    if(hotel.agregar(recepcionista))
                    {
                        System.out.println("El recepcionista se agrego correctamente\n");
                    }else {
                        System.out.println("Hubo un error al agregar el recepcionista\n");
                    }
                    break;
                }
                case 5: {

                    boolean idValido=false;
                    Recepcionista aux= new Recepcionista();
                    while(!idValido)
                    {
                        try{
                            System.out.println(hotel.listarRecepcionistas());
                            System.out.println("Ingrese la id del recepcionista a eliminar");
                            int idRecepcionista=scanner.nextInt();
                            scanner.nextLine();
                            idValido=true;
                            aux.setId(idRecepcionista);
                            if(hotel.getUsuarios().getLista().remove(aux))
                            {
                                System.out.println("El recepcionista fue eliminado correctamente\n");
                            }else
                            {
                                System.out.println("El id ingresado no existe\n");
                            }


                        }catch(InputMismatchException e)
                        {
                            System.out.println("Debe ingresar un id valido\n");
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
                    System.out.println("\n\033[32mDatos guardados con exito :)\033[0m\n");
                    break;
                }
                case 0:{
                    System.out.println("╭────── · · ୨୧ · · ──────╮");
                    System.out.println("    Saliendo del menú");
                    System.out.println( "╰────── · · ୨୧ · · ──────╯");
                    break;
                }

                default:
                    System.out.println("Opcion equivocada\n");
            }


        }while(opcion!=0);


     scanner.close();
    }


}
