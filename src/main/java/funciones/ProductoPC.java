package funciones;

import productos.ComputadorPortatil;

import java.util.ArrayList;

public class ProductoPC {
    /** atributos */
    private String modelo;
    private ArrayList<ComputadorPortatil> computadoresPortatiles;
    private int cantidad;

    /** inicializar atributos */
    public ProductoPC() {
        this.modelo = " ";
        this.cantidad = 0;
        this.computadoresPortatiles = new ArrayList<ComputadorPortatil>();
    }

    public String toString() {
        /** imprimir informacion del producto */
        String saltoLinea = System.getProperty("line.separator");
        return "Modelo " + modelo + saltoLinea + "caracteristicas: " + pcs() + saltoLinea + "cantidad en stock" + cantidad ;
    }

    /**generar el texto del Arraylist*/
    private String pcs() {
        String pcs = "";
        String saltoLinea = System.getProperty("line.separator");
        for (int x = 0; x < computadoresPortatiles.size(); x++) {
            pcs+= saltoLinea +"Marca: "+computadoresPortatiles.get(x).getMarca() + saltoLinea +"Color: "+ computadoresPortatiles.get(x).getColor();
        }
        return pcs;
    }

    /**getter and setter*/
    public void agregarProducto(int suma) {
        this.cantidad = cantidad + suma;
    }
    public void restarProducto(int resta) {
        this.cantidad = cantidad - resta;
    }
    public ArrayList<ComputadorPortatil> getComputadoresPortatiles() {
        return computadoresPortatiles;
    }
    public void setComputadoresPortatiles(ArrayList<ComputadorPortatil> computadoresPortatiles) { this.computadoresPortatiles = computadoresPortatiles; }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
