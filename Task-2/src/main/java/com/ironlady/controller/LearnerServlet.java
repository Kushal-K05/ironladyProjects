package com.ironlady.controller;

import com.ironlady.dao.LearnerDAO;
import com.ironlady.dao.ProgramDAO; // Needed for dropdown
import com.ironlady.model.Learner;
import com.ironlady.model.Program;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/learners")
public class LearnerServlet extends HttpServlet {
    private LearnerDAO learnerDAO;
    private ProgramDAO programDAO;

    public void init() {
        learnerDAO = new LearnerDAO();
        programDAO = new ProgramDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "create":
                createLearner(request, response);
                break;
            case "update":
                updateLearner(request, response);
                break;
            default:
                listLearners(request, response);
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
                deleteLearner(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            default:
                listLearners(request, response);
        }
    }

    private void listLearners(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Learner> listLearners = learnerDAO.getAllLearners();
        request.setAttribute("listLearners", listLearners);
        RequestDispatcher dispatcher = request.getRequestDispatcher("learners.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Program> listPrograms = programDAO.getAllPrograms();
        request.setAttribute("listPrograms", listPrograms);
        RequestDispatcher dispatcher = request.getRequestDispatcher("learner-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Learner existingLearner = learnerDAO.getLearnerById(id);
        List<Program> listPrograms = programDAO.getAllPrograms();
        RequestDispatcher dispatcher = request.getRequestDispatcher("learner-form.jsp");
        request.setAttribute("learner", existingLearner);
        request.setAttribute("listPrograms", listPrograms);
        dispatcher.forward(request, response);
    }

    private void createLearner(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Date enrollmentDate = Date.valueOf(request.getParameter("enrollmentDate"));
        int programId = Integer.parseInt(request.getParameter("programId"));

        Learner newLearner = new Learner(0, name, email, phone, enrollmentDate, programId);
        learnerDAO.addLearner(newLearner);
        response.sendRedirect("learners");
    }

    private void updateLearner(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Date enrollmentDate = Date.valueOf(request.getParameter("enrollmentDate"));
        int programId = Integer.parseInt(request.getParameter("programId"));

        Learner learner = new Learner(id, name, email, phone, enrollmentDate, programId);
        learnerDAO.updateLearner(learner);
        response.sendRedirect("learners");
    }

    private void deleteLearner(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        learnerDAO.deleteLearner(id);
        response.sendRedirect("learners");
    }
}
