package com.ironlady.dao;

import com.ironlady.model.Program;
import com.ironlady.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramDAO {

    public void addProgram(Program program) {
        String sql = "INSERT INTO programs (name, description, duration, fee) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, program.getName());
            stmt.setString(2, program.getDescription());
            stmt.setString(3, program.getDuration());
            stmt.setDouble(4, program.getFee());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Program> getAllPrograms() {
        List<Program> programs = new ArrayList<>();
        String sql = "SELECT * FROM programs";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                programs.add(new Program(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("duration"),
                    rs.getDouble("fee")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return programs;
    }

    public Program getProgramById(int id) {
        String sql = "SELECT * FROM programs WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Program(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("duration"),
                        rs.getDouble("fee")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateProgram(Program program) {
        String sql = "UPDATE programs SET name = ?, description = ?, duration = ?, fee = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, program.getName());
            stmt.setString(2, program.getDescription());
            stmt.setString(3, program.getDuration());
            stmt.setDouble(4, program.getFee());
            stmt.setInt(5, program.getId());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProgram(int id) {
        String sql = "DELETE FROM programs WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
