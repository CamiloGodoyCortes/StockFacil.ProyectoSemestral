package ventanas;

import productos.Televisor;
import validar.Validar;
import archivos.GestorJSONv5;
import funciones.AlmacenTelevisor;
import funciones.ProductoTelevisor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GuiNuevoTelevisor extends JFrame implements ActionListener {
    protected JPanel marcaP;
    protected JLabel marcaLB;
    protected JTextField marcaTF;

    protected JPanel ModeloP;
    protected JLabel ModeloLB;
    protected JTextField ModeloTF;

    protected JPanel tamañopulgadasP;
    protected JLabel tamañopulgadasLB;
    protected JTextField tamañopulgadasTF;

    protected JPanel guardarP;
    protected JButton guardarB;

    protected ProductoTelevisor productostv;

    public GuiNuevoTelevisor(String titulo) {

        super(titulo);
        this.setLayout(new FlowLayout());

        marcaP = new JPanel();
        marcaLB = new JLabel("Marca");
        marcaTF = new JTextField(10);

        ModeloP = new JPanel();
        ModeloLB = new JLabel("Modelo");
        ModeloTF = new JTextField(10);

        tamañopulgadasP = new JPanel();
        tamañopulgadasLB = new JLabel("TamañoPulgadas");
        tamañopulgadasTF = new JTextField(10);


        guardarP = new JPanel();
        guardarB = new JButton("Guardar");

        marcaP.add(marcaLB);
        marcaP.add(marcaTF);

        ModeloP.add(ModeloLB);
        ModeloP.add(ModeloTF);

        tamañopulgadasP.add(tamañopulgadasLB);
        tamañopulgadasP.add(tamañopulgadasTF);

        guardarP.add(guardarB);

        this.add(marcaP);
        this.add(ModeloP);
        this.add(tamañopulgadasP);
        this.add(guardarP);

        guardarB.addActionListener(this);
        productostv = new ProductoTelevisor();
        //Operaciones por defecto
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 200);
        setLocationRelativeTo(null);
        setResizable(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guardarB) {

            if (marcaTF.getText().isEmpty() || ModeloTF.getText().isEmpty() || tamañopulgadasTF.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Uno o mas campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);

            } else {
                Validar validar = new Validar();
                if (validar.validarNumero(tamañopulgadasTF.getText()) == false) {
                    JOptionPane.showMessageDialog(null, "Caracteres inválidos en TamañoPulgadas Ingrese solo numeros enteros", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {

                    if (validar.caracteresPermitidos(marcaTF.getText()) == true) {
                        Televisor televisor = new Televisor();
                        televisor.setMarca(marcaTF.getText());
                        televisor.setTamañoPulgadas((tamañopulgadasTF.getText()));
                        productostv.getTelevisores().add(televisor);

                    } else {
                        JOptionPane.showMessageDialog(null, "Caracteres inválidos en Marca ", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    try {
                        productostv.setModelo(ModeloTF.getText().toString());
                        AlmacenTelevisor televisor = GestorJSONv5.generarAlmacenTelevisor(GestorJSONv5.vectorLineasTV());
                        boolean existe = false;
                        for (int i = 0; i < televisor.productostv.size(); i++) {

                            if (productostv.getModelo().equals(televisor.productostv.get(i).getModelo())) {

                                JOptionPane.showMessageDialog(null, "El modelo ya existe en el inventario", "Error", JOptionPane.WARNING_MESSAGE);
                                existe = true;

                            }
                        }
                        if (existe == true) {
                            return;
                        } else {
                            try {
                                GestorJSONv5.agregarProductoArchivoTele(this.productostv);
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




