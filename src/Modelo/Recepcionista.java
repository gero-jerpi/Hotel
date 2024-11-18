package Modelo;

public class Recepcionista extends Usuario {




/// CHECK IN

    public Reserva crearReserva(Cliente cliente){
        Reserva nuevaReserva = new Reserva();

        if(cliente == null){
            throw new IllegalArgumentException("NULO");
        }






        return nuevaReserva;
    }




}
