<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Add Merchandise</title>
        <link rel="stylesheet" href="style/style.css">         
        <link rel="stylesheet" href="style/button.css">
        <link rel="stylesheet" href="style/form.css">

    </head>
    <body>
        <main class="container">
            <div class="form-container">
                <h1>${pageTitle}</h1>

                <form action="AddMerchServlet" method="post" enctype="multipart/form-data">
                    <label>Merchandise Name:</label>
                    <input type="text" name="merch"><br>

                    <label>Category:</label>
                    <select name="category">
                        <option value="apparel">Apparel</option>
                        <option value="accessory">Accessories</option>
                        <option value="bag">Bags</option>
                        <option value="stationery">Stationery</option>
                    </select><br>

                    <label>Price:</label>
                    <input type="number" step="0.01" name="price"><br>

                    <label>Stock:</label>
                    <input type="number" name="stock"><br>

                    <label>Club:</label>
                    <select name="clubID">
                        <c:forEach var="club" items="${clubs}">
                            <option value="${club.clubID}">${club.clubName}</option>
                        </c:forEach>
                    </select>

                    <label>Product Image:</label>
                    <input type="file" name="image"><br>

                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">Save</button>
                        <a href="ManageMerchServlet" class="btn">Cancel</a>
                    </div>
                </form>

                <%
                    String err = (String) request.getAttribute("errMessage");
                    if (err != null) {
                %>
                <p style="color:red;"><%= err%></p>
                <%
                    }
                %>
 
        </main>
    </body>
</html>
