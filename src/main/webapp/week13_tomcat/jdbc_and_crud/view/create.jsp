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
        <h2><a href="users-week13?action=user">List All Users</a></h2>
    </center>
    <div align="center">
        <form method="post">
            <table border="1" cellpadding="5">
                <caption><h2>Add New User</h2></caption>
            </table>
            <tr>
                <th>User Name: </th>
                <td><input type="text" name="name" id="name" size="45" /></td>
            </tr>
            <tr>
                <th>User Email: </th>
                <td><input type="text" name="email" id="email" size="45" /></td>
            </tr>
            <tr>
                <th>Country: </th>
                <td><input type="text" name="country" id="country" size="15" /></td>
            </tr>
            <tr>
                <td>Permission: </td>
                <td>
                    <input type="checkbox" name="add" size="15"/> add |
                    <input type="checkbox" name="edit" size="15"/> edit |
                    <input type="checkbox" name="delete" size="15"/> delete |
                    <input type="checkbox" name="view" size="15"/> view
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save"/>
                </td>
            </tr>
        </form>
    </div>
</body>
</html>
