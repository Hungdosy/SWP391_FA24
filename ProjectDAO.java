/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author ADK_Altmile
 */
// ProjectDAO.java
import DBConnect.DBConnection;
import Model.Project;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {
    private String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=Volunteerisms3;user=sa;password=sa;";
    private String jdbcUsername = "sa";
    private String jdbcPassword = "sa";

    private static final String INSERT_PROJECT_SQL = "INSERT INTO Projects (title, description, start_date, end_date, status) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_PROJECT_BY_ID = "SELECT * FROM Projects WHERE project_id = ?;";
    private static final String SELECT_ALL_PROJECTS = "SELECT * FROM Projects;";
    private static final String DELETE_PROJECT_SQL = "DELETE FROM Projects WHERE project_id = ?;";
    private static final String UPDATE_PROJECT_SQL = "UPDATE Projects SET title = ?, description = ?, start_date = ?, end_date = ?, status = ? WHERE project_id = ?;";

    // Kết nối đến database
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Thêm mới Project
    public void insertProject(Project project) throws SQLException {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROJECT_SQL)) {
            preparedStatement.setString(1, project.getTitle());
            preparedStatement.setString(2, project.getDescription());
            preparedStatement.setDate(3, new java.sql.Date(project.getStartDate().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(project.getEndDate().getTime()));
            preparedStatement.setString(5, project.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    // Chọn Project theo ID
    public Project selectProject(int id) {
        Project project = null;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROJECT_BY_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String title = rs.getString("title");
                String description = rs.getString("description");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");
                String status = rs.getString("status");
                project = new Project(id, title, description, startDate, endDate, status);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return project;
    }

    // Chọn tất cả Project
    public List<Project> selectAllProjects() {
        List<Project> projects = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PROJECTS);) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("project_id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");
                String status = rs.getString("status");
                projects.add(new Project(id, title, description, startDate, endDate, status));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return projects;
    }

    // Cập nhật Project
    public boolean updateProject(Project project) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PROJECT_SQL);) {
            statement.setString(1, project.getTitle());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new java.sql.Date(project.getStartDate().getTime()));
            statement.setDate(4, new java.sql.Date(project.getEndDate().getTime()));
            statement.setString(5, project.getStatus());
            statement.setInt(6, project.getProjectId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    // Xóa Project
    public boolean deleteProject(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_PROJECT_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    
    public Project getProjectById(int projectId) {
        Project project = null;
        Connection connection = null;
        try {
            // Get the connection from DBConnection
            connection = DBConnection.getConnection();
            
            // Prepare SQL query to fetch the project details
            String sql = "SELECT * FROM Projects WHERE project_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, projectId);

            // Execute the query and get the result
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                project = new Project();
                project.setProjectId(resultSet.getInt("project_id"));
                project.setTitle(resultSet.getString("title"));
                project.setStartDate(resultSet.getDate("start_date"));
                project.setEndDate(resultSet.getDate("end_date"));
                project.setDescription(resultSet.getString("description"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }
}

