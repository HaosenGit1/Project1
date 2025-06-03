<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="navbar.jsp" %>

<html>
<head>
    <title>Admin - Question Management</title>
</head>
<body>

<h2>Question Management</h2>

<a href="/questions/add">Add New Question</a>

<table border="1" style="margin-top: 10px;">
    <tr>
        <th>ID</th>
        <th>Category ID</th>
        <th>Description</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="q" items="${questions}">
        <tr>
            <td>${q.questionId}</td>
            <td>${q.categoryId}</td>
            <td>${q.description}</td>
            <td>
                <c:choose>
                    <c:when test="${q.active}">
                        Active
                    </c:when>
                    <c:otherwise>
                        Suspended
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <a href="/questions/edit/${q.questionId}">Edit</a> |
                <c:choose>
                    <c:when test="${q.active}">
                        <a href="/questions/suspend/${q.questionId}">Suspend</a>

                    </c:when>
                    <c:otherwise>
                        <a href="/questions/activate/${q.questionId}">Activate</a>
                    </c:otherwise>
                </c:choose> |
                <a href="/questions/delete/${q.questionId}" onclick="return confirm('Are you sure you want to delete this question?');">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>


</body>
</html>
