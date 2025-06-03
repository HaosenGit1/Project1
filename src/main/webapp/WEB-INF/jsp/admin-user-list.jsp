<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="navbar.jsp" %>

<html>
<head>
    <title>Admin - User Management</title>
</head>
<body>

<h2>User Management</h2>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Email</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Status</th>
        <th>Role</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.userId}</td>
            <td>${user.email}</td>
            <td>${user.firstname}</td>
            <td>${user.lastname}</td>
            <td>
                <c:choose>
                    <c:when test="${user.active}">
                        Active
                    </c:when>
                    <c:otherwise>
                        Suspended
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${user.admin}">
                        Admin
                    </c:when>
                    <c:otherwise>
                        User
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${user.active}">
                       <!-- <a href="/admin/users/suspend/${user.userId}">Suspend</a>    -->
                        <a href="/admin/users/suspend/${user.userId}?page=${currentPage}">Suspend</a>
                    </c:when>
                    <c:otherwise>
                        <a href="/admin/users/activate/${user.userId}?page=${currentPage}">Activate</a>
                    </c:otherwise>
                </c:choose>
                |
                <a href="/users/edit/${user.userId}">Edit</a>
                |
                <a href="/users/delete/${user.userId}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<!-- Pagination controls -->
<div style="margin-top: 20px;">
    <c:if test="${pageCount > 1}">
        Pages:
        <c:forEach begin="1" end="${pageCount}" var="page">
            <c:choose>
                <c:when test="${page == currentPage}">
                    <strong>${page}</strong>
                </c:when>
                <c:otherwise>
                    <a href="/admin/users?page=${page}">${page}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </c:if>
</div>

</body>
</html>
