<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>


<html>
<head><title>Home</title></head>
<body>
<h2>Welcome, ${user.firstname}!</h2>
<p>Your email: ${user.email}</p>

<h3>Quiz Categories</h3>
<ul>
    <c:forEach var="cat" items="${categories}">
        <li>${cat.name} <a href="/quizzes/start/${cat.categoryId}">Start Quiz</a></li>
    </c:forEach>
</ul>

<h3>Recent Quizzes</h3>
<table border="1">
    <tr><th>Name</th><th>Date Taken</th><th>Result</th></tr>
    <c:forEach var="quiz" items="${recentQuizzes}">
        <tr>
            <td>${user.firstname}</td>
            <td>${quiz.timeStart}</td>
            <td><a href="/quizzes/results/${quiz.quizId}">View Result</a></td>
        </tr>
    </c:forEach>
</table>

<p><a href="/login">Logout</a></p>
</body>
</html>
