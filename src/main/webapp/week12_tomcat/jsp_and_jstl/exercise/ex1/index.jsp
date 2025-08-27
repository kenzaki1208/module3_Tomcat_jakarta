<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/server-time/week12_tomcat/jsp_and_jstl/exercise/ex1/style.css">
</head>
<body>
<h2>Danh sách khách hàng</h2>

<table>
    <tr>
        <th>Tên</th>
        <th>Ngày sinh</th>
        <th>Địa chỉ</th>
        <th>Ảnh</th>
    </tr>
    <c:forEach var="cus" items="${customers}">
        <tr>
            <td>${cus.name}</td>
            <td>${cus.dob}</td>
            <td>${cus.address}</td>
            <td><img src="${cus.image}" alt="Ảnh"></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>