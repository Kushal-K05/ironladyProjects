<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Iron Lady</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
</head>

<body>

    <div class="auth-container">
        <div class="auth-card">
            <h1 class="auth-title">Iron Lady <br><span
                    style="font-size: 1rem; font-weight: 500; color: var(--text-light);">Internal Automation</span></h1>

            <% String error=(String) request.getAttribute("error"); %>
                <% if (error !=null) { %>
                    <div class="alert alert-danger">
                        <%= error %>
                    </div>
                    <% } %>

                        <form action="auth" method="post">
                            <input type="hidden" name="action" value="login">
                            <div class="form-group">
                                <label for="username" class="form-label">Username</label>
                                <input type="text" id="username" name="username" class="form-control"
                                    placeholder="admin" required>
                            </div>
                            <div class="form-group">
                                <label for="password" class="form-label">Password</label>
                                <input type="password" id="password" name="password" class="form-control"
                                    placeholder="admin123" required>
                            </div>
                            <button type="submit" class="btn btn-primary" style="width: 100%;">Sign In</button>
                        </form>
        </div>
    </div>

</body>

</html>