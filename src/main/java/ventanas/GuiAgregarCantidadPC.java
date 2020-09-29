package ventanas;

import validar.Validar;
import archivos.GestorJSONv5;
import funciones.AlmacenPC;
import funciones.ProductoPC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class GuiAgregarCantidadPC extends JFrame implements ActionListener {
    protected JScrollPane menuScrollPane;


    protected JPanel areaP;
    protected JTextArea area;
    protected JScrollPane areaScrollPane;

    protected JList listaProductos;
    protected JLabel counterLB;
    protected JLabel modeloLB;
    protected JPanel modeloP;

    protected JPanel seleccionarP;
    protected JButton seleccionarB;


    protected JPanel agregarP;
    protected JTextField agregarTF;
    protected JButton agregarB;

    public GuiAgregarCantidadPC(String title) throws IOException {

        super(title);
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);


        seleccionarB = new JButton("Seleccionar");
        seleccionarP = new JPanel();

        modeloP = new JPanel();
        modeloLB = new JLabel("Modelos");
        // para el Jlist
        AlmacenPC almacenPC = GestorJSONv5.generarAlmacenPC(GestorJSONv5.vectorLineasPC());
        DefaultListModel listModel = new DefaultListModel();
        for (int x = 0; x < almacenPC.productospc.size(); x++) {

            if (listModel.contains(almacenPC.productospc.get(x).getModelo())) {
            } else {

                listModel.addElement(almacenPC.productospc.get(x).getModelo());

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
        counterLB = new JLabel(String.valueOf(almacenPC.verCantidadproductosPC()));

        //Agregar
        this.agregarP = new JPanel();
        this.agregarB = new JButton("Agregar");
        this.agregarTF = new JTextField(8);
        //agregar los comportamientos a los obejtos de loa ventana
        seleccionarB.addActionListener(this);
        agregarB.addActionListener(this);

        // agregar objetos a la ventana
        modeloP.add(modeloLB);
        modeloP.add(counterLB);

        seleccionarP.add(seleccionarB);

        areaP.add(areaScrollPane);

        agregarP.add(agregarB);
        agregarP.add(agregarTF);

        this.add(modeloP);
        //para la barra
        this.add(menuScrollPane);
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.add(seleccionarP);
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
                almacenPC.productospc.clear();
                setVisible(false);
            }
        });


    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == seleccionarB) {
            int i = listaProductos.getSelectedIndex();
            mostrar(i);
        }

        if (e.getSource() == agregarB) {
            int indice = listaProductos.getSelectedIndex();
            AlmacenPC almacenPC = new AlmacenPC();
            Validar validar = new Validar();
            if (indice > -1) {
                if ((validar.validarNumero(agregarTF.getText()))==true) {
                    almacenPC.productospc.get(indice).agregarProducto(Integer.parseInt(agregarTF.getText()));

                    try {
                        ProductoPC productoPC = almacenPC.productospc.get(indice);
                        GestorJSONv5.borrarArchivoPC(almacenPC.productospc.get(indice).getModelo());
                        GestorJSONv5.agregarProductoArchivoPC(productoPC);

                        AlmacenPC AlmacenPCS = GestorJSONv5.generarAlmacenPC(GestorJSONv5.vectorLineasPC());
                        DefaultListModel listModel = new DefaultListModel();
                        for (int x = 0; x < AlmacenPCS.productospc.size(); x++) {

                            if (listModel.contains(AlmacenPCS.productospc.get(x).getModelo())) {
                            } else {

                                listModel.addElement(AlmacenPCS.productospc.get(x).getModelo());

                            }
                        }
                        listaProductos.setModel(listModel);
                    } catch (IOException ex) {
                        //error
                    }

                }else {
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

            AlmacenPC almacenPC = new AlmacenPC();
            String saltoLinea = System.getProperty("line.separator");
            area.setText("Los datos de celular son:" + saltoLinea + almacenPC.productospc.get(i).toString());

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Seleccione un modelo de la lista", "Error", JOptionPane.ERROR_MESSAGE);

        }

    }


}

