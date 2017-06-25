# MyBlog
Web application. Written on JavaEE (JSP/Servlet/JPA/EJB/JSTL/JAAS/MySQL/GlassFish)

Site structure:
1. The main page of the site. Contains a list of the most recently added articles.
2. The article reading page. Contains the full content of the selected article.
3. The registration page.
4. Administration area. Limited access only for authorized users with site administrator rights.

In the administration zone, you can:
1. Create articles / delete / edit articles and subject articles
2. View user data and change it.

MVC pattern used:
- Model - Session beans, Entity Classes, database
- View - JSP
- Controller - Servlet
