package DAO;

import Model.ProjectDocument;
import DBConnect.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDocumentDAO {

    private Connection connection;

    public ProjectDocumentDAO() throws SQLException {
        connection = DBConnection.getConnection();
    }

    public List<ProjectDocument> getDocumentsByProjectId(int projectId) {
        List<ProjectDocument> documents = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM Project_Documents WHERE project_id = ?");
            ps.setInt(1, projectId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProjectDocument document = new ProjectDocument();
                document.setDocumentId(rs.getInt("document_id"));
                document.setProjectId(rs.getInt("project_id"));
                document.setDocumentName(rs.getString("document_name"));
                document.setDocumentType(rs.getString("document_type"));
                document.setDocumentUrl(rs.getString("document_url"));
                document.setCreatedAt(rs.getTimestamp("created_at"));
                documents.add(document);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return documents;
    }
}
