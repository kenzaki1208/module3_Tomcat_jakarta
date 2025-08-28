<%--
  Created by IntelliJ IDEA.
  User: duytr
  Date: 28/08/2025
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>Product Discount Calculator - Result</h2>

    <p><b>Product Description:</b> ${description}</p>
    <p><b>List Price:</b> ${price}</p>
    <p><b>Discount Percent:</b> ${percent}%</p>
    <p><b>Discount Amount:</b> ${discountAmount}</p>
    <p><b>Discount Price:</b> ${discountPrice}</p>

    <br>
    <a href="${pageContext.request.contextPath}/week12_tomcat/discount.jsp">Back to Calculator</a>
</body>
</html>
