package com.mycompany.nicolasproyectonew2;

import java.sql.*;
import javax.swing.JOptionPane;

public class NicolasProyectonew2 {
    
    private static Connection connection;

    public static void main(String[] args) {
        conectarBaseDeDatos();
        menu();
    }

    private static void conectarBaseDeDatos() {
        String connectionURL = "jdbc:sqlserver://127.0.0.1:1433;"
                + "databaseName=Gorras;"
                + "user=Usersql;"
                + "password=root2;"
                + "encrypt=false;";
        try {
            connection = DriverManager.getConnection(connectionURL);
            System.out.println("Conexión exitosa.");
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }

    public static void menu() {
        int opcion = 0;

        while (opcion != 4) {
            String lectura = JOptionPane.showInputDialog(null, "INVENTARIO DE ARTÍCULOS\n1.- Ventas"
                    + "\n2.- Compras\n"
                    + "3.- Saldos\n"
                    + "4.- Salir\nElija una opción:\n");
            opcion = Integer.parseInt(lectura);
            switch (opcion) {
                case 1:
                    menuVentas();
                    break;
                case 2:
                    mostrarMenuProveedores();
                    break;
                case 3:
                    saldo();  // Llamamos la función saldo()
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "H A S T A  P R O N T O!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
                    break;
            }
        }
    }

    public static void saldo() {
    String SQL = "SELECT nombre,stock FROM productos"; // Muestra todos los stocks

    try (Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery(SQL)) {

        System.out.println("Leyendo datos...");

        StringBuilder mensaje = new StringBuilder("Stock disponible:\n");
        boolean hayDatos = false; 

        while (rs.next()) { // Iteramos por todas las filas
            int stock = rs.getInt("stock");
          
            mensaje.append("Stock: ").append(stock).append("\n");
            hayDatos = true;
        }

        if (hayDatos) {
            JOptionPane.showMessageDialog(null, mensaje.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron artículos.");
        }

    } catch (SQLException e) {
        System.out.println("Error al ejecutar la consulta: " + e.getMessage());
    }
}

    private static void mostrarMenuProveedores() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void menuVentas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public static void actualizarStock(String marca, int cantidad) {
    String sql = "UPDATE producto SET stock = stock + ? WHERE nombre = ?";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, cantidad);
        statement.setString(2, marca);
        int filasActualizadas = statement.executeUpdate();

        if (filasActualizadas > 0) {
            JOptionPane.showMessageDialog(null, "Stock actualizado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el producto para actualizar el stock.");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al actualizar el stock: " + e.getMessage());
    }
}
    public static void insertarCompraProveedor(String nombreProveedor, int cantidadComprada, int valorTotalCompra) {
        if (connection != null) {
            String sql = "INSERT INTO Proveedores (NombreProveedor, CantidadComprada, ValorTotalCompra) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, nombreProveedor);
                preparedStatement.setInt(2, cantidadComprada);
                preparedStatement.setInt(3, valorTotalCompra);
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Compra registrada en proveedores correctamente.");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al registrar la compra en proveedores: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay conexión a la base de datos.");
        }
    }
}
