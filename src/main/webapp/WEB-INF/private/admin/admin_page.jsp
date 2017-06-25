<%-- 
    Document   : admin_page
    Created on : 07.01.2017, 16:50:09
    Author     : YARUS
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!--<script src="//cdn.ckeditor.com/4.6.1/full/ckeditor.js"></script>-->        
        <script type="text/javascript" src="js/ckeditor/ckeditor.js"></script>        
        <link rel="stylesheet" type="text/css" href="css/style_admin.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin page</title>
        <style>
            .select-editable { position:relative; background-color:white; border:solid grey 1px;  width:340px; height:18px; }
            .select-editable select { position:absolute; top:0px; left:0px; font-size:14px; border:none; width:340px; margin:0; }
            .select-editable input { position:absolute; top:0px; left:0px; width:320px; padding:1px; font-size:12px; border:none; }
            .select-editable select:focus, .select-editable input:focus { outline:none; }
        </style>
    </head>
    <body>            
        <div class="karkas">
            <%@ include file="/WEB-INF/jspf/admin_header.jspf" %>            
            <div class="content-main">                
                <div class="leftBar">
                    <ul class="nav-left">
                        <c:if test="${content_id eq 'admin_article-list'}">
                            <li><a href="admin_page?content_id=admin_article-list" class="nav-activ">Статьи</a></li>
                            </c:if>
                            <c:if test="${content_id ne 'admin_article-list'}">
                            <li><a href="admin_page?content_id=admin_article-list">Статьи</a></li>
                            </c:if>
                            <c:if test="${content_id eq 'admin_user-list'}">
                            <li><a href="admin_page?content_id=admin_user-list" class="nav-activ">Пользователи</a></li>
                            </c:if>
                            <c:if test="${content_id ne 'admin_user-list'}">
                            <li><a href="admin_page?content_id=admin_user-list">Пользователи</a></li>
                            </c:if>                        
                        <!--<li><a href="admin_page_users">Пользователи</a></li>-->
                    </ul>
                </div> <!-- .leftBar -->
                <c:if test="${content_id eq 'admin_user-list'}">
                    <%@ include file="/WEB-INF/jspf/admin_user-list.jspf" %>   
                </c:if>     
                <c:if test="${content_id eq 'admin_article-list'}">       
                    <%@ include file="/WEB-INF/jspf/admin_article-list.jspf" %>   
                </c:if> 
                <c:if test="${content_id eq 'add_article'}">       
                    <%@ include file="/WEB-INF/jspf/add_article_page.jspf" %>   
                </c:if>
                <c:if test="${content_id eq 'edit_article'}">       
                    <%@ include file="/WEB-INF/jspf/edit_article_page.jspf" %>   
                </c:if>     

                <c:if test="${content_id eq 'add_user'}">       
                    <%@ include file="/WEB-INF/jspf/add_user_page.jspf" %>   
                </c:if>
                <c:if test="${content_id eq 'edit_user'}">       
                    <%@ include file="/WEB-INF/jspf/edit_user_page.jspf" %>   
                </c:if> 
            </div> <!-- .content-main -->
        </div> <!-- .karkas -->
    </body>
</html>
