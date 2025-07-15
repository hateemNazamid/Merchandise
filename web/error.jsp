<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="style/style.css">
    <style>
        .error-box {
            background-color: #ffebee;
            border-left: 6px solid #c62828;
            color: #c62828;
            padding: 20px;
            margin-top: 40px;
            border-radius: 8px;
            box-shadow: var(--shadow);
        }

        .error-box h2 {
            margin-top: 0;
        }

        .btn-back {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: var(--primary);
            color: white;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }

        .btn-back:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>
    <main class="container">
        <div class="error-box">
            <h2>An error occurred</h2>
            <p><strong>Message:</strong></p>
            <p>
                <c:out value="${errMessage}" />
            </p>
            <a href="home_admin.jsp" class="btn-back">← Back to Admin Home</a>
        </div>
    </main>
</body>
</html>
