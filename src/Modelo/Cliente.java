package Modelo;

public class Cliente {
    /// ATRIBUTOS
    private int id;
    private String dni;
    private String nombre;
    private String domicilio;
    private String origen;

    private static int incremento = 1;


    /// CONSTRUCTORES

    public Cliente(String dni, String nombre, String domicilio, String origen) {
        this.id = incremento++;
        this.dni = dni;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.origen = origen;
    }

    public Cliente(){
        this.id = incremento++;
    }

    /// GETTERS && SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public static int getIncremento() {
        return incremento;
    }

    public static void setIncremento(int incremento) {
        Cliente.incremento = incremento;
    }


    /// SOBREESCRITURA
    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", origen='" + origen + '\'' +
                '}';
    }
}
