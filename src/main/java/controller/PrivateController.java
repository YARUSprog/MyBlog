
package controller;

import com.mycompany.myblog.entity.Articles;
import com.mycompany.myblog.entity.Contacts;
import com.mycompany.myblog.entity.Groupuser;
import com.mycompany.myblog.entity.Users;
import com.mycompany.myblog.session.ArticlesFacade;
import com.mycompany.myblog.session.ContactsFacade;
import com.mycompany.myblog.session.GroupuserFacade;
import com.mycompany.myblog.session.UsersFacade;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author YARUS
 */
@WebServlet(name = "PrivateController", urlPatterns = {"/private", "/logout", "/admin_page", "/admin_page_users"})
@ServletSecurity(
        @HttpConstraint(rolesAllowed = {"private", "admin"}))
public class PrivateController extends HttpServlet {

    @EJB
    ArticlesFacade articlesFacade;

    @EJB
    UsersFacade usersFacade;

    @EJB
    GroupuserFacade groupuserFacade;

    @EJB
    ContactsFacade contactsFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if ("/private".equals(request.getServletPath())) {
            privateController(request, response);
        } else if ("/logout".equals(request.getServletPath())) {
            logoutController(request, response);
        } else if ("/admin_page".equals(request.getServletPath())) {
            admin_pageController(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void privateController(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("name", request.getUserPrincipal().getName());
        request.getRequestDispatcher("WEB-INF/private/private_article.jsp").forward(request, response);
    }

    private void logoutController(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("");
    }

    private void admin_pageController(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Block id which must be connected as a content block
        String content_id = request.getParameter("content_id");
        request.setAttribute("content_id", content_id);

        request.setAttribute("users", usersFacade.findAll());

        String command = request.getParameter("command");
        if (command != null && command.equals("load_article")) {
            loadArticleCommand(request);
        } else if (command != null && command.equals("edit_article")) {
            editArticleCommand(request);
        } else if (command != null && command.equals("add_article")) {
            addArticleCommand(request);
        } else if (command != null && command.equals("delete_article")) {
            deleteArticleCommand(request);
        } else if (command != null && command.equals("loadForEditUser")) {
            loadForEditUserCommand(request);
        } else if (command != null && command.equals("edit_user")) {
            editUserCommand(request);
        } else if (command != null && command.equals("delete_user")) {
            deleteUserCommand(request);
        }
        request.getRequestDispatcher("WEB-INF/private/admin/admin_page.jsp").
                forward(request, response);
    }

    private void loadArticleCommand(HttpServletRequest request) {
        //The id of the article to be displayed
        String article_id = request.getParameter("article_id");
        try {
            //Looking for an article on article_id
            Articles article = articlesFacade.find(Integer.parseInt(article_id));
            //We transmit the found article
            request.setAttribute("article", article);
            //If article_id is not a number, or if it is not there will be an exception 
            //and this block will not be executed
        } catch (Exception ex) {
            Logger.getLogger(PrivateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void editArticleCommand(HttpServletRequest request) {
        try {
            Articles article = articlesFacade.find(Integer.parseInt(request.getParameter("article_id")));
            article.setTitle(request.getParameter("title"));
            article.setText(request.getParameter("text"));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(request.getParameter("date"));
            article.setDate(date);
            article.setTheme(request.getParameter("theme"));
            articlesFacade.save(article);
            getServletContext().setAttribute("themes", articlesFacade.findAllTheme());
            getServletContext().setAttribute("articles", articlesFacade.findAll());
            request.setAttribute("info", "Cтатья изменена !");
            request.setAttribute("content_id", "admin_article-list");
        } catch (ParseException ex) {
            request.setAttribute("error", "Не верные данные !");
            request.setAttribute("content_id", "edit_article");
            request.setAttribute("command", "load_article");
            loadArticleCommand(request);
            Logger.getLogger(PrivateController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (javax.ejb.EJBException ex) {
            request.setAttribute("error", "Не верные данные !");
            request.setAttribute("content_id", "edit_article");
            loadArticleCommand(request);
            Logger.getLogger(PrivateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addArticleCommand(HttpServletRequest request) {
        try {
            Articles article = new Articles();            
            article.setTitle(request.getParameter("title"));
            article.setText(request.getParameter("text"));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(request.getParameter("date"));
            article.setDate(date);
            article.setTheme(request.getParameter("theme"));
            articlesFacade.save(article);
            getServletContext().setAttribute("themes", articlesFacade.findAllTheme());
            getServletContext().setAttribute("articles", articlesFacade.findAll());
            request.setAttribute("info", "Cтатья добавлена !");
            request.setAttribute("content_id", "admin_article-list");
        } catch (ParseException ex) {
            request.setAttribute("error", "Не верные данные !");
            request.setAttribute("content_id", "add_article");
            Logger.getLogger(PrivateController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (javax.ejb.EJBException ex) {
            request.setAttribute("error", "Не верные данные !");
            request.setAttribute("content_id", "add_article");
            Logger.getLogger(PrivateController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void deleteArticleCommand(HttpServletRequest request) {
        try {
            Articles article = articlesFacade.find(Integer.parseInt(request.getParameter("article_id")));
            articlesFacade.delete(article);
            getServletContext().setAttribute("themes", articlesFacade.findAllTheme());
            getServletContext().setAttribute("articles", articlesFacade.findAll());
            request.setAttribute(request.getParameter("article_id"), "admin_article-list");
            request.setAttribute("info", "Cтатья удалена !");
        } catch (Exception ex) {
            Logger.getLogger(PrivateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadForEditUserCommand(HttpServletRequest request) {
        String login = request.getParameter("user_id");
        Users user = usersFacade.find(login);
        request.setAttribute("user", user);
        List<Groupuser> groupusers = groupuserFacade.findAll();
        groupusers.remove(user.getGroupUser());
        request.setAttribute("groups", groupusers);
        List<Contacts> contacts = contactsFacade.findByLogin(user.getLogin());
        request.setAttribute("contacts", contacts);
    }

    private void editUserCommand(HttpServletRequest request) {
        try {
            Users user = usersFacade.find(request.getParameter("user_id"));    
            user.setPass(request.getParameter("password"));
            user.setGroupUser(groupuserFacade.find(request.getParameter("groupUser")));
            usersFacade.save(user);            
            request.setAttribute("users", usersFacade.findAll());
            request.setAttribute("info", "Данные пользователя изменены !");
            request.setAttribute("content_id", "admin_user-list");
        } catch (javax.ejb.EJBException ex) {
            request.setAttribute("error", "Не верные данные !");
            request.setAttribute("content_id", "edit_user");
            loadForEditUserCommand(request);
            Logger.getLogger(PrivateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void deleteUserCommand(HttpServletRequest request) {
        try {
            Users user = usersFacade.find(request.getParameter("user_id"));
            usersFacade.delete(user);            
            request.setAttribute("users", usersFacade.findAll());           
            request.setAttribute("info", "Пользователь удален !");
        } catch (Exception ex) {
            Logger.getLogger(PrivateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
