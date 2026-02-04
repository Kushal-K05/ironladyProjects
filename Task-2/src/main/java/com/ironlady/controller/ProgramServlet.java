package com.ironlady.controller;

import com.ironlady.dao.ProgramDAO;
import com.ironlady.model.Program;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/programs")
public class ProgramServlet extends HttpServlet {
    private ProgramDAO programDAO;

    public void init() {
        programDAO = new ProgramDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "create":
                createProgram(request, response);
                break;
            case "update":
                updateProgram(request, response);
                break;
            default:
                listPrograms(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "delete":
                deleteProgram(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            default:
                listPrograms(request, response);
        }
    }

    private void listPrograms(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Program> listPrograms = programDAO.getAllPrograms();
        request.setAttribute("listPrograms", listPrograms);
        RequestDispatcher dispatcher = request.getRequestDispatcher("programs.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("program-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Program existingProgram = programDAO.getProgramById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("program-form.jsp");
        request.setAttribute("program", existingProgram);
        dispatcher.forward(request, response);
    }

    private void createProgram(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String duration = request.getParameter("duration");
        double fee = Double.parseDouble(request.getParameter("fee"));

        Program newProgram = new Program(0, name, description, duration, fee);
        programDAO.addProgram(newProgram);
        response.sendRedirect("programs");
    }

    private void updateProgram(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String duration = request.getParameter("duration");
        double fee = Double.parseDouble(request.getParameter("fee"));

        Program program = new Program(id, name, description, duration, fee);
        programDAO.updateProgram(program);
        response.sendRedirect("programs");
    }

    private void deleteProgram(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        programDAO.deleteProgram(id);
        response.sendRedirect("programs");
    }
}
