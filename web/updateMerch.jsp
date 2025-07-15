<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Update Merchandise</title>
        <link rel="stylesheet" href="style/style.css">
    </head>
    <body>
        <a href="ManageMerchServlet" class="go-back-btn">← Back to Merchandise</a>

        <main class="container">
            <div class="form-container">
                <h1 class="text-center">Update Merchandise</h1>

                <form action="UpdateMerchServlet" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="merchID" value="${merch.merchID}" />

                    <!-- Merchandise Name -->
                    <div class="form-group">
                        <label>Merchandise Name:</label>
                        <input type="text" name="merch" value="${merch.merchName}" class="form-control" required />
                    </div>

                    <!-- Category -->
                    <div class="form-group">
                        <label>Category:</label>
                        <select name="category" class="form-control" required>
                            <option value="apparel" ${merch.category == 'apparel' ? 'selected' : ''}>Apparel</option>
                            <option value="accessory" ${merch.category == 'accessory' ? 'selected' : ''}>Accessories</option>
                            <option value="bag" ${merch.category == 'bag' ? 'selected' : ''}>Bags</option>
                            <option value="stationery" ${merch.category == 'stationery' ? 'selected' : ''}>Stationery</option>
                        </select>
                    </div>

                    <!-- Price -->
                    <div class="form-group">
                        <label>Price (RM):</label>
                        <input type="number" step="0.01" name="price" value="${merch.price}" class="form-control" required />
                    </div>

                    <!-- Stock -->
                    <div class="form-group">
                        <label>Stock:</label>
                        <input type="number" name="stock" value="${merch.stock}" class="form-control" required />
                    </div>

                    <!-- Club ID -->
                    <div class="form-group">
                        <label>Club:</label>
                        <select name="clubID" class="form-control" required>
                            <c:forEach var="club" items="${clubs}">
                                <option value="${club.clubID}" 
                                    <c:if test="${club.clubID == merch.clubID}">selected</c:if>>
                                    ${club.clubName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Image Upload -->
                    <div class="form-group">
                        <label>Update Image (optional):</label>
                        <input type="file" name="image" class="form-control" />
                    </div>

                    <!-- Submit Button -->
                    <div class="form-actions">
                        <input type="submit" value="Update Merchandise" class="btn btn-primary" />
                    </div>
                </form>

                <!-- Error Message -->
                <c:if test="${not empty errMessage}">
                    <div class="alert alert-danger">${errMessage}</div>
                </c:if>
            </div>
        </main>
    </body>
</html>
