package ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiPrincipalNuevoProducto extends JFrame implements ActionListener {
    private JButton TelevisorB;
    private JButton ComputadorPortatilB;
    private JButton CelularB;
    private JPanel botoneraP;

    public GuiPrincipalNuevoProducto(String title) {

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

                GuiNuevoCelular ventA = new GuiNuevoCelular("Celular");
                ventA.setVisible(true);

        }
        if (e.getSource() == TelevisorB) {
            GuiNuevoTelevisor ventana = new GuiNuevoTelevisor("Televisor");
            ventana.setVisible(true);
        }
        if (e.getSource() == ComputadorPortatilB) {

                GuiNuevoPC ventB = new GuiNuevoPC("ComputadorPortatil");
                ventB.setVisible(true);
            }
        }
    }

