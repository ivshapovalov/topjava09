<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal</title>
</head>
<body>
<H1 align="center"> Meal</H1>
<br>
<form action="updatemeal" method="post">
    <input type="hidden" name="id" value=${meal.id} />
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
        <tr>
            <td></td>
            <td><input type="submit" value="update meal"/></td>
        </tr>
    </table>
</form>
</body>
</html>
