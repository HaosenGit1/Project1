<%--
  Created by IntelliJ IDEA.
  User: haosenm2
  Date: 6/1/25
  Time: 4:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Choice Form</title></head>
<body>
<h2>Add Choice</h2>
<form action="/choices/save" method="post">
    Question ID: <input type="number" name="questionId" /><br/>
    Description: <input type="text" name="description" /><br/>
    Correct: <input type="checkbox" name="correct" /><br/>
    <button type="submit">Save</button>
</form>
<a href="/choices">Back to List</a>
</body>
</html>
