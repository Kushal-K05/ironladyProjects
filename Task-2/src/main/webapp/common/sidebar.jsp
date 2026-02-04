<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="sidebar">
    <div class="sidebar-header">
        <span>Iron Lady</span>
    </div>
    <ul class="nav-links">
        <li><a href="dashboard.jsp" class="nav-link ${pageContext.request.requestURI.endsWith('dashboard.jsp') ? 'active' : ''}">Dashboard</a></li>
        <li><a href="programs" class="nav-link ${pageContext.request.requestURI.contains('program') ? 'active' : ''}">Programs</a></li>
        <li><a href="learners" class="nav-link ${pageContext.request.requestURI.contains('learner') ? 'active' : ''}">Learners</a></li>
    </ul>
    <div style="margin-top: auto;">
        <a href="auth?action=logout" class="nav-link" style="color: var(--danger-color);">Logout</a>
    </div>
</div>
