package Ventanas;

import Productos.ComputadorPortatil;
import Validar.Validar;
import archivos.GestorJSONv5;
import funciones.AlmacenPC;
import funciones.ProductoPC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GuiNuevoPC extends JFrame implements ActionListener {
    protected JPanel marcaP;
    protected JLabel marcaLB;
    protected JTextField marcaTF;

    protected JPanel modeloP;
    protected JLabel modeloLB;
    protected JTextField modeloTF;

    protected JPanel colorP;
    protected JLabel colorLB;
    protected JTextField colorTF;


    protected JPanel guardarP;
    protected JButton guardarB;
    protected ProductoPC productospc;

    public GuiNuevoPC(String titulo) {

        super(titulo);
        this.setLayout(new FlowLayout());

        marcaP = new JPanel();
        marcaLB = new JLabel("Marca");
        marcaTF = new JTextField(10);

        modeloP = new JPanel();
        modeloLB = new JLabel("Modelo");
        modeloTF = new JTextField(10);

        colorP = new JPanel();
        colorLB = new JLabel("Color");
        colorTF = new JTextField(10);


        guardarP = new JPanel();
        guardarB = new JButton("Guardar");

        marcaP.add(marcaLB);
        marcaP.add(marcaTF);

        modeloP.add(modeloLB);
        modeloP.add(modeloTF);

        colorP.add(colorLB);
        colorP.add(colorTF);

        guardarP.add(guardarB);

        this.add(marcaP);
        this.add(modeloP);
        this.add(colorP);
        this.add(guardarP);

        guardarB.addActionListener(this);
        productospc = new ProductoPC();
        //Operaciones por defecto
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 200);
        setLocationRelativeTo(null);
        setResizable(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guardarB) {
            if (e.getSource() == guardarB) {

                if (modeloTF.getText().isEmpty() || marcaTF.getText().isEmpty() || colorTF.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Uno o mas campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);

                } else {
                    Validar validar = new Validar();
                    if (validar.caracteresPermitidos(marcaTF.getText())== true || validar.caracteresPermitidos(colorTF.getText())== true){
                        ComputadorPortatil pc = new ComputadorPortatil();
                        pc.setMarca(marcaTF.getText());
                        pc.setColor(colorTF.getText());
                        productospc.getComputadoresPortatiles().add(pc);
                        marcaTF.setText("");
                        colorTF.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Caracteres inválidos en marca o color solo se admiten letras", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    try {
                        productospc.setModelo(modeloTF.getText());
                        AlmacenPC almacenPC = GestorJSONv5.generarAlmacenPC(GestorJSONv5.vectorLineasPC());
                        boolean existe = false;

                        for (int i = 0; i < almacenPC.productospc.size(); i++) {

                            if (productospc.getModelo().equals(almacenPC.productospc.get(i).getModelo())) {

                                JOptionPane.showMessageDialog(null, "El modelo ya existe en el inventario", "Error", JOptionPane.WARNING_MESSAGE);
                                existe = true;


                            }
                        }

                        if (existe == true) {
                            return;
                        } else {
                            try {
                                GestorJSONv5.agregarProductoArchivoPC(this.productospc);
                                setVisible(false);

                            } catch (IOException e2) {
                            }
                        }

                    } catch (IOException e1) {
                    }

                }
            }
        }
    }
    }

