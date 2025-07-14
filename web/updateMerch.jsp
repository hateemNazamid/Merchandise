<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Update Merchandise</title>
    </head>
    <body>
        <h1>Update Merchandise</h1>

        <form action="UpdateMerchServlet" method="post" enctype="multipart/form-data">
            <input type="hidden" name="merchID" value="${merch.merchID}">

            <label>Merchandise Name:</label>
            <input type="text" name="merch" value="${merch.merchName}"><br>

            <label>Category:</label>
            <select name="category">
                <option value="apparel" ${merch.category == 'apparel' ? 'selected' : ''}>Apparel</option>
                <option value="accessory" ${merch.category == 'accessory' ? 'selected' : ''}>Accessories</option>
                <option value="bag" ${merch.category == 'bag' ? 'selected' : ''}>Bags</option>
                <option value="stationery" ${merch.category == 'stationery' ? 'selected' : ''}>Stationery</option>
            </select><br>

            <label>Price:</label>
            <input type="number" step="0.01" name="price" value="${merch.price}"><br>

            <label>Stock:</label>
            <input type="number" name="stock" value="${merch.stock}"><br>

            <label>Club ID:</label>
            <label>Club:</label>
            <select name="clubID">
                <c:forEach var="club" items="${clubs}">
                    <option value="${club.clubID}">${club.clubName}</option>
                </c:forEach>
            </select><br>

            <label>Update Image (optional):</label>
            <input type="file" name="image"><br>

            <input type="submit" value="Update">
        </form>


        <%
            String err = (String) request.getAttribute("errMessage");
            if (err != null) {
        %>
        <p style="color:red;"><%= err%></p>
        <%
            }
        %>
    </body>
</html>
