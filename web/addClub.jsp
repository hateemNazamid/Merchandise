<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Club</title>
    <link rel="stylesheet" href="style/form.css"> <!-- Optional: Add your CSS here -->
</head>
<body>
    <h1>Add Club</h1>

    <form action="AddClubServlet" method="post">
        <label for="clubName">Club Name:</label>
        <input type="text" id="clubName" name="clubName" required><br><br>

        <input type="submit" value="Submit">
    </form>

    <%-- Display error message if any --%>
    <%
        String err = (String) request.getAttribute("errMessage");
        if (err != null) {
    %>
        <p style="color: red;"><%= err %></p>
    <%
        }
    %>
</body>
</html>
