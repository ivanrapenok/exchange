<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>login page</title>
</head>

<body>
    <h1>Авторизация</h1>
    <form action="j_security_check" method="POST">
        <div id="loginBox">
            <p><strong>Ваш логин:</strong>
                <input placeholder="Введите логин" type="text" size="20" name="j_username"></p>
            <p><strong>Пароль:</strong>
                <input placeholder="Введите пароль" type="password" size="20" name="j_password"></p>
            <p><input type="submit" value="Авторизоваться"></p>
        </div>
    </form>
</body>

</html>
