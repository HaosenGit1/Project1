<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="navbar.jsp" %>

<html>
<head><title>Admin Home</title></head>
<body>

<h2>Admin Dashboard</h2>

<ul>
    <li><a href="/admin/users">User Management</a></li>
    <li><a href="/admin/quizzes">Quiz Result Management</a> </li>
    <li><a href="/admin/questions">Question Management</a> </li>
</ul>

<h3>Statistics </h3>
<p>Total Users: ${userCount}</p>

</body>
</html>