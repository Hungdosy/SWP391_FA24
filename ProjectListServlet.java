package Servlet;

import DAO.ProjectDAO;
import Model.Project;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProjectListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Tạo một instance của ProjectDAO
        ProjectDAO projectDAO = new ProjectDAO();

        // Gọi phương thức để lấy danh sách tất cả các dự án
        List<Project> projectList = projectDAO.selectAllProjects();

        // Đặt danh sách dự án vào request attribute để gửi tới JSP
        request.setAttribute("projectList", projectList);

        // Chuyển tiếp request và response tới trang project-list.jsp
        request.getRequestDispatcher("project-list.jsp").forward(request, response);
    }
}
