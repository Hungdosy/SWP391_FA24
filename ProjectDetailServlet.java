package Servlet;

import DAO.ProjectDAO;
import DAO.SupplyDAO;
import DAO.ProjectDocumentDAO;
import DAO.ProjectReviewDAO;
import Model.Project;
import Model.Supply;
import Model.ProjectDocument;
import Model.ProjectReview;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjectDetailServlet extends HttpServlet {

    private ProjectDAO projectDAO;
    private SupplyDAO supplyDAO;
    private ProjectDocumentDAO documentDAO;
    private ProjectReviewDAO reviewDAO;

    private static final Logger LOGGER = Logger.getLogger(ProjectDetailServlet.class.getName());

    @Override
    public void init() throws ServletException {
        try {
            projectDAO = new ProjectDAO();
            supplyDAO = new SupplyDAO();
            documentDAO = new ProjectDocumentDAO();
            reviewDAO = new ProjectReviewDAO();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Failed to initialize DAOs", ex);
            throw new ServletException("Failed to initialize DAOs", ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String projectIdParam = request.getParameter("id");
        
        if (projectIdParam == null || projectIdParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Project ID is required");
            return;
        }

        int projectId;
        try {
            projectId = Integer.parseInt(projectIdParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid project ID");
            return;
        }

        Project project = projectDAO.getProjectById(projectId);
        if (project == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Project not found");
            return;
        }

        List<Supply> supplies = supplyDAO != null ? supplyDAO.getSuppliesByProjectId(projectId) : new ArrayList<>();
        List<ProjectDocument> documents = documentDAO != null ? documentDAO.getDocumentsByProjectId(projectId) : new ArrayList<>();
        List<ProjectReview> reviews = reviewDAO != null ? reviewDAO.getReviewsByProjectId(projectId) : new ArrayList<>();

        request.setAttribute("project", project);
        request.setAttribute("supplies", supplies);
        request.setAttribute("documents", documents);
        request.setAttribute("reviews", reviews);

        request.getRequestDispatcher("/project-details.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}