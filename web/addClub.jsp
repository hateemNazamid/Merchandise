<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Add Club</title>
        <link rel="stylesheet" href="style/style.css">
        <link rel="stylesheet" href="style/form.css">
        <link rel="stylesheet" href="style/button.css">
        <style>
            body {
                font-family: 'Segoe UI', sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
            }

            .container {
                max-width: 600px;
                margin: 60px auto;
                background-color: #fff;
                padding: 30px 40px;
                border-radius: 8px;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            }

            h1 {
                text-align: center;
                margin-bottom: 30px;
                color: #333;
            }

            label {
                display: block;
                margin-bottom: 8px;
                font-weight: 600;
                color: #555;
            }

            input[type="text"] {
                width: 100%;
                padding: 10px;
                border-radius: 4px;
                border: 1px solid #ccc;
                margin-bottom: 20px;
                font-size: 16px;
            }

            .btn-submit {
                padding: 6px 12px;
                font-size: 14px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                background-color: #3498db;
                color: white;
            }
            .btn-submit:hover {
                background-color: #2980b9;
            }

            .error-message {
                color: red;
                margin-top: 10px;
                text-align: center;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <a href="ManageClubServlet" class="go-back-btn">← Back to Club List</a>
        <div class="container">
            <h1>Add New Club</h1>

            <form action="AddClubServlet" method="post">
                <label for="clubName">Club Name:</label>
                <input type="text" id="clubName" name="clubName" required>

                <input type="submit" value="Submit" class="btn-submit">
            </form>

            <% String err = (String) request.getAttribute("errMessage");
            if (err != null) {%>
            <div class="error-message"><%= err%></div>
            <% }%>
        </div>
    </body>
</html>
