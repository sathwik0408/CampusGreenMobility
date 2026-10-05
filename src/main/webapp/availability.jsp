<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Availability - Green Mobility</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
            <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
        </head>

        <body>
            <nav class="navbar navbar-expand-lg">
                <div class="container">
                    <a class="navbar-brand fw-bold" href="${pageContext.request.contextPath}/">🌱 Campus Green
                        Mobility</a>
                    <div class="collapse navbar-collapse">
                        <ul class="navbar-nav ms-auto">
                            <li class="nav-item"><span class="nav-link">Welcome, ${sessionScope.studentName}</span></li>
                            <li class="nav-item"><a class="nav-link fw-bold"
                                    href="${pageContext.request.contextPath}/availability">Book a Ride</a></li>
                            <li class="nav-item"><a class="nav-link"
                                    href="${pageContext.request.contextPath}/history">My Impact</a></li>
                            <li class="nav-item"><a class="nav-link"
                                    href="${pageContext.request.contextPath}/logout">Logout</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
            <div class="container mt-4">
                <h2 class="page-title mb-4">Available Rides</h2>
                <c:if test="${not empty requestScope.lastVehicleType and requestScope.lastVehicleType != 'None'}">
                    <div class="alert alert-info border-0 shadow-sm">
                        Your last selected vehicle type was: <strong>${requestScope.lastVehicleType.replaceAll("_", "
                            ")}</strong>
                    </div>
                </c:if>
                <div class="row g-4">
                    <c:forEach var="slot" items="${requestScope.slots}">
                        <div class="col-md-4">
                            <div class="card h-100 p-3">
                                <div class="d-flex justify-content-between align-items-center mb-3">
                                    <h4 class="m-0 text-success fw-bold">
                                        <c:choose>
                                            <c:when test="${slot.vehicleType == 'EV Shuttle'}">🚐 ${slot.vehicleType}
                                            </c:when>
                                            <c:otherwise>🚲 ${slot.vehicleType}</c:otherwise>
                                        </c:choose>
                                    </h4>
                                </div>
                                <p class="mb-2"><strong>Route:</strong><br>${slot.route}</p>
                                <p class="mb-2"><strong>Time:</strong><br>${slot.slotTime}</p>
                                <p class="mb-2"><strong>Distance:</strong> ${slot.distanceKm} km</p>
                                <div class="d-flex w-100 justify-content-between mb-3 text-muted">
                                    <small>Capacity: ${slot.capacity}</small>
                                    <small>Booked: ${slot.bookedCount}</small>
                                    <small class="fw-bold text-dark">Available: ${slot.availableSlots}</small>
                                </div>

                                <c:choose>
                                    <c:when test="${slot.availableSlots > 0}">
                                        <a href="${pageContext.request.contextPath}/booking?slotId=${slot.slotId}"
                                            class="btn btn-green w-100 mt-auto">Book Now</a>
                                    </c:when>
                                    <c:otherwise>
                                        <button class="btn btn-secondary w-100 mt-auto" disabled>FULL</button>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </c:forEach>
                    <c:if test="${empty requestScope.slots}">
                        <div class="col-12 text-center text-muted py-5">
                            <h5>No rides currently available.</h5>
                        </div>
                    </c:if>
                </div>
            </div>
        </body>

        </html>