CREATE DATABASE IF NOT EXISTS green_mobility;
USE green_mobility;

-- Students Table
CREATE TABLE IF NOT EXISTS Students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    total_co2_saved_kg DECIMAL(10,2) DEFAULT 0
);

-- Vehicles_Slots Table
CREATE TABLE IF NOT EXISTS Vehicles_Slots (
    slot_id INT PRIMARY KEY AUTO_INCREMENT,
    vehicle_type VARCHAR(50) NOT NULL,
    route VARCHAR(150) NOT NULL,
    slot_time DATETIME NOT NULL,
    capacity INT NOT NULL,
    booked_count INT DEFAULT 0,
    distance_km DECIMAL(6,2) NOT NULL DEFAULT 10.00
);

-- Bookings Table
CREATE TABLE IF NOT EXISTS Bookings (
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    slot_id INT NOT NULL,
    distance_km DECIMAL(6,2) NOT NULL,
    co2_saved_kg DECIMAL(8,2) NOT NULL,
    booked_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES Students(student_id),
    FOREIGN KEY (slot_id) REFERENCES Vehicles_Slots(slot_id),
    UNIQUE (student_id, slot_id)
);

-- Clear existing data if re-running
DELETE FROM Bookings;
DELETE FROM Vehicles_Slots;
DELETE FROM Students;
ALTER TABLE Students AUTO_INCREMENT = 1;
ALTER TABLE Vehicles_Slots AUTO_INCREMENT = 1;
ALTER TABLE Bookings AUTO_INCREMENT = 1;

-- Sample Students
INSERT INTO Students (name, email) VALUES
('John Doe', 'john@student.campus.edu'),
('Jane Smith', 'jane@student.campus.edu'),
('Alice Wonderland', 'alice@student.campus.edu');

-- Sample Vehicle Slots (Use times for today/tomorrow)
INSERT INTO Vehicles_Slots (vehicle_type, route, slot_time, capacity, booked_count, distance_km) VALUES
('EV Shuttle', 'Campus -> Hostel', DATE_ADD(NOW(), INTERVAL 2 HOUR), 20, 19, 8.5),
('EV Shuttle', 'Campus -> Main Gate', DATE_ADD(NOW(), INTERVAL 3 HOUR), 15, 5, 5.0),
('EV Shuttle', 'Hostel -> Academic Block', DATE_ADD(NOW(), INTERVAL 4 HOUR), 20, 20, 3.2),
('Shared Bike', 'Hostel -> Library', DATE_ADD(NOW(), INTERVAL 1 HOUR), 1, 0, 1.5),
('Shared Bike', 'Library -> Sports Complex', DATE_ADD(NOW(), INTERVAL 5 HOUR), 5, 2, 2.8);
