package model.service;

import model.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProduct {
    private static final String SELECT_ALL_PRODUCT = "SELECT * FROM product;";
    private static final String INSERT_NEW_PRODUCT = "INSERT INTO product(name, price, quantity, color, des, name_category) VALUES (?,?,?,?,?,?);";
    private static final String DELETE_PRODUCT = "DELETE FROM product WHERE name =?;";
    private static final String UPDATE_PRODUCT = "UPDATE product SET name=?, price=?, quantity=?, color=?, des=?, name_category=? WHERE name=?;";
    private static final String SELECT_PRODUCT_BY_NAME = "SELECT * FROM product WHERE name=?;";
    private static final String SEARCH_ALL_PRODUCT_BY_NAME = "SELECT * FROM product WHERE name like ?";

    @Override
    public List<Product> selectAllProduct() throws SQLException {
        List<Product> list = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nameProduct = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                String description = rs.getString("des");
                String name_category = rs.getString("name_category");
                list.add(new Product(id, nameProduct, price, quantity, color, description, name_category));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public void insertProduct(Product product) throws SQLException {
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setString(6, product.getName_category());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Product selectProductByName(String name) throws SQLException {
        Product product = null;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String nameProduct = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                String description = rs.getString("des");
                String name_category = rs.getString("name_category");
                product = new Product(nameProduct, price, quantity, color, description, name_category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }

    @Override
    public boolean updateProduct(String nameProduct, Product product) throws SQLException {
        boolean updated = true;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setString(6, product.getName_category());
            preparedStatement.setString(7, nameProduct);
            updated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return updated;
    }

    @Override
    public boolean deleteProduct(String nameProduct) throws SQLException {
        boolean deleted = true;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT);
            preparedStatement.setString(1, nameProduct);
            deleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return deleted;
    }

    @Override
    public List<Product> searchProduct(String name) throws SQLException {
        List<Product> list = new ArrayList<>();
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_ALL_PRODUCT_BY_NAME);) {
            name = "%" + name + "%";
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String productName = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                String description = rs.getString("des");
                String name_category = rs.getString("name_category");
                list.add(new Product(id, productName, price, quantity, color, description, name_category));
            }
        }
        return list;
    }
}

