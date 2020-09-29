package ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GuiPrincipalAgregarCantidad extends JFrame implements ActionListener {
    protected JButton TelevisorB;
    protected JButton ComputadorPortatilB;
    protected JButton CelularB;
    protected JPanel botoneraP;

    public GuiPrincipalAgregarCantidad(String title) {

        super(title);
        this.setLayout(new FlowLayout());

        //instanciar los objetos de la ventana
        CelularB = new JButton("Celular");
        ComputadorPortatilB = new JButton("ComputadorPortatil");
        TelevisorB = new JButton("Televisor");
        botoneraP = new JPanel();

        //agregar los comportamientos a los objetos de la ventana
        CelularB.addActionListener(this);
        ComputadorPortatilB.addActionListener(this);
        TelevisorB.addActionListener(this);

        // agregar objetos a la ventana
        botoneraP.add(CelularB);
        botoneraP.add(ComputadorPortatilB);
        botoneraP.add(TelevisorB);


        this.add(botoneraP);

        //Configuracion de la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 200);
        setLocationRelativeTo(null);
        setResizable(false);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == CelularB) {

            GuiAgregarCantidadCelular ventA = null;
            try {
                ventA = new GuiAgregarCantidadCelular("Celular Agregar Stock");
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            ventA.setVisible(true);

        }
        if (e.getSource() == TelevisorB) {
            GuiAgregarCantidadTV ventana = null;
            try {
                ventana = new GuiAgregarCantidadTV("Televisor Agregar Stock");
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            ventana.setVisible(true);
        }
        if (e.getSource() == ComputadorPortatilB) {

            GuiAgregarCantidadPC ventB = null;
            try {
                ventB = new GuiAgregarCantidadPC("ComputadorPortatil Agregar Stock");
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            ventB.setVisible(true);
        }
    }
}


