<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
    <title>We Got Jokessss</title>
    <style>
        table {
            font-family: "Times New Roman", SansSerif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border:5px darkblue;
            text-align: left;
            padding: 10px;
            width: 140px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>


<body>
<h1>Jokes</h1>
<h2>Chuck Norris Jokes</h2>
<textarea rows="5" cols="40">${fullstring}</textarea>
<table>
    <c:forEach var = "jokes" items = "${jokesList}">
        <tr>
            <!-- I set up a very nice naming scheme for my product SKUs. Its unfortunate we have to hide the ID now.
            <td>${jokes.getId()}</td>
            -->
            <td>${jokes.getThevalue()}</td>
            <td><a href="/delete/${jokes.getId()}">Delete</a></td>
        </tr>
    </c:forEach>
</table>

<br>
<form method="post" action="/">
    <input type="submit"  value="Load">
</form>
<br>
<form method="post" action="/save">
    <input type="submit" value="Save"></form>
</body>

</html>
