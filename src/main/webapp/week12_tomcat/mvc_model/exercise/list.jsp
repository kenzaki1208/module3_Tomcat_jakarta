<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Title</title>
</head>
<body>
<h1>Danh sách sản phẩm</h1>
<a href="${pageContext.request.contextPath}/products?action=create">Thêm sản phẩm</a>
<form method="get" action="${pageContext.request.contextPath}/products">
    <input type="hidden" name="action" value="search"/>
    <input type="text" name="keyword" placeholder="Tìm kiếm ..."/>
    <input type="submit" value="Tìm kiếm">
</form>
<table border="1" cellspacing="0" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Tên</th>
        <th>Giá</th>
        <th>Mô tả</th>
        <th>Nhà sản xuất</th>
        <th>Hành động</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.description}</td>
            <td>${product.manufacturer}</td>
            <td>
                <a href="${pageContext.request.contextPath}/products?action=view&id=${product.id}">Xem</a> |
                <a href="${pageContext.request.contextPath}/products?action=edit&id=${product.id}">Sửa</a> |
                <a href="${pageContext.request.contextPath}/products?action=delete&id=${product.id}">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
