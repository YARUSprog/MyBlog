<%-- 
    Document   : article
    Created on : 31.12.2016, 13:39:01
    Author     : YARUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Главная страница блога</title>
    </head>
    <body>        
        <div id="main">
            <aside class="leftAside">
                <h2>Темы статей</h2>
                <ul>
                    <c:forEach var="theme" items="${themes}">
                        <li><a href="index?theme?id=${theme}">${theme}</a></li>
                    </c:forEach>
                </ul>
                <!--<h2>Темы статей</h2>
                <ul>
                    <li><a href="#">Тема 1</a></li>
                    <li><a href="#">Тема 2</a></li>
                    <li><a href="#">Тема 3</a></li>
                    <li><a href="#">Тема 3</a></li>

                </ul>
                -->
            </aside>   
            ${param.name}
            <section>
                <article>
                    <h1>${article.title}</h1>
                    <div class="text-article">
                        ${article.text}
                    </div>
                    <div class="fotter-article">                        
                        <span class="date-article">Дата статьи: ${article.date}</span>
                    </div>
                </article>
            </section>
        </div>        
    </body>
</html>
