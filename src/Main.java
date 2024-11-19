import ENUMS.Estado;
import Excepciones.DatosHabitacionInvalidosExcepcion;
import Modelo.*;
import UI.Login;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws DatosHabitacionInvalidosExcepcion {

    Login login = new Login();
    login.elegirMenu();

    }
}