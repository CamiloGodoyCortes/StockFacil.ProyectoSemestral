package main;

import archivos.GestorJSONv5;
import ventanas.GuiPrincipal;

import java.io.IOException;

public class Main {
    public static void main (String[]args) throws IOException {

        GestorJSONv5.crearAlmacenVacioPC();
        GestorJSONv5.crearAlmacenVacioTelevisor();
        GestorJSONv5.crearAlmacenVacioCelular();
        GuiPrincipal ventana = new GuiPrincipal("Menu");
        ventana.setVisible(true);

    }
}

