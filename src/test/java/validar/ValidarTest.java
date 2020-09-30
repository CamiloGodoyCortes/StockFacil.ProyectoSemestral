package validar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import validar.Validar;

import static org.junit.Assert.*;

public class ValidarTest {
    Validar validar = new Validar();
private String palabra1;
private String palabra2;
private String numero1;
private String numero4;
private int numero2;
private int numero3;
    @Before
    public void setUp() throws Exception {
        this.palabra1 = ("Hola");
        this.palabra2 = ("Hola1");
        this.numero1 = "30";
        this.numero2 = 20;
        this.numero3 = -20;
        this.numero4 = "-20";
    }
    @After

    @Test
    public void validarNumero() {
       boolean a =validar.validarNumero(numero1);
       assertEquals(a,true);
       boolean b =validar.validarNumero(numero4);
       assertEquals(b,false);
    }

    @Test
    public void cantidadError() {
        boolean a = validar.cantidadError(numero1 , numero3);
        assertEquals(a,false);

        boolean b = validar.cantidadError(numero4 , numero2);
        assertEquals(b,true);
    }

    @Test
    public void caracteresPermitidos() {
        boolean a = validar.caracteresPermitidos(palabra1);
        assertEquals(a,true);
                boolean b = validar.caracteresPermitidos(palabra2);
        assertEquals(b,false);
    }
}