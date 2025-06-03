<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Register</title></head>
<body>
<h2>Register</h2>
<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>
<form action="/register/save" method="post">
    Email: <input type="email" name="email" required><br>
    First Name: <input type="text" name="firstname" required><br>
    Last Name: <input type="text" name="lastname" required><br>
    Password: <input type="password" name="password" required><br>
    <button type="submit">Register</button>
</form>
<a href="/login">Back to Login</a>
</body>
</html>
