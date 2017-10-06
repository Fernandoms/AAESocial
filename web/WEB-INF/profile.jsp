<%--
  Created by IntelliJ IDEA.
  User: arthurlorenzi
  Date: 04/10/17
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>${user.firstName} ${user.lastName}</h1>
    <img src="${user.photoUrl}" height="100" alt="profile pic" />
    <h2>Notificações</h2>
    <ul>
        <c:forEach items="${notifications}" var="notification">
            <li>${notification}</li>
        </c:forEach>
    </ul>
    <h2>Mensagens</h2>
    <table>
        <thead>
            <tr>
                <th>De</th>
                <th>Conteúdo</th>
                <th>Data</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${messages}" var="message">
            <tr>
                <td>${message.sender.firstName} ${message.sender.lastName}</td>
                <td>${message.content}</td>
                <td>${message.sendDate}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <h2>Enviar mensagens</h2>
    <form method="POST" action="FrontController">
        <input type="hidden" name="action" value="Send">
        <c:choose>
            <c:when test="${myprofile}">
                Para:
                <br>
                <select name="receiver" >
                    <c:forEach items="${receivers}" var="receiver">
                        <option value="${receiver.id}">${receiver.firstName} ${receiver.lastName}</option>
                    </c:forEach>
                </select>
            </c:when>
            <c:otherwise>
                <input type="hidden" name="receiver" value="${user.id}">
            </c:otherwise>
        </c:choose>
        <br>
        Mensagem:
        <br>
        <textarea rows="4" cols="70" name="messageContent"></textarea>
        <br>
        <input type="submit" value="Enviar">
    </form>
    <h2>Usuários</h2>
    <ul>
    <c:forEach items="${receivers}" var="receiver">
        <li>
            <a href="FrontController?action=Profile&id=${receiver.id}">${receiver.firstName} ${receiver.lastName}</a>
        </li>
    </c:forEach>
    </ul>
    <h2>Mudar status</h2>
    <form method="POST" action="FrontController">
        <input type="hidden" name="action" value="Status">
        <input type="submit" value="Marcar idle">
    </form>
    <h2>Calcular valor</h2>
    <form method="POST" action="FrontController">
        <input type="hidden" name="action" value="Calculate">
        <input type="submit" value="Valor">
    </form>
    <script>
        var msg = '${valueToPay}';
        if (msg) alert(msg);
    </script>
</body>
</html>
