<!--<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<div style="background-color: #f0f0f0; padding: 10px;">
    <a href="/home" <c:if test="${empty sessionScope.loggedInUser}">style="display:none;"</c:if>>Home</a> |

    <c:if test="${not empty sessionScope.openQuiz}">
        <a href="/quizzes/take/${sessionScope.openQuiz.quizId}">Taking Quiz</a> |
    </c:if>

    <c:choose>
        <c:when test="${empty sessionScope.loggedInUser}">
            <a href="/login">Login</a> |
            <a href="/register">Register</a> |
        </c:when>
        <c:otherwise>
            <a href="/login/logout">Logout</a> |
        </c:otherwise>
    </c:choose>
    <a href="/contacts">Contact Us</a>
</div>

-->

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<div style="background-color: #f0f0f0; padding: 10px;">
    <c:if test="${not empty sessionScope.loggedInUser}">
        <a href="/home">Home</a> |
        <c:if test="${sessionScope.loggedInUser.admin}">
            <a href="/admin">Admin Panel</a> |
        </c:if>
    </c:if>

    <c:if test="${not empty sessionScope.openQuiz}">
        <a href="/quizzes/take/${sessionScope.openQuiz.quizId}">Taking Quiz</a> |
    </c:if>

    <c:choose>
        <c:when test="${empty sessionScope.loggedInUser}">
            <a href="/login">Login</a> |
            <a href="/register">Register</a> |
        </c:when>
        <c:otherwise>
            <a href="/login/logout">Logout</a> |
        </c:otherwise>
    </c:choose>

    <a href="/contacts">Contact Us</a>
</div>
