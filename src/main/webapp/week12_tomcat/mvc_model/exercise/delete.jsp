<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Title</title>
</head>
<body>
    <h1>Bạn có muốn xóa không ?</h1>
    <p>ID: ${product.id}</p>
    <p>Tên: ${product.name}</p>
    <form action="${pageContext.request.contextPath}/products?action=delete" method="post">
        <input type="hidden" name="id" value="${product.id}"/>
        <input type="submit" value="Xóa"/>
    </form>
    <a href="${pageContext.request.contextPath}/products">Quay lại</a>
</body>
</html>
