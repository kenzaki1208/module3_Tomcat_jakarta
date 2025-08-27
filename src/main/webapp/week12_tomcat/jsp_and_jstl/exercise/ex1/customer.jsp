<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: duytr
  Date: 27/08/2025
  Time: 09:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        class  Customer {
            String name;
            String dob;
            String address;
            String image;

            public  Customer(String name, String dob, String address, String image) {
                this.name = name;
                this.dob = dob;
                this.address = address;
                this.image = image;
            }

            public String getName() {
                return name;
            }

            public String getDob() {
                return dob;
            }

            public String getAddress() {
                return address;
            }

            public String getImage() {
                return image;
            }
        }

        java.util.List<Customer> customerList = new java.util.ArrayList<>();
        customerList.add(new Customer("Mai Văn Hoàn", "1983-08-20", "Hà Nội", "/server-time/week12_tomcat/jsp_and_jstl/exercise/ex1/images/images.jfif"));
        customerList.add(new Customer("Nguyễn Văn Nam", "1983-08-20", "Hà Nội", "/server-time/week12_tomcat/jsp_and_jstl/exercise/ex1/images/images.png"));
        customerList.add(new Customer("Nguyễn Thái Hòa", "1983-08-20", "Hà Nội", "/server-time/week12_tomcat/jsp_and_jstl/exercise/ex1/images/images(1).jfif"));
        customerList.add(new Customer("Trần Đăng Khoa", "1983-08-20", "Hà Nội", "/server-time/week12_tomcat/jsp_and_jstl/exercise/ex1/images/images(2).jfif"));
        customerList.add(new Customer("Nguyễn Đình Thi", "1983-08-20", "Hà Nội", "/server-time/week12_tomcat/jsp_and_jstl/exercise/ex1/images/images_3.jfif"));

        request.setAttribute("customers", customerList);
        request.getRequestDispatcher("/week12_tomcat/jsp_and_jstl/exercise/ex1/index.jsp").forward(request, response);
    %>
</body>
</html>
