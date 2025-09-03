<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Management Application</title>
</head>
<body>
    <center>
        <h1>User Management</h1>
        <h2><a href="${pageContext.request.contextPath}/users-week13?action=create">Add New User</a></h2>
    </center>

    <form style="justify-self: center" action="${pageContext.request.contextPath}/users-week13" method="get">
        <input type="hidden" name="action" value="search"/>
        <input type="text" name="country" placeholder="Enter country"/>
        <input type="submit" value="Search"/>
    </form>

    <h3 style="text-align: center">
        <a href="${pageContext.request.contextPath}/users-week13?action=sort">
            Sort Users by Name
        </a>
    </h3>

    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Country</th>
                <th>Action</th>
            </tr>
            <c:forEach var="user" items="${listUser}">
                <tr>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.username}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td><c:out value="${user.country}"/></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/users?action=edit&id=${user.id}">Edit</a>
                        <a href="${pageContext.request.contextPath}/users?action=delete&id=${user.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
