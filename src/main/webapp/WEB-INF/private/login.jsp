<%-- 
    Document   : login
    Created on : 02.01.2017, 20:32:42
    Author     : YARUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
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
