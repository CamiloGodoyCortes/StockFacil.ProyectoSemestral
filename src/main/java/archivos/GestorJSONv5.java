package archivos;

import Productos.Celular;
import Productos.ComputadorPortatil;
import Productos.Televisor;
import funciones.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Clase de Gestion de Archivos
 */
public class GestorJSONv5 {

    /**Verifica si AlmacenCelular.json existe, de no existir lo crea. */

    public static void crearAlmacenVacioCelular() {

        if (new File("AlmacenCelular.json").exists()) {
        } else {

            try {

                Files.write(Paths.get("AlmacenCelular.json"), new String().getBytes());

            } catch (IOException e) {
            }

        }
    }
    /**Verifica si AlmacenPC.json existe, de no existir lo crea. */
    public static void crearAlmacenVacioPC() {

        if (new File("AlmacenPC.json").exists()) {
        } else {

            try {

                Files.write(Paths.get("AlmacenPC.json"), new String().getBytes());

            } catch (IOException e) {
            }

        }
    }
    /**Verifica si AlmacenTelevisor.json existe, de no existir lo crea. */
    public static void crearAlmacenVacioTelevisor() {

        if (new File("AlmacenTelevisor.json").exists()) {
        } else {

            try {

                Files.write(Paths.get("AlmacenTelevisor.json"), new String().getBytes());

            } catch (IOException e) {
            }

        }
    }


    /**
     * Añade Poductos a un JSONArray.
     * @param array contiene informacion del producto.
     * @param obj   Producto a crear.
     */

    public static void llenarJSONArray(JSONArray array, Object obj) {

        array.add(obj);

    }

    /**
     * Serializa un producto a formato JSONObject.
     *
     * @param televisores  Producto  en formato JSON.
     * @param cantidad    cantidad que existe de este producto en formato JSON.
     * @return JSONObject ProductoTelevisor en formato JSON.
     */


    public static JSONObject encodeTelevisor(JSONArray televisores, String cantidad, String Modelo) {
        JSONObject obj = new JSONObject();
        obj.put("Televisores", televisores);
        obj.put("cantidad", cantidad);
        obj.put("Modelo", Modelo);
        return obj;
    }

    /**
     * Serializa un producto a formato JSONObject.
     *
     * @param celulares  Producto  en formato JSON.
     * @param cantidad    cantidad que existe de este producto en formato JSON.
     * @return JSONObject ProductoCelulares en formato JSON.
     */
    public static JSONObject encodeCelular(JSONArray celulares, String cantidad, String Modelo) {
        JSONObject obj = new JSONObject();
        obj.put("Celulares", celulares);
        obj.put("cantidad", cantidad);
        obj.put("Modelo", Modelo);
        return obj;
    }
    /**
     * Serializa un producto a formato JSONObject.
     *
     * @param computadoresPortatiles  Producto  en formato JSON.
     * @param cantidad    cantidad que existe de este producto en formato JSON.
     * @return JSONObject ProductoTelevisor en formato JSON.
     */
    public static JSONObject encodePC(JSONArray computadoresPortatiles, String cantidad, String Modelo) {
        JSONObject obj = new JSONObject();
        obj.put("ComputadoresPortatiles", computadoresPortatiles);
        obj.put("cantidad", cantidad);
        obj.put("Modelo", Modelo);
        return obj;
    }

    /**
     * Agrega el texto almacenado en el JSONObject en un AlmacenPC.json y lo guarda.
     * @param obj ProductoPC en formato JSON.
     */

    public static void saveFilePC(JSONObject obj) throws IOException {

      //estos saltos de linea son una forma practica y funcional para distintos tipos de archivo
        String saltoLinea = System.getProperty("line.separator");

        ArrayList<String> lineas = vectorLineasPC();
        String textoViejo = "";

        for (int x = 0; x < lineas.size(); x++) {
            textoViejo += lineas.get(x) + saltoLinea;
        }

        try (FileWriter file = new FileWriter("AlmacenPC.json")) {

            file.write(textoViejo + obj.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * Agrega el texto almacenado en el JSONObject en un AlmacenCelular.json y lo guarda.
     * @param obj ProductoCelular en formato JSON.
     */
    public static void saveFileCelular(JSONObject obj) throws IOException {

        //estos saltos de linea son una forma practica y funcional para distintos tipos de archivo
        String saltoLinea = System.getProperty("line.separator");

        ArrayList<String> lineas = vectorLineasCL();
        String textoViejo = "";

        for (int x = 0; x < lineas.size(); x++) {
            textoViejo += lineas.get(x) + saltoLinea;
        }

        try (FileWriter file = new FileWriter("AlmacenCelular.json")) {

            file.write(textoViejo + obj.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * Agrega el texto almacenado en el JSONObject en un AlmacenTelevisor.json y lo guarda.
     * @param obj ProductoTelevisor en formato JSON.
     */
    public static void saveFileTelevisor(JSONObject obj) throws IOException {

        //estos saltos de linea son una forma practica y funcional para distintos tipos de archivo
        String saltoLinea = System.getProperty("line.separator");

        ArrayList<String> lineas = vectorLineasTV();
        String textoViejo = "";

        for (int x = 0; x < lineas.size(); x++) {
            textoViejo += lineas.get(x) + saltoLinea;
        }

        try (FileWriter file = new FileWriter("AlmacenTelevisor.json")) {

            file.write(textoViejo + obj.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Deserializa las lineas obtenidas de un .json y genera un ProductoTelevisor.
     *
     * @param lineas ProductoTelevisor en formato String.
     * @param n      Cantidad de lineas del .json.
     * @return ProductoTelevisor  como objeto de la clase del mismo nombre.
     */
    public static ProductoTelevisor decodeTele(ArrayList<String> lineas, int n) {

        ArrayList<Televisor> televisorsAL = new ArrayList();
        ProductoTelevisor tv = new ProductoTelevisor();
        JSONParser parser = new JSONParser();
        try {

            Object obj = parser.parse(lineas.get(n));

            JSONObject jsonObject = (JSONObject) obj;
            String Modelo = (String) jsonObject.get("Modelo");
            tv.setModelo(Modelo);
            String cantidad = ((String) jsonObject.get("cantidad"));
            tv.setCantidad(Integer.parseInt(cantidad));


            televisor(jsonObject, televisorsAL, tv);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return tv;

    }
    /**
     * Deserializa las lineas obtenidas de un .json y genera un ProductoCelular.
     *
     * @param lineas ProductoTelevisor en formato String.
     * @param n      Cantidad de lineas del .json.
     * @return ProductoCelular  como objeto de la clase del mismo nombre.
     */
    public static ProductoCelular decodeCelu(ArrayList<String> lineas, int n) {

        ArrayList<Celular> celularesAL = new ArrayList();
        ProductoCelular c = new ProductoCelular();

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(lineas.get(n));

            JSONObject jsonObject = (JSONObject) obj;
            String Modelo = (String) jsonObject.get("Modelo");
            String cantidad = ((String) jsonObject.get("cantidad"));

            c.setModelo(Modelo);
            c.setCantidad(Integer.parseInt(cantidad));

            celulares(jsonObject, celularesAL, c);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return c;

    }
    /**
     * Deserializa las lineas obtenidas de un .json y genera un ProductoPC.
     *
     * @param lineas ProductoTelevisor en formato String.
     * @param n      Cantidad de lineas del .json.
     * @return ProductoTPC  como objeto de la clase del mismo nombre.
     */
    public static ProductoPC decodePC(ArrayList<String> lineas, int n) {


        ArrayList<ComputadorPortatil> computadorPortatilsAL = new ArrayList();
        ProductoPC p = new ProductoPC();

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(lineas.get(n));

            JSONObject jsonObject = (JSONObject) obj;
            String Modelo = (String) jsonObject.get("Modelo");
            String cantidad = ((String) jsonObject.get("cantidad"));

            p.setModelo(Modelo);
            p.setCantidad(Integer.parseInt(cantidad));


            computadorPortatils(jsonObject, computadorPortatilsAL, p);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return p;

    }


    /**
     * Cuenta la cantidad de lineas dentro del AlmacenTelevisor.json.
     * @return int Cantidad de lineas del  AlmacenTelevisor.json.
     */

    public static int contarLineasTV() throws FileNotFoundException, IOException {
        int numLineas = 0;
        String fichero = new File("").getAbsolutePath() + File.separator + "AlmacenTelevisor.json";
        BufferedReader reader = new BufferedReader(new FileReader(fichero));
        String linea = reader.readLine();

        while (linea != null) {
            numLineas = numLineas + 1;
            linea = reader.readLine();
        }
        return numLineas;
    }
    /**
     * Cuenta la cantidad de lineas dentro del  AlmacenCelular.json.
     * @return int Cantidad de lineas del  AlmacenCelular.json.
     */
    public static int contarLineasCelular() throws FileNotFoundException, IOException {
        int numLineas = 0;
        String fichero = new File("").getAbsolutePath() + File.separator + "AlmacenCelular.json";
        BufferedReader reader = new BufferedReader(new FileReader(fichero));
        String linea = reader.readLine();

        while (linea != null) {
            numLineas = numLineas + 1;
            linea = reader.readLine();
        }
        return numLineas;
    }
    /**
     * Cuenta la cantidad de lineas dentro del AlmacenPC.json.
     * @return int Cantidad de lineas del AlmacenPC.json.
     */
    public static int contarLineasPC() throws FileNotFoundException, IOException {
        int numLineas = 0;
        String fichero = new File("").getAbsolutePath() + File.separator + "AlmacenPC.json";
        BufferedReader reader = new BufferedReader(new FileReader(fichero));
        String linea = reader.readLine();

        while (linea != null) {
            numLineas = numLineas + 1;
            linea = reader.readLine();
        }
        return numLineas;
    }


    /**
     * Convierte el AlmacenPC.json en texto plano.
     * @return ArrayList Contiene el contenido del AlmacenPC.json, de tal manera que cada linea es un String en el ArrayList.
     */

    public static ArrayList<String> vectorLineasPC() throws FileNotFoundException, IOException {

        int numLineas = 0;
        int contador = 0;
        String fichero = new File("").getAbsolutePath() + File.separator + "AlmacenPC.json";

        BufferedReader reader = new BufferedReader(new FileReader(fichero));
        String linea = reader.readLine();

        numLineas = contarLineasPC();

        ArrayList<String> datos = new ArrayList<String>();

        while (linea != null && contador < numLineas) {
            datos.add(linea);
            linea = reader.readLine();
            contador++;
        }
        return datos;
    }
    /**
     * Convierte el AlmacenCelular.json en texto plano.
     * @return ArrayList Contiene el contenido del AlmacenCelular.json, de tal manera que cada linea es un String en el ArrayList.
     */
    public static ArrayList<String> vectorLineasCL() throws FileNotFoundException, IOException {

        int numLineas = 0;
        int contador = 0;
        String fichero = new File("").getAbsolutePath() + File.separator + "AlmacenCelular.json";

        BufferedReader reader = new BufferedReader(new FileReader(fichero));
        String linea = reader.readLine();

        numLineas = contarLineasCelular();

        ArrayList<String> datos = new ArrayList<String>();

        while (linea != null && contador < numLineas) {
            datos.add(linea);
            linea = reader.readLine();
            contador++;
        }
        return datos;
    }
    /**
     * Convierte el AlmacenTelevisor.json en texto plano.
     * @return ArrayList Contiene el contenido del AlmacenTelevisor.json, de tal manera que cada linea es un String en el ArrayList.
     */
    public static ArrayList<String> vectorLineasTV() throws FileNotFoundException, IOException {

        int numLineas = 0;
        int contador = 0;
        String fichero = new File("").getAbsolutePath() + File.separator + "AlmacenTelevisor.json";

        BufferedReader reader = new BufferedReader(new FileReader(fichero));
        String linea = reader.readLine();

        numLineas = contarLineasTV();

        ArrayList<String> datos = new ArrayList<String>();

        while (linea != null && contador < numLineas) {
            datos.add(linea);
            linea = reader.readLine();
            contador++;
        }
        return datos;
    }

    /**
     * Genera el AlmacenCelular con el contenido del AlmacenCelular.json.
     * @param lineas Contiene el contenido del AlmacenCelular.json, de tal manera que cada linea es un String en el ArrayList.
     * @return AlmacenCelular como un AlmacenCelular que contiene todos los productos del AlmacenCelular.json.
     */

    public static AlmacenCelular generarAlmacenCelular(ArrayList<String> lineas) {

        AlmacenCelular almacenCelular = new AlmacenCelular();

        almacenCelular.productocelus.clear();

        for (int x = 0; x < lineas.size(); x++) {

            almacenCelular.productocelus.add(decodeCelu(lineas, x));

        }
        return almacenCelular;
    }
    /**
     * Genera el AlmacenPC con el contenido del AlmacenPC.json.
     * @param lineas Contiene el contenido del AlmacenPC.json, de tal manera que cada linea es un String en el ArrayList.
     * @return AlmacenPC como un AlmacenPC que contiene todos los productos del AlmacenCelular.json.
     */
    public static AlmacenPC generarAlmacenPC(ArrayList<String> lineas) {

        AlmacenPC almacenPC = new AlmacenPC();

        almacenPC.productospc.clear();

        for (int x = 0; x < lineas.size(); x++) {

            almacenPC.productospc.add(decodePC(lineas, x));


        }

        return almacenPC;
    }

    /**
     * Genera el AlmacenTelevisor con el contenido del AlmacenTelevisor.json.
     * @param lineas Contiene el contenido del AlmacenTelevisor.json, de tal manera que cada linea es un String en el ArrayList.
     * @return AlmacenTelevisor como un AlmacenTelevisor que contiene todos los productos del AlmacenTelevisor.json.
     */

    public static AlmacenTelevisor generarAlmacenTelevisor(ArrayList<String> lineas) {

        AlmacenTelevisor almacenTV = new AlmacenTelevisor();

        almacenTV.productostv.clear();

        for (int x = 0; x < lineas.size(); x++) {

            almacenTV.productostv.add(decodeTele(lineas, x));


        }

        return almacenTV;
    }


    /**
     * Añande un PorductoTelevisor al  AlmacenTelevisor.json.
     * @param productotelevisor ProductoTelevisor para ser agregada al AlmacenTelevisor.json.
     */
    public static void agregarProductoArchivoTele(ProductoTelevisor productotelevisor) throws IOException {


        JSONArray televisor = new JSONArray();

        String modelo = productotelevisor.getModelo();

        String cantidad = String.valueOf(productotelevisor.getCantidad());

        for (int x = 0; x < productotelevisor.getTelevisores().size(); x++) {
            llenarJSONArray(televisor, productotelevisor.getTelevisores().get(x).getMarca());
            llenarJSONArray(televisor, productotelevisor.getTelevisores().get(x).getTamañoPulgadas());
        }
        saveFileTelevisor(encodeTelevisor(televisor, cantidad, modelo));
    }

    /**
     * Añande un PorductoTelevisor al  AlmacenCelular.json.
     * @param productocelu ProductoCelular para ser agregada al AlmacenCelular.json.
     */
    public static void agregarProductoArchivoCelu(ProductoCelular productocelu) throws IOException {

        JSONArray celular = new JSONArray();

        String modelo = productocelu.getModelo();

        String cantidad = String.valueOf(productocelu.getCantidad());

        for (int x = 0; x < productocelu.getCelulares().size(); x++) {
            llenarJSONArray(celular, productocelu.getCelulares().get(x).getMarca());
            llenarJSONArray(celular, productocelu.getCelulares().get(x).getColor());
        }
        saveFileCelular(encodeCelular(celular, cantidad, modelo));
    }
    /**
     * Añande un PorductoPC al  AlmacenPC.json.
     * @param productopc ProductoPC para ser agregada al AlmacenPC.json.
     */
    public static void agregarProductoArchivoPC(ProductoPC productopc) throws IOException {

        JSONArray computadorportatil = new JSONArray();

        String modelo = productopc.getModelo();

        String cantidad = String.valueOf(productopc.getCantidad());

        for (int x = 0; x < productopc.getComputadoresPortatiles().size(); x++) {
            llenarJSONArray(computadorportatil, productopc.getComputadoresPortatiles().get(x).getMarca());
            llenarJSONArray(computadorportatil, productopc.getComputadoresPortatiles().get(x).getColor());
        }
        saveFilePC(encodePC(computadorportatil, cantidad, modelo));
    }


    /**
     * Elimina completamente el contenido del (archivo ingresado).json.
     * @param dir Ruta del archivo .json.
     */

    public static void vaciarProducto(String dir) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(dir));
            bw.write("");
            bw.close();
        } catch (IOException e) {
        }

    }

    /**
     * Elimina un ProductoCelular del AlmacenCelular.json, pero no afecta a los demas ProductosCelular.
     * @param modelo modelo del Productocelular a eliminar.
     */

    public static void borrarArchivoCelu(String modelo) throws IOException {

        ArrayList<ProductoCelular> celular = AlmacenCelular.productocelus;

        for (int i = 0; i < celular.size(); i++) {

            if (celular.get(i).getModelo().equals(modelo)) {

                celular.remove(i);

            }

        }

        vaciarProducto("AlmacenCelular.json");

        for (int i = 0; i < celular.size(); i++) {

            agregarProductoArchivoCelu(celular.get(i));

        }

    }

    /**
     * Elimina un ProductoTelevisor del AlmacenTelevisor.json, pero no afecta a los demas ProductosTelevisor.
     * @param modelo modelo del ProductoTelevisor a eliminar.
     */
    public static void borrarArchivoTV(String modelo) throws IOException {

        ArrayList<ProductoTelevisor> tv = AlmacenTelevisor.productostv;

        for (int i = 0; i < tv.size(); i++) {

            if (tv.get(i).getModelo().equals(modelo)) {

                tv.remove(i);

            }

        }

        vaciarProducto("AlmacenTelevisor.json");

        for (int i = 0; i < tv.size(); i++) {

            agregarProductoArchivoTele(tv.get(i));

        }

    }

    /**
     * Elimina un ProductoPC del AlmacenPC.json, pero no afecta a los demas ProductosPC.
     * @param modelo modelo del ProductoPC a eliminar.
     */

    public static void borrarArchivoPC(String modelo) throws IOException {

        ArrayList<ProductoPC> pc = AlmacenPC.productospc;

        for (int i = 0; i < pc.size(); i++) {

            if (pc.get(i).getModelo().equals(modelo)) {

                pc.remove(i);

            }

        }

        vaciarProducto("AlmacenPC.json");

        for (int i = 0; i < pc.size(); i++) {

            agregarProductoArchivoPC(pc.get(i));

        }

    }

    /**
     * Encargado de llenar el ArrayList Televisores de un ProductoTelevisor desde un JSONObject.
     * @param jsonObject   Contiene el producto en formato JSON.
     * @param televisorsAL Contiene el producto en formato ProductoTelevisor.
     * @param p            ProductoTelevisor que posee como atributo a televisorsAL.
     */

    public static void televisor(JSONObject jsonObject, ArrayList televisorsAL, ProductoTelevisor p) {

        JSONArray televisorsJA = (JSONArray) jsonObject.get("Televisores");
        Iterator<String> iteratorTV = televisorsJA.iterator();

        while (iteratorTV.hasNext()) {
            Televisor tv = new Televisor();

            tv.setMarca(iteratorTV.next());
            tv.setTamañoPulgadas(iteratorTV.next());

            televisorsAL.add(tv);

        }
        p.setTelevisores(televisorsAL);
    }

    /**
     * Encargado de llenar el ArrayList Celulares de un ProductoCelular desde un JSONObject.
     * @param jsonObject   Contiene el producto en formato JSON.
     * @param celularesAL Contiene el producto en formato ProductoCelular.
     * @param p            ProductoTelevisor que posee como atributo a televisorsAL.
     */


    public static void celulares (JSONObject jsonObject, ArrayList celularesAL, ProductoCelular p){

            JSONArray celularesJA = (JSONArray) jsonObject.get("Celulares");
            Iterator<String> iteratorCL = celularesJA.iterator();

            while (iteratorCL.hasNext()) {
                Celular cl = new Celular();
                cl.setMarca(iteratorCL.next());
                cl.setColor(iteratorCL.next());
                celularesAL.add(cl);
            }

            p.setCelulares(celularesAL);
        }
    /**
     * Encargado de llenar el ArrayList ComputadoresPortatiles de un ProductoPC desde un JSONObject.
     * @param jsonObject   Contiene el producto en formato JSON.
     * @param computadorPortatilsAL Contiene el producto en formato ProductoPC.
     * @param p            ProductoPC que posee como atributo a computadorPortatilsAL.
     */

    public static void computadorPortatils (JSONObject jsonObject, ArrayList computadorPortatilsAL, ProductoPC p){

            JSONArray PCJA = (JSONArray) jsonObject.get("ComputadoresPortatiles");
            Iterator<String> iteratorCL = PCJA.iterator();

            while (iteratorCL.hasNext()) {
                ComputadorPortatil cl = new ComputadorPortatil();
                cl.setMarca(iteratorCL.next());
                cl.setColor(iteratorCL.next());
                computadorPortatilsAL.add(cl);
            }

            p.setComputadoresPortatiles(computadorPortatilsAL);
        }

    }



