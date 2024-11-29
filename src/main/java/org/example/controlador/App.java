package org.example.controlador;

import org.example.vista.VistaLogin;

import java.sql.SQLException;

class App
{
    public static void main( String[] args ) throws SQLException {
        VistaLogin loginVista = new VistaLogin();
        loginVista.setVisible(true);
    }
}
