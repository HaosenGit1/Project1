<%--
  Created by IntelliJ IDEA.
  User: haosenm2
  Date: 6/1/25
  Time: 4:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Quiz Question Form</title></head>
<body>
<h2>Add Quiz Question</h2>
<form action="/quiz-questions/save" method="post">
    Quiz ID: <input type="number" name="quizId" /><br/>
    Question ID: <input type="number" name="questionId" /><br/>
    User Choice ID (optional): <input type="number" name="userChoiceId" /><br/>
    <button type="submit">Save</button>
</form>
<a href="/quiz-questions">Back to List</a>
</body>
</html>
