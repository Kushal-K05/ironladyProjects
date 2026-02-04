package com.ironlady.service;

import com.ironlady.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChatbotService {

    public String processQuery(String userQuery) {
        String query = userQuery.toLowerCase().trim();
        String response = "I'm sorry, I didn't quite understand that. Could you please rephrase?";

        try (Connection conn = DBConnection.getConnection()) {
            
            // 1. Check for specific program inquiries first (Dynamic Data)
            if (query.contains("leadership") || query.contains("bootcamp") || query.contains("corporate")) {
                response = getProgramDetails(conn, query);
            } 
            // 2. Fallback to Rule-based Keyword Matching
            if (response.startsWith("I'm sorry") || response.isEmpty()) {
                response = getRuleBasedResponse(conn, query);
            }
            
            // 3. Log the interaction
            logInteraction(conn, userQuery, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while processing your request.";
        }
        
        return response;
    }

    private String getProgramDetails(Connection conn, String query) throws SQLException {
        String sql = "SELECT * FROM programs WHERE LOWER(name) LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
             // Simple basic matching for demo purposes
            if (query.contains("leadership")) stmt.setString(1, "%leadership%");
            else if (query.contains("bootcamp")) stmt.setString(1, "%bootcamp%");
            else if (query.contains("corporate")) stmt.setString(1, "%corporate%");
            else return "";

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return String.format("<strong>%s</strong><br>%s<br><strong>Eligibility:</strong> %s<br><strong>Duration:</strong> %s",
                            rs.getString("name"), rs.getString("description"), rs.getString("eligibility"), rs.getString("duration"));
                }
            }
        }
        return "";
    }

    private String getRuleBasedResponse(Connection conn, String query) throws SQLException {
        String sql = "SELECT response_text FROM chatbot_rules WHERE ? LIKE CONCAT('%', keyword, '%')";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, query); // Check if input contains any keyword
            // Note: This simple SQL matching might pick the first one it finds. 
            // For more complex AI, we'd process in Java, but this meets the "DB-driven" requirement.
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("response_text");
                }
            }
        }
        return "I'm sorry, I didn't quite understand that. You can ask about our 'programs', 'fees', or 'eligibility'.";
    }

    private void logInteraction(Connection conn, String userQuery, String botResponse) {
        String sql = "INSERT INTO interactions (user_query, bot_response) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userQuery);
            stmt.setString(2, botResponse);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to log interaction: " + e.getMessage());
        }
    }
}
