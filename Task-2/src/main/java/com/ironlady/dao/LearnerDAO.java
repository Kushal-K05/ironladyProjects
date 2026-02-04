package com.ironlady.dao;

import com.ironlady.model.Learner;
import com.ironlady.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LearnerDAO {

    public void addLearner(Learner learner) {
        String sql = "INSERT INTO learners (name, email, phone, enrollment_date, program_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, learner.getName());
            stmt.setString(2, learner.getEmail());
            stmt.setString(3, learner.getPhone());
            stmt.setDate(4, learner.getEnrollmentDate());
            stmt.setInt(5, learner.getProgramId());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Learner> getAllLearners() {
        List<Learner> learners = new ArrayList<>();
        String sql = "SELECT l.*, p.name as program_name FROM learners l LEFT JOIN programs p ON l.program_id = p.id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Learner learner = new Learner(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getDate("enrollment_date"),
                    rs.getInt("program_id")
                );
                learner.setProgramName(rs.getString("program_name"));
                learners.add(learner);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return learners;
    }

    public Learner getLearnerById(int id) {
        String sql = "SELECT * FROM learners WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Learner(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getDate("enrollment_date"),
                        rs.getInt("program_id")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateLearner(Learner learner) {
        String sql = "UPDATE learners SET name = ?, email = ?, phone = ?, enrollment_date = ?, program_id = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, learner.getName());
            stmt.setString(2, learner.getEmail());
            stmt.setString(3, learner.getPhone());
            stmt.setDate(4, learner.getEnrollmentDate());
            stmt.setInt(5, learner.getProgramId());
            stmt.setInt(6, learner.getId());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteLearner(int id) {
        String sql = "DELETE FROM learners WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
