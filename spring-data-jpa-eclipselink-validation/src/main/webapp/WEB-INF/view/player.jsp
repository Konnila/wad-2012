
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create player</title>
    </head>
    <body>
        <h1>Add a new player!</h1>
        <form:form commandName="player" action="${pageContext.request.contextPath}/player" method="POST">
            <form:input path="name" /><form:errors path="name" /><br/>

            <select name="teamId">
                <c:forEach var="team" items="${teams}">
                    <option value="${team.id}">${team.name}</option>
                </c:forEach>
            </select>
            <input type="submit">
        </form:form>
    </body>
</html>
