package org.example.servertime.week12;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/list-customers")
public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customerList = new ArrayList<Customer>();
        customerList.add(new Customer("Mai Văn Hoàn", "1983-08-20", "Hà Nội", "/server-time/week12_tomcat/jsp_and_jstl/exercise/ex1/images/images.jfif"));
        customerList.add(new Customer("Nguyễn Văn Nam", "1983-08-20", "Hà Nội", "/server-time/week12_tomcat/jsp_and_jstl/exercise/ex1/images/images.png"));
        customerList.add(new Customer("Nguyễn Thái Hòa", "1983-08-20", "Hà Nội", "/server-time/week12_tomcat/jsp_and_jstl/exercise/ex1/images/images(1).jfif"));
        customerList.add(new Customer("Trần Đăng Khoa", "1983-08-20", "Hà Nội", "/server-time/week12_tomcat/jsp_and_jstl/exercise/ex1/images/images(2).jfif"));
        customerList.add(new Customer("Nguyễn Đình Thi", "1983-08-20", "Hà Nội", "/server-time/week12_tomcat/jsp_and_jstl/exercise/ex1/images/images_3.jfif"));

        request.setAttribute("customers", customerList);
        request.getRequestDispatcher("/week12_tomcat/jsp_and_jstl/exercise/ex1/index.jsp").forward(request, response);
    }
}
