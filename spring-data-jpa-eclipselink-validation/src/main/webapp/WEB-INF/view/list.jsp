<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Player list</title>
    </head>
    <body>
        <h1>Hello World!</h1>

        <h2>Players</h2>
        <div>
            <c:forEach var="player" items="${players}">
                <p>Player: ${player.name}, Team: ${player.team.name}</p> 
            </c:forEach>
        </div>


        <h2>Teams</h2>
        <div>
            <c:forEach var="team" items="${teams}">
                <p>Team: ${team.name}, Players:<br/>
                    <c:forEach var="p" items="${team.players}">
                        <span>${p.name}</span>
                    </c:forEach>
                </p>
            </c:forEach>
        </div>

        <p><a href="${pageContext.request.contextPath}/player">Add a new player!</a></p>


        <h2>Add a new Team</h2>
        <div>
            <form method="POST" action="team">
                Name: <input name="name" type="text"/><input type="submit"/>
            </form>
        </div>

    </body>
</html>
