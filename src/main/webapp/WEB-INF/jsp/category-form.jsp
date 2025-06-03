<%--
  Created by IntelliJ IDEA.
  User: haosenm2
  Date: 6/1/25
  Time: 4:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Category Form</title></head>
<body>
<h2>Add Category</h2>
<form action="/categories/save" method="post">
    Name: <input type="text" name="name" /><br/>
    <button type="submit">Save</button>
</form>
<a href="/categories">Back to List</a>
</body>
</html>
