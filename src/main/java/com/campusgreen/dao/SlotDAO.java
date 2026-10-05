package com.campusgreen.dao;

import com.campusgreen.model.VehicleSlot;
import com.campusgreen.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SlotDAO {

    public List<VehicleSlot> getAvailableSlots() {
        List<VehicleSlot> slots = new ArrayList<>();
        String query = "SELECT * FROM Vehicles_Slots WHERE slot_time > NOW() ORDER BY slot_time ASC";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                VehicleSlot slot = new VehicleSlot();
                slot.setSlotId(rs.getInt("slot_id"));
                slot.setVehicleType(rs.getString("vehicle_type"));
                slot.setRoute(rs.getString("route"));
                slot.setSlotTime(rs.getTimestamp("slot_time"));
                slot.setCapacity(rs.getInt("capacity"));
                slot.setBookedCount(rs.getInt("booked_count"));
                slot.setDistanceKm(rs.getDouble("distance_km"));
                slots.add(slot);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return slots;
    }

    public VehicleSlot getSlotById(int slotId) {
        String query = "SELECT * FROM Vehicles_Slots WHERE slot_id = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, slotId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    VehicleSlot slot = new VehicleSlot();
                    slot.setSlotId(rs.getInt("slot_id"));
                    slot.setVehicleType(rs.getString("vehicle_type"));
                    slot.setRoute(rs.getString("route"));
                    slot.setSlotTime(rs.getTimestamp("slot_time"));
                    slot.setCapacity(rs.getInt("capacity"));
                    slot.setBookedCount(rs.getInt("booked_count"));
                    slot.setDistanceKm(rs.getDouble("distance_km"));
                    return slot;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
