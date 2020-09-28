package Ventanas;

import archivos.GestorJSONv5;
import funciones.AlmacenCelular;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class GuiBorrarModeloCelular extends JFrame implements ActionListener {
    protected JScrollPane menuScrollPane;


    protected JList listaProductos;
    protected JLabel counterLB;
    protected JLabel modeloLB;
    protected JPanel modeloP;

    protected JPanel borrarP;
    protected JButton borrarB;


    public GuiBorrarModeloCelular(String title) throws IOException {

        super(title);
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);


        borrarB = new JButton("Borrar");
        borrarP = new JPanel();

        modeloP = new JPanel();
        modeloLB = new JLabel("Modelos");
        // para el Jlist
        AlmacenCelular almacenCelular = GestorJSONv5.generarAlmacenCelular(GestorJSONv5.vectorLineasCL());
        DefaultListModel listModel = new DefaultListModel();
        for (int x = 0; x < almacenCelular.productocelus.size(); x++) {

            if (listModel.contains(almacenCelular.productocelus.get(x).getModelo())) {
            } else {

                listModel.addElement(almacenCelular.productocelus.get(x).getModelo());

            }
        }
        listaProductos = new JList();
        listaProductos.setModel(listModel);

        menuScrollPane = new JScrollPane(listaProductos);
        menuScrollPane.setPreferredSize(new Dimension(100, 100));

        listaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Para saber la cantidad de recetas almacenadas
        counterLB = new JLabel(String.valueOf(almacenCelular.verCantidadproductosCelular()));


        //agregar los comportamientos a los obejtos de loa ventana
        borrarB.addActionListener(this);

        // agregar objetos a la ventana
        modeloP.add(modeloLB);
        modeloP.add(counterLB);

        borrarP.add(borrarB);


        this.add(modeloP);
        //para la barra
        this.add(menuScrollPane);
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.add(borrarP);

        //Configuracion ventana
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(900, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        this.setVisible(true);


        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                almacenCelular.productocelus.clear();
                setVisible(false);
            }
        });


    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == borrarB) {

            try {
                int i = listaProductos.getSelectedIndex();
                borrar(i);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

    }
        private void borrar(int i) throws IOException {

            if (i > -1) {

                try{

                    AlmacenCelular almacenCelular = new AlmacenCelular();
                    almacenCelular.productocelus.remove(i);
                    GestorJSONv5.borrarArchivoCelu(listaProductos.getName());
                    JOptionPane.showMessageDialog(null, "modelo eliminado exitosamente");
                    ((DefaultListModel) listaProductos.getModel()).remove(i);
                    counterLB.setText(String.valueOf(almacenCelular.verCantidadproductosCelular()));

                }
                catch(IOException e){}

            } else {

                JOptionPane.showMessageDialog(null, "Seleccione un modelo de la lista");

            }

        }


    }


