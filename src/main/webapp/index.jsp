<%-- 
    Document   : index
    Created on : 31.12.2016, 14:00:30
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
                        <li><a href="index?theme=${theme}">${theme}</a></li>
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
            <section>          
                <c:forEach var="article" items="${articles}">
                    <article>
                        <h1>${article.title}</h1>
                        <div class="text-article">
                            ${fn:substring(article.text,0,300)} ...
                        </div>
                        <div class="fotter-article">
                            <span class="read"><a href="article?article_id=${article.id}">
                                    Читать...</a></span>
                            <span class="date-article">Дата статьи: ${article.date}</span>
                        </div>
                    </article>
                </c:forEach>
            </section>
        </div>        
    </body>
</html>