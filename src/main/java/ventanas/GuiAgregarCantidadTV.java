package ventanas;

import validar.Validar;
import archivos.GestorJSONv5;
import funciones.AlmacenTelevisor;
import funciones.ProductoTelevisor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class GuiAgregarCantidadTV extends JFrame implements ActionListener {
    private JScrollPane menuScrollPane;


    private JPanel areaP;
    private JTextArea area;
    private JScrollPane areaScrollPane;

    private JList listaProductos;
    private JLabel counterLB;
    private JLabel modeloLB;
    private JPanel modeloP;

    private JPanel seleccionar1P;
    private JButton seleccionar1B;


    private JPanel agregarP;
    private JTextField agregarTF;
    private JButton agregarB;

    public GuiAgregarCantidadTV(String title) throws IOException {

        super(title);
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);


        seleccionar1B = new JButton("Seleccionar");
        seleccionar1P = new JPanel();

        modeloP = new JPanel();
        modeloLB = new JLabel("Modelos");
        // para el Jlist
        AlmacenTelevisor almacenTelevisor = GestorJSONv5.generarAlmacenTelevisor(GestorJSONv5.vectorLineasTV());
        DefaultListModel listModel = new DefaultListModel();
        for (int x = 0; x < almacenTelevisor.productostv.size(); x++) {

            if (listModel.contains(almacenTelevisor.productostv.get(x).getModelo())) {
            } else {

                listModel.addElement(almacenTelevisor.productostv.get(x).getModelo());

            }
        }
        listaProductos = new JList();
        listaProductos.setModel(listModel);

        menuScrollPane = new JScrollPane(listaProductos);
        menuScrollPane.setPreferredSize(new Dimension(100, 100));

        areaP = new JPanel();
        listaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        areaP = new JPanel();
        area = new JTextArea();
        area.setEditable(false);
        areaScrollPane = new JScrollPane(area);
        areaScrollPane.setPreferredSize(new Dimension(200, 150));

        //Para saber la cantidad de recetas almacenadas
        counterLB = new JLabel(String.valueOf(almacenTelevisor.verCantidadproductosTelevisor()));

        //Agregar
        this.agregarP = new JPanel();
        this.agregarB = new JButton("Agregar");
        this.agregarTF = new JTextField(8);
        //agregar los comportamientos a los obejtos de loa ventana
        seleccionar1B.addActionListener(this);
        agregarB.addActionListener(this);

        // agregar objetos a la ventana
        modeloP.add(modeloLB);
        modeloP.add(counterLB);

        seleccionar1P.add(seleccionar1B);

        areaP.add(areaScrollPane);

        agregarP.add(agregarB);
        agregarP.add(agregarTF);

        this.add(modeloP);
        //para la barra
        this.add(menuScrollPane);
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.add(seleccionar1P);
        this.add(areaP);
        this.add(agregarP);

        //Configuracion ventana
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(900, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        this.setVisible(true);


        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                almacenTelevisor.productostv.clear();
                setVisible(false);
            }
        });


    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == seleccionar1B) {
            int i = listaProductos.getSelectedIndex();
            mostrar(i);
        }

        if (e.getSource() == agregarB) {
            int indice = listaProductos.getSelectedIndex();
            AlmacenTelevisor almacenTelevisor = new AlmacenTelevisor();
            Validar validar = new Validar();
            if (indice > -1) {
                if ((validar.validarNumero(agregarTF.getText())) == true) {
                    almacenTelevisor.productostv.get(indice).agregarProducto(Integer.parseInt(agregarTF.getText()));
                    ;
                    try {
                        ProductoTelevisor productoTelevisor = almacenTelevisor.productostv.get(indice);
                        GestorJSONv5.borrarArchivoTV(almacenTelevisor.productostv.get(indice).getModelo());
                        GestorJSONv5.agregarProductoArchivoTele(productoTelevisor);

                        AlmacenTelevisor almacenTelevisor1 = GestorJSONv5.generarAlmacenTelevisor(GestorJSONv5.vectorLineasTV());
                        DefaultListModel listModel = new DefaultListModel();
                        for (int x = 0; x < almacenTelevisor1.productostv.size(); x++) {

                            if (listModel.contains(almacenTelevisor1.productostv.get(x).getModelo())) {
                            } else {

                                listModel.addElement(almacenTelevisor1.productostv.get(x).getModelo());

                            }
                        }
                        listaProductos.setModel(listModel);
                    } catch (IOException ex) {
                        //error
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese solo nuemeros enteros y positivos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un modelo de la lista", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void mostrar(int i) {

        try {

            AlmacenTelevisor almacenTelevisor = new AlmacenTelevisor();
            String saltoLinea = System.getProperty("line.separator");
            area.setText("Los datos de celular son:" + saltoLinea + almacenTelevisor.productostv.get(i).toString());

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Seleccione un modelo de la lista", "Error", JOptionPane.ERROR_MESSAGE);

        }

    }
}

