<%--
  Created by IntelliJ IDEA.
  User: haosenm2
  Date: 6/1/25
  Time: 4:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="navbar.jsp" %>

<html>
<head><title>Choice List</title></head>
<body>
<h2>Choices</h2>
<a href="/choices/add">Add Choice</a>
<table border="1">
  <tr><th>ID</th><th>Question ID</th><th>Description</th><th>Correct</th></tr>
  <c:forEach var="c" items="${choices}">
    <tr>
      <td>${c.choiceId}</td>
      <td>${c.questionId}</td>
      <td>${c.description}</td>
      <td>${c.correct}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
