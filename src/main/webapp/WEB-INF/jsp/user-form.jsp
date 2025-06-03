<%--
  Created by IntelliJ IDEA.
  User: haosenm2
  Date: 6/1/25
  Time: 4:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>User Form</title></head>
<body>
<h2>${user.userId == 0 ? 'Add User' : 'Edit User'}</h2>
<form action="${user.userId == 0 ? '/users/save' : '/users/update'}" method="post">
    <input type="hidden" name="userId" value="${user.userId}" />
    Email: <input type="text" name="email" value="${user.email}" /><br/>
    Password: <input type="password" name="password" value="${user.password}" /><br/>
    First Name: <input type="text" name="firstname" value="${user.firstname}" /><br/>
    Last Name: <input type="text" name="lastname" value="${user.lastname}" /><br/>
    <button type="submit">Save</button>
</form>
<a href="/users">Back to list</a>
</body>
</html>
