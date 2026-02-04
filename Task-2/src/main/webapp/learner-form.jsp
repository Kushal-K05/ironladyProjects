<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <% if (session.getAttribute("user")==null) { response.sendRedirect("index.jsp"); return; } %>
            <jsp:include page="common/header.jsp" />
            <jsp:include page="common/sidebar.jsp" />

            <div class="main-content">
                <div class="top-bar">
                    <h1 class="page-title">
                        <c:if test="${learner != null}">Edit Learner</c:if>
                        <c:if test="${learner == null}">New Learner</c:if>
                    </h1>
                    <a href="learners" class="btn btn-secondary">Back to List</a>
                </div>

                <div class="card" style="max-width: 600px;">
                    <form action="learners" method="post">
                        <c:if test="${learner != null}">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="id" value="${learner.id}">
                        </c:if>
                        <c:if test="${learner == null}">
                            <input type="hidden" name="action" value="create">
                        </c:if>

                        <div class="form-group">
                            <label for="name" class="form-label">Full Name</label>
                            <input type="text" id="name" name="name" class="form-control" value="${learner.name}"
                                required>
                        </div>

                        <div class="form-group">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" id="email" name="email" class="form-control" value="${learner.email}"
                                required>
                        </div>

                        <div class="form-group">
                            <label for="phone" class="form-label">Phone</label>
                            <input type="tel" id="phone" name="phone" class="form-control" value="${learner.phone}"
                                required>
                        </div>

                        <div class="form-group">
                            <label for="programId" class="form-label">Program</label>
                            <select id="programId" name="programId" class="form-control" required>
                                <option value="">Select a Program</option>
                                <c:forEach var="program" items="${listPrograms}">
                                    <option value="${program.id}" <c:if
                                        test="${learner != null && learner.programId == program.id}">selected</c:if>
                                        >${program.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="enrollmentDate" class="form-label">Enrollment Date</label>
                            <input type="date" id="enrollmentDate" name="enrollmentDate" class="form-control"
                                value="${learner.enrollmentDate}" required>
                        </div>

                        <button type="submit" class="btn btn-primary">Save Learner</button>
                    </form>
                </div>
            </div>

            <jsp:include page="common/footer.jsp" />