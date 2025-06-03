<%--
  Created by IntelliJ IDEA.
  User: haosenm2
  Date: 6/1/25
  Time: 4:51 PM
  To change this template use File | Settings | File Templates.
--%>

<!--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Question Form</title></head>
<body>
<h2>Add Question</h2>
<form action="/questions/save" method="post">
  Category ID: <input type="number" name="categoryId" /><br/>
  Description: <input type="text" name="description" /><br/>
  Active: <input type="checkbox" name="active" /><br/>
  <button type="submit">Save</button>
</form>
<a href="/questions">Back to List</a>
</body>
</html>


-->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="navbar.jsp" %>

<html>
<head>
  <title>Question Form</title>
</head>
<body>

<h2><c:if test="${question.questionId > 0}">Edit</c:if><c:if test="${question.questionId == 0}">Add</c:if> Question</h2>

<form action="/questions/save" method="post">
  <input type="hidden" name="questionId" value="${question.questionId}" />

  Category ID: <input type="number" name="categoryId" value="${question.categoryId}" required /><br/>
  Description: <input type="text" name="description" value="${question.description}" required /><br/>
  Active: <input type="checkbox" name="active" <c:if test="${question.active}">checked</c:if> /><br/>

  <button type="submit">Save</button>
</form>

<a href="/questions/all">Back to Question List</a>

</body>
</html>
