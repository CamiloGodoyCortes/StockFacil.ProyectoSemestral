package funciones;
import java.util.ArrayList;

public class AlmacenPC {
    /** crea Arrailist del producto especifico*/
    public static ArrayList<ProductoPC> productospc = new ArrayList<ProductoPC>();

    /** para poder usar instancias del arraylist generado*/
    public AlmacenPC(){
    }

    /** para poder ver la cantidad objetos (productos) que ahi en el arraylist*/
    public int verCantidadproductosPC() {
        return this.productospc.size();
    }


}
