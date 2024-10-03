/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author ADK_Altmile
 */
import Model.ProjectReview;
import DBConnect.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectReviewDAO {

    private Connection connection;

    public ProjectReviewDAO() throws SQLException {
        connection = DBConnection.getConnection();
    }

    public List<ProjectReview> getReviewsByProjectId(int projectId) {
        List<ProjectReview> reviews = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM ProjectReviews WHERE project_id = ?");
            ps.setInt(1, projectId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProjectReview review = new ProjectReview();
                review.setReviewId(rs.getInt("review_id"));
                review.setUserId(rs.getInt("user_id"));
                review.setProjectId(rs.getInt("project_id"));
                review.setRating(rs.getInt("rating"));
                review.setComment(rs.getString("comment"));
                review.setCreatedAt(rs.getTimestamp("created_at"));
                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }
}
