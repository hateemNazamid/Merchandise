<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Manage Orders</title>
        <style>
            body {
                background-color: #f9f9f9;
                color: #333;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                margin: 40px;
            }
            h1, h2, h3 {
                color: #2196f3;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
                background-color: #fff;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
                border-radius: 8px;
                overflow: hidden;
            }
            th, td {
                padding: 14px 18px;
                border-bottom: 1px solid #eee;
                text-align: left;
            }
            th {
                background-color: #e3f2fd;
                color: #0d47a1;
                font-weight: bold;
            }
            tr:hover {
                background-color: #f1f1f1;
            }
            p {
                font-size: 16px;
            }
            .success {
                color: #2e7d32;
                background-color: #e8f5e9;
                padding: 12px 16px;
                border-left: 6px solid #43a047;
                margin-top: 20px;
                border-radius: 5px;
                width: fit-content;
            }
            .summary {
                background-color: #fff;
                padding: 16px;
                margin-top: 30px;
                box-shadow: 0 2px 6px rgba(0, 0, 0, 0.05);
                border-radius: 8px;
            }
            .summary p {
                margin: 10px 0;
            }
        </style>
    </head>
    <body>
        <h1>Order List</h1>

        <c:if test="${not empty successMessage}">
            <div class="success">${successMessage}</div>
        </c:if>

        <table>
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
                        <td>RM ${order.totalPrice}</td>
                        <td>${order.orderDate}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="summary">
            <h2>Order Summary Report</h2>
            <p><strong>Total Orders:</strong> ${totalOrders}</p>
            <p><strong>Total Revenue:</strong> RM ${totalRevenue}</p>
        </div>

        <div class="summary">
            <h3>Top Selling Merchandise</h3>
            <table>
                <thead>
                    <tr>
                        <th>Merch ID</th>
                        <th>Order Count</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${topMerchList}">
                        <tr>
                            <td>${item.merchID}</td>
                            <td>${item.orderCount}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
