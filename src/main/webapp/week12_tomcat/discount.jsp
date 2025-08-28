<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h2>Product Discount Calculator</h2>
  <form method="post" action="${pageContext.request.contextPath}/display-discount">
    <label>Product Description:</label><br>
    <input type="text" name="description"><br><br>

    <label>List Price:</label><br>
    <input type="text" name="price"><br><br>

    <label>Discount Percent (%):</label><br>
    <input type="text" name="percent"><br><br>

    <input type="submit" value="Calculate Discount">
  </form>
</body>
</html>
