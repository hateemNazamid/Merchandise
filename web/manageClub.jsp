<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Manage Club</title>
    </head>
    <body>
        <h1>Club List</h1>

        <a href="addClub.jsp">Add New Club</a><br><br>

        <table border="1" cellpadding="5">
            <tr>
                <th>Club ID</th>
                <th>Club Name</th>
                <th>Manager</th>            
                <th>Actions</th>

            </tr>

            <c:forEach var="club" items="${clubList}">
                <tr>
                    <td>${club.clubID}</td>
                    <td>${club.clubName}</td>              
                    <td>${club.adminName}</td>
                    <td>
                        <form action="UpdateClubFormServlet" method="post">
                            <input type="hidden" name="clubID" value="${club.clubID}">
                            <input type="submit" value="Update">
                        </form>
                        <form action="DeleteServlet" method="post">
                            <input type="hidden" name="table" value="CLUB">
                            <input type="hidden" name="column" value="clubID">
                            <input type="hidden" name="id" value="${club.clubID}">
                            <button type="submit" class="btn btn-sm btn-danger">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
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
