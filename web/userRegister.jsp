<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="style/style.css">
    </head>
    <body>
        
        <header class="header">
            
            <div class="container navbar">
                <div class="brand">Merchandise Management</div>
            </div>
        </header>

        <main class="container">
            <div class="form-container">
                <a href="index.html" class="go-back-btn">← Go To Main Menu</a>
                <h1 class="text-center">User Register</h1>

                <form action="RegisterServlet" method="post">
                    <input type="hidden" name="type" value="user">
                    <div class="form-group">
                        <label for="name">Full Name:</label>
                        <input type="text" name="name" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="text" name="email" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="username">Username:</label>
                        <input type="text" name="username" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="password" name="password" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="address">Address:</label>
                        <input type="text" name="address" class="form-control" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Register</button>
                </form>

                <p class="mt-3 text-center">Already have an Account? <a href="userLogin.jsp">Login here</a></p>
                <p class="mt-3 text-center">An Admin? <a href="adminRegister.jsp">Register here!</a></p>

                <c:if test="${not empty errMessage}">
                    <div class="alert alert-danger mt-3">${errMessage}</div>
                </c:if>

            </div>
        </main>
    </body>
</html>