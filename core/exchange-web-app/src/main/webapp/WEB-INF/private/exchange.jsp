<%--
  Created by IntelliJ IDEA.
  User: dagon
  Date: 12/10/2016
  Time: 10:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Exchange</title>
</head>
<body>
    <h1>EXCHANGE</h1>
    <a href="logout">log out</a>
    <section>
        <c:forEach var="trade" items="${trades}">
            <article>
                <h4>${trade.share}</h4>
                <div>
                    ${fn:substring(article.text,0,300)} ...
                </div>
                <div class="fotter-article">
                        <span class="read"><a href="article?id=${article.id}">
                                     Читать...</a></span>
                    <span class="date-article">Дата статьи: ${article.date}</span>
                </div>
            </article>
        </c:forEach>
    </section>
    <form action="PrivateController" method="POST">

    </form>
</body>
</html>
