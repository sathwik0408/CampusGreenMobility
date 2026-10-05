<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>My Impact - Green Mobility</title>
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
                            <li class="nav-item"><a class="nav-link"
                                    href="${pageContext.request.contextPath}/availability">Book a Ride</a></li>
                            <li class="nav-item"><a class="nav-link fw-bold"
                                    href="${pageContext.request.contextPath}/history">My Impact</a></li>
                            <li class="nav-item"><a class="nav-link"
                                    href="${pageContext.request.contextPath}/logout">Logout</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
            <div class="container mt-4">
                <div class="row mb-4">
                    <div class="col-md-12 text-center">
                        <div class="card p-4 stat-card shadow-sm border-0 d-inline-block w-50">
                            <h5 class="text-muted">My Cumulative Carbon Savings</h5>
                            <h1 class="text-success fw-bold m-0">${requestScope.totalCo2Saved} kg CO2</h1>
                        </div>
                    </div>
                </div>

                <h3 class="page-title mb-3">Booking History</h3>

                <c:choose>
                    <c:when test="${not empty requestScope.bookings}">
                        <div class="table-responsive bg-white rounded shadow-sm">
                            <table class="table table-hover align-middle mb-0">
                                <thead class="table-light">
                                    <tr>
                                        <th>Booking ID</th>
                                        <th>Vehicle Type</th>
                                        <th>Route</th>
                                        <th>Slot Time</th>
                                        <th>Distance</th>
                                        <th>CO2 Saved</th>
                                        <th>Booked At</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="b" items="${requestScope.bookings}">
                                        <tr>
                                            <td>#${b.bookingId}</td>
                                            <td><strong>${b.vehicleType}</strong></td>
                                            <td>${b.route}</td>
                                            <td>${b.slotTime}</td>
                                            <td>${b.distanceKm} km</td>
                                            <td><span class="badge bg-success rounded-pill">+${b.co2SavedKg} kg</span>
                                            </td>
                                            <td class="text-muted"><small>${b.bookedAt}</small></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="alert alert-light text-center py-4 border">
                            <p class="mb-0">You have no past bookings. Start reserving rides to save CO2!</p>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </body>

        </html>