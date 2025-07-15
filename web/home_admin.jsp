<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% String currentAdmin = (String) session.getAttribute("adminUsername");%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
        <link rel="stylesheet" href="style/style.css">
        <link rel="stylesheet" href="style/customer.css">
    </head>
    <body class="customer-theme">
        <header class="header customer-header">
            <div class="container navbar">
                <div class="brand customer-brand">Merchandise Portal</div>
                <div class="nav-links">
                    <a href="LogoutServlet" class="btn btn-danger">
                        <i class="fas fa-sign-out-alt"></i> Logout
                    </a>
                </div>
            </div>
        </header>

        <main class="container">
            <div class="welcome-card">
                <h1>Welcome back, <%=currentAdmin%>!</h1>
                
            </div>

            <div class="action-panel">
                <div class="action-card">
                    <i class="fas fa-tshirt"></i>
                    <h3>Browse Merchandise</h3>
                    <p>Manage Merchandises</p>
                    <a href="ManageMerchServlet" class="btn btn-customer">View Catalog</a>
                </div>



                <div class="action-card">
                    <i class="fas fa-history"></i>
                    <h3>Club Overview</h3>
                    <p>Manage Clubs</p>
                    <a href="ManageClubServlet" class="btn btn-customer">View Clubs</a>
                </div>

                <div class="action-card">
                    <i class="fas fa-history"></i>
                    <h3>Order History</h3>
                    <p>Track user orders</p>
                    <a href="ManageOrderServlet" class="btn btn-customer">View Order History</a>
                </div>



                <div class="action-card">
                    <i class="fas fa-user-cog"></i>
                    <h3>Account Settings</h3>
                    <p>Update your profile</p>
                    <a href="updateAdmin.jsp" class="btn btn-customer">Edit Profile</a>
                </div>
            </div>
        </main>
    </body>
</html>