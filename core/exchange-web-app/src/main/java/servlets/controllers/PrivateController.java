package servlets.controllers;

import beans.ExchangeSessionBean;
import beans.OwnershipsManager;
import beans.UsersManager;
import entities.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "PrivateController", urlPatterns = {"/trade", "/exchange", "/logout" })
//@ServletSecurity( @HttpConstraint(rolesAllowed = {"private"}) )
public class PrivateController extends HttpServlet {

    @EJB
    OwnershipsManager ownershipsManager;

    @EJB
    UsersManager usersManager;

    public PrivateController() {
        super();
    }

    protected void processGetRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        switch (request.getServletPath()) {
            case "/exchange":
                request.getRequestDispatcher("WEB-INF/private/exchange.jsp").forward(request, response);
                break;
            case "/trade":
                User user = usersManager.getUser(request.getUserPrincipal().getName());
                request.setAttribute("user", user);
                request.setAttribute("ownerships", ownershipsManager.getUsersOwnerships(user.getUserId()));
                request.getRequestDispatcher("WEB-INF/private/trade.jsp").forward(request, response);
                break;
            case "/logout":
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                response.sendRedirect("/ewa-app/exchange");
                break;
            default:
                break;
        }
    }

    protected void processPostRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        switch (request.getServletPath()) {
            case "/trade":

                break;
            default:
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processGetRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processPostRequest(req, resp);
    }
}
