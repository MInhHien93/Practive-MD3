package model.service;

import model.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProduct {
    List<Product> selectAllProduct() throws SQLException;
    void insertProduct(Product product) throws SQLException;
    Product selectProductByName(String name) throws SQLException;
    boolean updateProduct(String name, Product product) throws SQLException;
    boolean deleteProduct(String name) throws SQLException;
    List<Product> searchProduct(String name) throws SQLException;
}
