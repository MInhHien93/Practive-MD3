package model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/PractiveMD3";
        String jdbcUser = "root";
        String jdbcPassword = "Anhhien121";
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
            System.out.println("Kết Nối Thành Công ");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Kết Nối Không Thành Công ");
        }
        return connection;
    }
}
