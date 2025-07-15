<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update Club</title>
    <link rel="stylesheet" href="style/style.css">
</head>
<body>
    <a href="home_admin.jsp" class="go-back-btn">← Go To Main Menu</a>

    <main class="container">
        <div class="form-container">
            <h1 class="text-center">Update Club</h1>

            <form action="UpdateClubServlet" method="post">
                <!-- Club ID (hidden) -->
                <input type="hidden" name="clubID" value="${club.clubID}" />

                <!-- Club Name -->
                <div class="form-group">
                    <label for="clubName">Club Name:</label>
                    <input type="text" id="clubName" name="clubName" value="${club.clubName}" required class="form-control" />
                </div>

                <!-- Admin Selection -->
                <div class="form-group">
                    <label for="adminID">Manager Name:</label>
                    <select name="adminID" id="adminID" class="form-control" required>
                        <c:forEach var="admin" items="${adminList}">
                            <option value="${admin.adminID}"
                                <c:if test="${admin.adminID == club.adminID}">selected</c:if>>
                                ${admin.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <!-- Submit Button -->
                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Update Club</button>
                </div>
            </form>

            <!-- Success Message -->
            <c:if test="${not empty successMessage}">
                <p style="color: green; margin-top: 1rem;">${successMessage}</p>
            </c:if>

            <!-- Error Message -->
            <c:if test="${not empty errMessage}">
                <div class="alert alert-danger">${errMessage}</div>
            </c:if>
        </div>
    </main>
</body>
</html>
