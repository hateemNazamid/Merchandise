<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Merchandise</title>
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
            max-width: 700px;
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
            margin-top: 20px;
            margin-bottom: 8px;
            font-weight: 600;
            color: #555;
        }

        input[type="text"],
        input[type="number"],
        input[type="file"],
        select {
            width: 100%;
            padding: 10px;
            border-radius: 4px;
            border: 1px solid #ccc;
            font-size: 16px;
        }

        .form-actions {
            margin-top: 30px;
            display: flex;
            justify-content: flex-end;
            gap: 10px;
        }

        .btn {
            padding: 10px 20px;
            border-radius: 4px;
            text-decoration: none;
            font-size: 16px;
            border: none;
            cursor: pointer;
        }

        .btn-primary {
            background-color: #c49b63;
            color: #fff;
        }

        .btn-primary:hover {
            background-color: #a67c52;
        }

        .btn:hover {
            opacity: 0.9;
        }

        .go-back-btn {
            position: fixed;
            top: 20px;
            left: 20px;
            background-color: #c49b63;
            color: #fff;
            padding: 8px 16px;
            border: none;
            border-radius: 4px;
            font-weight: 600;
            font-size: 14px;
            text-decoration: none;
            z-index: 50;
        }

        .go-back-btn:hover {
            background-color: #a67c52;
        }

        .error-message {
            color: red;
            font-weight: bold;
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <a href="ManageMerchServlet" class="go-back-btn">← Back to Merch List</a>
    <main class="container">
        <h1>${pageTitle}</h1>

        <form action="AddMerchServlet" method="post" enctype="multipart/form-data">
            <label for="merch">Merchandise Name:</label>
            <input type="text" id="merch" name="merch" required>

            <label for="category">Category:</label>
            <select id="category" name="category" required>
                <option value="apparel">Apparel</option>
                <option value="accessory">Accessories</option>
                <option value="bag">Bags</option>
                <option value="stationery">Stationery</option>
            </select>

            <label for="price">Price (RM):</label>
            <input type="number" step="0.01" id="price" name="price" required>

            <label for="stock">Stock Quantity:</label>
            <input type="number" id="stock" name="stock" required>

            <label for="clubID">Club:</label>
            <select id="clubID" name="clubID" required>
                <c:forEach var="club" items="${clubs}">
                    <option value="${club.clubID}">${club.clubName}</option>
                </c:forEach>
            </select>

            <label for="image">Product Image:</label>
            <input type="file" id="image" name="image" accept="image/*" required>

            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Save</button>
                <a href="ManageMerchServlet" class="btn">Cancel</a>
            </div>
        </form>

        <%
            String err = (String) request.getAttribute("errMessage");
            if (err != null) {
        %>
        <div class="error-message"><%= err %></div>
        <%
            }
        %>
    </main>
</body>
</html>
