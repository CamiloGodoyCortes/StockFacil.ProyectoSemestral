package funciones;
import java.util.ArrayList;

public class AlmacenCelular {
    /**
     * crea Arrailist del producto especifico
     */
    public static ArrayList<ProductoCelular> productocelus = new ArrayList<ProductoCelular>();

    /**
     * para poder usar instancias del arraylist generado
     */
    public AlmacenCelular() {
    }

    /** para poder ver la cantidad objetos (productos) que ahi en el arraylist*/
    public int verCantidadproductosCelular() {
        return this.productocelus.size();
    }
}