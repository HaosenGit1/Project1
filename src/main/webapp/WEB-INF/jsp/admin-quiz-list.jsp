<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="navbar.jsp" %>

<html>
<head>
    <title>Admin - Quiz Results</title>
</head>
<body>

<h2>Quiz Result Management</h2>

<form method="get" action="/admin/quizzes">
    Filter:
    Category ID: <input type="number" name="categoryId" value="${categoryId}"/>
    User ID: <input type="number" name="userId" value="${userId}"/>
    <button type="submit">Filter</button>
</form>

<table border="1" style="margin-top: 10px;">
    <tr>
        <th>Taken Time</th>
        <th><a href="/admin/quizzes?sortBy=category">Category</a></th>
        <th><a href="/admin/quizzes?sortBy=user">User</a></th>
        <th>Number of Questions</th>
        <th>Score</th>
        <th>Details</th>
    </tr>
    <c:forEach var="qr" items="${quizResults}">
        <tr>
            <td>${qr.time_start}</td>
            <td>${qr.category_name}</td>
            <td>${qr.firstname} ${qr.lastname}</td>
            <td>${qr.question_count}</td>
            <td>${qr.score}</td>
            <td><a href="/quizzes/results/${qr.quiz_id}">View</a></td>
        </tr>
    </c:forEach>
</table>

<div style="margin-top: 20px;">
    <c:if test="${pageCount > 1}">
        Pages:
        <c:forEach begin="1" end="${pageCount}" var="page">
            <c:choose>
                <c:when test="${page == currentPage}">
                    <strong>${page}</strong>
                </c:when>
                <c:otherwise>
                    <a href="/admin/quizzes?page=${page}&categoryId=${categoryId}&userId=${userId}&sortBy=${sortBy}">${page}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </c:if>
</div>

</body>
</html>
