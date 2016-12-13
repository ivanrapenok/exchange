<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Trade</title>
</head>
<body>
<div align="center">
    <h1>SHARES</h1>
    <p>Welcome <strong>${user.userId}</strong>! Current balance: ${user.money}</p>
    <p><a href="exchange">Back to exchange</a> <a href="logout">Log out</a></p>
    <table>
        <tr>
            <th>Share name</th>
            <th>Share type</th>
            <th>Share price</th>
            <th>Count</th>
        </tr>
        <c:forEach var="ownership" items="${ownerships}">
            <tr>
                <td><strong> ${ownership.shareOwn.shareId}</strong></td>
                <td>${ownership.shareOwn.shareType}</td>
                <td>${ownership.shareOwn.price}</td>
                <td><strong>${ownership.shareCount}</strong></td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <h3>CREATE NEW TRADE</h3>
    <form action="trade" method="POST">
        <p>
            Share: <select name="newTradeShareId">
                <c:forEach var="ownership" items="${ownerships}">
                    <option value="${ownership.shareOwn}">${ownership.shareOwn.shareId}</option>
                </c:forEach>
            </select>
            Type: <select name="newTradeType">
                <option value="0">Sale</option>
                <option value="1">Purchase</option>
            </select>
        </p>
        <p><input placeholder="count" type="number" size="20" name="newTradeShareCount"></p>
        <p><input placeholder="total price" type="number" size="20" name="newTradeSharePrice"></p>
        <p><input type="submit" name="create-trade" value="Create trade"></p>
    </form>
</div>
</body>
</html>
