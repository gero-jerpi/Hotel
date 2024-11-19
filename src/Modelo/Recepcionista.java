package Modelo;

import Excepciones.FechaInvalidaException;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Scanner;

public class Recepcionista extends Usuario {


    /// CONSTRUCTORES

    public Recepcionista(String nombre, String rol, String dni) {
        super(nombre, rol, dni);
    }

    public Recepcionista() {

    }




    /// METODOS


    /// JSON A RECEPCIONISTA

    public static Usuario JSONArecepcionista(JSONObject jsonObject) {
        Recepcionista recepcionista = new Recepcionista();
        try {
            recepcionista.setId(jsonObject.getInt("id"));
            recepcionista.setNombre(jsonObject.getString("nombre"));
            recepcionista.setRol(jsonObject.getString("rol"));
            recepcionista.setDni(jsonObject.getString("dni"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return recepcionista;
    }


    /// CHECK IN

    public Reserva crearReserva(Cliente cliente, Habitacion habitacion) throws FechaInvalidaException {
        Reserva nuevaReserva = new Reserva();

        if (cliente == null || habitacion == null) {
            throw new IllegalArgumentException("NULO");
        }

        nuevaReserva.setCliente(cliente);
        nuevaReserva.setHabitacion(habitacion);

        Scanner scanner = new Scanner(System.in);


        System.out.println("Ingrese la fecha de inicio de la reserva.\n"); ///QUIZAS ESTE PRINT NO DEBERÍA IR ACÁ
        String finicio = scanner.next();
        if (!nuevaReserva.verificarFecha(finicio)) { //acá podria poner el scanner directamente
            throw new FechaInvalidaException();
        } else nuevaReserva.setFechaInicio(finicio);
        System.out.println("Ingrese la fecha de fin de la reserva.\n");
        String ffin = scanner.next();
        if (!nuevaReserva.verificarFecha(ffin)) {
            throw new FechaInvalidaException();
        } else nuevaReserva.setFechaFin(ffin);

        return nuevaReserva;
    }



}
