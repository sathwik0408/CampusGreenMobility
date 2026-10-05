package com.campusgreen.dao;

import com.campusgreen.model.Student;
import com.campusgreen.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public Student getStudentByEmail(String email) {
        String query = "SELECT * FROM Students WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getInt("student_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getDouble("total_co2_saved_kg"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Student> getLeaderboard() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT student_id, name, email, total_co2_saved_kg FROM Students ORDER BY total_co2_saved_kg DESC LIMIT 10";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("student_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getDouble("total_co2_saved_kg")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}
