package Ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GuiPrincipalRetirarCantidad extends JFrame implements ActionListener {
    protected JButton TelevisorB;
    protected JButton ComputadorPortatilB;
    protected JButton CelularB;
    protected JPanel botoneraP;

    public GuiPrincipalRetirarCantidad(String title) {

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

            GuiRetirarCantidadCelular ventA = null;
            try {
                ventA = new GuiRetirarCantidadCelular("Celular");
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            ventA.setVisible(true);

        }
        if (e.getSource() == TelevisorB) {
            GuiRetirarCantidadTV ventana = null;
            try {
                ventana = new GuiRetirarCantidadTV("Televisor");
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            ventana.setVisible(true);
        }
        if (e.getSource() == ComputadorPortatilB) {

            GuiRetirarCantidadPC ventB = null;
            try {
                ventB = new GuiRetirarCantidadPC("ComputadorPortatil");
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            ventB.setVisible(true);
        }
    }
}

