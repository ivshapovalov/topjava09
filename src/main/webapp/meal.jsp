<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal</title>
</head>
<body>
<H1 align="center"> Meal</H1>
<br>
<form action="savemeal" method="post">
    <input type="hidden" name="id" value=${meal.id}>
    <table border="1" align="center">
        <tr>
            <td>ID</td>
            <td>${meal.id}</td>
        </tr>
        <tr>
            <td>DateTime</td>
            <td><input type="text" name='dateTime' value='${meal.getDateTimeAsString()}'></td>
        </tr>
        <tr>
            <td>Description</td>
            <td><input type="text" name='description' value='${meal.description}'>
            </td>
        </tr>
        <tr>
            <td>Calories</td>
            <td><input type="number" name='calories' value='${meal.calories}'></td>
        </tr>
    </table>

    <table border="0" align="center">
        <tr>
            <td>
                <input type="submit" value="Save meal"/>
                <button onclick="location.href='../meals'" type="button">Meals list</button>
                <button onclick="location.href='../'" type="button">Main page</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
