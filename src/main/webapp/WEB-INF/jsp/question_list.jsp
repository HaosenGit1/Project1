<%--
  Created by IntelliJ IDEA.
  User: haosenm2
  Date: 6/1/25
  Time: 4:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="navbar.jsp" %>

<html>
<head><title>Question List</title></head>
<body>
<h2>Questions</h2>
<a href="/questions/add">Add Question</a>
<table border="1">
    <tr><th>ID</th><th>Category ID</th><th>Description</th><th>Active</th></tr>
    <c:forEach var="q" items="${questions}">
        <tr>
            <td>${q.questionId}</td>
            <td>${q.categoryId}</td>
            <td>${q.description}</td>
            <td>${q.active}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
