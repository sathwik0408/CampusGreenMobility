package com.campusgreen.servlet;

import com.campusgreen.dao.SlotDAO;
import com.campusgreen.model.VehicleSlot;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/availability")
public class AvailabilityServlet extends HttpServlet {
    private SlotDAO slotDAO = new SlotDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("studentId") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        List<VehicleSlot> slots = slotDAO.getAvailableSlots();
        request.setAttribute("slots", slots);

        // Read last booked vehicle cookie
        String lastVehicleType = "None";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("lastVehicleType".equals(cookie.getName())) {
                    lastVehicleType = cookie.getValue();
                }
            }
        }
        request.setAttribute("lastVehicleType", lastVehicleType);

        request.getRequestDispatcher("/availability.jsp").forward(request, response);
    }
}
