package com.campusgreen.servlet;

import com.campusgreen.dao.BookingDAO;
import com.campusgreen.dao.StudentDAO;
import com.campusgreen.model.Booking;
import com.campusgreen.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/history")
public class HistoryServlet extends HttpServlet {
    private BookingDAO bookingDAO = new BookingDAO();
    private StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("studentId") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int studentId = (int) session.getAttribute("studentId");
        String email = (String) session.getAttribute("studentEmail");

        List<Booking> bookings = bookingDAO.getStudentBookings(studentId);
        Student student = studentDAO.getStudentByEmail(email); // To get live total CO2

        request.setAttribute("bookings", bookings);
        request.setAttribute("totalCo2Saved", (student != null) ? student.getTotalCo2SavedKg() : 0.0);

        request.getRequestDispatcher("/my-bookings.jsp").forward(request, response);
    }
}
