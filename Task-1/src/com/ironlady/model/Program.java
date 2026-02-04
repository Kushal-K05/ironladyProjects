package com.ironlady.model;

public class Program {
    private int id;
    private String name;
    private String description;
    private String eligibility;
    private String duration;
    private double fees;
    private String certification;

    public Program() {}

    public Program(int id, String name, String description, String eligibility, String duration, double fees, String certification) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.eligibility = eligibility;
        this.duration = duration;
        this.fees = fees;
        this.certification = certification;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getEligibility() { return eligibility; }
    public void setEligibility(String eligibility) { this.eligibility = eligibility; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public double getFees() { return fees; }
    public void setFees(double fees) { this.fees = fees; }

    public String getCertification() { return certification; }
    public void setCertification(String certification) { this.certification = certification; }
}
