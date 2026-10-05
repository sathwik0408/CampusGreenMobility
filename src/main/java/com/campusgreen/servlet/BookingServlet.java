package com.campusgreen.servlet;

import com.campusgreen.dao.BookingDAO;
import com.campusgreen.dao.SlotDAO;
import com.campusgreen.model.VehicleSlot;
import com.campusgreen.util.CarbonCalculator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/booking")
public class BookingServlet extends HttpServlet {
    private SlotDAO slotDAO = new SlotDAO();
    private BookingDAO bookingDAO = new BookingDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("studentId") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String slotIdParam = request.getParameter("slotId");
        if (slotIdParam == null || slotIdParam.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/availability");
            return;
        }

        try {
            int slotId = Integer.parseInt(slotIdParam);
            VehicleSlot slot = slotDAO.getSlotById(slotId);
            if (slot == null) {
                response.sendRedirect(request.getContextPath() + "/availability");
                return;
            }
            request.setAttribute("slot", slot);
            request.getRequestDispatcher("/confirm-booking.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/availability");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("studentId") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String slotIdParam = request.getParameter("slotId");
        if (slotIdParam == null) {
            response.sendRedirect(request.getContextPath() + "/availability");
            return;
        }

        try {
            int slotId = Integer.parseInt(slotIdParam);
            int studentId = (int) session.getAttribute("studentId");

            VehicleSlot slot = slotDAO.getSlotById(slotId);
            if (slot == null) {
                response.sendRedirect(request.getContextPath() + "/availability");
                return;
            }

            double co2Saved = CarbonCalculator.calculateCO2Saved(slot.getDistanceKm());

            // Process atomic transaction booking
            String result = bookingDAO.createBooking(studentId, slotId, slot.getDistanceKm(), co2Saved);

            if ("SUCCESS".equals(result)) {
                // Update cookie with latest vehicle type booked
                Cookie vehicleCookie = new Cookie("lastVehicleType", slot.getVehicleType().replaceAll(" ", "_"));
                vehicleCookie.setMaxAge(60 * 60 * 24 * 30); // 30 days
                response.addCookie(vehicleCookie);

                request.setAttribute("slot", slot);
                request.setAttribute("co2Saved", co2Saved);
                request.getRequestDispatcher("/booking-success.jsp").forward(request, response);
            } else {
                request.setAttribute("error", result);
                request.setAttribute("slot", slot);
                request.getRequestDispatcher("/confirm-booking.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/availability");
        }
    }
}
