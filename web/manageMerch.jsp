<%@ page import="java.sql.*, java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Manage Merchandise</title>
        <link rel="stylesheet" href="style/style.css">
        <link rel="stylesheet" href="style/button.css">
        <link rel="stylesheet" href="style/form.css">
        <style>
            h2.club-heading {
                margin-top: 40px;
                border-bottom: 2px solid #ddd;
                padding-bottom: 5px;
                color: #333;
            }
            .table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 30px;
            }
            .table th, .table td {
                padding: 10px;
                border: 1px solid #ddd;
                text-align: left;
            }
            .thumbnail {
                border-radius: 4px;
                width: 50px;
                height: auto;
            }
            .action-buttons {
                display: flex;
                gap: 5px;
            }
            .inline-form {
                display: inline;
            }
        </style>
    </head>
    <body>
        <a href="home_admin.jsp" class="go-back-btn">← Go To Main Menu</a>
        <main class="container">
            <div class="flex-between mb-3">
                <h1>Merchandise</h1>
                <a href="AddMerchFormServlet" class="btn btn-primary">+ Add New</a>
            </div>

            <%
                Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                boolean merchFound = false;
                String lastClub = "";

                try {
                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                    conn = DriverManager.getConnection("jdbc:derby://localhost:1527/merchandise", "app", "app");

                    String sql = "SELECT M.*, C.CLUBNAME, C.CLUBID "
                            + "FROM MERCHANDISE M JOIN CLUB C ON M.CLUBID = C.CLUBID "
                            + "ORDER BY C.CLUBNAME, M.NAME";
                    ps = conn.prepareStatement(sql);
                    rs = ps.executeQuery();

                    boolean openTable = false;

                    while (rs.next()) {
                        merchFound = true;
                        int merchID = rs.getInt("MERCHID");
                        String name = rs.getString("NAME");
                        String category = rs.getString("CATEGORY");
                        double price = rs.getDouble("PRICE");
                        int stock = rs.getInt("STOCK");
                        String clubName = rs.getString("CLUBNAME");
                        int clubID = rs.getInt("CLUBID");

                        if (!clubName.equals(lastClub)) {
                            if (openTable) {
                                out.println("</tbody></table></div>"); // close previous card
                            }
            %>
            <h2 class="club-heading"><%= clubName%></h2>
            <div class="card">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Image</th>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Category</th>
                            <th>Price</th>
                            <th>Stock</th>
                            <th>Club ID</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                                lastClub = clubName;
                                openTable = true;
                            }
                        %>
                        <tr>
                            <td>
                                <img src="ManageImageServlet?id=<%= merchID%>" alt="Image" class="thumbnail" />

                            </td>
                            <td><%= merchID%></td>
                            <td><%= name%></td>
                            <td><%= category%></td>
                            <td>RM <%= String.format("%.2f", price)%></td>
                            <td><%= stock%></td>
                            <td><%= clubID%></td>
                            <td class="action-buttons">
                                <a href="UpdateMerchFormServlet?merchID=<%= merchID%>" class="btn btn-sm">Edit</a>
                                <form action="DeleteServlet" method="post" class="inline-form">
                                    <input type="hidden" name="table" value="MERCHANDISE">
                                    <input type="hidden" name="column" value="merchID">
                                    <input type="hidden" name="id" value="<%= merchID%>">
                                    <button type="submit" class="btn btn-sm btn-danger">Delete</button>
                                </form>
                            </td>
                        </tr>
                        <%
                            }

                            if (openTable) {
                                out.println("</tbody></table></div>"); // Close last table
                            }

                            if (!merchFound) {
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
                    </main>
                    </body>
                    </html>
