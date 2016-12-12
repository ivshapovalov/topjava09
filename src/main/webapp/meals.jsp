<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Meal list</title>
</head>
<body>
<H1 align="center"> Meal list</H1>
<br>
<table border="1" align="center">

    <thead>
    <tr>
        <th>Number</th>
        <th>DateTime</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>

    </thead>
    <c:set var="num" value="${1}"/>
    <c:forEach items="${meals}" var="row">
        <c:choose>
            <c:when test="${row.exceed == true}">
                <c:set var="color" value="${'red'}"/>
            </c:when>
            <c:otherwise>
                <c:set var="color" value="${'green'}"/>
            </c:otherwise>
        </c:choose>
        <tr bgcolor='${color}'>
            <td><c:out value="${num}"/></td>
            <td>${row.getDateTimeAsString()}</td>
            <td>${row.description}</td>
            <td>${row.calories}</td>
        </tr>
        <c:set var="num" value="${num+1}"/>
    </c:forEach>
</table>
</body>
</html>
