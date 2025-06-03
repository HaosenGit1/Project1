

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>

<html>
<head><title>Take Quiz</title></head>
<body>
<h2>Take Quiz</h2>
<form action="/quizzes/submit" method="post">
    <c:forEach var="qq" items="${quizQuestions}">
        <div>
            <p><b>Q:</b> ${qq.question.description}</p>
            <c:forEach var="choice" items="${qq.choices}">
                <input type="radio" name="question_${qq.qqId}" value="${choice.choiceId}" />
                ${choice.description}<br>
            </c:forEach>
        </div>
        <br/>
    </c:forEach>
    <input type="hidden" name="quizId" value="${quizId}"/>
    <button type="submit">Submit Quiz</button>
</form>
</body>
</html>
