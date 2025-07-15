<%@ page import="java.sql.*, java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>View Merchandise</title>
        <link rel="stylesheet" href="style/style.css"> 
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0; padding: 0;
            }
            .container {
                max-width: 1000px;
                margin: 30px auto;
                padding: 20px;
                background: white;
                border-radius: 8px;
                box-shadow: 0 0 10px #ccc;
            }
            h1 {
                text-align: center;
            }
            h2.club-heading {
                margin-top: 40px;
                border-bottom: 2px solid #ddd;
                padding-bottom: 5px;
                color: #333;
            }
            .merch-grid {
                display: flex;
                flex-wrap: wrap;
                gap: 20px;
                justify-content: center;
                margin-top: 15px;
            }
            .merch-item {
                width: 250px;
                border: 1px solid #ddd;
                border-radius: 8px;
                overflow: hidden;
                text-align: center;
                background-color: #fff;
            }
            .merch-item img {
                width: 100%;
                height: 200px;
                object-fit: cover;
            }
            .merch-item h3 {
                margin: 10px 0 5px;
            }
            .merch-item p {
                color: #555;
            }
            .buy-btn {
                display: inline-block;
                margin: 10px 0 15px;
                padding: 8px 16px;
                background-color: #28a745;
                color: white;
                border: none;
                border-radius: 20px;
                text-decoration: none;
            }
            .buy-btn:hover {
                background-color: #218838;
            }
            .no-merch {
                text-align: center;
                color: #888;
                font-size: 18px;
                margin-top: 50px;
            }
            .merch-item input[type="number"] {
                padding: 4px;
                text-align: center;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

        </style>
    </head>
    <body>
        <a href="home_customer.jsp" class="go-back-btn">← Go To Main Menu</a>
        <div class="container">
            <h1>Available Merchandise</h1>
            <%
                Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                boolean merchFound = false;
                String lastClub = "";

                try {
                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                    conn = DriverManager.getConnection("jdbc:derby://localhost:1527/merchandise", "app", "app");

                    String sql = "SELECT M.NAME, M.PRICE, M.IMAGE, C.CLUBNAME, M.MERCHID "
                            + "FROM MERCHANDISE M JOIN CLUB C ON M.CLUBID = C.CLUBID "
                            + "ORDER BY C.CLUBNAME, M.NAME";
                    ps = conn.prepareStatement(sql);
                    rs = ps.executeQuery();

                    while (rs.next()) {
                        merchFound = true;
                        String name = rs.getString("NAME");
                        double price = rs.getDouble("PRICE");
                        String image = rs.getString("IMAGE");
                        String clubName = rs.getString("CLUBNAME");
                        int merchID = rs.getInt("MERCHID");

                        // Show a new club heading if this is a different club than previous
                        if (!clubName.equals(lastClub)) {
                            if (!lastClub.equals("")) {
                                out.println("</div>"); // close previous merch-grid
                            }
            %>
            <h2 class="club-heading"><%= clubName%></h2>
            <div class="merch-grid">
                <%
                        lastClub = clubName;
                    }
                %>
                <div class="merch-item">
                    <img src="ManageImageServlet?id=<%= merchID%>" alt="Image" class="thumbnail" />
                    <h3><%= name%></h3>
                    <p>Price: $<%= String.format("%.2f", price)%></p>
                    <form action="paymentMethod.jsp" method="post">
                        <input type="hidden" name="id" value="<%= merchID%>" />
                        <input type="hidden" name="price" value="<%= price%>" />
                        <label for="qty_<%= merchID%>">Qty:</label>
                        <input type="number" name="quantity"  min="1" max="99" value="1" style="width: 60px; margin-bottom: 8px;" required />
                        <br>
                        <button type="submit" class="buy-btn">Buy</button>
                    </form>

                </div>
                <%
                    }

                    if (merchFound) {
                        out.println("</div>"); // close final merch-grid
                    } else {
                %>
                <div class="no-merch">No merchandise available at the moment.</div>
                <%
                        }

                    } catch (Exception e) {
                        out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
                    } finally {
                        try {
                            if (rs != null) {
                                rs.close();
                            }
                        } catch (SQLException e) {
                        }
                        try {
                            if (ps != null) {
                                ps.close();
                            }
                        } catch (SQLException e) {
                        }
                        try {
                            if (conn != null) {
                                conn.close();
                            }
                        } catch (SQLException e) {
                        }
                    }
                %>
            </div>
    </body>
</html>
