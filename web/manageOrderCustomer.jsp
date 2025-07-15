<%@ page import="bean.OrderBean" %>
<%@ page import="java.util.List" %>
<%
    List<OrderBean> orders = (List<OrderBean>) request.getAttribute("customerOrders");
%>
<html>
    <head>
        <title>Customer Order</title>
        <link rel="stylesheet" href="style/style.css"> 
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
        <a href="home_customer.jsp" class="go-back-btn"> Go To Main Menu</a>
    <c:if test="${not empty successMessage}">
        <div class="success">${successMessage}</div>
    </c:if>

    <div class="summary">
        <h2>Your Orders</h2>
    </div>

    <div class="summary">
        <table border="1">
            <tr>
                <th>Order ID</th>
                <th>Merchandise</th>
                <th>Total Price</th>
                <th>Order Date</th>
            </tr>
            <%
                if (orders != null && !orders.isEmpty()) {
                    for (OrderBean order : orders) {
            %>
            <tr>
                <td><%= order.getOrderID()%></td>
                <td><%= order.getMerch().getMerchName()%></td>
                <td>RM <%= String.format("%.2f", order.getTotalPrice())%></td>
                <td><%= order.getOrderDate()%></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr><td colspan="4">You have no orders.</td></tr>
            <%
                }
            %>
        </table>
    </div>

</body>
</html>