<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="navbar.jsp" %>

<html>
<head><title>Quiz Results</title></head>
<body>
<h2>Quiz Results: ${quiz.name}</h2>

<p>Score: ${correctCount} / ${totalQuestions} → ${percent}%</p>
<p>Status: <strong><c:if test="${passed}">Passed ✅</c:if><c:if test="${!passed}">Failed ❌</c:if></strong></p>

<table border="1">
    <tr>
        <th>Question</th>
        <th>Your Answer</th>
        <th>Correct?</th>
    </tr>
    <c:forEach var="qq" items="${questions}">
        <tr>
            <td>${qq.question.description}</td>
            <td>
                <c:forEach var="choice" items="${qq.choices}">
                    <c:if test="${choice.choiceId == qq.userChoiceId}">
                        ${choice.description}
                    </c:if>
                </c:forEach>
            </td>
            <td>
                <c:if test="${qq.correct}">✅</c:if>
                <c:if test="${!qq.correct}">❌</c:if>
            </td>
        </tr>
    </c:forEach>
</table>

<p><a href="/quizzes/user/${quiz.userId}">Back to My Quizzes</a></p>
</body>
</html>
