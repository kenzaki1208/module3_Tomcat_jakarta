<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        .login {
            height:180px; width:230px;
            margin:0;
            padding:10px;
            border:1px #CCC solid;
        }
        .login input {
            padding:5px; margin:5px
        }
    </style>
</head>
<body>
    <form method="get" action="/server-time/login">
        <div class="login">
            <h2>Login</h2>
            <input type="text" name="username" placeholder="Username"/>
            <input type="password" name="password" placeholder="Password/">
            <input type="submit" value="Sign in"/>
        </div>
    </form>
</body>
</html>
