package ventanas;

import archivos.GestorJSONv5;
import funciones.AlmacenTelevisor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class GuiBorrarModeloTV extends JFrame implements ActionListener {
    protected JScrollPane menuScrollPane;


    protected JList listaProductos;
    protected JLabel counterLB;
    protected JLabel modeloLB;
    protected JPanel modeloP;
    protected JPanel borrarP;
    protected JButton borrarB;


    public GuiBorrarModeloTV(String title) throws IOException {

        super(title);
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);


        borrarB = new JButton("Borrar");
        borrarP = new JPanel();

        modeloP = new JPanel();
        modeloLB = new JLabel("Modelos");
        // para el Jlist
        AlmacenTelevisor almacenTV = GestorJSONv5.generarAlmacenTelevisor(GestorJSONv5.vectorLineasTV());
        DefaultListModel listModel = new DefaultListModel();
        for (int x = 0; x < almacenTV.productostv.size(); x++) {

            if (listModel.contains(almacenTV.productostv.get(x).getModelo())) {
            } else {

                listModel.addElement(almacenTV.productostv.get(x).getModelo());

            }
        }
        listaProductos = new JList();
        listaProductos.setModel(listModel);

        menuScrollPane = new JScrollPane(listaProductos);
        menuScrollPane.setPreferredSize(new Dimension(100, 100));

        listaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Para saber la cantidad de recetas almacenadas
        counterLB = new JLabel(String.valueOf(almacenTV.verCantidadproductosTelevisor()));


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
        setSize(600, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        this.setVisible(true);


        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                almacenTV.productostv.clear();
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

                AlmacenTelevisor almacenTelevisor = new AlmacenTelevisor();
                almacenTelevisor.productostv.remove(i);
                GestorJSONv5.borrarArchivoTV(listaProductos.getName());
                JOptionPane.showMessageDialog(null, "modelo eliminado exitosamente");
                ((DefaultListModel) listaProductos.getModel()).remove(i);
                counterLB.setText(String.valueOf(almacenTelevisor.verCantidadproductosTelevisor()));

            }
            catch(IOException e){}

        } else {

            JOptionPane.showMessageDialog(null, "Seleccione un modelo de la lista");

        }

    }


}
