package servlets.controllers;

import beans.ExchangeSessionBean;
import entities.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "PrivateController", urlPatterns = {"/trade", "/exchange", "/logout"})
//@ServletSecurity( @HttpConstraint(rolesAllowed = {"private"}) )
public class PrivateController extends HttpServlet {
    @EJB
    ExchangeSessionBean esb;

    /*private static final String SESSION_KEY = "sessionKey";*/

    public PrivateController() {
        super();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
/* DONT DELETE ITS IMPORTANT FOR STATEFULL SESSIONS BEANS
        ExchangeSessionBean esb = (ExchangeSessionBean) request.getSession().getAttribute(SESSION_KEY);
        if (esb == null) {
            try {
                InitialContext ic = new InitialContext();
                esb = (ExchangeSessionBean)
                        ic.lookup("java:global/ewa-app/ExchangeSessionBean");
                request.getSession().setAttribute(SESSION_KEY, esb);
                System.out.println("shoppingCart created");
            } catch (NamingException e) {
                throw new ServletException(e);
            }
        }
*/
        if ("/authorization".equals(request.getServletPath())) {
            request.setAttribute("name", request.getUserPrincipal().getName());
            User user = esb.getUser(request.getUserPrincipal().getName());
            request.setAttribute("count", esb.getCount());
            String res = (user == null) ? "null" : user.toString();
            request.setAttribute("user", res + esb.toString());
            request.getRequestDispatcher("WEB-INF/private/exchange.jsp").
                    forward(request, response);
        } else if ("/test".equals(request.getServletPath())) {
            request.getRequestDispatcher("WEB-INF/private/test.jsp").
                    forward(request, response);
        } else if ("/exchange".equals(request.getServletPath())) {
            request.getRequestDispatcher("WEB-INF/private/exchange.jsp").
                    forward(request, response);
        } else if ("/trade".equals(request.getServletPath())) {
            request.getRequestDispatcher("WEB-INF/private/trade.jsp").
                    forward(request, response);
        } else if ("/logout".equals(request.getServletPath())) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect("/ewa-app/exchange");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
