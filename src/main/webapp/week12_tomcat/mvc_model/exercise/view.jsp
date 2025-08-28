<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Chi tiết sản phẩm</title>
</head>
<body>
<div class="container">
  <div>
    <h1>Thông tin chi tiết sản phẩm</h1>
    <div>
      <span>ID:</span> ${product.id}
    </div>
    <div>
      <span>Tên:</span> ${product.name}
    </div>
    <div>
      <span>Giá:</span> ${product.price}
    </div>
    <div>
      <span>Mô tả:</span> ${product.description}
    </div>
    <div>
      <span>Nhà sản xuất:</span> ${product.manufacturer}
    </div>
    <div>
      <a href="${pageContext.request.contextPath}/products">Quay lại</a>
    </div>
  </div>
</div>
</body>
</html>
