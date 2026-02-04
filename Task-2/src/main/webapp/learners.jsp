<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <% if (session.getAttribute("user")==null) { response.sendRedirect("index.jsp"); return; } %>
            <jsp:include page="common/header.jsp" />
            <jsp:include page="common/sidebar.jsp" />

            <div class="main-content">
                <div class="top-bar">
                    <h1 class="page-title">Learners</h1>
                    <a href="learners?action=new" class="btn btn-primary">+ New Learner</a>
                </div>

                <div class="card">
                    <div class="table-container">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Email</th>
                                    <th>Phone</th>
                                    <th>Program</th>
                                    <th>Enrollment Date</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="learner" items="${listLearners}">
                                    <tr>
                                        <td>${learner.id}</td>
                                        <td>${learner.name}</td>
                                        <td>${learner.email}</td>
                                        <td>${learner.phone}</td>
                                        <td>${learner.programName}</td>
                                        <td>${learner.enrollmentDate}</td>
                                        <td>
                                            <div class="action-buttons">
                                                <a href="learners?action=edit&id=${learner.id}"
                                                    class="btn btn-secondary">Edit</a>
                                                <a href="learners?action=delete&id=${learner.id}" class="btn btn-danger"
                                                    onclick="return confirm('Are you sure?')">Delete</a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty listLearners}">
                                    <tr>
                                        <td colspan="7" style="text-align: center; color: var(--text-light);">No
                                            learners found.</td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <jsp:include page="common/footer.jsp" />