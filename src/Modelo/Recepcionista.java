package Modelo;

public class Recepcionista extends Usuario {
    public Recepcionista(String nombre, String rol){
        super(nombre, rol);
    }

    public  Recepcionista(){

    }



/// CHECK IN

    public Reserva crearReserva(Cliente cliente){
        Reserva nuevaReserva = new Reserva();

        if(cliente == null){
            throw new IllegalArgumentException("NULO");
        }






        return nuevaReserva;
    }




}
