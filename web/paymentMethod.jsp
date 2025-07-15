<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*, java.util.*" %>
<%
    String merchIDParam = request.getParameter("id");
    String strPrice = request.getParameter("price");
    String quantityParam = request.getParameter("quantity");

    // Null-check BEFORE using the values
    if (merchIDParam == null || strPrice == null || quantityParam == null) {
        response.sendRedirect("viewMerch.jsp");
        return;
    }

    int merchID = Integer.parseInt(merchIDParam);
    double price = Double.parseDouble(strPrice);
    int quantity = Integer.parseInt(quantityParam);
    double total = price * quantity;
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Select Payment Method</title>
        <link rel="stylesheet" href="style/style.css">
        <style>
            body {
                font-family: 'Segoe UI', sans-serif;
                background-color: #f9f9f9;
                margin: 0;
                padding: 0;
            }

            .container {
                max-width: 500px;
                margin: 80px auto;
                background-color: #fff;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            }

            h1 {
                text-align: center;
                margin-bottom: 25px;
            }

            .payment-option {
                margin-bottom: 20px;
            }

            label {
                display: block;
                padding: 10px;
                background: #f4f4f4;
                border: 1px solid #ccc;
                border-radius: 6px;
                cursor: pointer;
            }

            input[type="radio"] {
                margin-right: 10px;
            }

            .btn {
                display: inline-block;
                padding: 10px 20px;
                background-color: #c49b63;
                color: #fff;
                border: none;
                border-radius: 5px;
                text-decoration: none;
                font-weight: bold;
                cursor: pointer;
            }

            .btn:hover {
                background-color: #a67c52;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Choose Payment Method</h1>

            <form action="AddOrderServlet" method="post">

                <input type="hidden" name="merchID" value="<%= merchID%>">
                <input type="hidden" name="price" value="<%= total%>">
                <input type="hidden" name="quantity" value="<%= quantity%>">


                <div class="payment-option">
                    <label><input type="radio" name="paymentMethod" value="TnG" required> Touch 'n Go</label>
                </div>
                <div class="payment-option">
                    <label><input type="radio" name="paymentMethod" value="Card"> Credit/Debit Card</label>
                </div>
                <div class="payment-option">
                    <label><input type="radio" name="paymentMethod" value="Cash"> Cash</label>
                </div>
                <div class="payment-option">
                    <label><input type="radio" name="paymentMethod" value="Online Banking"> Online Banking</label>
                </div>

                <button type="submit" class="btn" onclick="alert('Payment Successful')">Proceed to Payment</button>
            </form>
        </div>
    </body>
</html>
