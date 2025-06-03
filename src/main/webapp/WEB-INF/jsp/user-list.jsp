<%--
  Created by IntelliJ IDEA.
  User: haosenm2
  Date: 6/1/25
  Time: 4:28 PM
  To change this template use File | Settings | File Templates.
--%>

<!--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="navbar.jsp" %>

<html>
<head><title>User List</title></head>
<body>
<h2>Users</h2>
<a href="/users/add">Add User</a>
<table border="1">
    <tr><th>ID</th><th>Email</th><th>First Name</th><th>Last Name</th><th>Actions</th></tr>
    <c:forEach var="u" items="${users}">
        <tr>
            <td>${u.userId}</td>
            <td>${u.email}</td>
            <td>${u.firstname}</td>
            <td>${u.lastname}</td>
            <td>
                <a href="/users/edit/${u.userId}">Edit</a> |
                <a href="/users/delete/${u.userId}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

-->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="navbar.jsp" %>

<html>
<head><title>User Management</title></head>
<body>

<h2>User Management</h2>

<a href="/users/add">Add User</a>

<table border="1" style="margin-top:10px;">
    <tr>
        <th>ID</th>
        <th>Email</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="u" items="${users}">
        <tr>
            <td>${u.userId}</td>
            <td>${u.email}</td>
            <td>${u.firstname}</td>
            <td>${u.lastname}</td>
            <td>
                <c:choose>
                    <c:when test="${u.active}">Active ✅</c:when>
                    <c:otherwise>Suspended ❌</c:otherwise>
                </c:choose>
            </td>
            <td>
                <a href="/users/edit/${u.userId}">Edit</a> |
                <a href="/users/delete/${u.userId}">Delete</a> |
                <form action="/users/toggleStatus/${u.userId}" method="post" style="display:inline;">
                    <button type="submit">
                        <c:choose>
                            <c:when test="${u.active}">Suspend</c:when>
                            <c:otherwise>Activate</c:otherwise>
                        </c:choose>
                    </button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<!-- Pagination Controls -->
<div style="margin-top:10px;">
    <c:forEach begin="1" end="${totalPages}" var="i">
        <c:choose>
            <c:when test="${i == currentPage}">
                <strong>[${i}]</strong>
            </c:when>
            <c:otherwise>
                <a href="/users?page=${i}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</div>

</body>
</html>
