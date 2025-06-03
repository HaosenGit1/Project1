

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="navbar.jsp" %>

<html>
<head><title>Quiz Question</title></head>
<body>

<h2>Question ${index + 1} of ${totalQuestions}</h2>

<form method="post" action="/quizzes/take/${quizId}/question/${index}">
    <p>${question.question.description}</p>

    <c:forEach var="choice" items="${question.choices}">
        <input type="radio" name="choiceId" value="${choice.choiceId}" required>
        ${choice.description}<br>
    </c:forEach>

    <br>

    <button type="submit">
        <c:if test="${index + 1 < totalQuestions}">Next</c:if>
        <c:if test="${index + 1 == totalQuestions}">Finish</c:if>
    </button>
</form>

</body>
</html>
