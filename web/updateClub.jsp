<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Update Club</title>
    </head>
    <body>
        <h2>Update Club</h2>

        <form action="UpdateClubServlet" method="post">
            <!-- Club ID (hidden) -->
            <input type="hidden" name="clubID" value="${club.clubID}" />

            <!-- Club Name -->
            <label>Club Name:</label><br>
            <input type="text" name="clubName" value="${club.clubName}" required /><br><br>

            <!-- Admin Selection -->
            <label>Manager Name</label><br>
            <select name="adminID" required>
                <c:forEach var="admin" items="${adminList}">
                    <option value="${admin.adminID}"
                            <c:if test="${admin.adminID == club.adminID}">selected</c:if>>
                        ${admin.name}
                    </option>
                </c:forEach>
            </select><br><br>

            <input type="submit" value="Update Club" />
        </form>
        <%
            String success = (String) request.getAttribute("successMessage");
            if (success != null) {
        %>
        <p style="color: green;"><%= success%></p>
        <%
            }
        %>
        <%
            String err = (String) request.getAttribute("errMessage");
            if (err != null) {
        %>
        <p style="color:red;"><%= err%></p>
        <%
            }
        %>
    </body>
</html>
