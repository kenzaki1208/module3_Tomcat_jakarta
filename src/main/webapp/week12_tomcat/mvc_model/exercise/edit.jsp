<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Title</title>
</head>
<body>
    <h1>Sửa sản phẩm</h1>
    <form action="${pageContext.request.contextPath}/products?action=edit" method="post">
        <input type="hidden" name="id" value="${product.id}"/>
        Tên: <input type="text" name="name" value="${product.name}"/> <br>
        Giá: <input type="text" name="price" value="${product.price}"/> <br>
        Mô tả: <input type="text" name="description" value="${product.description}"/> <br>
        Nhà sản xuất: <input type="text" name="manufacturer" value="${product.manufacturer}"/> <br>
        <input type="submit" value="Cập nhật"/>
    </form>
    <a href="${pageContext.request.contextPath}/products">Quay lại</a>
</body>
</html>
