package com.jeffersonmontoya;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConversorMonedas {
    private JFrame frame;
    private JComboBox<String> comboPaisOrigen;
    private JComboBox<String> comboPaisDestino;
    private JTextField textoCantidad;
    private JLabel etiquetaResultado;

    public ConversorMonedas() {
        crearInterfaz();
    }

    private void crearInterfaz() {
        frame = new JFrame("Conversor de Monedas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Combo pais origen
        String[] paises = {"USD - Estados Unidos", "EUR - Europa", "COP - Colombia", "MXN - México"};
        comboPaisOrigen = new JComboBox<>(paises);

        // Combo pais destino
        comboPaisDestino = new JComboBox<>(paises);

        // Texto cantidad
        textoCantidad = new JTextField(10);

        // Etiqueta resultado
        etiquetaResultado = new JLabel("");

        // Botón convertir
        JButton botonConvertir = new JButton("Convertir");
        botonConvertir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertirMoneda();
            }
        });

        frame.add(comboPaisOrigen);
        frame.add(comboPaisDestino);
        frame.add(textoCantidad);
        frame.add(botonConvertir);
        frame.add(etiquetaResultado);

        frame.pack();
        frame.setVisible(true);
    }

    private void convertirMoneda() {
        String paisOrigen = (String) comboPaisOrigen.getSelectedItem();
        String paisDestino = (String) comboPaisDestino.getSelectedItem();
        double cantidad = Double.parseDouble(textoCantidad.getText());

        CurrencyConverter converter = new CurrencyConverter();
        double resultado = converter.convertir(paisOrigen, paisDestino, cantidad);

        etiquetaResultado.setText(String.valueOf(resultado));
    }

    public static void main(String[] args) {
        new ConversorMonedas();
    }
}