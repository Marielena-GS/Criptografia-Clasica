package ui;

import cifrados.Cifrado;
import factory.CifradoFactory;
import java.awt.datatransfer.StringSelection;
import javax.swing.*;
import java.awt.*;

public class VentanaCifrado extends JFrame {

    private JTextArea entrada;
    private JTextArea salida;
    private JTextField clave;
    private JLabel descripcion;
    private JLabel ayudaClave;
    private JLabel contador;

    private int opcion = 1;

    public VentanaCifrado() {
        setTitle("Cifrado Clásico");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initUI();
    }

    private void initUI() {

        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        root.setBackground(new Color(245, 247, 250));

        JPanel top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
        top.setBackground(root.getBackground());

        JLabel titulo = new JLabel("Opciones de Cifrado Clásico", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        top.add(titulo);
        top.add(Box.createVerticalStrut(10));

        JPanel botones = new JPanel(new GridLayout(1, 5, 10, 10));
        botones.setBackground(root.getBackground());

        String[] nombres = { "César", "Atbash", "Vigenere", "Rail Fence", "Playfair" };

        for (int i = 0; i < nombres.length; i++) {
            int index = i + 1;

            JButton btn = new JButton(nombres[i]);
            btn.setFocusPainted(false);
            btn.setBackground(Color.WHITE);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

            btn.addActionListener(e -> seleccionar(index));

            botones.add(btn);
        }

        top.add(botones);
        top.add(Box.createVerticalStrut(10));

        descripcion = new JLabel("<html><b>Descripción:</b> Selecciona un cifrado</html>", JLabel.CENTER);
        descripcion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descripcion.setAlignmentX(Component.CENTER_ALIGNMENT);

        top.add(descripcion);

        top.add(Box.createVerticalStrut(10));

        JPanel panelClave = new JPanel();
        panelClave.setBackground(root.getBackground());

        ayudaClave = new JLabel("Clave:");
        ayudaClave.setFont(new Font("Segoe UI", Font.BOLD, 13));

        clave = new JTextField(15);

        panelClave.add(ayudaClave);
        panelClave.add(clave);

        top.add(panelClave);

        root.add(top, BorderLayout.NORTH);

        JPanel centro = new JPanel(new GridLayout(1, 2, 15, 15));
        centro.setBackground(root.getBackground());

        entrada = new JTextArea();
        entrada.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        entrada.setLineWrap(true);
        entrada.setWrapStyleWord(true);
        entrada.setBorder(BorderFactory.createTitledBorder("Texto a cifrar"));

        salida = new JTextArea();
        salida.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        salida.setLineWrap(true);
        salida.setWrapStyleWord(true);
        salida.setBorder(BorderFactory.createTitledBorder("Resultado"));

        JScrollPane scroll1 = new JScrollPane(entrada);
        JScrollPane scroll2 = new JScrollPane(salida);

        centro.add(scroll1);
        centro.add(scroll2);

        root.add(centro, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
        bottom.setBackground(root.getBackground());

        contador = new JLabel("Caracteres: 0");
        contador.setAlignmentX(Component.CENTER_ALIGNMENT);

        entrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                contador.setText("Caracteres: " + entrada.getText().length());
            }
        });

        bottom.add(contador);
        bottom.add(Box.createVerticalStrut(10));

        JPanel acciones = new JPanel();
        acciones.setBackground(root.getBackground());

        JButton cifrar = new JButton("Cifrar");
        JButton descifrar = new JButton("Descifrar");
        JButton copiar = new JButton("Copiar");

        cifrar.setBackground(new Color(52, 152, 219));
        cifrar.setForeground(Color.WHITE);

        descifrar.setBackground(new Color(46, 204, 113));
        descifrar.setForeground(Color.WHITE);

        copiar.setBackground(new Color(155, 89, 182));
        copiar.setForeground(Color.WHITE);

        acciones.add(cifrar);
        acciones.add(descifrar);
        acciones.add(copiar);

        bottom.add(acciones);

        root.add(bottom, BorderLayout.SOUTH);

        cifrar.addActionListener(e -> procesar(true));

        descifrar.addActionListener(e -> {
            entrada.setText(salida.getText());
            procesar(false);
        });

        copiar.addActionListener(e -> {
            StringSelection sel = new StringSelection(salida.getText());
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel, null);
            JOptionPane.showMessageDialog(this, "Texto copiado");
        });

        setContentPane(root);
    }

    private void seleccionar(int op) {
        this.opcion = op;

        Cifrado c = CifradoFactory.crearCifrado(op, 1, "clave");
        descripcion.setText("<html><b>Descripción:</b> " + c.getDescripcion() + "</html>");

        switch (op) {
            case 1:
                ayudaClave.setText("Clave: Número (ej: 3)");
                break;
            case 2:
                ayudaClave.setText("No necesita clave");
                break;
            case 3:
                ayudaClave.setText("Clave: palabra (ej: clave)");
                break;
            case 4:
                ayudaClave.setText("Clave: número (ej: 3)");
                break;
            case 5:
                ayudaClave.setText("Clave: palabra sin espacios");
                break;
        }
    }

    private void procesar(boolean cifrar) {
        try {
            String texto = entrada.getText();
            String claveTexto = clave.getText();
            int claveNum = 0;

            if (claveTexto.matches("\\d+")) {
                claveNum = Integer.parseInt(claveTexto);
            }

            Cifrado c = CifradoFactory.crearCifrado(opcion, claveNum, claveTexto);
            String res = cifrar ? c.cifrar(texto) : c.descifrar(texto);

            salida.setText(res);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}