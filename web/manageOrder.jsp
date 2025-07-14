<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Manage Orders</title>
    </head>
    <body>
        <h1>Order List</h1>

        <c:if test="${not empty successMessage}">
            <p style="color:green;">${successMessage}</p>
        </c:if>

        <table border="1">
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Merch ID</th>
                    <th>Customer ID</th>
                    <th>Total Price</th>
                    <th>Order Date</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${orderList}">
                    <tr>
                        <td>${order.orderID}</td>
                        <td>${order.merchID}</td>
                        <td>${order.customerID}</td>
                        <td>${order.totalPrice}</td>
                        <td>${order.orderDate}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <h2>Order Summary Report</h2>
        <p><strong>Total Orders:</strong> ${totalOrders}</p>
        <p><strong>Total Revenue:</strong> RM ${totalRevenue}</p>

        <h3>Top Selling Merchandise</h3>
        <table border="1">
            <tr>
                <th>Merch ID</th>
                <th>Order Count</th>
            </tr>
            <c:forEach var="item" items="${topMerchList}">
                <tr>
                    <td>${item.merchID}</td>
                    <td>${item.orderCount}</td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
