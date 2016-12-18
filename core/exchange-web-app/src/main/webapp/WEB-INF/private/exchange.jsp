<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Exchange</title>
</head>
<body>
<div align="center">
    <h1>EXCHANGE</h1>
    <p>Welcome <strong>${user.userId}</strong>! Current balance: ${user.money}</p>
    <p><a href="trade">My resources</a> <a href="logout">Log out</a></p>
    <table>
        <tr>
            <th>Share name</th>
            <th>Share type</th>
            <th>Share price</th>
            <th>Count</th>
            <th>Total price</th>
        </tr>
        <c:forEach var="trade" items="${myTrades}">
            <form action="exchange" method="POST">
                <tr>
                    <td><strong> ${trade.shareShareId.shareId}</strong></td>
                    <td>${trade.shareShareId.shareType}</td>
                    <td>${trade.shareShareId.price}</td>
                    <td><strong>${trade.shareCount}</strong></td>
                    <td><strong>${trade.price}</strong></td>
                    <input type="hidden" name="tradeId" value="${trade.tradeId}">
                    <td><input type="submit" name="cancel" value="Cancel"></td>
                </tr>
            </form>
        </c:forEach>
        <c:forEach var="trade" items="${purchaseTrades}">
            <form action="exchange" method="POST">
                <tr>
                    <td><strong> ${trade.shareShareId.shareId}</strong></td>
                    <td>${trade.shareShareId.shareType}</td>
                    <td>${trade.shareShareId.price}</td>
                    <td><strong>${trade.shareCount}</strong></td>
                    <td><strong>${trade.price}</strong></td>
                    <input type="hidden" name="tradeId" value="${trade.tradeId}">
                    <td><input type="submit" name="sale" value="Sale"></td>
                </tr>
            </form>
        </c:forEach>
        <c:forEach var="trade" items="${saleTrades}">
            <form action="exchange" method="POST">
                <tr>
                    <td><strong> ${trade.shareShareId.shareId}</strong></td>
                    <td>${trade.shareShareId.shareType}</td>
                    <td>${trade.shareShareId.price}</td>
                    <td><strong>${trade.shareCount}</strong></td>
                    <td><strong>${trade.price}</strong></td>
                    <input type="hidden" name="tradeId" value="${trade.tradeId}">
                    <td><input type="submit" name="purchase" value="Purchase"></td>
                </tr>
            </form>
        </c:forEach>
    </table>
</div>
</body>
</html>
