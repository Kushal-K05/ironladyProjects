package com.ironlady.model;

public class Program {
    private int id;
    private String name;
    private String description;
    private String duration;
    private double fee;

    public Program() {}

    public Program(int id, String name, String description, String duration, double fee) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.fee = fee;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public double getFee() { return fee; }
    public void setFee(double fee) { this.fee = fee; }
}
