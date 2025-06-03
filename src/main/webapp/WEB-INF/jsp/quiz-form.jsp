<%--
  Created by IntelliJ IDEA.
  User: haosenm2
  Date: 6/1/25
  Time: 4:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Quiz Form</title></head>
<body>
<h2>Add Quiz</h2>
<form action="/quizzes/save" method="post">
  User ID: <input type="number" name="userId" /><br/>
  Category ID: <input type="number" name="categoryId" /><br/>
  Name: <input type="text" name="name" /><br/>
  <button type="submit">Save</button>
</form>
<a href="/quizzes">Back to List</a>
</body>
</html>
