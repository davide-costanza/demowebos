<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Hello</title>
</head>
<body>
    <h2>This is the INDEX!</h2>

    <h3>
        <a href="<c:url value="/hello" />" >Hello</a>
    </h3>

</body>
</html>

