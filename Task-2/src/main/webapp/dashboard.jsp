<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <% if (session.getAttribute("user")==null) { response.sendRedirect("index.jsp"); return; } %>
            <jsp:include page="common/header.jsp" />
            <jsp:include page="common/sidebar.jsp" />

            <div class="main-content">
                <div class="top-bar">
                    <h1 class="page-title">Dashboard</h1>
                    <div class="user-profile">
                        <span>Welcome, <strong>${sessionScope.user.username}</strong></span>
                    </div>
                </div>

                <div class="card">
                    <h2>System Overview</h2>
                    <p style="color: var(--text-light); margin-top: 0.5rem;">Welcome to the Iron Lady Internal Business
                        Automation Solution. Use the sidebar to navigate to Program or Learner management.</p>
                </div>

                <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(300px, 1fr)); gap: 1.5rem;">
                    <div class="card">
                        <h3>Programs</h3>
                        <p style="margin: 1rem 0;">Manage training programs including details and fees.</p>
                        <a href="programs" class="btn btn-primary">Manage Programs</a>
                    </div>
                    <div class="card">
                        <h3>Learners</h3>
                        <p style="margin: 1rem 0;">Manage learner enrollments and profiles.</p>
                        <a href="learners" class="btn btn-primary">Manage Learners</a>
                    </div>
                </div>
            </div>

            <jsp:include page="common/footer.jsp" />