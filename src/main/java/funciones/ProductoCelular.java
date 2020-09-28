package funciones;

import Productos.Celular;

import java.util.ArrayList;

public class ProductoCelular {
    /** atributos */
    private String modelo;
    private ArrayList<Celular> celulares;
    private int cantidad;


    public ProductoCelular() {
        /** inicializar atributos */
        this.modelo = " ";
        this.cantidad = 0;
        this.celulares = new ArrayList<Celular>();
    }


    public String toString() {
        /** imprimir informacion del producto */
        String saltoLinea = System.getProperty("line.separator");
        return "Modelo " + modelo + saltoLinea + "caracteristicas: " + cels() + saltoLinea + "cantidad en stock" + cantidad ;
    }

    /**generar el texto del Arraylist*/
    private String cels() {
        String cels = "";
        String saltoLinea = System.getProperty("line.separator");
        for (int x = 0; x < celulares.size(); x++) {
            cels+= saltoLinea +"Marca: "+ celulares.get(x).getMarca() + saltoLinea +"Color: "+ celulares.get(x).getColor();
        }
        return cels;
    }

    /** metodos del producto */
    public void agregarProducto(int suma) {this.cantidad = cantidad + suma; }
    public void restarProducto(int resta) {
        this.cantidad = cantidad - resta;
    }

    /**getter and setter*/
    public ArrayList<Celular> getCelulares() {
        return celulares;
    }
    public void setCelulares(ArrayList<Celular> celulares) {
        this.celulares = celulares;
    }
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

