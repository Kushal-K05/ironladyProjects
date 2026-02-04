package com.ironlady.controller;

import com.ironlady.service.ChatbotService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/chat")
public class ChatServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ChatbotService chatbotService;

    public void init() {
        chatbotService = new ChatbotService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set CORS headers if needed for local testing, generally strictly needed if ports differ
        // response.setHeader("Access-Control-Allow-Origin", "*");
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String userMessage = request.getParameter("message");
        if (userMessage == null || userMessage.trim().isEmpty()) {
            response.getWriter().write("{\"reply\": \"Please type a message.\"}");
            return;
        }

        String botReply = chatbotService.processQuery(userMessage);

        // Simple JSON construction manually to avoid extra dependencies like Gson/Jackson for this simple task
        // Escaping quotes is minimal here but sufficient for the demo text
        String jsonResponse = String.format("{\"reply\": \"%s\"}", botReply.replace("\"", "\\\"").replace("\n", " "));
        
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }
}
