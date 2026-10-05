<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Success - Green Mobility</title>
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
                                    href="${pageContext.request.contextPath}/availability">Book Another Ride</a></li>
                            <li class="nav-item"><a class="nav-link"
                                    href="${pageContext.request.contextPath}/history">My Impact</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
            <div class="container mt-5">
                <div class="row justify-content-center">
                    <div class="col-md-7 text-center">
                        <div class="card p-5 border-0 shadow-sm stat-card">
                            <h1 class="display-3 mb-3">✅</h1>
                            <h2 class="fw-bold mb-4">Booking Confirmed!</h2>
                            <p class="lead">You have successfully booked a <strong>${slot.vehicleType}</strong> ride for
                                <strong>${slot.route}</strong> at <strong>${slot.slotTime}</strong>.</p>

                            <div class="bg-white p-4 rounded text-center mt-3 shadow-sm mx-auto"
                                style="max-width: 300px;">
                                <h5 class="text-muted">CO2 Emission Saved</h5>
                                <h2 class="text-success fw-bold m-0">+${co2Saved} kg</h2>
                            </div>

                            <div class="mt-5">
                                <a href="${pageContext.request.contextPath}/history"
                                    class="btn btn-outline-success">View My Bookings</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </body>

        </html>