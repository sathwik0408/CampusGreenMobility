<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Campus Green Mobility</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg">
        <div class="container">
            <a class="navbar-brand fw-bold" href="${pageContext.request.contextPath}/">🌱 Campus Green Mobility</a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav ms-auto">
                    <c:choose>
                        <c:when test="${not empty sessionScope.studentId}">
                            <li class="nav-item"><span class="nav-link">Welcome, ${sessionScope.studentName}</span></li>
                            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/availability">Book a Ride</a></li>
                            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/history">My Impact</a></li>
                            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container mt-5 text-center">
        <h1 class="page-title mb-4">Track Your Carbon Savings</h1>
        <p class="lead">Book shared campus transport and reduce your environmental footprint.</p>
        <c:choose>
            <c:when test="${not empty sessionScope.studentId}">
                <a href="${pageContext.request.contextPath}/availability" class="btn btn-green btn-lg mt-3">Find a Ride</a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/login" class="btn btn-green btn-lg mt-3">Login to book</a>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
