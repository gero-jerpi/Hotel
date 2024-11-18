package Modelo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public abstract class Usuario {

    private int id;
    private static int idIncremental;
    private String nombre;
    private String rol;



   ///CONSTRUCTORES

    public Usuario(String nombre, String rol) {
        this.id = idIncremental++;
        this.nombre = nombre;
        this.rol = rol;
    }

    public Usuario(){
    }

//GETTERS Y SETTERS


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

    /// USUARIO A JSON
    public JSONObject usuarioAJSON(){
        JSONObject jsonObject = new JSONObject();

        try{
            jsonObject.put("id", this.id);
            jsonObject.put("nombre", this.nombre);
            jsonObject.put("rol", this.rol);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return jsonObject;
    }



    ///METODO EQUALS

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


    ///TOSTRING


    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }

}
