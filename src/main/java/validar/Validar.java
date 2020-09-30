package validar;

public class Validar {
    
    public boolean validarNumero(String cadena) {
        if (cadena.matches("[0-9]*")) {
            return true;
        } else {
            return false;
        }
    }
    public  boolean cantidadError(String cadena, int i) {
        if (i < Integer.parseInt(cadena)) {
            return false;
        } else {
            return true;
        }


    }
    public  boolean caracteresPermitidos (String cadena)  {

        if (!cadena.matches("[a-zA-Z\\s]+$")) {
            return false;
        }
        else{
            return true;}
        }
    }



