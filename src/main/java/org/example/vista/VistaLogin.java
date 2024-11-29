package org.example.vista;

import org.example.dao.UsuarioDao;
import org.example.modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaLogin extends JFrame {

    private UsuarioDao usuarioDAO;

    public VistaLogin() {
        usuarioDAO = new UsuarioDao();  // Inicializar el acceso a la base de datos

        // Configuración de la ventana principal de login
        setTitle("Login - Escape Room");
        setSize(400, 250);  // Aumentamos el tamaño para incluir el nuevo botón
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel loginPanel = new JPanel(new GridLayout(4, 2));  // Cambiamos de 3 a 4 filas

        // Componentes de la ventana de login
        JLabel userLabel = new JLabel("Usuario:");
        JTextField userField = new JTextField(20);

        JLabel passLabel = new JLabel("Contraseña:");
        JPasswordField passField = new JPasswordField(20);

        JButton loginButton = new JButton("Iniciar Sesión");
        JButton registerButton = new JButton("Registrarse");
        JButton viewSessionsButton = new JButton("Ver Inicios de Sesión");  // Nuevo botón

        // Añadir componentes al panel
        loginPanel.add(userLabel);
        loginPanel.add(userField);
        loginPanel.add(passLabel);
        loginPanel.add(passField);
        loginPanel.add(loginButton);
        loginPanel.add(registerButton);// Añadimos el botón de ver sesiones
        loginPanel.add(viewSessionsButton);
        // Añadir el panel a la ventana
        add(loginPanel);

        // Acción del botón de "Registrarse"
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRegisterWindow();
            }
        });

        // Acción del botón de "Iniciar Sesión"
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = userField.getText();
                String contraseña = new String(passField.getPassword());

                // Validar usuario y contraseña desde la base de datos
                if (usuarioDAO.validarUsuario(usuario, contraseña)) {
                    JOptionPane.showMessageDialog(null, "Login Exitoso");

                    // Cerrar la ventana de login
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o Contraseña incorrectos");
                }
            }
        });

    }

    // Método para crear la ventana de registro
    private void openRegisterWindow() {
        JFrame registerFrame = new JFrame("Registro");
        registerFrame.setSize(500, 400);
        registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registerFrame.setLocationRelativeTo(null);

        // Panel principal para registro
        JPanel registerPanel = new JPanel(new GridLayout(7, 2, 10, 10));

        // Componentes de la ventana de registro
        JLabel nameLabel = new JLabel("Nombre:");
        JTextField nameField = new JTextField(20);

        JLabel surnameLabel = new JLabel("Apellido:");
        JTextField surnameField = new JTextField(20);

        JLabel ageLabel = new JLabel("Edad:");
        JTextField ageField = new JTextField(20);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);

        JLabel userLabel = new JLabel("Usuario:");
        JTextField userField = new JTextField(20);

        JLabel passLabel = new JLabel("Contraseña:");
        JPasswordField passField = new JPasswordField(20);

        JLabel confirmPassLabel = new JLabel("Confirmar Contraseña:");
        JPasswordField confirmPassField = new JPasswordField(20);

        JButton createAccountButton = new JButton("Crear Cuenta");

        // Añadir componentes al panel
        registerPanel.add(nameLabel);
        registerPanel.add(nameField);
        registerPanel.add(surnameLabel);
        registerPanel.add(surnameField);
        registerPanel.add(ageLabel);
        registerPanel.add(ageField);
        registerPanel.add(emailLabel);
        registerPanel.add(emailField);
        registerPanel.add(userLabel);
        registerPanel.add(userField);
        registerPanel.add(passLabel);
        registerPanel.add(passField);
        registerPanel.add(confirmPassLabel);
        registerPanel.add(confirmPassField);
        registerPanel.add(new JLabel()); // Espacio vacío
        registerPanel.add(createAccountButton);

        // Añadir el panel a la ventana de registro
        registerFrame.add(registerPanel);
        registerFrame.setVisible(true);

        // Acción del botón "Crear Cuenta"
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nameField.getText();
                String apellido = surnameField.getText();
                String edadTexto = ageField.getText();
                String email = emailField.getText();
                String usuario = userField.getText();
                String contraseña = new String(passField.getPassword());
                String confirmarContraseña = new String(confirmPassField.getPassword());

                // Validar campos
                if (nombre.isEmpty() || apellido.isEmpty() || edadTexto.isEmpty() || email.isEmpty() || usuario.isEmpty() || contraseña.isEmpty()) {
                    JOptionPane.showMessageDialog(registerFrame, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validar edad
                int edad;
                try {
                    edad = Integer.parseInt(edadTexto);
                    if (edad <= 0) {
                        JOptionPane.showMessageDialog(registerFrame, "Por favor, ingresa una edad válida.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(registerFrame, "La edad debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validar formato de email
                if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$")) {
                    JOptionPane.showMessageDialog(registerFrame, "Por favor, ingresa un email válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validar coincidencia de contraseñas
                if (!contraseña.equals(confirmarContraseña)) {
                    JOptionPane.showMessageDialog(registerFrame, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Crear un nuevo objeto Usuario
                Usuario usuarioNuevo = new Usuario(nombre, apellido, edad, email, usuario, contraseña);

                // Intentar registrar el nuevo usuario
                if (usuarioDAO.registrarUsuario(usuarioNuevo)) {
                    JOptionPane.showMessageDialog(registerFrame, "Cuenta creada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    registerFrame.dispose(); // Cerrar la ventana de registro
                } else {
                    JOptionPane.showMessageDialog(registerFrame, "Error al crear la cuenta. Intenta con otro nombre de usuario.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VistaLogin().setVisible(true);
        });
    }
}
