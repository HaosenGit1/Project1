<%--
  Created by IntelliJ IDEA.
  User: haosenm2
  Date: 6/1/25
  Time: 4:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="navbar.jsp" %>

<html>
<head><title>Quiz Questions</title></head>
<body>
<h2>Quiz Questions</h2>
<a href="/quiz-questions/add">Add Quiz Question</a>
<table border="1">
    <tr><th>ID</th><th>Quiz ID</th><th>Question ID</th><th>User Choice ID</th></tr>
    <c:forEach var="qq" items="${quizQuestions}">
        <tr>
            <td>${qq.qqId}</td>
            <td>${qq.quizId}</td>
            <td>${qq.questionId}</td>
            <td>${qq.userChoiceId}</td>
        </tr>
    </c:forEach>
</table>
<p><a href="/home">Back to Home Page</a></p>

</body>
</html>
