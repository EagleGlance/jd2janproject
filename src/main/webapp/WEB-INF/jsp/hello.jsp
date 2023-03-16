<%--
  Created by IntelliJ IDEA.
  User: Noirix
  Date: 09.02.2023
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
${userName}

<div>
    <h1>System Users</h1>
</div>
<div>
    <table>
        <tr>
            <td> Id </td>
            <td> Login </td>
            <td> Password </td>
            <td> Email </td>
            <td> Phone number</td>
            <td> Passport series and number</td>
            <td>Edit</td>
            <td>Delete</td>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.login}</td>
                <td>${user.password}</td>
                <td>${user.phone_number}</td>
                <td>${user.email}</td>
                <td>${user.passport_series_and_number}</td>
                <td><button>Edit</button></td>
                <td><button>Delete</button></td>
            </tr>
        </c:forEach>
    </table>
</div>


</body>
</html>
