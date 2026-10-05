package com.campusgreen.model;

public class Student {
    private int studentId;
    private String name;
    private String email;
    private double totalCo2SavedKg;

    public Student() {
    }

    public Student(int studentId, String name, String email, double totalCo2SavedKg) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.totalCo2SavedKg = totalCo2SavedKg;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getTotalCo2SavedKg() {
        return totalCo2SavedKg;
    }

    public void setTotalCo2SavedKg(double totalCo2SavedKg) {
        this.totalCo2SavedKg = totalCo2SavedKg;
    }
}
