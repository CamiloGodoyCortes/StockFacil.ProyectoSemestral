package ventanas;

import productos.Celular;
import validar.Validar;
import archivos.GestorJSONv5;
import funciones.AlmacenCelular;
import funciones.ProductoCelular;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GuiNuevoCelular extends JFrame implements ActionListener {

    private JPanel marcaP;
    private JLabel marcaLB;
    private JTextField marcaTF;

    private JPanel modeloP;
    private JLabel modeloLB;
    private JTextField modeloTF;

    private JPanel colorP;
    private JLabel colorLB;
    private JTextField colorTF;


    private JPanel guardarP;
    private JButton guardarB;

    private ProductoCelular productocelu;

    public GuiNuevoCelular(String titulo) {

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

        productocelu = new ProductoCelular();

        //Operaciones por defecto
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 200);
        setLocationRelativeTo(null);
        setResizable(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guardarB) {

            if (modeloTF.getText().isEmpty() || marcaTF.getText().isEmpty() || colorTF.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Uno o mas campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);

            } else {
                Validar validar = new Validar();
                if (validar.caracteresPermitidos(marcaTF.getText())== true && validar.caracteresPermitidos(colorTF.getText())== true){
                    Celular celular = new Celular();
                    celular.setMarca(marcaTF.getText());
                    celular.setColor(colorTF.getText());
                    productocelu.getCelulares().add(celular);
                    marcaTF.setText("");
                    colorTF.setText("");

                }
                else{
                    JOptionPane.showMessageDialog(null, "Caracteres inválidos en marca o color solo se admiten letras", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                    try {
                        productocelu.setModelo(modeloTF.getText());
                        AlmacenCelular almacencelular = GestorJSONv5.generarAlmacenCelular(GestorJSONv5.vectorLineasCL());
                        boolean existe = false;

                        for (int i = 0; i < almacencelular.productocelus.size(); i++) {

                            if (productocelu.getModelo().equals(almacencelular.productocelus.get(i).getModelo())) {

                                JOptionPane.showMessageDialog(null, "El modelo ya existe en el inventario", "Error", JOptionPane.WARNING_MESSAGE);
                                existe = true;

                            }
                        }

                        if (existe == true) {
                            return;
                        } else {
                            try {
                                GestorJSONv5.agregarProductoArchivoCelu(this.productocelu);
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
