<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Title</title>
</head>
<body>
    <h1>Thêm sản phẩm</h1>
    <form action="${pageContext.request.contextPath}/products?action=create" method="post">
        Tên: <input type="text" name="name"/> <br>
        Giá: <input type="text" name="price"/> <br>
        Mô tả: <input type="text" name="description"/> <br>
        Nhà sản xuất: <input type="text" name="manufacturer"/> <br>
        <input type="submit" value="Thêm">
    </form>
    <a href="${pageContext.request.contextPath}/products">Quay lại</a>
</body>
</html>
