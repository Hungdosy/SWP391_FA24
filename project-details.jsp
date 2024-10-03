<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Model.Project" %>
<%@ page import="Model.Supply" %>
<%@ page import="Model.ProjectDocument" %>
<%@ page import="Model.ProjectReview" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Project Details</title>
    </head>
    <body>
        <h1>Project Details</h1>

        <% 
        Project project = (Project) request.getAttribute("project");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        %>

        <h2>Project Information</h2>
        <p><strong>Name:</strong> <%= project.getTitle() %></p>
        <p><strong>Start Date:</strong> <%= project.getStartDate() %></p>
        <p><strong>End Date:</strong> <%= project.getEndDate() %></p>
        <p><strong>Description:</strong> <%= project.getDescription() %></p>

        <!-- Supplies Section (unchanged) -->
        <h2>Supplies</h2>
        <%
            List<Supply> supplies = (List<Supply>) request.getAttribute("supplies");
            if (supplies != null && !supplies.isEmpty()) {
        %>
        <table border="1">
            <tr>
                <th>Item Name</th>
                <th>Quantity Needed</th>
                <th>Quantity Provided</th>
                <th>Unit</th>
            </tr>
            <% for (Supply supply : supplies) { %>
            <tr>
                <td><%= supply.getItemName() %></td>
                <td><%= supply.getQuantityNeeded() %></td>
                <td><%= supply.getQuantityProvided() != null ? supply.getQuantityProvided() : "N/A" %></td>
                <td><%= supply.getUnit() != null ? supply.getUnit() : "N/A" %></td>
            </tr>
            <% } %>
        </table>
        <% } else { %>
        <p>No supplies available for this project.</p>
        <% } %>
        <!-- ... -->

        <!-- Project Documents Section -->
        <h2>Project Documents</h2>
        <%
            List<ProjectDocument> documents = (List<ProjectDocument>) request.getAttribute("documents");
            if (documents != null && !documents.isEmpty()) {
        %>
        <table border="1">
            <tr>
                <th>Document Name</th>
                <th>Document Type</th>
                <th>Created At</th>
                <th>Download</th>
            </tr>
            <% for (ProjectDocument document : documents) { %>
            <tr>
                <td><%= document.getDocumentName() %></td>
                <td><%= document.getDocumentType() %></td>
                <td><%= dateFormat.format(document.getCreatedAt()) %></td>
                <td><a href="<%= document.getDocumentUrl() %>" download>Download</a></td>
            </tr>
            <% } %>
        </table>
        <% } else { %>
        <p>No documents available for this project.</p>
        <% } %>

        <!-- Project Reviews Section -->
        <h2>Project Reviews</h2>
        <%
            List<ProjectReview> reviews = (List<ProjectReview>) request.getAttribute("reviews");
            if (reviews != null && !reviews.isEmpty()) {
        %>
        <table border="1">
            <tr>
                <th>User ID</th>
                <th>Rating</th>
                <th>Comment</th>
                <th>Created At</th>
            </tr>
            <% for (ProjectReview review : reviews) { %>
            <tr>
                <td><%= review.getUserId() %></td>
                <td><%= review.getRating() %></td>
                <td><%= review.getComment() %></td>
                <td><%= dateFormat.format(review.getCreatedAt()) %></td>
            </tr>
            <% } %>
        </table>
        <% } else { %>
        <p>No reviews available for this project.</p>
        <% } %>

    </body>
</html>