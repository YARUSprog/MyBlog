
package controller;

import com.mycompany.myblog.entity.Articles;
import com.mycompany.myblog.session.ArticlesFacade;
import com.mycompany.myblog.session.UserManager;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author YARUS
 */
@WebServlet(name = "Web_controller", loadOnStartup = 1, urlPatterns = {"/article", "/registration", "/index"})
public class Web_controller extends HttpServlet {

    @EJB
    ArticlesFacade articlesFacade;

    @EJB
    UserManager userManager;

    @Override
    public void init() throws ServletException {
        getServletContext().setAttribute("articles", articlesFacade.findAll());
        getServletContext().setAttribute("themes", articlesFacade.findAllTheme());
    }

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
        String userPath = request.getServletPath();
        if ("/article".equals(userPath)) {
            articleController(request);
            request.getRequestDispatcher("/WEB-INF/views/article.jsp").forward(request, response);
        } else if ("/registration".equals(userPath)) {
            registrationController(request);
            request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
        } else if ("/index".equals(userPath)) {
            indexController(request);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
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

    private void articleController(HttpServletRequest request) {
        String article_id = request.getParameter("article_id");
        try {
            Articles article = articlesFacade.find(Integer.parseInt(article_id));
            request.setAttribute("article", article);
        } catch (Exception ex) {
            Logger.getLogger(Web_controller.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    private void registrationController(HttpServletRequest request) {
        String login = null;
        String pass = null;
        String pass2 = null;
        HashMap<String, String[]> contacts = new HashMap<String, String[]>();
        Enumeration<String> parameters = request.getParameterNames();
        while (parameters.hasMoreElements()) {
            String parameter = parameters.nextElement();
            if (parameter.equals("login")) {
                login = request.getParameter(parameter);
            } else if (parameter.equals("password")) {
                pass = request.getParameter(parameter);
            } else if (parameter.equals("password2")) {
                pass2 = request.getParameter(parameter);
            } else {
                contacts.put(parameter, request.getParameterValues(parameter));
            }
        }
        Integer codeOperation = 0;
        codeOperation = userManager.addUser(login, pass, pass2, contacts);
        if (codeOperation == 0) {
            request.setAttribute("notif", "Пользователь " + login + " успешно создан!");
        } else if (codeOperation == 3) {
            request.setAttribute("notif", "Такой пользователь уже зарегистрирован !");
        }        
    }

    private void indexController(HttpServletRequest request) {
        String theme = request.getParameter("theme");
        List<Articles> articles = articlesFacade.findByTheme(theme);
        request.setAttribute("articles", articles);        
    }
}
