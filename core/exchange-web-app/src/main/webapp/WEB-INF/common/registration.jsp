<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>login page</title>
</head>

<body>
<form action="registration" method="POST">
    <div id="loginBox" align="center">
        <p>${regmessage}</p>
        <p><input placeholder="username" type="text" size="20" name="user_id"></p>
        <p><input placeholder="password" type="password" size="20" name="password"></p>
        <p><input placeholder="confirm_password" type="password" size="20" name="repeated"></p>
        <p><input type="submit" name="sign-in" value="Sign in">
        <p><a href="exchange">Authorization</a></p>
    </div>
</form>

</body>