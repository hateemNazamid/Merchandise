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
        <main class="container">
            <div class="form-container">
                <h1 class="text-center">Register</h1>

                <form action="RegisterServlet" method="post">
                    <div class="form-group">
                        <label>Full Name:</label>
                        <input type="text" name="name" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Email:</label>
                        <input type="text" name="email" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Username:</label>
                        <input type="text" name="username" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Password:</label>
                        <input type="password" name="password" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Address:</label>
                        <input type="text" name="address" class="form-control" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Register</button>
                </form>

                <c:if test="${not empty errMessage}">
                    <div class="alert alert-danger mt-3">${errMessage}</div>
                </c:if>
            </div>
        </main>
    </body>
</html>
