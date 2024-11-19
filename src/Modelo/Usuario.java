package Modelo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public abstract class Usuario {


    /// ATRIBUTOS

    protected int id;
    protected String nombre;
    protected String rol;
    protected String dni;

    protected static int idIncremental=1;



   /// CONSTRUCTORES

    public Usuario(String nombre, String rol, String dni) {
        this.id = idIncremental++;
        this.nombre = nombre;
        this.rol = rol;
        this.dni = dni;
    }

    public Usuario(){
        this.id = idIncremental++;
    }



    //GETTERS Y SETTERS

    public static int getIdIncremental() {
        return idIncremental;
    }

    public static void setIdIncremental(int idIncremental) {
        Usuario.idIncremental = idIncremental;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    /// METODOS

    /// RECEPCIONISTA A JSON
    public JSONObject recepcionistaAJSON(){
        JSONObject jsonObject = new JSONObject();

        try{
            jsonObject.put("id", this.id);
            jsonObject.put("nombre", this.nombre);
            jsonObject.put("rol", this.rol);
            jsonObject.put("dni", this.dni);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return jsonObject;
    }



    /// SOBREESCRITURA

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return id == usuario.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }

}
