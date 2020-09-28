package Validar;

public class Validar {
    public boolean validarNumero(String cadena) {
        if (cadena.matches("[0-9]*")) {
            return true;
        } else {
            return false;
        }
    }
    public  boolean CantidadError(String cadena, int i) {
        if (i < Integer.parseInt(cadena)) {
            return false;
        } else {
            return true;
        }


    }
    public  boolean caracteresPermitidos (String string)  {

        if (!string.matches("[a-zA-Z\\s]+$")) {
            return false;
        }
        else{
            return true;}
        }
    }



