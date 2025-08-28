package org.example.servertime.week12.mcv_model.product_exercise;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private final ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create": showCreateForm(req, resp); break;
            case "edit": showEditForm(req, resp); break;
            case "delete" : showDeleteForm(req, resp); break;
            case "view": viewProduct(req, resp); break;
            case "search": searchProduct(req, resp); break;
            default: listProducts(req, resp); break;
        }
    }

    private void listProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<Product> products = productService.findAll();
        req.setAttribute("products", products);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/week12_tomcat/mvc_model/exercise/list.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void searchProduct(HttpServletRequest req, HttpServletResponse resp) {
        String keyword = req.getParameter("keyword");
        List<Product> products = productService.searchByName(keyword);
        req.setAttribute("products", products);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/week12_tomcat/mvc_model/exercise/list.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void viewProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = this.productService.findById(id);
        RequestDispatcher dispatcher;
        if (product == null) {
            dispatcher = req.getRequestDispatcher("/week12_tomcat/mvc_model/exercise/error-404.jsp");
        } else {
            req.setAttribute("product", product);
            dispatcher = req.getRequestDispatcher("/week12_tomcat/mvc_model/exercise/view.jsp");
        }
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showDeleteForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = this.productService.findById(id);
        RequestDispatcher dispatcher;
        if (product == null) {
            dispatcher = req.getRequestDispatcher("/week12_tomcat/mvc_model/exercise/error-404.jsp");
        } else {
            req.setAttribute("product", product);
            dispatcher = req.getRequestDispatcher("/week12_tomcat/mvc_model/exercise/delete.jsp");
        }
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = this.productService.findById(id);
        RequestDispatcher dispatcher;
        if (product == null) {
            dispatcher = req.getRequestDispatcher("/week12_tomcat/mvc_model/exercise/error-404.jsp");
        } else {
            req.setAttribute("product", product);
            dispatcher = req.getRequestDispatcher("/week12_tomcat/mvc_model/exercise/edit.jsp");
        }
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        RequestDispatcher dispatcher = req.getRequestDispatcher("/week12_tomcat/mvc_model/exercise/create.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create": createProduct(req, resp); break;
            case "edit": updateProduct(req, resp); break;
            case "delete": deleteProduct(req, resp); break;
        }
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = this.productService.findById(id);
        RequestDispatcher dispatcher;
        if (product == null) {
            dispatcher = req.getRequestDispatcher("/week12_tomcat/mvc_model/exercise/error-404.jsp");
        } else {
            this.productService.remove(id);
            try {
                resp.sendRedirect("/server-time/products");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        resp.sendRedirect("/products");
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");
        String manufacturer = req.getParameter("manufacturer");

        Product product = this.productService.findById(id);
        RequestDispatcher dispatcher;
        if (product == null) {
            dispatcher = req.getRequestDispatcher("/week12_tomcat/mvc_model/exercise/error-404.jsp");
        } else {
            product.setName(name);
            product.setPrice(price);
            product.setDescription(description);
            product.setManufacturer(manufacturer);
            this.productService.update(id, product);
            req.setAttribute("product", product);
            req.setAttribute("message", "Product has been updated successfully!");
            dispatcher = req.getRequestDispatcher("/week12_tomcat/mvc_model/exercise/edit.jsp");
        }
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (int) (Math.random() * 10000);
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");
        String manufacturer = req.getParameter("manufacturer");

        Product product = new Product(id, name, price, description, manufacturer);
        this.productService.save(product);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/week12_tomcat/mvc_model/exercise/create.jsp");
        req.setAttribute("message", "Product has been created successfully!");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/products");
    }
}
