<%--
  Created by IntelliJ IDEA.
  User: haosenm2
  Date: 6/1/25
  Time: 4:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="navbar.jsp" %>

<html>
<head><title>Category List</title></head>
<body>
<h2>Categories</h2>
<a href="/categories/add">Add Category</a>
<table border="1">
  <tr><th>ID</th><th>Name</th><th>Actions</th></tr>
  <c:forEach var="c" items="${categories}">
    <tr>
      <td>${c.categoryId}</td>
      <td>${c.name}</td>
      <td><a href="/categories/delete/${c.categoryId}">Delete</a></td>
    </tr>
  </c:forEach>
</table>
</body>
</html>

