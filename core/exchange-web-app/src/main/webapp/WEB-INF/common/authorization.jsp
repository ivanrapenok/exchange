<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>login page</title>
</head>

<body>
<form action="j_security_check" method="POST">
    <div id="loginBox" align="center">
        <p><%= request.getParameter("authmessage") %></p>
        <p><input placeholder="username" type="text" size="20" name="j_username"></p>
        <p><input placeholder="password" type="password" size="20" name="j_password"></p>
        <p><input type="submit" name="log-in" value="Log in">
        <p><a href="registration">Registration</a></p>
    </div>
</form>
</body>

</html>
