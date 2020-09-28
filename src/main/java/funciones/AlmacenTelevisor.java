package funciones;
import java.util.ArrayList;

public class AlmacenTelevisor {
    /** crea Arrailist del producto especifico*/
    public static ArrayList<ProductoTelevisor> productostv=new ArrayList<ProductoTelevisor>();

    /** para poder usar instancias del arraylist generado*/
    public AlmacenTelevisor(){
    }

    /** para poder ver la cantidad objetos (productos) que ahi en el arraylist*/
    public int verCantidadproductosTelevisor() {
        return this.productostv.size();
    }

}
