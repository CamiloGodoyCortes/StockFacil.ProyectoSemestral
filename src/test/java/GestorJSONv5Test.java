import Productos.Celular;
import Productos.ComputadorPortatil;
import Productos.Televisor;
import archivos.GestorJSONv5;
import funciones.ProductoCelular;
import funciones.ProductoPC;
import funciones.ProductoTelevisor;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GestorJSONv5Test {
    private GestorJSONv5 gestordatos;
    private ProductoTelevisor productoTelevisor;
    private ProductoCelular productoCelular;
    private ProductoPC productoPC;

    @Before
    public void setUp() {
        this.gestordatos.vaciarProducto("AlmacenCelular.json");
        this.gestordatos.vaciarProducto("AlmacenPC.json");
        this.gestordatos.vaciarProducto("AlmacenTelevisor.json");
        this.gestordatos = new GestorJSONv5();
        this.productoCelular = new ProductoCelular();
        this.productoPC = new ProductoPC();
        this.productoTelevisor = new ProductoTelevisor();

        this.productoCelular.setModelo("P-30A");
        this.productoCelular.setCantidad(30);
        Celular celular = new Celular();
        celular.setMarca("Huawei");
        celular.setColor("Azul");
        this.productoCelular.getCelulares().add(celular);

        this.productoPC.setModelo("Omen-0001laN");
        this.productoPC.setCantidad(20);
        ComputadorPortatil computadorPortatil = new ComputadorPortatil();
        computadorPortatil.setMarca("HP");
        computadorPortatil.setColor("Negro");
        this.productoPC.getComputadoresPortatiles().add(computadorPortatil);

        /** explicar que las pulgadas estan en String por la transformacion a la hora de borrar y modificar, pero en su respectivo rellenador
        en la apliacion tiene una validacion para que solo sea numeros enteros*/

        this.productoTelevisor.setModelo("SM-30");
        this.productoTelevisor.setCantidad(10);
        Televisor televisor = new Televisor();
        televisor.setMarca("Samsung");
        televisor.setTamañoPulgadas("20");
        this.productoTelevisor.getTelevisores().add(televisor);

    }

    @Test
    public void decodeTele() {
        ProductoTelevisor resultadoObtenido;

        try {
            this.gestordatos.agregarProductoArchivoTele(this.productoTelevisor);
            ArrayList<String> listaProductos = this.gestordatos.vectorLineasTV();
            resultadoObtenido = this.gestordatos.decodeTele(listaProductos,listaProductos.size()-1);


            assertEquals(this.productoTelevisor.toString(), resultadoObtenido.toString());


        } catch (IOException ex) {

            fail("No se pudo deserializar el AlmacenTelevisor.json");

        }

    }

    @Test
    public void decodeCelu() {
        ProductoCelular resultadoObtenido;

        try {
            this.gestordatos.agregarProductoArchivoCelu(this.productoCelular);
            ArrayList<String> listaProductos = this.gestordatos.vectorLineasCL();
            resultadoObtenido = this.gestordatos.decodeCelu(listaProductos,listaProductos.size()-1);


            assertEquals(this.productoCelular.toString(), resultadoObtenido.toString());


        } catch (IOException ex) {

            fail("No se pudo deserializar el AlmacenCelular.json");

        }
    }

    @Test
    public void decodePC() {
        ProductoPC resultadoObtenido;

        try {
            this.gestordatos.agregarProductoArchivoPC(this.productoPC);
            ArrayList<String> listaProductos = this.gestordatos.vectorLineasPC();
            resultadoObtenido = this.gestordatos.decodePC(listaProductos,listaProductos.size()-1);


            assertEquals(this.productoPC.toString(), resultadoObtenido.toString());


        } catch (IOException ex) {

            fail("No se pudo deserializar el AlmacenPC.json");

        }
    }

    @Test
    public void borrarArchivoCelu() {
        ArrayList<String> listaProductos;

        try {

            listaProductos = this.gestordatos.vectorLineasCL();
            this.gestordatos.generarAlmacenCelular(listaProductos);
            this.gestordatos.borrarArchivoCelu("P-30A");
            int resultadoObtenido = this.gestordatos.contarLineasCelular();

            assertEquals(0,resultadoObtenido);

        } catch (IOException ex) {

            fail("No se pudo borrar el productoCelular del AlmacenCelular.json");

        }
    }

    @Test
    public void borrarArchivoTV() {
        ArrayList<String> listaProductos;

        try {

            listaProductos = this.gestordatos.vectorLineasTV();
            this.gestordatos.generarAlmacenTelevisor(listaProductos);
            this.gestordatos.borrarArchivoTV("SM-30");
            int resultadoObtenido = this.gestordatos.contarLineasTV();

            assertEquals(0,resultadoObtenido);

        } catch (IOException ex) {

            fail("No se pudo borrar el productoTelevisor del AlmacenTelevisor.json");

        }
    }


    @Test
    public void borrarArchivoPC() {
        ArrayList<String> listaProductos;

        try {

            listaProductos = this.gestordatos.vectorLineasPC();
            this.gestordatos.generarAlmacenPC(listaProductos);
            this.gestordatos.borrarArchivoPC("Omen-0001laN");
            int resultadoObtenido = this.gestordatos.contarLineasPC();

            assertEquals(0,resultadoObtenido);

        } catch (IOException ex) {

            fail("No se pudo borrar el ProductoPC del AlmacenPC.json");

        }
    }
}
