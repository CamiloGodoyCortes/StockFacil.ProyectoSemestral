package funciones;

import productos.Televisor;

import java.util.ArrayList;

public class ProductoTelevisor {
    /** atributos */
    private String modelo;
    private ArrayList<Televisor> televisores;
    private int cantidad;


    public ProductoTelevisor() {
        /** inicializar atributos */
        this.modelo = " ";
        this.cantidad = 0;
        this.televisores = new ArrayList<Televisor>();
    }
    /** metodos del producto */
    public void agregarProducto(int suma) {
        this.cantidad = cantidad + suma;
    }
    public void restarProducto(int resta) {
        this.cantidad = cantidad - resta;
    }


    public String toString()
    /** imprimir informacion del producto */{
        String saltoLinea = System.getProperty("line.separator");
        return "Modelo " + modelo + saltoLinea + "caracteristicas: " + tvs() + saltoLinea + "cantidad en stock" + cantidad ;
    }

    /**generar el texto del Arraylist*/
    private String tvs() {
        String tvs = "";
        String saltoLinea = System.getProperty("line.separator");
        for (int x = 0; x < televisores.size(); x++) {
            tvs+= saltoLinea +"Marca: "+televisores.get(x).getMarca() + saltoLinea +"TamañoPulgadas: "+ televisores.get(x).getTamañoPulgadas();
        }
        return tvs;
    }

    /**getter and setter*/
    public ArrayList<Televisor> getTelevisores() {
        return televisores;
    }
    public void setTelevisores(ArrayList<Televisor> televisores) {
        this.televisores = televisores;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}

