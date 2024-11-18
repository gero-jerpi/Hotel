import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtils {
    public static void escribir(JSONObject jsonObject, String ruta){
        try {
            FileWriter fileWriter = new FileWriter(ruta);
            fileWriter.write(jsonObject.toString(4));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static String leer(String ruta){
        String contenido = "";
        try{
            contenido = new String(Files.readAllBytes(Paths.get(ruta)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return contenido;
    }






}
