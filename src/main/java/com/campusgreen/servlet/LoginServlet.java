package com.campusgreen.servlet;

import com.campusgreen.dao.StudentDAO;
import com.campusgreen.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        if (email != null && !email.trim().isEmpty()) {
            Student student = studentDAO.getStudentByEmail(email.trim());
            if (student != null) {
                HttpSession session = request.getSession();
                session.setAttribute("studentId", student.getStudentId());
                session.setAttribute("studentName", student.getName());
                session.setAttribute("studentEmail", student.getEmail());
                response.sendRedirect(request.getContextPath() + "/availability");
                return;
            } else {
                request.setAttribute("error", "Email not found in database.");
            }
        } else {
            request.setAttribute("error", "Please enter your email.");
        }
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
