<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Confirm Booking - Green Mobility</title>
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
                                    href="${pageContext.request.contextPath}/availability">Back to Availability</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
            <div class="container mt-5">
                <div class="row justify-content-center">
                    <div class="col-md-6">
                        <h2 class="page-title text-center mb-4">Confirm Your Booking</h2>

                        <c:if test="${not empty requestScope.error}">
                            <div class="alert alert-danger" role="alert">
                                ${requestScope.error}
                            </div>
                        </c:if>

                        <div class="card p-4 shadow-sm border-0">
                            <h4 class="text-success mb-3">${slot.vehicleType}</h4>
                            <p class="mb-1"><strong>Route:</strong> ${slot.route}</p>
                            <p class="mb-1"><strong>Time:</strong> ${slot.slotTime}</p>
                            <p class="mb-1"><strong>Distance:</strong> ${slot.distanceKm} km</p>
                            <form action="${pageContext.request.contextPath}/booking" method="post" class="mt-4">
                                <input type="hidden" name="slotId" value="${slot.slotId}">
                                <button type="submit" class="btn btn-green w-100 btn-lg shadow-sm">Confirm
                                    Reserve</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </body>

        </html>