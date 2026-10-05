package com.campusgreen.dao;

import com.campusgreen.model.Booking;
import com.campusgreen.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    public String createBooking(int studentId, int slotId, double distanceKm, double co2SavedKg) {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // Start transaction

            // 1. Check for duplicate booking
            String checkDuplicateQuery = "SELECT COUNT(*) FROM Bookings WHERE student_id = ? AND slot_id = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkDuplicateQuery)) {
                checkStmt.setInt(1, studentId);
                checkStmt.setInt(2, slotId);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        conn.rollback();
                        return "You have already booked this slot.";
                    }
                }
            }

            // 2. Atomically increment booked_count only if capacity allows
            String updateCapacityQuery = "UPDATE Vehicles_Slots SET booked_count = booked_count + 1 WHERE slot_id = ? AND booked_count < capacity";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateCapacityQuery)) {
                updateStmt.setInt(1, slotId);
                int rowsAffected = updateStmt.executeUpdate();
                if (rowsAffected == 0) {
                    conn.rollback();
                    return "The vehicle slot is full.";
                }
            }

            // 3. Insert the booking
            String insertBookingQuery = "INSERT INTO Bookings (student_id, slot_id, distance_km, co2_saved_kg) VALUES (?, ?, ?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertBookingQuery)) {
                insertStmt.setInt(1, studentId);
                insertStmt.setInt(2, slotId);
                insertStmt.setDouble(3, distanceKm);
                insertStmt.setDouble(4, co2SavedKg);
                insertStmt.executeUpdate();
            }

            // 4. Update the student's total CO2
            String updateStudentQuery = "UPDATE Students SET total_co2_saved_kg = total_co2_saved_kg + ? WHERE student_id = ?";
            try (PreparedStatement studentStmt = conn.prepareStatement(updateStudentQuery)) {
                studentStmt.setDouble(1, co2SavedKg);
                studentStmt.setInt(2, studentId);
                studentStmt.executeUpdate();
            }

            // If everything is successful, commit
            conn.commit();
            return "SUCCESS";

        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return "A database error occurred during booking.";
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Booking> getStudentBookings(int studentId) {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT b.booking_id, v.vehicle_type, v.route, v.slot_time, b.distance_km, b.co2_saved_kg, b.booked_at "
                +
                "FROM Bookings b " +
                "JOIN Vehicles_Slots v ON b.slot_id = v.slot_id " +
                "WHERE b.student_id = ? " +
                "ORDER BY b.booked_at DESC";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Booking b = new Booking();
                    b.setBookingId(rs.getInt("booking_id"));
                    b.setVehicleType(rs.getString("vehicle_type"));
                    b.setRoute(rs.getString("route"));
                    b.setSlotTime(rs.getTimestamp("slot_time"));
                    b.setDistanceKm(rs.getDouble("distance_km"));
                    b.setCo2SavedKg(rs.getDouble("co2_saved_kg"));
                    b.setBookedAt(rs.getTimestamp("booked_at"));
                    bookings.add(b);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
}
