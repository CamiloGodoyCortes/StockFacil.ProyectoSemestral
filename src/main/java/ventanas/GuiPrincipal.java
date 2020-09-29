package ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiPrincipal extends JFrame implements ActionListener {
    protected JButton AgregarCantidadB;
    protected JButton RetirarCantidadB;
    protected JButton AgregarNuevoProductoB;
    protected JButton BorrarModeloB;
    protected JPanel botoneraP;


    public GuiPrincipal(String title){
        super(title);
        this.setLayout(new FlowLayout());

        //instanciar los objetos de la ventana
        AgregarCantidadB = new JButton("AgregarCantidad");
        RetirarCantidadB= new JButton("RetirarCantidad");
        AgregarNuevoProductoB= new JButton("AgregarNuevoProducto");
        BorrarModeloB= new JButton("BorrarModelo");
        botoneraP = new JPanel();

        //agregar los comportamientos a los objetos de la ventana
        AgregarCantidadB.addActionListener(this);
        RetirarCantidadB.addActionListener(this);
        AgregarNuevoProductoB.addActionListener(this);
        BorrarModeloB.addActionListener(this);

        // agregar objetos a la ventana
        botoneraP.add(AgregarCantidadB);
        botoneraP.add(RetirarCantidadB);
        botoneraP.add(AgregarNuevoProductoB);
        botoneraP.add(BorrarModeloB);

        this.add(botoneraP);

        //Configuracion de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 200);
        setLocationRelativeTo(null);
        setResizable(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == AgregarCantidadB) {

            GuiPrincipalAgregarCantidad ventA = new GuiPrincipalAgregarCantidad("Seleccion de AgregaCantidad");
            ventA.setVisible(true);

        }
        if (e.getSource() == RetirarCantidadB) {
            GuiPrincipalRetirarCantidad ventana = null;
            ventana = new GuiPrincipalRetirarCantidad("Seleccion de RetirarCantidad");
            ventana.setVisible(true);
        }
        if (e.getSource() ==  AgregarNuevoProductoB) {

            GuiPrincipalNuevoProducto ventB = new GuiPrincipalNuevoProducto("Ingresar Nuevo Producto");
            ventB.setVisible(true);
        }
        if (e.getSource() == BorrarModeloB){
            GuiPrincipalBorrarModelo ventC = new GuiPrincipalBorrarModelo("Seleccione que tipo quiere borrar");
            ventC.setVisible(true);
        }

    }
}
