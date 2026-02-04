<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <% if (session.getAttribute("user")==null) { response.sendRedirect("index.jsp"); return; } %>
            <jsp:include page="common/header.jsp" />
            <jsp:include page="common/sidebar.jsp" />

            <div class="main-content">
                <div class="top-bar">
                    <h1 class="page-title">
                        <c:if test="${program != null}">Edit Program</c:if>
                        <c:if test="${program == null}">New Program</c:if>
                    </h1>
                    <a href="programs" class="btn btn-secondary">Back to List</a>
                </div>

                <div class="card" style="max-width: 600px;">
                    <form action="programs" method="post">
                        <c:if test="${program != null}">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="id" value="${program.id}">
                        </c:if>
                        <c:if test="${program == null}">
                            <input type="hidden" name="action" value="create">
                        </c:if>

                        <div class="form-group">
                            <label for="name" class="form-label">Program Name</label>
                            <input type="text" id="name" name="name" class="form-control" value="${program.name}"
                                required>
                        </div>

                        <div class="form-group">
                            <label for="description" class="form-label">Description</label>
                            <textarea id="description" name="description" class="form-control"
                                rows="3">${program.description}</textarea>
                        </div>

                        <div class="form-group">
                            <label for="duration" class="form-label">Duration</label>
                            <input type="text" id="duration" name="duration" class="form-control"
                                value="${program.duration}" placeholder="e.g. 6 Months" required>
                        </div>

                        <div class="form-group">
                            <label for="fee" class="form-label">Fee</label>
                            <input type="number" id="fee" name="fee" class="form-control" value="${program.fee}"
                                step="0.01" required>
                        </div>

                        <button type="submit" class="btn btn-primary">Save Program</button>
                    </form>
                </div>
            </div>

            <jsp:include page="common/footer.jsp" />