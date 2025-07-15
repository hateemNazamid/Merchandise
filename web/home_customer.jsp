<%@page import="bean.RegisterBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% String currentUser = (String) session.getAttribute("custUsername");%>
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Customer Home</title>
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
            <h1>Welcome back, <%=currentUser%>!</h1>
            
        </div>

        <div class="action-panel">
            <div class="action-card">
                <i class="fas fa-tshirt"></i>
                <h3>Browse Merchandise</h3>
                <p>Explore our latest products</p>
                <a href="viewMerch.jsp" class="btn btn-customer">View Catalog</a>
            </div>
            
            <div class="action-card">
                <i class="fas fa-history"></i>
                <h3>Order History</h3>
                <p>Track your purchases</p>
                <a href="OrderServlet" class="btn btn-customer">View History</a>
            </div>
            
            <div class="action-card">
                <i class="fas fa-user-cog"></i>
                <h3>Account Settings</h3>
                <p>Update your profile</p>
                <a href="updateCustomer.jsp" class="btn btn-customer">Edit Profile</a>
            </div>
        </div>
    </main>
</body>
</html>