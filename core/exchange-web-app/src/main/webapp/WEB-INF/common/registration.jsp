<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>login page</title>
</head>

<body>
<form action="CommonController" method="POST">
    <div id="loginBox" align="center">
        <p>${regmessage}</p>
        <p><input placeholder="username" type="text" size="20" name="j_username"></p>
        <p><input placeholder="password" type="password" size="20" name="j_password"></p>
        <p><input type="submit" name="sign-in" value="Sign in">
        <p><a href="exchange">Authorization</a></p>
    </div>
</form>

</body>