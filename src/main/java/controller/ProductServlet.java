package controller;

import model.entity.Product;
import model.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/productController")
public class ProductServlet extends HttpServlet {
    ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    showAddForm(request, response);
                    break;
                case "delete":
                    deleteProduct(request, response);
                    break;
                case "update":
                    showEditForm(request, response);
                    break;
                default:
                    showProduct(request, response);
                    break;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                try {
                    insertProduct(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "update":
                try {
                    updateProduct(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "search":
                try {
                    searchProduct(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
        }
    }

    protected void showProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Product> list = productService.selectAllProduct();
        request.setAttribute("list", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    protected void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("createProduct.jsp");
        dispatcher.forward(request, response);
    }


    protected void insertProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String nameProduct = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        String name_category = request.getParameter("name_category");
        Product product = new Product(nameProduct, price, quantity, color, description, name_category);
        productService.insertProduct(product);
        response.sendRedirect("/productController");
    }


    protected void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String nameProduct = request.getParameter("name");
        Product product = productService.selectProductByName(nameProduct);
        request.setAttribute("product", product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("updateProduct.jsp");
        dispatcher.forward(request, response);
    }

    protected void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String nameProduct = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("des");
        String name_category = request.getParameter("name_category");
        Product product = new Product(nameProduct, price, quantity, color, description, name_category);
        productService.updateProduct(nameProduct, product);
        response.sendRedirect("/productController");
    }

    protected void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String name = request.getParameter("name");
        productService.deleteProduct(name);
        response.sendRedirect("/productController");
    }

    protected void searchProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String nameProduct = request.getParameter("name");
        List<Product> list = productService.searchProduct(nameProduct);
        request.setAttribute("search", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("searchProduct.jsp");
        dispatcher.forward(request, response);
    }
}
