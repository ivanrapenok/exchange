package servlets.controllers;

import beans.UsersManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CommonController", urlPatterns = {"/registration"})
public class CommonController extends HttpServlet {

    @EJB
    UsersManager userManager;

    public CommonController() {
        super();
    }

    protected void processGetRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if ("/registration".equals(request.getServletPath())) {
            request.setAttribute("regmessage", "Registration");
            request.getRequestDispatcher("WEB-INF/common/registration.jsp").
                    forward(request, response);
        }
    }

    protected void processPostRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if ("/registration".equals(request.getServletPath())) {
            String regMessage = "Something wrong";
            try {
                String userId = request.getParameter("user_id");
                String userPswd = request.getParameter("password");
                String repeatedPswd = request.getParameter("repeated");

                Integer answer = userManager.addUser(userId, userPswd, repeatedPswd, null);

                if (answer == 0) {
                    regMessage = "User successfully added";
                } else if (answer == 1) {
                    regMessage = "User with such name exists";
                } else if (answer == 2) {
                    regMessage = "Some fields empty";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            request.setAttribute("regmessage", regMessage);
            request.getRequestDispatcher("WEB-INF/common/registration.jsp").
                    forward(request, response);
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
