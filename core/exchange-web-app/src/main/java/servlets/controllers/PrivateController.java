package servlets.controllers;

import beans.Exchange;
import entities.Trade;
import entities.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PrivateController", urlPatterns = {"/trade", "/exchange", "/logout" })
public class PrivateController extends HttpServlet {

    @EJB
    Exchange exchange;

    public PrivateController() {
        super();
    }

    protected void processPostRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String userId = request.getUserPrincipal().getName();

        switch (request.getServletPath()) {
            case "/exchange":
                Long tradeId = Long.valueOf(request.getParameter("tradeId"));
                exchange.acceptTrade(tradeId, userId);
                response.sendRedirect("/ewa-app/exchange");
                break;
            case "/trade":
                String shareId = request.getParameter("newTradeShareId");
                String stringType = request.getParameter("newTradeType");
                String stringCount = request.getParameter("newTradeShareCount");
                String stringPrice = request.getParameter("newTradeSharesPrice");

                Integer tradeType = Integer.valueOf(stringType);
                Long shareCount = Long.valueOf(stringCount);
                Double price = Double.valueOf(stringPrice);

                exchange.createTrade(userId, tradeType, shareId, shareCount, price);
                response.sendRedirect("/ewa-app/trade");
                break;
            default:
                break;
        }
    }

    protected void processGetRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        User user;
        switch (request.getServletPath()) {
            case "/exchange":
                user = exchange.getUser(request.getUserPrincipal().getName());
                List<Trade> trades = exchange.getAllTrades();
                if (trades == null) trades = new ArrayList<>();
                List<Trade> myTrades = new ArrayList<>();
                List<Trade> purchaseTrades = new ArrayList<>();
                List<Trade> saleTrades = new ArrayList<>();
                for (Trade trade : trades) {
                    if (trade.getUserInitiatorId().equals(user)) {
                        myTrades.add(trade);
                    } else if (trade.getTradeType() == 0) {
                        saleTrades.add(trade);
                    } else {
                        purchaseTrades.add(trade);
                    }
                }
                request.setAttribute("user", user);
                request.setAttribute("myTrades", myTrades);
                request.setAttribute("purchaseTrades", purchaseTrades);
                request.setAttribute("saleTrades", saleTrades);
                request.getRequestDispatcher("WEB-INF/private/exchange.jsp").forward(request, response);
                break;
            case "/trade":
                user = exchange.getUser(request.getUserPrincipal().getName());
                request.setAttribute("user", user);
                request.setAttribute("shares", exchange.getSharesList());
                request.setAttribute("ownerships", exchange.getUsersOwnerships(user.getUserId()));
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


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processGetRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processPostRequest(req, resp);
    }
}
