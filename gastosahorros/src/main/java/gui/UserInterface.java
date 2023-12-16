package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import controllers.UsuariosController;
import models.Usuarios;

public class UserInterface {

    private static UsuariosController usuariosController = new UsuariosController();
    private static JTextField nombreTextField;
    private static JTextField contraseñaTextField;
    private static JTextArea resultTextArea;
    private static JFrame frame;

    public static void createAndShowGUI() {
        frame = new JFrame("Gestión de Usuarios");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setMinimumSize(new Dimension(600, 300)); // Establece un tamaño mínimo para la ventana

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Gestión de Usuarios");
        titleLabel.setBounds(10, 20, 200, 25);
        panel.add(titleLabel);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(10, 60, 80, 25);
        panel.add(nombreLabel);

        nombreTextField = new JTextField(20);
        nombreTextField.setBounds(100, 60, 165, 25);
        panel.add(nombreTextField);

        JLabel contraseñaLabel = new JLabel("Contraseña:");
        contraseñaLabel.setBounds(10, 100, 80, 25);
        panel.add(contraseñaLabel);

        contraseñaTextField = new JTextField(20);
        contraseñaTextField.setBounds(100, 100, 165, 25);
        panel.add(contraseñaTextField);

        JButton searchUserButton = new JButton("Mostrar Usuarios");
        searchUserButton.setBounds(10, 140, 150, 25);
        panel.add(searchUserButton);

        resultTextArea = new JTextArea();
        resultTextArea.setBounds(300, 60, 270, 135);
        resultTextArea.setEditable(false);
        panel.add(resultTextArea);

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setBounds(10, 180, 150, 25);
        panel.add(loginButton);

        searchUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreTextField.getText().trim();
                List<Usuarios> usuarios = usuariosController.buscarUsuariosPorNombre(nombre);
                displaySearchResult(usuarios);
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreTextField.getText().trim();
                String contraseña = contraseñaTextField.getText().trim();

                if (usuariosController.iniciarSesion(nombre, contraseña)) {
                    openSecondPanel(nombre);
                } else {
                    JOptionPane.showMessageDialog(null, "Inicio de sesión fallido. Verifica tu nombre y contraseña.", "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ajusta la altura de la ventana para que termine justo después del último botón
        int windowHeight = Math.max(resultTextArea.getBounds().y + resultTextArea.getBounds().height + 40, loginButton.getBounds().y + loginButton.getBounds().height + 40);
        panel.setPreferredSize(new Dimension(600, windowHeight));

        frame.pack(); // Hace que la ventana se ajuste automáticamente al nuevo tamaño
    }

    private static void openSecondPanel(String nombreUsuario) {
        // Aquí puedes abrir el segundo panel con la información de resumen de categorías
        // Puedes utilizar otro JFrame o un JDialog, dependiendo de tus necesidades
        JFrame secondFrame = new JFrame("Resumen de Categorías - " + nombreUsuario);
        secondFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        secondFrame.setSize(400, 200);

        JPanel panel = new JPanel();
        secondFrame.add(panel);

        JLabel welcomeLabel = new JLabel("¡Bienvenido, " + nombreUsuario + "!");
        welcomeLabel.setBounds(10, 20, 200, 25);
        panel.add(welcomeLabel);

        // Agrega más componentes y lógica para el segundo panel según tus necesidades

        secondFrame.setVisible(true);
    }

    private static void displaySearchResult(List<Usuarios> usuarios) {
        if (usuarios.isEmpty()) {
            resultTextArea.setText("No se encontraron usuarios con ese nombre.");
        } else {
            StringBuilder result = new StringBuilder("Usuarios encontrados:\n");
            for (Usuarios usuario : usuarios) {
                result.append("ID: ").append(usuario.getId_usuario()).append(", Nombre: ").append(usuario.getNombre()).append("\n");
            }
            resultTextArea.setText(result.toString());
        }
    }
}
