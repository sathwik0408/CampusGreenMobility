<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Login - Green Mobility</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
            <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
        </head>

        <body>
            <nav class="navbar navbar-expand-lg">
                <div class="container">
                    <a class="navbar-brand fw-bold" href="${pageContext.request.contextPath}/">🌱 Campus Green
                        Mobility</a>
                </div>
            </nav>
            <div class="container mt-5">
                <div class="row justify-content-center">
                    <div class="col-md-5">
                        <div class="card p-5">
                            <h2 class="text-center page-title mb-4">Student Login</h2>

                            <c:if test="${not empty requestScope.error}">
                                <div class="alert alert-danger" role="alert">
                                    ${requestScope.error}
                                </div>
                            </c:if>

                            <form action="${pageContext.request.contextPath}/login" method="post">
                                <div class="mb-4">
                                    <label for="email" class="form-label">Student Email</label>
                                    <input type="email" class="form-control" id="email" name="email"
                                        placeholder="john@student.campus.edu" required>
                                    <div class="form-text mt-2">Try: john@student.campus.edu</div>
                                </div>
                                <button type="submit" class="btn btn-green w-100">Login</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </body>

        </html>