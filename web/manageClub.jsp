<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Manage Club</title>
        <link rel="stylesheet" href="style/style.css">
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background-color: #f9f9f9;
                margin: 0;
                padding: 0;
            }
            .container {
                max-width: 1000px;
                margin: 40px auto;
                background: #fff;
                padding: 30px;
                box-shadow: 0 2px 10px rgba(0,0,0,0.1);
                border-radius: 8px;
            }
            h1 {
                margin-bottom: 20px;
                color: #333;
            }
            .add-btn {
                display: inline-block;
                margin-bottom: 20px;
                background-color: #4CAF50;
                color: white;
                padding: 8px 16px;
                text-decoration: none;
                border-radius: 5px;
            }
            .add-btn:hover {
                background-color: #45a049;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }
            table th, table td {
                padding: 12px;
                border: 1px solid #ddd;
                text-align: left;
            }
            table th {
                background-color: #f2f2f2;
                color: #333;
            }
            .action-buttons {
                display: flex;
                gap: 8px;
            }
            .btn-sm {
                padding: 6px 12px;
                font-size: 14px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
            .btn-danger {
                background-color: #e74c3c;
                color: white;
            }
            .btn-danger:hover {
                background-color: #c0392b;
            }
            .btn-update {
                background-color: #3498db;
                color: white;
            }
            .btn-update:hover {
                background-color: #2980b9;
            }
            .message {
                padding: 10px;
                border-radius: 5px;
                margin-top: 15px;
                font-weight: bold;
            }
            .success {
                background-color: #e8f5e9;
                color: #2e7d32;
            }
            .error {
                background-color: #ffebee;
                color: #c62828;
            }
        </style>
    </head>
    <body>
        <a href="home_admin.jsp" class="go-back-btn">← Go To Main Menu</a>
        <div class="container">
            <h1>Club List</h1>
            <a href="addClub.jsp" class="add-btn">+ Add New Club</a>

            <table>
                <thead>
                    <tr>
                        <th>Club ID</th>
                        <th>Club Name</th>
                        <th>Manager</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="club" items="${clubList}">
                        <tr>
                            <td>${club.clubID}</td>
                            <td>${club.clubName}</td>
                            <td>${club.adminName}</td>
                            <td class="action-buttons">
                                <form action="UpdateClubFormServlet" method="post">
                                    <input type="hidden" name="clubID" value="${club.clubID}">
                                    <input type="submit" value="Update" class="btn-sm btn-update">
                                </form>
                                <form action="DeleteServlet" method="post">
                                    <input type="hidden" name="table" value="CLUB">
                                    <input type="hidden" name="column" value="clubID">
                                    <input type="hidden" name="id" value="${club.clubID}">
                                    <button type="submit" class="btn-sm btn-danger">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <%
                String success = (String) request.getAttribute("successMessage");
                if (success != null) {
            %>
            <div class="message success"><%= success%></div>
            <%
                }
                String err = (String) request.getAttribute("errMessage");
                if (err != null) {
            %>
            <div class="message error"><%= err%></div>
            <%
                }
            %>
        </div>
    </body>
</html>
