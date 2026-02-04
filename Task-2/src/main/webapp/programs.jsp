<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <% if (session.getAttribute("user")==null) { response.sendRedirect("index.jsp"); return; } %>
            <jsp:include page="common/header.jsp" />
            <jsp:include page="common/sidebar.jsp" />

            <div class="main-content">
                <div class="top-bar">
                    <h1 class="page-title">Programs</h1>
                    <a href="programs?action=new" class="btn btn-primary">+ New Program</a>
                </div>

                <div class="card">
                    <div class="table-container">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th>Duration</th>
                                    <th>Fee</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="program" items="${listPrograms}">
                                    <tr>
                                        <td>${program.id}</td>
                                        <td>${program.name}</td>
                                        <td class="truncate-text" title="${program.description}">${program.description}
                                        </td>
                                        <td>${program.duration}</td>
                                        <td>&#8377;${program.fee}</td>
                                        <td>
                                            <div class="action-buttons">
                                                <a href="programs?action=edit&id=${program.id}"
                                                    class="btn btn-secondary">Edit</a>
                                                <a href="programs?action=delete&id=${program.id}" class="btn btn-danger"
                                                    onclick="return confirm('Are you sure?')">Delete</a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty listPrograms}">
                                    <tr>
                                        <td colspan="6" style="text-align: center; color: var(--text-light);">No
                                            programs found.</td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <jsp:include page="common/footer.jsp" />