<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<%@ include file="navbar.jsp" %>

<html>
<head><title>Contact List</title></head>
<body>
<h2>Contacts</h2>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Subject</th>
        <th>Message</th>
        <th>Email</th>
        <th>Time</th>
    </tr>
    <c:forEach var="contact" items="${contacts}">
        <tr>
            <td>${contact.contactId}</td>
            <td>${contact.subject}</td>
            <td>${contact.message}</td>
            <td>${contact.email}</td>
            <td>${contact.time}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
