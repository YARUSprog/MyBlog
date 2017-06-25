<%-- 
    Document   : admin_page
    Created on : 07.01.2017, 16:50:09
    Author     : YARUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/style_admin.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>            
        <div class="karkas">
            <%@ include file="/WEB-INF/jspf/admin_header.jspf" %>            
            <div class="content-main">                
                <div class="leftBar">
                    <ul class="nav-left">
                        <li><a href="admin_page">Статьи</a></li>
                        <li><a href="#" class="nav-activ">Пользователи</a></li>                                            
                    </ul>
                </div> <!-- .leftBar -->
                <div class="content">
                    <h2>Cписок пользователей</h2>
                    <a href="#"><img class="add_some" src="images/add_page.jpg" alt="добавить пользователя" /></a>
                    <table class="tabl" cellspacing="1">
                        <tr>
                            <th class="number">№</th>
                            <th class="str_name">Логин</th>                            
                            <th class="str_action">Действие</th>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td class="name_page">default</td>                            
                            <td><a href="#" class="edit">изменить пароль</a>&nbsp; | &nbsp;<a href="#" class="del">удалить</a></td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td class="name_page">some_gay</td>                            
                            <td><a href="#" class="edit">изменить пароль</a>&nbsp; | &nbsp;<a href="#" class="del">удалить</a></td>
                        </tr>        
                    </table>
                    <a href="#"><img class="add_some" src="images/add_page.jpg" alt="добавить пользователя" /></a>
                </div> <!-- .content -->
            </div> <!-- .content-main -->
        </div> <!-- .karkas -->
    </body>
</html>
