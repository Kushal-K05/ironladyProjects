package com.ironlady.model;

import java.sql.Date;

public class Learner {
    private int id;
    private String name;
    private String email;
    private String phone;
    private Date enrollmentDate;
    private int programId;
    private String programName; // For display purposes

    public Learner() {}

    public Learner(int id, String name, String email, String phone, Date enrollmentDate, int programId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.enrollmentDate = enrollmentDate;
        this.programId = programId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Date getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(Date enrollmentDate) { this.enrollmentDate = enrollmentDate; }

    public int getProgramId() { return programId; }
    public void setProgramId(int programId) { this.programId = programId; }

    public String getProgramName() { return programName; }
    public void setProgramName(String programName) { this.programName = programName; }
}
