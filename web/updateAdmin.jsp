<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="bean.RegisterBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    RegisterBean admin = (RegisterBean) session.getAttribute("admin");
    if (admin == null) {
        response.sendRedirect("adminlogin.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Edit Profile</title>
        <link rel="stylesheet" href="style/style.css">
    </head>
    <body>
        <a href="home_admin.jsp" class="go-back-btn">← Go To Main Menu</a>
        <main class="container">
            <div class="form-container">
                <h1 class="text-center">Edit Your Profile</h1>

                <c:if test="${not empty successMessage}">
                    <div class="alert alert-success">${successMessage}</div>
                </c:if>

                <form action="UpdateAdminServlet" method="post">
                    <div class="form-group">
                        <label>Full Name:</label>
                        <input type="text" name="name" class="form-control" value="${admin.getName()}" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Username:</label>
                        <input type="text" name="username" class="form-control" value="${admin.getUsername()}" required>
                    </div>
                    <div class="form-group">
                        <label>Password:</label>
                        <input type="password" name="password" class="form-control" value="${admin.getPassword()}" required>
                    </div>
                    <input type="hidden" name="id" value="${admin.id}">
                    <button type="submit" class="btn btn-primary">Update Profile</button>
                </form>

                <c:if test="${not empty errMessage}">
                    <div class="alert alert-danger">${errMessage}</div>
                </c:if>

            </div>
        </main>
    </body>
</html>
