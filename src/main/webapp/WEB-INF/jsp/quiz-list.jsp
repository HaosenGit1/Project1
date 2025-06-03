<%--
  Created by IntelliJ IDEA.
  User: haosenm2
  Date: 6/1/25
  Time: 4:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="navbar.jsp" />


<html>
<head><title>Quiz List</title></head>
<body>
<h2>Quizzes</h2>
<a href="/quizzes/add">Add Quiz</a>
<table border="1">
  <tr><th>ID</th><th>User ID</th><th>Category ID</th><th>Name</th><th>Start</th><th>End</th></tr>
  <c:forEach var="q" items="${quizzes}">
    <tr>
      <td>${q.quizId}</td>
      <td>${q.userId}</td>
      <td>${q.categoryId}</td>
      <td>${q.name}</td>
      <td>${q.timeStart}</td>
      <td>${q.timeEnd}</td>
    </tr>
  </c:forEach>
</table>
<p><a href="/home">Back to Home Page</a></p>

</body>
</html>

