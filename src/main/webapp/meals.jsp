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
<H2 align="center"> Calories per day: ${caloriesPerDay}</H2>
<br>
<table border="1" align="center">

    <thead>
    <tr>
        <th>№</th>
        <th>ID</th>
        <th>DateTime</th>
        <th>Description</th>
        <th>Calories</th>
        <th>EDIT</th>
        <th>DELETE</th>
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
            <td>${row.id}</td>
            <td>${row.getDateTimeAsString()}</td>
            <td>${row.description}</td>
            <td>${row.calories}</td>

                <%--<td><a href="meals/editmeal?id=${row.id}">edit</a><br></td>--%>
                <%--<td><a href="meals/deletemeal?id=${row.id}">delete</a><br></td>--%>
            <td>
                <button onclick="location.href='meals/meal?id=${row.id}'"
                        type="button">
                    edit
                </button>
            </td>
            <td>
                <button onclick="location.href='meals/deletemeal?id=${row.id}'"
                        type="button">
                    delete
                </button>
            </td>
        </tr>
        <c:set var="num" value="${num+1}"/>
    </c:forEach>
</table>

<table border="0" align="center">
    <tr>
        <td>
            <%--<a href="meals/editmeal">new meal</a>--%>
            <button onclick="location.href='meals/meal'" type="button">New meal</button>
            <button onclick="location.href='./'" type="button">Main page</button>

        </td>
    </tr>
</table>


<br>
${message}<br>

</body>
</html>
