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
        <a href="index.html" class="go-back-btn">← Go To Main Menu</a>
        <header class="header">
            <div class="container navbar">
                <div class="brand">Merchandise Management</div>
            </div>
        </header>

        <main class="container">
            <div class="form-container">
                <h1 class="text-center">User Login</h1>

                <form action="LoginServlet" method="post">
                    <input type="hidden" name="type" value="user">
                    <div class="form-group">
                        <label for="username">Username:</label>
                        <input type="text" id="username" name="username" class="form-control">
                    </div>

                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="password" id="password" name="password" class="form-control">
                    </div>

                    <button type="submit" class="btn btn-primary">Login</button>
                </form>

                <p class="mt-3 text-center">Don't have an account? <a href="userRegister.jsp">Register here</a></p>
                <p class="mt-3 text-center">An Admin? <a href="adminLogin.jsp">Login here!</a></p>

                <c:if test="${not empty errMessage}">
                    <div class="alert alert-danger">
                        ${errMessage}
                    </div>
                </c:if>
            </div>
        </main>
    </body>
</html>